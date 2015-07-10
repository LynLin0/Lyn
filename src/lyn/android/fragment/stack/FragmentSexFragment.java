package lyn.android.fragment.stack;

import lyn.android.R;
import lyn.android.fragment.LogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class FragmentSexFragment extends LogFragment implements OnClickListener{
	
	
	private Button nextBtn;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_sex, container,false);
		nextBtn = (Button)contentView.findViewById(R.id.fragment_sex_btn);
		return contentView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
	}

	private void setupView() {
		nextBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		switch (v.getId()) {
		case R.id.fragment_sex_btn:
			ft.hide(this);
			ft.replace(R.id.fragment_stack_layout, new FragmentTagFragment());
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	        ft.addToBackStack(null);
	        ft.commit();
			break;
		default:
			break;
		}
	}

}
