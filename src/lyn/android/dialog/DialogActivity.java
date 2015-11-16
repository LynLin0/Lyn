package lyn.android.dialog;

import lyn.android.util.SwitchLogger;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-7-13
 *
 */

public class DialogActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(DialogActivity.this).setMessage("haha").setPositiveButton("ok", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).setNegativeButton("cancel", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
			}
		}, 5000);
	}
	
	@Override
	protected void onPause() {
		SwitchLogger.d("dialog", "onPause was called");
		super.onPause();
	}

}
