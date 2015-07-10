package lyn.android.fragment.argument;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragmentArgumentActivity extends FragmentActivity {
	private static final String VALUE_HAHA = "hahaha";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			FragmentArgument fragmentArgument = new FragmentArgument(VALUE_HAHA);
			fragmentArgument.setValue(VALUE_HAHA);
			Bundle bundle = new Bundle();
			bundle.putString(FragmentArgument.ARGUMENT_VALUE, VALUE_HAHA);
			fragmentArgument.setArguments(bundle);
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, fragmentArgument).commit();
		}
	}
}
