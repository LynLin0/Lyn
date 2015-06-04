package lyn.android.fragment;

import lyn.android.util.LynLog;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-4-3
 *
 */

public class LogFragment extends Fragment{

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		LynLog.d("Fragment", "onActivityCreated start");
		super.onActivityCreated(savedInstanceState);
		LynLog.d("Fragment", "onActivityCreated end");
	}

	@Override
	public void onAttach(Activity activity) {
		LynLog.d("Fragment", "onAttach start");
		super.onAttach(activity);
		LynLog.d("Fragment", "onAttach end");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		LynLog.d("Fragment", "onCreate start");
		super.onCreate(savedInstanceState);
		LynLog.d("Fragment", "onCreate end");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LynLog.d("Fragment", "onCreateView start");
		View view = super.onCreateView(inflater, container, savedInstanceState);
		LynLog.d("Fragment", "onCreateView end");
		return view;
	}

	@Override
	public void onDestroy() {
		LynLog.d("Fragment", "onDestroy start");
		super.onDestroy();
		LynLog.d("Fragment", "onDestroy end");
	}

	@Override
	public void onDestroyView() {
		LynLog.d("Fragment", "onDestroyView start");
		super.onDestroyView();
		LynLog.d("Fragment", "onDestroyView end");
	}

	@Override
	public void onDetach() {
		LynLog.d("Fragment", "onDetach start");
		super.onDetach();
		LynLog.d("Fragment", "onDetach end");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		LynLog.d("Fragment", "onHiddenChanged start");
		super.onHiddenChanged(hidden);
		LynLog.d("Fragment", "onHiddenChanged end");
	}

	@Override
	public void onPause() {
		LynLog.d("Fragment", "onPause start");
		super.onPause();
		LynLog.d("Fragment", "onPause end");
	}

	@Override
	public void onResume() {
		LynLog.d("Fragment", "onResume start");
		super.onResume();
		LynLog.d("Fragment", "onResume end");
	}

	@Override
	public void onStart() {
		LynLog.d("Fragment", "onStart start");
		super.onStart();
		LynLog.d("Fragment", "onStart end");
	}

	@Override
	public void onStop() {
		LynLog.d("Fragment", "onStop start");
		super.onStop();
		LynLog.d("Fragment", "onStop end");
	}
	

}
