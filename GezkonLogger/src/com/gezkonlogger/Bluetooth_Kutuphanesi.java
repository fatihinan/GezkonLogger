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
	 * Bir ölçüm boyunca Bluetooth bilgilerinin tutulacaðý listelerdir.
	 * 
	 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
	 */
	
	 static List<Cihaz_Kutuphanesi> bluetooth_liste=new ArrayList<Cihaz_Kutuphanesi>();	

		/********************************************************************************************
		 * 
		 * FONKSÝYON ADI: 				BroadcastReceiver </br> </br>
		 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile bluetooth taramasý yapýlmakta ve tarama sonuçlarý listeye yüklenmektedir. </br> </br>
		 * <!--
		 * ERÝÞÝM: Public </br> </br>
		 * <!--
		 * PARAMETRELER:
	     * 				ADI							TÝPÝ				AÇIKLAMASI
		 * DÖNÜÞ:	
	     * 				ADI							TÝPÝ				AÇIKLAMASI			
	     * -->
		 * GEREKLÝLÝK: Bluetooth'un açýk olmasý gerekmektedir.
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

