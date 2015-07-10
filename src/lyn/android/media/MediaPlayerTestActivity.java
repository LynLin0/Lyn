package lyn.android.media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ListView;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-11
 * 
 */

public class MediaPlayerTestActivity extends Activity {
	private List<String> urlList = new ArrayList<String>();
	private ListView listView;
//	private PlayerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_act_media_player_test);
		initData();
		findView();
		setupView();
	}

	private void setupView() {
//		adapter = new PlayerAdapter();
		final VideoView playView = new VideoView(this);
		playView.setLayoutParams(new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT, 400));
//		playView.setup("http://open.qiniudn.com/thinking-in-go.mp4", "", true);
//		playView.setCallBack(adapter);
		listView.addHeaderView(playView);
//		listView.setAdapter(adapter);
		listView.setRecyclerListener(new RecyclerListener() {
			@Override
			public void onMovedToScrapHeap(View view) {
				SwitchLogger.d("MEDIA", "onMovedToScrapHeap");
				((VideoView) view).reset();
			}
		});
		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem != 0) {
					playView.reset();
				}
			}
		});
	}

	private void initData() {
		try {
			String dir = Environment.getExternalStorageDirectory()
					.getCanonicalFile().getAbsolutePath()
					+ "/video/";

			urlList.add("http://mvvideo1.meitudata.com/557439afbf0373740.mp4");
			urlList.add("http://mvvideo1.meitudata.com/5576a12d9fd506331.mp4");
			urlList.add("http://mvvideo2.meitudata.com/5577f2db5b4674704.mp4");
			urlList.add("http://mvvideo1.meitudata.com/5578d5db82a9b9447.mp4");
			urlList.add("http://mvvideo2.meitudata.com/557552dabd32e2679.mp4");
			urlList.add("http://mvvideo1.meitudata.com/557905929b8da6910.mp4");
//			 urlList.add(dir+"8.mp4");
//			 urlList.add(dir+"1.mp4");
//			 urlList.add(dir+"2.mp4");
//			 urlList.add(dir+"3.mp4");
//			 urlList.add(dir+"4.mp4");
//			 urlList.add(dir+"5.mp4");
//			 urlList.add(dir+"6.mp4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findView() {
		listView = (ListView) findViewById(R.id.media_player_test_listview);
	}

//	private class PlayerAdapter extends BaseAdapter implements CallBack {
//		private VideoView currentView;
//
//		@Override
//		public int getCount() {
//			return urlList.size();
//		}
//
//		@Override
//		public String getItem(int position) {
//			return urlList.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			String url = getItem(position);
//			if (convertView == null) {
//				convertView = LayoutInflater.from(MediaPlayerTestActivity.this)
//						.inflate(R.layout.media_item_media_player_test, parent,
//								false);
//				convertView.getLayoutParams().height=1080;
////				((VideoView) convertView).setCallBack(this);
//			}
////			((VideoView) convertView).setup(url, "", false);
//			if (currentView != null) {
//				if (convertView != currentView) {
//					((VideoView) convertView).reset();
//				}
//			}
//			return convertView;
//		}
//
//		@Override
//		public void onPlay(VideoView view) {
//			if (currentView != null) {
//				if (currentView != view) {
//					currentView.reset();
//				}
//			}
//			currentView = view;
//		}
//
//	}

}
