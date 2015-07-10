package lyn.android.fragment.viewpager;

import lyn.android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentViewPagerActivity extends FragmentActivity implements OnCheckedChangeListener{
	
	public static final String ARGUMENT_INFO = "info";
	private NonSwipeableViewPager viewpager;
	private RadioGroup radiogroup;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_act_viewpager);
		findView();
		setupView();
	}

	private void findView() {
		viewpager = (NonSwipeableViewPager)findViewById(R.id.fragment_viewpager_viewpager); 
		radiogroup = (RadioGroup)findViewById(R.id.fragment_viewpager_first_radiogroup);
	}
	
	private void setupView() {
		viewpager.setOffscreenPageLimit(3);
		viewpager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager()));
		radiogroup.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.fragment_viewpager_first_radiobutton:
			viewpager.setCurrentItem(0);
			break;
		case R.id.fragment_viewpager_second_radiobutton:
			viewpager.setCurrentItem(1);
			break;
		case R.id.fragment_viewpager_third_radiobutton:
			viewpager.setCurrentItem(2);
			break;
		case R.id.fragment_viewpager_fourth_radiobutton:
			viewpager.setCurrentItem(3);
			break;
		default:
			break;
		}
	}
}
