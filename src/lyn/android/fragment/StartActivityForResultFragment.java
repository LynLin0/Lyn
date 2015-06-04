package lyn.android.fragment;

import lyn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2014-12-29
 * 
 */

public class StartActivityForResultFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d("haha","fragment onCreateView");
		
		View contentView = inflater.inflate(R.layout.fg_startactivityforresult, container,false);
		return contentView;
	}
	
	
	

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("haha","fragment onActivityCreated");
	}
	
//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		super.setUserVisibleHint(isVisibleToUser);
//		Log.d("haha","fragment setUserVisibleHint");
//	}
//	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		Log.d("haha","fragment onAttach");
//	}
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		Log.d("haha","fragment onCreate");
//	}
//	
//	@Override
//	public void onStart() {
//		super.onStart();
//		Log.d("haha","fragment onStart");
//	}
//	
//	@Override
//	public void onResume() {
//		super.onResume();
//		Log.d("haha","fragment onResume");
//	}
//	
//	@Override
//	public void onPause() {
//		super.onPause();
//		Log.d("haha","fragment onPause");
//	}
//	
//	@Override
//	public void onStop() {
//		super.onStop();
//		Log.d("haha","fragment onStop");
//	}
//	
//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		Log.d("haha","fragment onDestroyView");
//	}
//	
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		Log.d("haha","fragment onDestroy");
//	}
//	
//	@Override
//	public void onDetach() {
//		super.onDetach();
//		Log.d("haha","fragment onDetach");
//	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("haha","fragment on result");
	}
	

}
