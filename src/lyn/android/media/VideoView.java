package lyn.android.media;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import lyn.android.R;
import lyn.android.util.SwitchLogger;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Lyn lynlin@vanchu.net
 * @date 2015-6-11
 * 
 */

public class VideoView extends FrameLayout implements OnClickListener,
		OnCompletionListener, OnPreparedListener, OnInfoListener,
		OnBufferingUpdateListener, OnErrorListener {

	private TextureView textureView;
	private ImageView playImageView;
	private ProgressBar progressBar;
	private ImageView previewImageView;

	private String url;
	private boolean isPreparing = false;
	private boolean isLoading = false;
	private long startTime = 0;
	private int playImageId;
	private int videoWidth = 0;
	private int videoHeight = 0;
	private boolean isRepeat = false;

	private MediaPlayer mediaPlayer;

	public VideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public VideoView(Context context) {
		super(context);
		init(null);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void init(AttributeSet attrs) {

		// 4.0以下显示兼容性view
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			if (getContext() != null) {
				LayoutInflater.from(getContext()).inflate(
						R.layout.view_video_unsupport, this, true);
				final TextView unsupportTxt = (TextView) findViewById(R.id.video_unsupport_txt);
				unsupportTxt.setVisibility(View.VISIBLE);
				unsupportTxt.setText(getContext().getString(
						R.string.video_system_unsupport));
			}
			return;
		}

		LayoutInflater.from(getContext()).inflate(R.layout.view_video, this,
				true);

		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.VideoView);
			playImageId = a.getResourceId(R.styleable.VideoView_playImg, 0);
			a.recycle();
		}
		textureView = (TextureView) findViewById(R.id.video_textureview);
		playImageView = (ImageView) findViewById(R.id.video_play_imageview);
		progressBar = (ProgressBar) findViewById(R.id.video_progressbar);
		previewImageView = (ImageView) findViewById(R.id.video_preview_imageview);
		textureView.setSurfaceTextureListener(new SurfaceTextureListener() {
			@Override
			public void onSurfaceTextureUpdated(SurfaceTexture surface) {
				// do nothing
			}

			@Override
			public void onSurfaceTextureSizeChanged(SurfaceTexture surface,
					int width, int height) {
				SwitchLogger.d("MEDIA", "onSurfaceTextureSizeChanged");
			}

			@Override
			public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
				SwitchLogger.e("MEDIA", "onSurfaceTextureDestroyed");
				reset();
				return false;
			}

			@Override
			public void onSurfaceTextureAvailable(SurfaceTexture surface,
					int width, int height) {
				SwitchLogger.d("MEDIA", "onSurfaceTextureAvailable");
				toPrepare();
			}
		});
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onTextureClicked();
			}
		});
		textureView.setOnClickListener(this);
		previewImageView.setOnClickListener(this);
		if (playImageId != 0) {
			playImageView.setImageResource(playImageId);
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return;
		}

		int longSide = Math.max(videoHeight, videoWidth);
		int shortSide = Math.min(videoHeight, videoWidth);
		double ratio = shortSide * 1.0 / longSide;

		textureView.getLayoutParams().width = getMeasuredWidth();
		textureView.getLayoutParams().height = (int) (ratio * getMeasuredWidth());
	}

	/**
	 * 配置View的显示
	 * 
	 * @param url
	 *            视频url
	 * @param img
	 *            预览图url
	 * @param isRepeat
	 *            是否重复播放
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void setup(String oriUrl, String img, int width, int height,
			boolean isRotate, boolean isRepeat) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return;
		}
		 this.url = oriUrl;
		
		// // 兼容不同格式的url
		// if (this.url != null && !this.url.startsWith("http://")) {
		// this.url = this.url.startsWith("/") ? this.url : "/" + this.url;
		// this.url = ServerCfg.CDN + this.url;
		// }
		this.videoWidth = width;
		this.videoHeight = height;
		if (isRotate) {
			textureView.setRotation(90f);
		} else {
			textureView.setRotation(0f);
		}
		// if (img != null && !img.trim().equals("")) {
		// if (img.startsWith("file")) {
		// NBitmapLoader.executeLocal(img, previewImageView,
		// DisplayImageCfg.TYPE_NO_DEFAULT_IMAGE);
		// } else {
		// NBitmapLoader.execute(img, previewImageView,
		// DisplayImageCfg.TYPE_NO_DEFAULT_IMAGE);
		// }
		// }
	}

	// public void setup(VideoItemEntity videoItemEntity, String from) {
	// PostImgEntity imgEntity = videoItemEntity.getImgEntitys().get(0);
	// setup(videoItemEntity.getVideo().getResource(), imgEntity.getImage(),
	// imgEntity.getWidth(), imgEntity.getHeight(), videoItemEntity
	// .getVideo().isRotate(), false, from);
	// }


	public void seekTo(int msec) {
		mediaPlayer.seekTo(msec);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void play() {
		if (url == null || url.trim().equals("")) {
			SwitchLogger.d("MEDIA", "视频url为空");
			reset();
			return;
		}
		if (isLoading) {
			return;
		}
		// 正在prepare时不做任何处理
		if (isPreparing) {
			return;
		}
		//
		playImageView.setVisibility(View.GONE);
		// 5.resume play
		if (textureView.getVisibility() == View.VISIBLE) {
			SwitchLogger.d("MEDIA", "resume");
			if (mediaPlayer == null) {
				return;
			}
			mediaPlayer.start();
			previewImageView.setVisibility(View.GONE);
		} else {
			// 5.start prepare
			// SwitchLogger.d("MEDIA", "start prepare");
			progressBar.setVisibility(View.VISIBLE);
			isLoading = true;
			prepareToStart();
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void prepareToStart() {
		textureView.setVisibility(View.VISIBLE);
		if (textureView.isAvailable()) {
			toPrepare();
		}
	}

	public void pause() {
		mediaPlayer.pause();
		playImageView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.video_textureview:
			onTextureClicked();
			break;
		case R.id.video_preview_imageview:
			onPreviewClicked();
			break;
		default:
			break;
		}
	}

	public void onPreviewClicked() {
		play();
	}

	public void onTextureClicked() {
		if (mediaPlayer == null) {
			return;
		}
		if (mediaPlayer.isPlaying()) {
			pause();
		} else {
			play();
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		SwitchLogger.e("MEDIA", "onCompletion");
		// 4.complete
		if (!isRepeat) {
			playImageView.setVisibility(View.VISIBLE);
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onPrepared(MediaPlayer mp) {
		if (isPreparing == true) {
			SwitchLogger.e("MEDIA", "start time="
					+ (System.currentTimeMillis() - startTime));
			// textureView.getLayoutParams().height =
			// mp.getVideoHeight()/mp.getVideoWidth()*DeviceInfo.getScreenWidth(getContext());
			mediaPlayer.start();
			// 3.first play
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if (textureView.getVisibility() == VISIBLE) {
						progressBar.setVisibility(View.GONE);
						playImageView.setVisibility(View.GONE);
						previewImageView.setVisibility(View.GONE);
						isPreparing = false;
					}
				}
			}, 500);
		}
	}

	// 由于视频先下载再播放，所以不需要处理缓冲
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		// 2.buffer start
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (textureView.getVisibility() == View.VISIBLE) {
				progressBar.setVisibility(View.VISIBLE);
			} else {
				progressBar.setVisibility(View.GONE);
			}
			break;
		// 1.buffer end
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			progressBar.setVisibility(View.GONE);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		SwitchLogger.e("MEDIA", "percent=" + percent);
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		SwitchLogger.e("MEDIA", "error:what=" + what + " extra=" + extra);
		if (what == 1 && extra == -1004) {
			// GmqTip.show(getContext(), R.string.network_exception);
		} else {
			// GmqTip.show(getContext(), "视频播放失败");
		}
		reset();
		return true;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void toPrepare() {
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.reset();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(url);
			mediaPlayer
					.setSurface(new Surface(textureView.getSurfaceTexture()));
			mediaPlayer.setLooping(isRepeat);
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnInfoListener(this);
			mediaPlayer.setOnErrorListener(this);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.prepareAsync();
			isPreparing = true;
			startTime = System.currentTimeMillis();
			// progressBar.setVisibility(View.VISIBLE);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 0 reset
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void reset() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return;
		}
		SwitchLogger.d("MEDIA", "reset");
		textureView.setVisibility(View.GONE);
		previewImageView.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
		playImageView.setVisibility(View.VISIBLE);
		isPreparing = false;
		isLoading = false;
		releaseMediaplayerIfExist();
	}

	public void releaseMediaplayerIfExist() {
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public void setPlayImageResource(int resId) {
		playImageView.setImageResource(resId);
	}
}
