package lyn.android.fragment.dialog;

import lyn.android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentDialogActivity extends FragmentActivity implements OnClickListener{
	public static final String TAG_DIALOG = "dialog";
	public static int dialogIndex ;
	private Button reportBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dialogIndex =0;
		setContentView(R.layout.fragment_act_dialog);
		reportBtn = (Button)findViewById(R.id.fragment_dialog_btn);
		reportBtn.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		dialogIndex++;
		new FragmentDialogFirstFragment().show(getSupportFragmentManager(), TAG_DIALOG);
	}
}
