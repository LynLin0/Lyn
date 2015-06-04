package lyn.android;

import lyn.android.util.Util;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-12
 * 
 */

public class StrictModeActivity extends Activity {

//	private static final String FILE_NAME = "test";
//	private static final String TAG = "StrictModeActivity";
//	static MemoryLeak memoryLeak = null;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.enableStrictMode();
		super.onCreate(savedInstanceState);
	}
	
	
}
