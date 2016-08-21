package se.dhk.sahayata;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Sendmsg extends Activity{
	Button btn ;
	EditText et;
    AppLocationService appLocationService;
    Geocoder geocoder;
    ProgressDialog pd;
    String temp;
    Location gpsLocation; 
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmsg);
		btn= (Button)findViewById(R.id.senddd);
		et=(EditText)findViewById(R.id.editText1);
		pd=new ProgressDialog(Sendmsg.this);
		//new Thread(new Runnable() {
			//public void run() {
			//}
		//}).start();
		
    }
    @Override
    protected void onStart() {
    	appLocationService = new AppLocationService(Sendmsg.this);
		gpsLocation= appLocationService.getLocation(LocationManager.GPS_PROVIDER);			
		geocoder = new Geocoder(Sendmsg.this, Locale.ENGLISH);
		btn.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		temp=et.getText().toString();	
		pd.setMessage("Loading location");
		pd.setCancelable(true);
		pd.show();
        act(); 
		}
		});
    	super.onStart();
    }
    public void act(){
    	if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String result = "Latitude: " +gpsLocation.getLatitude()+" \nLongitude: " +gpsLocation.getLongitude();
            Toast.makeText(getApplicationContext(), gpsLocation.getLatitude()+"\n"+gpsLocation.getLongitude(), 1000).show();
            //et.setText(result);
            try {
           	  List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);           	 
           	  if(addresses != null) {
           	   Address returnedAddress = addresses.get(0);
           	   StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
           	   for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
           	    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
           	   }
           	   et.setText(temp+"\n"+result+"\n"+strReturnedAddress.toString());
           	  pd.hide();
           	  Toast.makeText(getApplicationContext(),result+"\n"+strReturnedAddress.toString(),Toast.LENGTH_LONG).show();
           	  Intent in=new Intent(Intent.ACTION_VIEW);
           	  in.setData(Uri.parse("sms:"));
           	  in.setType("vnd.android-dir/mms-sms");
           	  in.putExtra("sms_body",temp+"\n"+result+"\n"+strReturnedAddress.toString());
           	  startActivity(in);
           	  }
           	 } catch (IOException e) {
           	  	//  myAddress.setText("Cannot get Address!");
           	 }
  
        }else{
       	
        }		
	
    }
}

