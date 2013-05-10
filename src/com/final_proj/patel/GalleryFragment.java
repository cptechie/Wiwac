package com.final_proj.patel;

import java.io.File;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.chintanpatel.wiwac.R;

public class GalleryFragment extends Fragment implements OnClickListener {

	private String path;
	BitmapFactory.Options options;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);
        
        path = Environment.getExternalStorageDirectory()+"/Wiwac";
        
        return view;
    }
    
    @Override
    public void onStart() {
    	File[] images = (new File(path)).listFiles();
        
        options = new BitmapFactory.Options();
		options.inSampleSize=8;
		
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

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);
	 
	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = 50;
	 
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	 
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	 
	    return output;
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

			((ImageView) view.findViewById(R.id.rounded_image)).setImageBitmap(getRoundedCornerBitmap(BitmapFactory.decodeFile(item.path, options)));

			return view;
		}
	}
	
}