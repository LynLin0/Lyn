package lyn.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lyn.android.view.PullToRefreshListView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-3-6
 *
 */

public class PullToRefreshActivity extends Activity {

	private PullToRefreshListView lv;
	private List<Map<String,String>> list= new ArrayList<Map<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_pull_to_refresh);
		lv = (PullToRefreshListView)findViewById(R.id.pull_to_refresh_listview);
		Map<String, String> map;
		for(int i=0;i<50;i++){
			map = new HashMap<String,String>();
			map.put("haha", "haha"+i);
			list.add(map);
		}
		lv.setAdapter(new SimpleAdapter(this, list , R.layout.main_item, new String[]{"haha"}, new int[]{R.id.main_item_tv}));
	}
}
