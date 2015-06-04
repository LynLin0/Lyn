package lyn.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2014-12-17
 *
 */

public class MyListView extends ListView{

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyListView(Context context) {
		super(context);
		init();
	}
	
	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		for(int index = 1 ; index < getChildCount() ; index++){
			View child = getChildAt(index);
			child.setTranslationY(100);
		}
		return super.dispatchTouchEvent(ev);
	}
	
	
	private void init() {
		setDivider(null);
//		getChildAt(2).setTranslationY(300f);
	}

	
	
}
