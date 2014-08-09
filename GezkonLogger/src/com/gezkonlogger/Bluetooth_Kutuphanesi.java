package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Bluetooth_Kutuphanesi {
	  
	/**
	 * Bir �l��m boyunca Bluetooth bilgilerinin tutulaca�� listelerdir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 */
	
	 static List<Cihaz_Kutuphanesi> bluetooth_liste=new ArrayList<Cihaz_Kutuphanesi>();	

		/********************************************************************************************
		 * 
		 * FONKS�YON ADI: 				BroadcastReceiver </br> </br>
		 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile bluetooth taramas� yap�lmakta ve tarama sonu�lar� listeye y�klenmektedir. </br> </br>
		 * <!--
		 * ER���M: Public </br> </br>
		 * <!--
		 * PARAMETRELER:
	     * 				ADI							T�P�				A�IKLAMASI
		 * D�N��:	
	     * 				ADI							T�P�				A�IKLAMASI			
	     * -->
		 * GEREKL�L�K: Bluetooth'un a��k olmas� gerekmektedir.
		 *
		*********************************************************************************************/
	  final static BroadcastReceiver b_alici = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String str_olay = intent.getAction();
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(str_olay)) {
		             // Get the BluetoothDevice object from the Intent
		        	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        	 float f_rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
		        	 bluetooth_liste.add(new Cihaz_Kutuphanesi(device.getAddress(),f_rssi,device.getName()));
		           //  Toast.makeText(MainActivity.icerik,String.valueOf(rssi),Toast.LENGTH_LONG).show();
		        }
		    }
		};
}		

