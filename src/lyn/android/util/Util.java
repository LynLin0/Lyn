package lyn.android.util;

import lyn.android.StrictModeActivity;
import android.annotation.TargetApi;
import android.os.StrictMode;
import android.os.Build.VERSION_CODES;


/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-12
 * 
 */

public class Util {

	@TargetApi(VERSION_CODES.HONEYCOMB)
	public static void enableStrictMode() {
		StrictMode.ThreadPolicy.Builder threadPolicyBuilder = new StrictMode.ThreadPolicy.Builder()
				.detectAll().penaltyLog();
		StrictMode.VmPolicy.Builder vmPolicyBuilder = new StrictMode.VmPolicy.Builder()
				.detectAll().penaltyLog();
		

//		threadPolicyBuilder.penaltyFlashScreen();
//		StrictMode.setThreadPolicy(threadPolicyBuilder.build());
		StrictMode.setVmPolicy(vmPolicyBuilder.build());
	}
}
