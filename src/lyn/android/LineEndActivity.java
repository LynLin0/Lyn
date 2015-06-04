package lyn.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-3-31
 *
 */

public class LineEndActivity extends Activity{
	private final static String TAG = "LineEndActivity";
	
	private TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_lineend);
		txt = (TextView)findViewById(R.id.lineend_txt);
		txt.setText("c\nf\n\n");
		txt.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				txt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		        if (txt.getLineCount() > 2) {
		            int lineEndIndex = txt.getLayout().getLineEnd(1);
		            String text = txt.getText().subSequence(0, lineEndIndex).toString();
		            txt.setText(text);
		        }
			}
		});
	}

}
