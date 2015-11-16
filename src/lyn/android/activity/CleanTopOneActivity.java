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

public class CleanTopOneActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_clean_top_one);
		findViewById(R.id.clean_top_one_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CleanTopOneActivity.this,CleanTopTwoActivity.class));
			}
		});
	}
}
