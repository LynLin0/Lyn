package lyn.android.fragment.argument;

import lyn.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentArgument extends Fragment{
	public static final String ARGUMENT_VALUE = "value";
	
	private String valueFromConstruct;
	private String valueFromSetMethod;
	private String valueFromArgument;

	private TextView constructTxt;

	private TextView setMethodTxt;

	private TextView argumentTxt;
	
	public FragmentArgument() {
	}
	
	public FragmentArgument(String value) {
		this.valueFromConstruct= value; 
	}
	
	public void setValue(String value){
		this.valueFromSetMethod = value;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.valueFromArgument = getArguments().getString(ARGUMENT_VALUE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_fg_argument,container,false);
		constructTxt = (TextView)contentView.findViewById(R.id.fragment_argument_construct_txt);
		setMethodTxt = (TextView)contentView.findViewById(R.id.fragment_argument_set_method_txt);
		argumentTxt = (TextView)contentView.findViewById(R.id.fragment_argument_argument_txt);
		
		return contentView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setupView();
}

	private void setupView() {
		constructTxt.setText("valueFromConstruct="+valueFromConstruct);
		setMethodTxt.setText("valueFromSetMethod="+valueFromSetMethod);
		argumentTxt.setText("valueFromArgument="+valueFromArgument);
	}
}