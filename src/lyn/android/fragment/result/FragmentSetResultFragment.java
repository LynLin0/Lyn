package lyn.android.fragment.result;

import lyn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSetResultFragment extends Fragment implements OnClickListener{
	private Button resultBtn;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_set_result, container,false);
		resultBtn = (Button)contentView.findViewById(R.id.fragment_set_result_btn);
		return contentView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
	}

	private void setupView() {
		resultBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_set_result_btn:
			Intent intent = new Intent();
			intent.putExtra(FragmentForResultActivity.INTENT_RESULT, "hahahha");
			getActivity().setResult(Activity.RESULT_OK, intent);
			getActivity().finish();
			break;
		default:
			break;
		}
	}
}
