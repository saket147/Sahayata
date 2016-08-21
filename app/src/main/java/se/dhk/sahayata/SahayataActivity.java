package se.dhk.sahayata;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

public class SahayataActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       new Thread(new Runnable() {
			
			public void run() {
				 try
		         {
		        	 Thread.sleep(2000);
		        	 startActivity(new Intent(SahayataActivity.this,Sendmsg.class));
		             finish();
		         } catch (Exception e) {
		        	 e.printStackTrace();
				}		
		         
			}
		}).start();
        
           }
    }
        