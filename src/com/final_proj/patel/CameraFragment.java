package com.final_proj.patel;

import java.io.File;

import com.chintanpatel.wiwac.R;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraFragment extends Fragment implements OnClickListener {

	private Uri mImageUri;
	private View view;
	private static final int CAMERA_EVENT = 2345;
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_fragment, container, false);
        
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo;
        try
        {
            // place where to store camera taken picture
            photo = this.createTemporaryFile("picture", ".jpg");
            photo.delete();
        }
        catch(Exception e)
        {
            Log.v("CameraFragment", "Can't create file to take picture!");
            Toast.makeText(getActivity(), "Please check SD card! Image shot is impossible!", Toast.LENGTH_LONG).show();
            return null;
        }
        mImageUri = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        //start camera intent
        startActivityForResult(intent, CAMERA_EVENT);
        
        return view;
    }
    
    private File createTemporaryFile(String part, String ext) throws Exception
    {
        File tempDir= Environment.getExternalStorageDirectory();
        tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
        if(!tempDir.exists())
        {
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	public void grabImage(ImageView imageView)
	{
		Log.d("CameraFragment", "Inside grabImage");
		
		getActivity().getContentResolver().notifyChange(mImageUri, null);
	    ContentResolver cr = getActivity().getContentResolver();
	    
	    Bitmap bitmap;
	    try
	    {
	    	Log.d("CameraFragment", "Inside try block to getBitmap");
	    	
	        bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);
	        imageView.setImageBitmap(bitmap);
	        
	        Log.d("CameraFragment", "Image Set");
	    }
	    catch (Exception e)
	    {
	        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
	        Log.d("CameraFragment", "Failed to load", e);
	    }
	}


	//called after camera intent finished
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		Log.d("CameraFragment", "Inside onActivityResult");
		
	    if(requestCode==CAMERA_EVENT)
	    {
	    	Log.d("CameraFragment", "Inside requestCode==CAMERA_EVENT");
	    	
			ImageView imageView = (ImageView) view.findViewById(R.id.imageview_pic_holder);
			//... some code to inflate/create/find appropriate ImageView to place grabbed image
			this.grabImage(imageView);
	    }
	    
	    super.onActivityResult(requestCode, resultCode, intent);
	}

}