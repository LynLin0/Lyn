package lyn.android.media;

import java.io.File;
import java.io.IOException;

import lyn.android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-9
 * 
 */

public class RecordActivity extends Activity implements OnClickListener {

	private Button recordBtn;
	private SurfaceView surfaceView;

	private MediaRecorder recorder;
	private boolean isRecording;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vidio_act_record);
		findView();
		setupView();
	}

	private void setupView() {
		recordBtn.setOnClickListener(this);
		surfaceView.getHolder().setFixedSize(1280,720);  
	}

	private void findView() {
		recordBtn = (Button) findViewById(R.id.vidio_record_btn);
		surfaceView = (SurfaceView) findViewById(R.id.vidio_record_preview_surfaceview);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.vidio_record_btn:
			onRecord();
			break;
		default:
			break;
		}
	}

	private void onRecord() {
		if(isRecording){
			stopRecord();
		}else{
			record();
		}
	}

	private void stopRecord() {
		if (isRecording)  
        {  
            // 停止录制  
            recorder.stop();  
            // 释放资源  
            recorder.release();  
            recorder = null;  
            isRecording = false;
        }  		
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void record() {
		// 创建保存录制视频的视频文件
		File videoFile;
		try {
			Camera camera = Camera.open();
	        camera.setDisplayOrientation(90);
	        camera.unlock();
			
			
			videoFile = new File(Environment.getExternalStorageDirectory()
					.getCanonicalFile() + "/testvideo.3pg");
			recorder = new MediaRecorder();
			recorder.reset();
			recorder.setCamera(camera);
			// 设置从麦克风采集声音(或来自录像机的声音AudioSource.CAMCORDER)
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 设置从摄像头采集图像
			recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			// 设置视频文件的输出格式
			// 必须在设置声音编码格式、图像编码格式之前设置
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			// 设置声音编码的格式
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			// 设置图像编码的格式
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
			recorder.setVideoSize(1280,720);
			// 每秒 4帧
			recorder.setVideoFrameRate(20);
			recorder.setOutputFile(videoFile.getAbsolutePath());
			// 指定使用SurfaceView来预览视频
			recorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
			recorder.setOrientationHint(90);
			recorder.prepare();
			// 开始录制
			recorder.start();
			isRecording = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
