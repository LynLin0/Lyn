package lyn.android.fragment;

import lyn.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2014-12-29
 *
 */

public class ResultActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_result);
		findViewById(R.id.tv_result).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	public void finish() {
		setResult(111);
		super.finish();
	}
	
	
}
