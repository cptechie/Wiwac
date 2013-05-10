package com.final_proj.patel;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.chintanpatel.wiwac.R;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		GalleryFragment fragment = new GalleryFragment();
		fragmentTransaction.add(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public void onMenuClick(MenuItem item) {
		switch(item.getItemId()){
			case R.id.create:
				
				if(getFragmentManager().findFragmentById(R.id.fragment_container).getClass() == CameraFragment.class){
					((CameraFragment) getFragmentManager().findFragmentById(R.id.fragment_container)).startCamera();
				}
				
				else{
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
					ft.replace(R.id.fragment_container, new CameraFragment());
					ft.addToBackStack(null);
					ft.commit(); 
				}

				break;
		}
	}
    
}
