package lyn.android.fragment;

import lyn.android.R;
import lyn.android.util.LynLog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-4-1
 *
 */

public class FragmentPracticeActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LynLog.d("Fragment", "-----------------------Activity onCreate start------------------------------");
		super.onCreate(savedInstanceState);
		LynLog.d("Fragment", "***********************Activity onCreate end*******************************");
		setContentView(R.layout.act_fragment_practice);
		if(savedInstanceState==null){
			StateLossFragment fragment = new StateLossFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_pratice_layout,fragment).commit();
		}
	}	
	
	public void reAddFragment(){
			StateLossFragment fragment = new StateLossFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_pratice_layout,fragment).commit();
	}
	
	
	@Override
	protected void onPause() {
		LynLog.d("Fragment", "-----------------------Activity onPause start-----------------------------");
		super.onPause();
		LynLog.d("Fragment", "***********************Activity onPause end*******************************");
	}
	
	@Override
	protected void onStart() {
		LynLog.d("Fragment", "-----------------------Activity onStart start-----------------------------");
		super.onStart();
		LynLog.d("Fragment", "***********************Activity onStart end*******************************");
	}
	
	@Override
	protected void onResume() {
		LynLog.d("Fragment", "-----------------------Activity onResume start-----------------------------");
		super.onResume();
		LynLog.d("Fragment", "***********************Activity onResume end*******************************");
	}
	
	@Override
	protected void onStop() {
		LynLog.d("Fragment", "-----------------------Activity onStop start-----------------------------");
		super.onStop();
		LynLog.d("Fragment", "***********************Activity onStop end*******************************");
	}
	
	@Override
	protected void onRestart() {
		LynLog.d("Fragment", "-----------------------Activity onRestart start-----------------------------");
		super.onRestart();
		LynLog.d("Fragment", "***********************Activity onRestart end*******************************");
	}
	
	@Override
	protected void onDestroy() {
		LynLog.d("Fragment", "-----------------------Activity onDestory start----------------------------");
		super.onDestroy();
		LynLog.d("Fragment", "***********************Activity onDestory end*******************************");
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		LynLog.d("Fragment", "-----------------------Activity onSaveInstanceState start------------------");
		super.onSaveInstanceState(outState);
		LynLog.d("Fragment", "***********************Activity onSaveInstanceState end*******************");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		LynLog.d("Fragment", "-----------------------Activity onRestoreInstanceState start------------------");
		super.onRestoreInstanceState(savedInstanceState);
		LynLog.d("Fragment", "***********************Activity onRestoreInstanceState end*******************");
	}
}
