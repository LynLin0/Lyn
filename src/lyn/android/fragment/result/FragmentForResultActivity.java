package lyn.android.fragment.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public class FragmentForResultActivity extends FragmentActivity {

	public static final int REQUEST_FOR_RESULT = 1134;
	public static final int REQUEST_FOR_RESULT_CUSTOM = 1135;
	public static final String INTENT_RESULT = "result";
	private FragmentForResultFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragment = new FragmentForResultFragment();
		getSupportFragmentManager().beginTransaction()
				.add(android.R.id.content, fragment).commit();
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode,
			final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && data != null) {
					FragmentForResultFragment.resultTxt.append("\n"
							+ FragmentForResultFragment.requestIndex
							+ ":activity:" + data.getStringExtra(INTENT_RESULT)+requestCode+">>16="+(requestCode>>16));
		}
		if (requestCode == REQUEST_FOR_RESULT_CUSTOM
				&& resultCode == Activity.RESULT_OK && data != null) {
					fragment.onActivityResult(requestCode, resultCode, data);
		}
	}
}
