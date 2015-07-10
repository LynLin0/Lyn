package lyn.android.fragment.result;

import lyn.android.R;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentForResultFragment extends Fragment implements
		OnClickListener {

	protected static final int SENSOR_SHAKE = 110;
	private Button activityBtn;
	private Button fragmentBtn;
	private FragmentForResultSubLeftFragment subFragment;
	private SensorManager sensorManager;
	private ViewPager viewPager;
	public static TextView resultTxt;
	public static int requestIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestIndex = 0;
		sensorManager = (SensorManager) getActivity().getSystemService(
				getActivity().SENSOR_SERVICE);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (sensorManager != null) {
			sensorManager.registerListener(sensorEventListener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_for_result,
				container, false);
		activityBtn = (Button) contentView
				.findViewById(R.id.fragment_for_result_activity_btn);
		fragmentBtn = (Button) contentView
				.findViewById(R.id.fragment_for_result_fragment_btn);
		resultTxt = (TextView) contentView
				.findViewById(R.id.fragment_for_result_result_txt);
		viewPager = (ViewPager) contentView
				.findViewById(R.id.fragment_for_result_viewpager);
		return contentView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
	}

	private void setupView() {
		activityBtn.setOnClickListener(this);
		fragmentBtn.setOnClickListener(this);
		subFragment = new FragmentForResultSubLeftFragment();
		viewPager.setAdapter(new FragmentForResultPagerAdapter(getFragmentManager()));
//		getFragmentManager().beginTransaction()
//				.add(R.id.fragment_for_result_sub_layout, subFragment).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_for_result_activity_btn:
			requestIndex++;
			resultTxt.append("\n" + requestIndex + ":activity request");
			getActivity().startActivityForResult(
					new Intent(getActivity(), FragmentSetResultActivity.class),
					FragmentForResultActivity.REQUEST_FOR_RESULT);
			break;
		case R.id.fragment_for_result_fragment_btn:
			requestIndex++;
			resultTxt.append("\n" + requestIndex + ":fragment request");
			startActivityForResult(new Intent(getActivity(),
					FragmentSetResultActivity.class),
					FragmentForResultActivity.REQUEST_FOR_RESULT);
			break;
		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && data != null) {
			resultTxt
					.append("\n"
							+ requestIndex
							+ ":fragment:"
							+ data.getStringExtra(FragmentForResultActivity.INTENT_RESULT)+" "+requestCode);
		}
		if (requestCode == FragmentForResultActivity.REQUEST_FOR_RESULT_CUSTOM
				&& resultCode == Activity.RESULT_OK && data != null) {
			subFragment.onActivityResult(requestCode, resultCode, data);
		}
	}

	private SensorEventListener sensorEventListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			// 传感器信息改变时执行该方法
			float[] values = event.values;
			float x = values[0]; // x轴方向的重力加速度，向右为正
			float y = values[1]; // y轴方向的重力加速度，向前为正
			float z = values[2]; // z轴方向的重力加速度，向上为正
			int medumValue = 19;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
			if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
					|| Math.abs(z) > medumValue) {
				Message msg = new Message();
				msg.what = SENSOR_SHAKE;
				handler.sendMessage(msg);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	/**
	 * 动作执行
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:
				requestIndex =0;
				resultTxt.setText("result");
				break;
			}
		}
	};
}
