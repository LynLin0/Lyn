package lyn.android.media;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;


import lyn.android.util.SwitchLogger;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-12 用于代理Mediaplayer的网络请求，在代理层可做数据的缓存
 * 
 */

public class HttpProxy {
	private static final String LOCAL_HOST = "127.0.0.1";
	private static final String REMOTE_HOST = "mvvideo2.meitudata.com";
	private static final int LOCAL_SERVER_PORT = 3434;
	private static final int HTTP_PORT = 80;

	private ServerSocket localServer;
	private String url;
	private String remoteHost;
	private SocketAddress remoteAddress;
	private Socket remoteSocket;
	private InputStream inRemoteSocket;
	private OutputStream outRemoteSocket;
	protected Socket localSocket;
	protected InputStream inLocalSocket;
	protected OutputStream outLocalSocket;

	public HttpProxy() {
		try {
			// 指定端口，接收的请求队列长度，主机地址
			localServer = new ServerSocket(LOCAL_SERVER_PORT, 1,
					InetAddress.getByName(LOCAL_HOST));
			new Thread() {
				public void run() {
					startProxy();
				}
			}.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void startProxy() {
		SocketAddress address = new InetSocketAddress(REMOTE_HOST, HTTP_PORT);
		try {
			remoteSocket = new Socket();
			remoteSocket.connect(address);
			inRemoteSocket = remoteSocket.getInputStream();
			outRemoteSocket = remoteSocket.getOutputStream();
			
			/**
			 * 接收本地request，并转发到远程服务器
			 */
			new Thread(){
				public void run() {
					byte[] buffer = new byte[1024];
					try {
						int length;
						SwitchLogger.e("MEDIA","proxy init success");
						localSocket = localServer.accept();
						inLocalSocket = localSocket.getInputStream();
						outLocalSocket = localSocket.getOutputStream();
						String reqStr = "";
						while ((length = inLocalSocket.read(buffer))!=-1) {
							reqStr = new String(buffer);
							SwitchLogger.e("MEDIA",reqStr);
							if(reqStr.contains("GET")&&reqStr.contains("\r\n\r\n")){
//								reqStr = reqStr.replace(LOCAL_HOST+":3434", REMOTE_HOST+":80");
								SwitchLogger.e("MEDIA","reqStr:\n"+reqStr);
								outRemoteSocket.write(buffer);
								outRemoteSocket.flush();
								continue;
							}else{
								outRemoteSocket.write(buffer);
								outRemoteSocket.flush();
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				};
			}.start();
			/**
			 * 接收远程服务器reply，并转发到本地客户端
			 */
			new Thread(){
				public void run() {
					int length;
					byte[] buffer = new byte[1024];
					try {
						while ((length = inRemoteSocket.read(buffer))!=-1) {
							outLocalSocket.write(buffer, 0, length);
							outLocalSocket.flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
