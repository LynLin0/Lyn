package lyn.android.fragment;

import lyn.android.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2014-12-29
 * 
 */

public class StartActivityForResultActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.act_startactivityforresult);
		Log.d("haha","activity onCreate");

//		getSupportFragmentManager()
//				.beginTransaction()
//				.add(R.id.fl_startactivityforresult,new StartActivityForResultFragment(),
//						"fg_startactivityforresult").commit();
		
		
		

//		Fragment fragment = getSupportFragmentManager().findFragmentByTag(
//				"fg_startactivityforresult");
		// Fragment fragment = getSupportFragmentManager().findFragmentById(
		// R.id.fg_startactivityforresult);

//		Log.d("haha",
//				new StringBuffer("findFragment = ").append(
//						fragment != null ? true : false).toString());
	}
	
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("haha","activity onRestart");
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("haha","activity onPause");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("haha","activity onDestroy");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("haha","activity onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("haha","activity onResume");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d("haha","activity onStop");
	}
	
	
//	dispatchA

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		Log.d("haha", "activity on result");
		super.onActivityResult(arg0, arg1, arg2);
	}

}
