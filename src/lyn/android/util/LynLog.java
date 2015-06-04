package lyn.android.util;

import android.util.Log;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-6
 *
 */

public final class LynLog {
	private static final String PREFIX_LOG = "LYN";
	
	public static void d(String tag,String msg){
		Log.d(PREFIX_LOG+tag, msg);
	}
}
