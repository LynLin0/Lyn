package lyn.android.util;

import android.util.Log;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-6
 *
 */

public final class SwitchLogger {
	private static final String PREFIX_LOG = "LYN";
	
	public static void d(String tag,String msg){
		Log.d(PREFIX_LOG+tag, msg);
	}
	
	public static void e(String tag,String msg){
		Log.e(PREFIX_LOG+tag, msg);
	}
}
