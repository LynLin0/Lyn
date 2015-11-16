package lyn.android.keyboard;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard.Key;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Choreographer.FrameCallback;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-11-16
 *
 */

public class KeyboardActivity extends Activity{
	private TextView faceButton;
	private FrameLayout panelLayout;
	private EditText inputEditText;
	private InputMethodManager mm;
	private LinearLayout inputLayout;
	private int keyboardHeight;
	private static final int MIN_HEIGHT = 300;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_keyboard);
		initData();
		findView();
		setupView();
	}

	private void initData() {
		mm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	private void findView() {
		faceButton = (TextView)findViewById(R.id.keyboard_input_face_button);
		panelLayout = (FrameLayout)findViewById(R.id.keyboard_panel_layout);
		inputLayout = (LinearLayout)findViewById(R.id.keyboard_input_layout);
		inputEditText = (EditText)findViewById(R.id.keyboard_input_edittext);
	}
	
	private void setupView() {
		inputEditText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inputEditText.setFocusable(true);
				inputEditText.setFocusableInTouchMode(true);
				inputEditText.requestFocus();
				
				panelLayout.getLayoutParams().height = 0;
				mm.showSoftInput(inputEditText, InputMethodManager.SHOW_FORCED);
				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
				
			}
		});
		faceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int[] location = new int[2];
				inputLayout.getLocationOnScreen(location);
				DisplayMetrics displayMetrics = KeyboardActivity.this.getApplicationContext()
						.getResources().getDisplayMetrics();
				keyboardHeight = displayMetrics.heightPixels-location[1]-inputLayout.getHeight();
				SwitchLogger.d("haha", "height="+keyboardHeight);
				
				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
				mm.hideSoftInputFromWindow(inputEditText.getWindowToken(),0);
				panelLayout.getLayoutParams().height =Math.max(keyboardHeight,
						MIN_HEIGHT);
				panelLayout.setVisibility(View.VISIBLE);
			}
		});
		
	}
}
