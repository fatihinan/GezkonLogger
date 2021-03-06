/*******************************************************************************
 * IM_BluetoothKutuphanesi.java
 *
 *
 * AMA�:
 *
 * IM_BluetoothKutuphanesi ile bilinen 12 bluetooth ayg�t� mac adreslerine g�re bulunmakta 
 * ve �zellikleri listelere eklenmektedir.
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * Loglanmas� istenilen bluetooth cihazlar�n�n mac adreslerinin tutan listedir.
 * liste_bluetooth_mac_adresleri IM_AnaUygulama taraf�ndan rssi verilerinin mac adreslerine g�re s�ralanmas� 
 * i�leminde kullan�laca��ndan dolay� static olarak tan�mlanm��t�r.
 * List<String> liste_bluetooth_mac_adresleri        
 *  
 * Bir �l��m boyunca Bluetooth bilgilerinin tutulaca�� listelerdir.
 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
 * liste_bluetooth IM_AnaUygulama taraf�ndan loglama i�lemi s�ras�nda bluetooth rssi verilerinin okunmas� 
 * i�leminde kullan�laca�� i�in static olarak tan�mlanm��t�r.
 * List<Cihaz_Kutuphanesi> liste_bluetooth

 * 
 * FONKS�YON PROTOT�PLER�:
 *
 * Bu fonksiyon ile belirlenen mac adresleri listeye eklenerek loglama s�ras�nda kullan�lmak �zere kaydedilmektedir.
 * public void MacAdresleriniAta()
 * 
 * Bu fonksiyon ile bluetooth taramas� yap�lmakta ve tarama sonu�lar� listeye eklenmektedir.
 * final static BroadcastReceiver b_alici = new BroadcastReceiver() 
 * 
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Furkan G�NER
 * Tarih: 11.08.2014
 * G�ncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 * 
 * 
 * TEL�F HAKKI:
 * 
 * GezkonLogger yaz�l�m� �novasyon M�hendislik, Teknoloji Geli�tirme, Dan��manl�k, San. ve Tic. Ltd. �ti.
 * taraf�ndan geli�tirilmi� olup her hakk� sakl�d�r. ��erisinde bulunan g�rsel, metin, kod vb. �gelerin
 * tamam�n�n veya bir b�l�m�n�n kullan�lmas� ve kamuya a��k olan/olmayan fiizksel veya dijital ortamlarda 
 * payla��lmas�/yay�nlanmas�/da��t�lmas� yasakt�r.
 *
 ******************************************************************************/

package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IM_BluetoothKutuphanesi {
	 
	//furkan�n telefon s4 mini 4C:A5:6D:1D:3C:AB
	//fatihin tel gio 22:22:EB:ED:16:0F
	//��RKET�N M�N� S4 BC:44:86:F2:C0:00
	//furkan�n tablet 78:A8:73:5A:C6:FF
	//furkan�n bilgisayar E0:B9:A5:F6:2B:E6
	
	
	/**
	 * Loglanmas� istenilenh bluetooth cihazlar�n�n mac adreslerinin tutan listedir.
	 * 
	 * liste_bluetooth_mac_adresleri IM_AnaUygulama taraf�ndan rssi verilerinin mac adreslerine g�re s�ralanmas� 
	 * 
	 * i�leminde kullan�laca��ndan dolay� static olarak tan�mlanm��t�r.
	 */
	static List<String> liste_bluetooth_mac_adresleri = new ArrayList<String>();
	
	/**
	 * Bir �l��m boyunca Bluetooth bilgilerinin tutulaca�� listelerdir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 * 
	 * liste_bluetooth IM_AnaUygulama taraf�ndan loglama i�lemi s�ras�nda bluetooth rssi verilerinin okunmas� 
	 * 
	 * i�leminde kullan�laca�� i�in static olarak tan�mlanm��t�r.
	 */
	 static List<IM_CihazBilgileriKutuphanesi> liste_bluetooth=new ArrayList<IM_CihazBilgileriKutuphanesi>();
	 

	 
	 	/********************************************************************************************
		 * 
		 * FONKS�YON ADI: 				IM_MacAdresleriniAta </br> </br>
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
	 public void IM_MacAdresleriniAta()
	 {
		 liste_bluetooth_mac_adresleri.add("4C:A5:6D:1D:3C:AB");
		 liste_bluetooth_mac_adresleri.add("22:22:EB:ED:16:0F");
		 liste_bluetooth_mac_adresleri.add("BC:44:86:F2:C0:00");
		 liste_bluetooth_mac_adresleri.add("78:A8:73:5A:C6:FF");
		 liste_bluetooth_mac_adresleri.add("E0:B9:A5:F6:2B:E6");
		 //iso
		 liste_bluetooth_mac_adresleri.add("E6:68:46:5B:DE:5D");
		 liste_bluetooth_mac_adresleri.add("68:94:23:FC:19:B4");
		 liste_bluetooth_mac_adresleri.add("5C:AC:4C:D9:DD:3B");
		 liste_bluetooth_mac_adresleri.add("50:32:75:E1:1F:F9");
		 liste_bluetooth_mac_adresleri.add("5C:AC:4C:D9:DD:3B");
		 liste_bluetooth_mac_adresleri.add("E4:92:FB:C4:88:39");
		 liste_bluetooth_mac_adresleri.add("C4:85:08:02:1B:C3");
 
	 }
	 
	 
	 
		/********************************************************************************************
		 * 
		 * FONKS�YON ADI: 				BroadcastReceiver </br> </br>
		 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile bluetooth taramas� yap�lmakta ve tarama sonu�lar� listeye eklenmektedir. </br> </br>
		 * <!--
		 * ER���M: Public </br> </br>
		 * <!--
		 * PARAMETRELER:
	     * 				ADI							T�P�				A�IKLAMASI
		 * D�N��:	
	     * 				ADI							T�P�				A�IKLAMASI			
	     * -->
		 * GEREKL�L�K: 
		 *
		*********************************************************************************************/
	  final static BroadcastReceiver b_alici = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		    	/**
		   	     * Yeni bluetooth bulundu�unda gelen action stringdir.
		   	     */
		    	String str_durum = intent.getAction();
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(str_durum)) {
		             
		        	/**
			   	     * Bulunan bluetooth cihaz�d�r.
			   	     */
		        	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        	 
		        	 /**
		        	  * Bluetooth cihazdan rssi de�eri al�nm��t�r. Birimi dbm dir.
		        	  */
		        	 float f_rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
		        	 
		        	 if(liste_bluetooth_mac_adresleri.contains(device.getAddress()))
		        	 {
		        		 liste_bluetooth.add(new IM_CihazBilgileriKutuphanesi(device.getAddress(),f_rssi,device.getName()));
		        	 }
		        	
		        }
		    }
		};
}		

