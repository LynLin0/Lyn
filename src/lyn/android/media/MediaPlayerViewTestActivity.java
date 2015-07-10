package lyn.android.media;

import lyn.android.R;
import android.app.Activity;
import android.os.Bundle;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-12
 *
 */

public class MediaPlayerViewTestActivity extends Activity{
	private VideoView playerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_act_media_player_view_test);
		findView();
		setupView();
	}




	private void findView() {
		playerView = (VideoView)findViewById(R.id.media_player_view_test_playerview);
	}

	private void setupView() {
		playerView.setup("http://guimi.qiniudn.com/s1/c2429ffc87144457ae97f0b0a41562b7.mp4",null,640,480,false,false);
	}
}
