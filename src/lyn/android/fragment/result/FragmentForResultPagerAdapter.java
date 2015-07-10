package lyn.android.fragment.result;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentForResultPagerAdapter extends FragmentPagerAdapter{
	private List<Fragment> list= new ArrayList<Fragment>();
	
	public FragmentForResultPagerAdapter(FragmentManager fm) {
		super(fm);
		list.add(new FragmentForResultSubLeftFragment());
		list.add(new FragmentForResultSubRightFragment());
	}

	@Override
	public Fragment getItem(int position) {
		return list.get(position);
	}

	@Override
	public int getCount() {
		return 2;
	}
	


}
