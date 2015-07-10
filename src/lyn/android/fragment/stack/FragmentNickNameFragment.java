package lyn.android.fragment.stack;

import lyn.android.R;
import lyn.android.fragment.LogFragment;
import lyn.android.util.SwitchLogger;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentNickNameFragment extends LogFragment implements OnClickListener{

	
	private Button nextBtn;
	private EditText nameEditText;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		SwitchLogger.d("fragment", "onCreateview start");
		View contentView = inflater.inflate(R.layout.fragment_fg_nick_name, container,false);
		nameEditText = (EditText)contentView.findViewById(R.id.fragment_nick_name_edittext);
		nextBtn = (Button)contentView.findViewById(R.id.fragment_nick_name_btn);
		SwitchLogger.d("fragment", "onCreateview end");
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
	public void onClick(View v	) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		switch (v.getId()) {
		case R.id.fragment_nick_name_btn:
			ft.replace(R.id.fragment_stack_layout, new FragmentSexFragment());
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	        ft.addToBackStack(null);
	        ft.commit();
	    	((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(nameEditText.getWindowToken(),0 );
			break;
		default:
			break;
		}
	}
}
