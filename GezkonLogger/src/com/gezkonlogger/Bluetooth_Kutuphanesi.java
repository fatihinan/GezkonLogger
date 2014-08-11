package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.widget.Toast;

public class Bluetooth_Kutuphanesi {
	 
	//furkan�n telefon s4 mini 4C:A5:6D:1D:3C:AB
	//fatihin tel gio 22:22:EB:ED:16:0F
	//��RKET�N M�N� S4 BC:44:86:F2:C0:00
	//furkan�n tablet 78:A8:73:5A:C6:FF
	//furkan�n bilgisayar E0:B9:A5:F6:2B:E6
	
	/**
	 * Loglanmas� istenilenh bluetooth cihazlar�n�n mac adreslerinin tutan listedir.
	 */
	static List<String> liste_bluetooth_mac_adresleri = new ArrayList<String>();
	
	/**
	 * Bir �l��m boyunca Bluetooth bilgilerinin tutulaca�� listelerdir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 */
	 static List<Cihaz_Kutuphanesi> bluetooth_liste=new ArrayList<Cihaz_Kutuphanesi>();
	 
	 /**
	  * Bulunan bluetooth cihaz�n�n rssi de�erinin tutuldu�u de�i�kendir.
	  */
	 static float f_rssi;

	 
	 	/********************************************************************************************
		 * 
		 * FONKS�YON ADI: 				MacAdresleriniAta </br> </br>
		 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile belirlenen mac adresleri listeye eklenerek
		 * loglama s�ras�nda kullan�lmak �zere kaydedilmektedir. </br> </br>
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
	 public void MacAdresleriniAta()
	 {
		 liste_bluetooth_mac_adresleri.add("4C:A5:6D:1D:3C:AB");
		 liste_bluetooth_mac_adresleri.add("22:22:EB:ED:16:0F");
		 liste_bluetooth_mac_adresleri.add("BC:44:86:F2:C0:00");
		 liste_bluetooth_mac_adresleri.add("78:A8:73:5A:C6:FF");
		 liste_bluetooth_mac_adresleri.add("E0:B9:A5:F6:2B:E6");
	 }
	 
	 
	 
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
		        	 
		        	 f_rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
		        	 
		        	 if(liste_bluetooth_mac_adresleri.contains(device.getAddress()))
		        	 {
		        		 bluetooth_liste.add(new Cihaz_Kutuphanesi(device.getAddress(),f_rssi,device.getName()));
		        	 }
		        	
		        }
		    }
		};
}		

