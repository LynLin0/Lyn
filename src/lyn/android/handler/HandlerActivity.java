package lyn.android.handler;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-9-10
 * 
 */

public class HandlerActivity extends Activity {

	private TextView txt;
	private Handler handler;
	private Runnable firstRunnable;
	private Runnable secondRunnable;
	private Button btn;
	private MyThread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_handler);
		txt = (TextView) findViewById(R.id.handler_txt);
		btn = (Button) findViewById(R.id.handler_btn);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				thread.cancl();
			}
		});
		firstRunnable = new Runnable() {
			@Override
			public void run() {
				txt.setText("nimei");
			}
		};
		secondRunnable = new Runnable() {
			@Override
			public void run() {
				txt.setBackgroundColor(Color.RED);
			}
		};
		thread = new MyThread();
		thread.start();
		
		// remove delay callback
		// handler.postDelayed(firstRunnable, 5000);
		// new Thread(){
		// public void run() {
		// Looper.prepare();
		// new Handler().postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// handler.removeCallbacks(firstRunnable);
		// }
		// }, 2000);
		// Looper.loop();
		// };
		// }.start();
	}
	private class MyThread extends Thread{
		private Handler handler;
		public void run() {
			Looper.prepare();
			handler = new Handler() {
				public void handleMessage(Message msg) {
					try {
						Thread.sleep(3000);
						SwitchLogger.d("Handler","haha");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			};
			Looper.loop();
			for(int i=0;i<50;i++){
				handler.sendEmptyMessage(0);
			}
			while (true) {
			}
		}
		public void cancl(){
			handler.removeMessages(0);
		}
	};
}
