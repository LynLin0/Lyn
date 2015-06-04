package lyn.android.fragment;

import lyn.android.R;
import lyn.android.util.LynLog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-4-3
 *
 */

public class StateLossFragment extends LogFragment{

	private View txt;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		LynLog.d("Fragment", "onCreateView start");
		View layout = inflater.inflate(R.layout.fg_state_loss, container,false);
		txt = layout.findViewById(R.id.state_loss_txt);
		LynLog.d("Fragment", "onCreateView end");
		return layout;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		txt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(getActivity(),ResultActivity.class),111);
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		LynLog.d("Fragment", "onActivityResult start");
		super.onActivityResult(requestCode, resultCode, data);
		LynLog.d("Fragment", "onActivityResult end");
	}
	
}
