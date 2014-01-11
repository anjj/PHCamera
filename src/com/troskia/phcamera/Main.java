package com.troskia.phcamera;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity {

	/*Vars*/
	static final int REQUEST_TAKE_PHOTO = 1;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	String mCurrentPhotoPath  ;
	ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_main);
		mImageView = new ImageView(getApplicationContext());
		dispatchTakePictureIntent();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	       Toast.makeText(getApplicationContext(), "File saved to: \n " + mCurrentPhotoPath , Toast.LENGTH_LONG).show();
	        finish();
	    }
	}
	

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {

	        }
	        // Continue only if the File was successfully created
	        if (photoFile != null) {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
	    }
	}
	
	
	/**
	 * Creacion del tempFile que sera el RealFile 
	 * @return
	 * @throws IOException
	 */
	private File createImageFile() throws IOException {
    
	    Calendar cal = Calendar.getInstance();      
	    Calendar dt = Calendar.getInstance(); 
	    dt.clear();
	    dt.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DATE)); 

	    int month = dt.get(Calendar.MONTH);
	    int day = dt.get(Calendar.DATE);
	    int Year = dt.get(Calendar.YEAR);
	    String dateTime = XfromDate(day, month, Year);
	   
	    String imageFileName = "PH+_" + dateTime + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory("/DCIM/PH+/");
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	
	private String XfromDate(int day, int month, int year){
		String convert = String.valueOf(day);
		
		switch (month) {
		case 0:
				convert = convert + "_Enero";
			break;
			case 1:
				convert = convert + "_Febrero";			
			break;
			case 2:
				convert = convert + "_Marzo";
				break;
			case 3:
				convert = convert + "_Abril";
				break;
			case 4:
				convert = convert + "_Mayo";
				break;
			case 5:
				convert = convert + "_Junio";
				break;
			case 6:
				convert = convert + "_Julio";
				break;
			case 7:
				convert = convert + "_Agosto";
				break;
			case 8:
				convert = convert + "_Septiembre";
				break;
			case 9:
				convert = convert + "_Octubre";
				break;
			case 10:
				convert = convert + "_Noviembre";
				break;
			case 11:
				convert = convert + "_Diciembre";
				break;
		}
			convert = convert + "_"  + String.valueOf(year);
		return convert;
	}
}
