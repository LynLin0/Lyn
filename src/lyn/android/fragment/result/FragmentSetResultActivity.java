package lyn.android.fragment.result;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragmentSetResultActivity extends FragmentActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportFragmentManager().beginTransaction().add(android.R.id.content, new FragmentSetResultFragment()).commit();
	}
}
