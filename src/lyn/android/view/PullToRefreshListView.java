package lyn.android.view;

import lyn.android.util.LynLog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.net.wifi.WifiConfiguration.Status;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-6
 * 
 */

public class PullToRefreshListView extends LinearLayout {

	private static final String TAG = "PullToRefreshListView";

	private static final int RECOVER_DURATION = 100;

	private static final float FRICTION = 2.0f;

	private LogListView innerListView;

	private boolean isFirstPull = true;

	private boolean isRecovering = false;

	private float startY;

	private float actionDownY;

	private TextView headView;

	private int headHeight = 100;

	private int footHeight = 100;

	private Interpolator interpolator = new LinearInterpolator();

	private int disY;

	private TextView footView;

	private Status currStatus = Status.NORMAL;
	// private OnPullListener onPullListener;

	private OnSmoothScrollFinishedListener onSmoothScrollFinishedListener = new OnSmoothScrollFinishedListener() {
		@Override
		public void onSmoothScrollFinished() {
			if(currStatus==Status.PULL_DOWN){
				postDelayed(new Runnable() {
					@Override
					public void run() {
						onPullDownComplete();
					}
				}, 1000);
			}else{
				postDelayed(new Runnable() {
					@Override
					public void run() {
						onPullDownComplete();
					}
				}, 1000);
			}
			// onPullListener.onPullDown(innerListView);
		}
	};

	private OnSmoothScrollFinishedListener doNothingListener = new OnSmoothScrollFinishedListener() {
		@Override
		public void onSmoothScrollFinished() {
		}
	};


	private class SmoothScrollRunnable implements Runnable {
		private int fromY;
		private int toY;
		private int currentY;
		private long startTime;
		private OnSmoothScrollFinishedListener listener;

		public SmoothScrollRunnable(int fromY, int toY,
				OnSmoothScrollFinishedListener listener) {
			this.fromY = fromY;
			this.toY = toY;
			this.startTime = System.currentTimeMillis();
			this.listener = listener;
		}

		@Override
		public void run() {
			int detaY = Math.round((fromY - toY)
					* interpolator.getInterpolation(Math.min(
							(System.currentTimeMillis() - startTime) * 1.0f
									/ RECOVER_DURATION, 1.0f)));
			currentY = fromY - detaY;
			scrollTo(0, currentY);
			if (toY != currentY) {
				postDelayed(this, 16);
			} else {
				listener.onSmoothScrollFinished();
			}
		}
	}

	public void onPullDownComplete() {
		postDelayed(
				new SmoothScrollRunnable(-headHeight, 0, doNothingListener), 16);
	}
	
	public void onPullUpComplete() {
		postDelayed(
				new SmoothScrollRunnable(footHeight, 0, doNothingListener), 16);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PullToRefreshListView(Context context) {
		super(context);
		init();
	}

	public void init() {
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER);
		innerListView = new LogListView(getContext());
		headView = new TextView(getContext());
		headView.setHeight(headHeight);
		headView.setText("updating...");
		headView.setTextColor(Color.RED);
		footView = new TextView(getContext());
		footView.setHeight(footHeight);
		footView.setText("loading more...");
		footView.setTextColor(Color.GREEN);

		addView(headView);
		addView(innerListView, new LayoutParams(LayoutParams.MATCH_PARENT, 0,
				1.0f));
		addView(footView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 0.0f));
		setPadding(0, -headHeight, 0, -footHeight);
	}

	public void setAdapter(ListAdapter adapter) {
		innerListView.setAdapter(adapter);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (isRecovering) {
			return true;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			LynLog.d(TAG, "touch 1");
			if (isFirstPull) {
				startY = event.getY();
				isFirstPull = false;
			}
			return true;
		case MotionEvent.ACTION_MOVE:
			LynLog.d(TAG, "touch 2");
			if (isFirstPull) {
				startY = event.getY();
				isFirstPull = false;
			}
			if (currStatus == Status.PULL_DOWN) {
				disY = Math
						.round(Math.min(startY - event.getY(), 0) / FRICTION);
				scrollTo(0, disY);
			}
			if (currStatus == Status.PULL_UP) {
				disY = Math
						.round(Math.max(startY - event.getY(), 0) / FRICTION);
				scrollTo(0, disY);
			}
			return true;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			LynLog.d(TAG, "touch 3");
			if (currStatus == Status.PULL_DOWN) {
				if (Math.abs(disY) >= headHeight) {
					postDelayed(new SmoothScrollRunnable(disY, -headHeight,
							onSmoothScrollFinishedListener), 16);
				} else {
					postDelayed(new SmoothScrollRunnable(disY, 0,
							doNothingListener), 16);
				}
			}
			if (currStatus == Status.PULL_UP) {
				if (Math.abs(disY) >= footHeight) {
					postDelayed(new SmoothScrollRunnable(disY, footHeight,
							onSmoothScrollFinishedListener), 16);
				} else {
					postDelayed(new SmoothScrollRunnable(disY, 0,
							doNothingListener), 16);
				}
			}
			isFirstPull = true;
			return true;
		}
		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		LynLog.d(TAG, "isRecovering = " + isRecovering);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			LynLog.d(TAG, "inter 1");
			actionDownY = ev.getY();
			return false;
		case MotionEvent.ACTION_MOVE:
			LynLog.d(TAG, "inter 2");
			if (ev.getY() > actionDownY) {
				if (innerListView.getFirstVisiblePosition() == 0) {
					if (innerListView.getChildAt(0).getTop() >= innerListView
							.getTop()) {
						currStatus = Status.PULL_DOWN;
						return true;
					}
				}
			} else if (ev.getY() < actionDownY) {
				if (innerListView.getLastVisiblePosition() == innerListView
						.getCount() - 1) {
					if (innerListView.getChildAt(
							innerListView.getLastVisiblePosition()
									- innerListView.getFirstVisiblePosition())
							.getBottom() <= innerListView.getBottom()) {
						currStatus = Status.PULL_UP;
						return true;
					}
				}
			}
		}
		return false;
	}

	private static interface OnSmoothScrollFinishedListener {
		void onSmoothScrollFinished();
	}

	public static interface OnPullListener {
		public void onPullDown(final View view);

		public void onPullUp(final View view);
	}

	public static enum Status {
		NORMAL, PULL_DOWN, PULL_UP
	}
}
