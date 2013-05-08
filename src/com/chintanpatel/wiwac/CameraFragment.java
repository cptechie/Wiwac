package com.chintanpatel.wiwac;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class CameraFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.camera_fragment, container, false);
        return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	

}