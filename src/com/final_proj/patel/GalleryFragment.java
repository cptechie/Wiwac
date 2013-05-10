package com.final_proj.patel;

import java.io.File;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.chintanpatel.wiwac.R;

public class GalleryFragment extends Fragment implements OnClickListener {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
    	View view = inflater.inflate(R.layout.gallery_fragment, container, false);
        return view;
    }
    
    @Override
    public void onStart() {
    	File[] images = (new File(HelperClass.path)).listFiles();
		
		ImageAdapter adapter = new ImageAdapter(getActivity());
		((ListView) getView().findViewById(R.id.gallery_listview)).setAdapter(adapter);
		
		for(File image : images){
			adapter.add(new ImageItem(getActivity(), image.getPath()));
		}
 
    	super.onStart();
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	class ImageItem {
		final String path;

		ImageItem(Context c, String tempPath) {
			path = tempPath;
		}
	}

	class ImageAdapter extends ArrayAdapter<ImageItem> {
		private final LayoutInflater mInflater;

		public ImageAdapter(Context context) {
			super(context, 0);
			mInflater = LayoutInflater.from(getContext());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewGroup view = null;

			if (convertView == null) {
				view = (ViewGroup) mInflater.inflate(R.layout.rounded_image, parent, false);
			} else {
				view = (ViewGroup) convertView;
			}

			ImageItem item = getItem(position);

			((ImageView) view.findViewById(R.id.rounded_image)).setImageBitmap(HelperClass.getBitmap(item.path));

			return view;
		}
	}
	
}