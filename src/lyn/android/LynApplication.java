package lyn.android;

import android.app.Application;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-11-13
 *
 */

public class LynApplication extends Application{
	public static long lastTime;
	@Override
	public void onCreate() {
		lastTime=System.currentTimeMillis();
		super.onCreate();
	}
}
