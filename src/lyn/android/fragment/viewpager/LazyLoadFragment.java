package lyn.android.fragment.viewpager;

import lyn.android.fragment.LogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class LazyLoadFragment extends LogFragment{
	
	private boolean isLoading;
	private boolean isViewPrepared;
	private boolean isNew = true;
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(isVisibleToUser&&isViewPrepared&&!isLoading){
			loadData();
		}
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(isNew){
			if(getUserVisibleHint()){
				loadData();
			}
			isNew = false;
		}
		isViewPrepared = true;
		
	}
	
	public void loadData(){
		isLoading = true;
		lazyLoad();
	}
	
	protected abstract void lazyLoad();
}
