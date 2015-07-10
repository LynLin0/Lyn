package lyn.android;

import java.util.ArrayList;
import java.util.List;

import lyn.android.fragment.FragmentPracticeActivity;
import lyn.android.fragment.argument.FragmentArgumentActivity;
import lyn.android.fragment.dialog.FragmentDialogActivity;
import lyn.android.fragment.result.FragmentForResultActivity;
import lyn.android.fragment.stack.FragmentStackActivity;
import lyn.android.fragment.viewpager.FragmentViewPagerActivity;
import lyn.android.media.MediaPlayerTestActivity;
import lyn.android.media.MediaPlayerViewTestActivity;
import lyn.android.media.RecordActivity;
import lyn.android.util.Util;
import lyn.android.view.PullToRefreshListView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-2-12
 *
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HomeActivity extends Activity{
	
	public List<Item> list = new ArrayList<Item>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.enableStrictMode();
		super.onCreate(savedInstanceState);
		FrameLayout frameLayout = new FrameLayout(this);
		ListView listView = new ListView(this);
//		listView.setAdapter(new HomeAdapter().addItem("StrictMode", StrictModeActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("PullToRefreshListView", PullToRefreshActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("StaggeredGridView", StaggeredGridActivity.class));
		listView.setAdapter(new HomeAdapter().addItem("MediaPlayerTest", MediaPlayerTestActivity.class));
		listView.setAdapter(new HomeAdapter().addItem("PlayerView", MediaPlayerViewTestActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("LineEnd", LineEndActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("Fragment", FragmentPracticeActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("FragmentForResult", FragmentForResultActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("FragmentStack", FragmentStackActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("FragmentArgument", FragmentArgumentActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("FragmentViePager", FragmentViewPagerActivity.class));
//		listView.setAdapter(new HomeAdapter().addItem("FragmentDialog", FragmentDialogActivity.class));
		
		frameLayout.addView(listView);
		setContentView(frameLayout);
	}
	
	private class Item{
		private String text;
		private Class<?> clazz;
		public Class<?> getClazz() {
			return clazz;
		}
		public void setClazz(Class<?> clazz) {
			this.clazz = clazz;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}
	
	
	private class HomeAdapter extends BaseAdapter{
		
		public HomeAdapter addItem(String text,Class<?> clazz){
			Item item = new Item();
			item.setText(text);
			item.setClazz(clazz);
			list.add(item);
			return this;
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Item getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Item item = getItem(position);
			if(convertView==null){
				convertView = new Button(HomeActivity.this);
			}
			((Button)convertView).setText(item.getText());
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity(new Intent(HomeActivity.this, item.getClazz()));	
				}
			});
			return convertView;
		}
		
	}


}
