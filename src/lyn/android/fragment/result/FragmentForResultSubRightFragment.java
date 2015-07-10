package lyn.android.fragment.result;

import lyn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentForResultSubRightFragment extends Fragment implements
		OnClickListener {

	private Button activityBtn;
	private Button fragmentBtn;
	private Button customBtn;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(
				R.layout.fragment_fg_for_result_sub, container, false);
		activityBtn = (Button) contentView
				.findViewById(R.id.fragment_for_result_sub_activity_btn);
		fragmentBtn = (Button) contentView
				.findViewById(R.id.fragment_for_result_sub_fragment_btn);
		customBtn = (Button) contentView
				.findViewById(R.id.fragment_for_result_sub_custom_btn);
		return contentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
	}

	private void setupView() {
		activityBtn.setOnClickListener(this);
		fragmentBtn.setOnClickListener(this);
		customBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_for_result_sub_activity_btn:
			FragmentForResultFragment.requestIndex++;
			FragmentForResultFragment.resultTxt.append("\n"
					+ FragmentForResultFragment.requestIndex
					+ ":activity request");
			getActivity().startActivityForResult(
					new Intent(getActivity(), FragmentSetResultActivity.class),
					FragmentForResultActivity.REQUEST_FOR_RESULT);
			break;
		case R.id.fragment_for_result_sub_fragment_btn:
			FragmentForResultFragment.requestIndex++;
			FragmentForResultFragment.resultTxt.append("\n"
					+ FragmentForResultFragment.requestIndex
					+ ":sub fragment request");
			startActivityForResult(new Intent(getActivity(),
					FragmentSetResultActivity.class),
					FragmentForResultActivity.REQUEST_FOR_RESULT);
			break;
		case R.id.fragment_for_result_sub_custom_btn:
			FragmentForResultFragment.requestIndex++;
			FragmentForResultFragment.resultTxt.append("\n"
					+ FragmentForResultFragment.requestIndex
					+ ":custom activity request");
			getActivity().startActivityForResult(
					new Intent(getActivity(), FragmentSetResultActivity.class),
					FragmentForResultActivity.REQUEST_FOR_RESULT_CUSTOM);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode,
			final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && data != null) {
			FragmentForResultFragment.resultTxt
					.append("\n"
							+ FragmentForResultFragment.requestIndex
							+ ":sub fragment right:"
							+ data.getStringExtra(FragmentForResultActivity.INTENT_RESULT)+" "+requestCode);
		}
	}
}
