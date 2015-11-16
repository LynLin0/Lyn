package lyn.android.media;

import java.io.IOException;

import lyn.android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.rtp.AudioStream;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-8-19
 *
 */

public class TestPlayerPauseActivity extends Activity implements OnPreparedListener{
	
	private TextureView tv;
	private Button playBtn;
	private Button pauseBtn;
	private MediaPlayer  mediaPlayer;
	private boolean isPrepareing = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_test_player_pause);
		findView();
		setupView();
	}

	private void setupView() {
		playBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer==null){
					toPrepare();
				}else{
					if(!isPrepareing){
						mediaPlayer.start();
					}
				}
			}

		});
		
		pauseBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer!=null&&!isPrepareing&&mediaPlayer.isPlaying()){
					mediaPlayer.pause();
				}
			}
		});
	}
	
	@SuppressLint("NewApi")
	private void toPrepare() {
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.reset();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource("http://quniutest.qiniudn.com/s1/03ebabec24c14cce93d6386668a989f5.mp4");
			mediaPlayer.setSurface(new Surface(tv.getSurfaceTexture()));
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.prepareAsync();
			isPrepareing = true;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findView() {
		tv = (TextureView)findViewById(R.id.test_playder_pause_textrueview);
		playBtn = (Button)findViewById(R.id.test_player_pause_play_btn);
		pauseBtn = (Button)findViewById(R.id.test_player_pause_pause_btn);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		isPrepareing = false;
		mediaPlayer.start();
	}

}
