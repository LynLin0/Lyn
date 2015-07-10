package lyn.android.fragment.stack;

import lyn.android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentStackActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_act_stack);
		
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_stack_layout,new FragmentNickNameFragment()).commit();
	}
	
}
