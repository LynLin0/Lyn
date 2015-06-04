package lyn.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lyn.android.view.StaggeredGridView;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-3-6
 *
 */

public class StaggeredGridActivity extends Activity{

	private StaggeredGridView sgv;
	private List<Item> data = new ArrayList<Item>();
	private StaggeredGridAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_staggered_grid);
		sgv = (StaggeredGridView)findViewById(R.id.staggered_grid_staggeredgridview);
		for(int i=0;i<100;i++){
			Item item = new Item();
			item.setHeight(50+new Random().nextInt(50));
			data.add(item);
		}
		adapter = new StaggeredGridAdapter(data);
		sgv.setAdapter(adapter);
	}
	
	private class Item{
		private int height;

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}
	
	private class StaggeredGridAdapter extends BaseAdapter{
		private List<Item> list;
		public StaggeredGridAdapter(List<Item> list){
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = LayoutInflater.from(StaggeredGridActivity.this).inflate(R.layout.main_item, null);
			}
			convertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,list.get(position).getHeight()));
			return convertView;
		}
	}
	
}
