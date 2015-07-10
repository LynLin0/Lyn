package lyn.android.media;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-18
 *
 */

public class PlayCenter {

	private static List<IPlayObsever> list = new ArrayList<IPlayObsever>();

	
	public static void register(IPlayObsever observer){
		if(!list.contains(observer)){
			list.add(observer);
		}
	}
	
	public static void unregister(IPlayObsever observer){
		if(list.contains(observer)){
			list.remove(observer);
		}
	} 
	
	public static void play(){
		for(int i=0;i<list.size();i++){
			list.get(i).onPlay();
		}
	}
	
	
	
	public interface IPlayObsever{
		public void onPlay();
	}

}
