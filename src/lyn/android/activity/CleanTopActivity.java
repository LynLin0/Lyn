package lyn.android.activity;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-10-23
 *
 */

public class CleanTopActivity extends Activity{
	private static final String TAG = "CleanTop";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SwitchLogger.d(TAG,"onCreate");
		setContentView(R.layout.act_clean_top);
		findViewById(R.id.clean_top_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CleanTopActivity.this,CleanTopOneActivity.class));
			}
		});
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		SwitchLogger.d(TAG,"onNewIntent");
	}

}
