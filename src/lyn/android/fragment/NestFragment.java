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
import android.view.View.OnClickListener;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2014-12-29
 *
 */

public class NestFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fg_nest, container,false);
		contentView.findViewById(R.id.btn_fragmet_to_result).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(getActivity(),ResultActivity.class), 0);
			}
		});
		
		contentView.findViewById(R.id.btn_activity_to_result).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().startActivityForResult(new Intent(getActivity(),ResultActivity.class), 0);
			}
		});
		return contentView;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("haha","nest fragment on result");
	}
	
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		Log.d("haha","fragment setUserVisibleHint");
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("haha","fragment onAttach");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("haha","fragment onCreate");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.d("haha","fragment onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("haha","fragment onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d("haha","fragment onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.d("haha","fragment onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d("haha","fragment onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("haha","fragment onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.d("haha","fragment onDetach");
	}

	
	
	
	
	
}
