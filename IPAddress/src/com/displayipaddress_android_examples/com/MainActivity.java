package com.displayipaddress_android_examples.com;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button button;
	TextView textview;
	String IPaddress;
	Boolean IPValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button = (Button)findViewById(R.id.button1);
        textview = (TextView)findViewById(R.id.textView1);     
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				NetwordDetect();
				
			}
		});
        
    }
    
    
	  //Check if Internet Network is active
	    private void NetwordDetect() {
	    	
	    boolean WIFI = false;
	    
	    boolean MOBILE = false;
	    
	    ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    
	    NetworkInfo[] networkInfo = CM.getAllNetworkInfo();
	    
	    for (NetworkInfo netInfo : networkInfo) {
	    	
	     if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
	    	 
	       if (netInfo.isConnected())
	    	   
	    	   WIFI = true;
	     
	       if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
	    	   
	           if (netInfo.isConnected())
	        	   
	        	   MOBILE = true;
	       }
	   
	   if(WIFI == true)
		   
	   {
		   IPaddress = GetDeviceipWiFiData();
		   textview.setText(IPaddress);
		   
		   
	   }
	   
	  if(MOBILE == true)
	  {
		  
		  IPaddress = GetDeviceipMobileData();
		  textview.setText(IPaddress);
		  
	  }
	   
}
    
    
    public String GetDeviceipMobileData(){
    	   try {
    	       for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();  
    	       en.hasMoreElements();) {
    	       NetworkInterface networkinterface = en.nextElement();
    	           for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
    	           InetAddress inetAddress = enumIpAddr.nextElement();
    	                if (!inetAddress.isLoopbackAddress()) {
    	                return inetAddress.getHostAddress().toString();
    	                }
    	           }
    	       }
    	       } catch (Exception ex) {
    	          Log.e("Current IP", ex.toString());
    	      }
    	      return null;
    	}
    
    public String GetDeviceipWiFiData()
    {
		
    	 WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
    	 
         @SuppressWarnings("deprecation")
         
 		 String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
         
    	 return ip;
    	
    }
    
}
