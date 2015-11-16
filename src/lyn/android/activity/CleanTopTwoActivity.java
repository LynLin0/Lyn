package lyn.android.activity;

import lyn.android.R;
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

public class CleanTopTwoActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_clean_top_two);
		findViewById(R.id.clean_top_two_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CleanTopTwoActivity.this,CleanTopActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				Intent intent1 = new Intent(CleanTopTwoActivity.this,CleanTopTwoActivity.class);
				startActivity(intent1);
			}
		});
	}
}
