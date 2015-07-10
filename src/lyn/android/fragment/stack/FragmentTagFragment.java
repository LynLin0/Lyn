package lyn.android.fragment.stack;

import lyn.android.R;
import lyn.android.fragment.LogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTagFragment extends LogFragment implements OnClickListener{

	
	private Button submitBtn;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_tag, container,false);
		submitBtn =(Button)contentView.findViewById(R.id.fragment_tag_btn);
		return contentView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
	}

	private void setupView() {
		submitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_tag_btn:
			getActivity().finish();
			break;
		default:
			break;
		}
	}
	
	
}
