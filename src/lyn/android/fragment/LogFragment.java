package lyn.android.fragment;

import lyn.android.util.SwitchLogger;
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
		SwitchLogger.d("Fragment", "onActivityCreated start");
		super.onActivityCreated(savedInstanceState);
		SwitchLogger.d("Fragment", "onActivityCreated end");
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		SwitchLogger.d("Fragment", "setUserVisibleHint start");
		super.setUserVisibleHint(isVisibleToUser);
		SwitchLogger.d("Fragment", "setUserVisibleHint end");
		
	}

	@Override
	public void onAttach(Activity activity) {
		SwitchLogger.d("Fragment", "onAttach start");
		super.onAttach(activity);
		SwitchLogger.d("Fragment", "onAttach end");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		SwitchLogger.d("Fragment", "onCreate start");
		super.onCreate(savedInstanceState);
		SwitchLogger.d("Fragment", "onCreate end");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		SwitchLogger.d("Fragment", "onCreateView start");
		View view = super.onCreateView(inflater, container, savedInstanceState);
		SwitchLogger.d("Fragment", "onCreateView end");
		return view;
	}

	@Override
	public void onDestroy() {
		SwitchLogger.d("Fragment", "onDestroy start");
		super.onDestroy();
		SwitchLogger.d("Fragment", "onDestroy end");
	}

	@Override
	public void onDestroyView() {
		SwitchLogger.d("Fragment", "onDestroyView start");
		super.onDestroyView();
		SwitchLogger.d("Fragment", "onDestroyView end");
	}

	@Override
	public void onDetach() {
		SwitchLogger.d("Fragment", "onDetach start");
		super.onDetach();
		SwitchLogger.d("Fragment", "onDetach end");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		SwitchLogger.d("Fragment", "onHiddenChanged start");
		super.onHiddenChanged(hidden);
		SwitchLogger.d("Fragment", "onHiddenChanged end");
	}

	@Override
	public void onPause() {
		SwitchLogger.d("Fragment", "onPause start");
		super.onPause();
		SwitchLogger.d("Fragment", "onPause end");
	}

	@Override
	public void onResume() {
		SwitchLogger.d("Fragment", "onResume start");
		super.onResume();
		SwitchLogger.d("Fragment", "onResume end");
	}

	@Override
	public void onStart() {
		SwitchLogger.d("Fragment", "onStart start");
		super.onStart();
		SwitchLogger.d("Fragment", "onStart end");
	}

	@Override
	public void onStop() {
		SwitchLogger.d("Fragment", "onStop start");
		super.onStop();
		SwitchLogger.d("Fragment", "onStop end");
	}
	

}
