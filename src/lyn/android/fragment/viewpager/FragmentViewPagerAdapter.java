package lyn.android.fragment.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter{
	List<Fragment> list = new ArrayList<Fragment>();
	public FragmentViewPagerAdapter(FragmentManager fm) {
		super(fm);
		list.add(FragmentViewPagerFragment.newInstance("1"));
		list.add(FragmentViewPagerFragment.newInstance("2"));
		list.add(FragmentViewPagerFragment.newInstance("3"));
		list.add(FragmentViewPagerFragment.newInstance("4"));
	}


	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
