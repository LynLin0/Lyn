package lyn.android.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lyn.android.util.SwitchLogger;
import android.R.integer;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Scroller;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-11
 * 
 */

public class StaggeredGridView extends ViewGroup {

	private static final int NUM_RECYCLE_VIEW = 20;
	private static final int COLUMN = 2;
	private ListAdapter adapter;
//	private Scroller scroller;
	private int firstPosition; 
	private float lastY;
	private String TAG = "StraggedGridView";
	private int activePointerId;
	private List<View> recycleViewlist = new LinkedList<View>();

	public StaggeredGridView(Context context) {
		super(context);
		init();
		recycleViewlist.add(recycleViewlist.remove(2));
	}

	public StaggeredGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
//		scroller = new Scroller(getContext());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastY = event.getY();
			activePointerId = MotionEventCompat.getPointerId(event, 0);
			break;
		case MotionEvent.ACTION_MOVE:
			offsetChildren((int) (event.getY()-lastY));
			lastY = event.getY();
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childCount =getChildCount();
		int column = COLUMN;
		int columnWidth = (r - l) / column;
		int needRecycle = -1;
		
		//第一次view还不存在，就要全部获取出来
		if(recycleViewlist.size()==0){
			for(int i=firstPosition;i<NUM_RECYCLE_VIEW;i++){
				View view = adapter.getView(i, null, this);
				recycleViewlist.add(view);
				addView(view);
			}
		}
		
		for(int i=0;i<recycleViewlist.size();i++){
			View childView = recycleViewlist.get(i);
			LayoutParams lp = (LayoutParams) childView.getLayoutParams();
			childView
					.measure(MeasureSpec.UNSPECIFIED, MeasureSpec
							.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY));
			
			int childHeight = childView.getMeasuredHeight();
			int childLeft = i % column * columnWidth;
			int childTop = getAboveChildHeight(i);
			int childRight = childLeft + columnWidth;
			int childBottom = childTop + childHeight;
			childView.layout(childLeft, childTop, childRight, childBottom);
			
			if(childBottom<0){
				needRecycle = i;
			}
		}
		
		if(needRecycle!=-1){
			removeViewAt(needRecycle);
			View view = adapter.getView(firstPosition+20,recycleViewlist.remove(needRecycle), this);
			addView(view);
			recycleViewlist.add(view);
		}
		
		
		//从adapter中拿出前几个view来初始化布局
//		for (int childIndex = 0; childIndex < childCount; childIndex++) {
//			View childView = getChildAt(childIndex);
//			LayoutParams lp = (LayoutParams) childView.getLayoutParams();
//			childView
//					.measure(MeasureSpec.UNSPECIFIED, MeasureSpec
//							.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY));
//			int childHeight = childView.getMeasuredHeight();
//			int childLeft = childIndex % column * columnWidth;
//			int childTop = getAboveChildHeight(childIndex);
//			int childRight = childLeft + columnWidth;
//			int childBottom = childTop + childHeight;
//			childView.layout(childLeft, childTop, childRight, childBottom);
//		}
	}

	private int getAboveChildHeight(int childIndex) {
		if (childIndex < COLUMN) {
			return 0;
		}
		return recycleViewlist.get(childIndex - COLUMN).getBottom();
	}

	private void offsetChildren(int offset) {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			child.layout(child.getLeft(), child.getTop() + offset,
					child.getRight(), child.getBottom() + offset);
		}
	}

	public void setAdapter(ListAdapter adapter) {
		this.adapter = adapter;
	}

//	@Override
//	public void computeScroll() {
//		if (scroller.computeScrollOffset()) {
//			postInvalidate();
//		}
//	}

}
