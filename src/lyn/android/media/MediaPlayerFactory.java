package lyn.android.media;

import android.media.MediaPlayer;

/** 
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-15
 *
 */

public class MediaPlayerFactory {
	private static MediaPlayer player;
	public static MediaPlayer getInstance(){
		if(player==null){
			player = new MediaPlayer();
		}
		return player;
	}

}
