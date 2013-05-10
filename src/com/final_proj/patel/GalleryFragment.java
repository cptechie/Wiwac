package com.final_proj.patel;

import java.io.File;

import android.app.Fragment;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chintanpatel.wiwac.R;

public class GalleryFragment extends Fragment implements OnClickListener {

	private String path;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);
        
        path = Environment.getExternalStorageDirectory()+"/Wiwac";
        
        return view;
    }
    
    @Override
    public void onStart() {
    	File[] images = (new File(path)).listFiles();
        
        ImageView imgView = new ImageView(getActivity());
        
        BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize=4;
		Bitmap bmp = BitmapFactory.decodeFile(images[0].getPath(), options);
        
		imgView.setImageBitmap(getRoundedCornerBitmap(bmp));
		
		LinearLayout layout = (LinearLayout) getView().findViewById(R.id.gallery);
		layout.addView(imgView);
        
        Log.d("GalleryFragment", "ImageCreated with path: " + images[0].getPath());
        
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
	
}