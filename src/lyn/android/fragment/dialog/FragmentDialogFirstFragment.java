package lyn.android.fragment.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lyn.android.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

public class FragmentDialogFirstFragment extends DialogFragment implements
		OnItemClickListener {
	private ListView listView;
	private List<Map<String, String>> data= new ArrayList<Map<String, String>>();;
	private TextView titleTxt;
	private String str;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Holo_Light_Dialog);
		fakeData();
		str = "#"+FragmentDialogActivity.dialogIndex+" dialog";
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_dialog_first,
				container, false);
		titleTxt = (TextView)contentView.findViewById(R.id.fragment_dialog_txt);
		listView = (ListView) contentView
				.findViewById(R.id.fragment_dialog_listview);
		titleTxt.setText(str);
		listView.setAdapter(new SimpleAdapter(getActivity(), data,
				R.layout.fragment_item_dialog, new String[] { "index" },
				new int[] { R.id.fragment_dialog_item }));
		listView.setOnItemClickListener(this);
		return contentView;
	}


	private void fakeData() {
		HashMap<String, String> map = null;
		for (int i = 0; i < 5; i++) {
			map = new HashMap<String, String>();
			map.put("index", "#" + i + " item");
			data.add(map);
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		FragmentDialogActivity.dialogIndex++;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag(
				FragmentDialogActivity.TAG_DIALOG);
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		new FragmentDialogFirstFragment().show(ft,
				FragmentDialogActivity.TAG_DIALOG);
	}
}
