package lyn.android.media;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import lyn.android.R;
import lyn.android.util.SwitchLogger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-7-23
 * 
 */

// private static final String VIDEO_URL =
// "http://open.qiniudn.com/thinking-in-go.mp4";
public class MediaCacheActivty extends Activity implements OnPreparedListener {

	private static final String FILE_NAME = "haha.mp4";
//	private static final String VIDEO_URL = "http://open.qiniudn.com/thinking-in-go.mp4";
    private static final String VIDEO_URL = "http://guimi.qiniudn.com/s1/c2429ffc87144457ae97f0b0a41562b7.mp4";
	private TextureView textureView;
	private MediaPlayer mediaPlayer;
	private Button playBtn;
	private Toast toast;
	private RandomAccessFile dataFile;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1100){
				showTip(getExternalCacheDir()+"/"+FILE_NAME);
				play(getExternalCacheDir()+"/"+FILE_NAME);
			}
		};
	};
	private boolean isPlaying = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_cache);
		try {
			FileOutputStream fos = openFileOutput("what", MODE_PRIVATE);
			SwitchLogger.d("haha",new File("what").length()+"");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//		inidData();
//		findView();
//		setupView();
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupView() {
		playBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTip("开始下载");
				new Thread(new DownloadRunable()).start();
			}
		});
	}

	private void showTip(String text) {
		Log.d("LYN", text);
	}

	private class DownloadRunable implements Runnable {

		@Override
		public void run() {
			OutputStream out;
			try {
				HttpPost httpPost = new HttpPost(VIDEO_URL);
				HttpResponse response = new DefaultHttpClient()
						.execute(httpPost);
				dataFile = new RandomAccessFile(getExternalCacheDir()+"/"+FILE_NAME, "rw");
				int readBytes;
				showTip("code = "+response.getStatusLine().getStatusCode());
				if (response.getStatusLine().getStatusCode() == 200) {
					InputStream in = response.getEntity().getContent();
					byte[] buffer = new byte[1024];
					long current = 0;
					long total = response.getEntity().getContentLength();
					while ((readBytes = in.read(buffer)) != -1) {
						current+=readBytes;
						long percent = current*100/total;
						dataFile.write(buffer, 0, readBytes);
						if(percent>0&&!isPlaying){
							showTip("正在播放**************************************");
							handler.sendEmptyMessage(1100);
							isPlaying = true;
						}else{
							showTip("下载进度"+percent);
						}
					}
					showTip("下载成功");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@SuppressLint("NewApi")
	public void play(String path) {
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.reset();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(path);
			mediaPlayer
					.setSurface(new Surface(textureView.getSurfaceTexture()));
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.prepareAsync();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void inidData() {
		toast = Toast.makeText(this, "哈哈", 1000);
	}

	private void findView() {
		textureView = (TextureView) findViewById(R.id.media_cache_textureview);
		playBtn = (Button) findViewById(R.id.media_cache_btn);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();
	}

}
