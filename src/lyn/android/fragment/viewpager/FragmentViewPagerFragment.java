package lyn.android.fragment.viewpager;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class FragmentViewPagerFragment extends LazyLoadFragment{
	private TextView infoTxt;
	private ProgressBar progressBar;
	private boolean isViewPrepared;
	private boolean isLoading;
	
	public static FragmentViewPagerFragment  newInstance(String info){
		FragmentViewPagerFragment fragmnet = new FragmentViewPagerFragment();
		Bundle bundle = new Bundle();
		bundle.putString(FragmentViewPagerActivity.ARGUMENT_INFO, info);
		fragmnet.setArguments(bundle);
		return fragmnet;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		SwitchLogger.d("Fragment", "onCreateView start");
		View contentView = inflater.inflate(R.layout.fragment_fg_viewpager, container,false);
		progressBar = (ProgressBar)contentView.findViewById(R.id.fragment_viewpager_progress);
		infoTxt = (TextView)contentView.findViewById(R.id.fragment_viewpager_txt);
		isViewPrepared = true;
		SwitchLogger.d("Fragment", "onCreateView end");
		return contentView;
	}

	@Override
	protected void lazyLoad() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				progressBar.setVisibility(View.GONE);
				infoTxt.setText("#"+getArguments().getString(FragmentViewPagerActivity.ARGUMENT_INFO)+" fragment load completed");
			}
		}, 10000);
	}
}
