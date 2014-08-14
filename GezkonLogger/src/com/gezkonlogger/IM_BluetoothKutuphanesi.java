/*******************************************************************************
 * IM_BluetoothKutuphanesi.java
 *
 *
 * AMAÇ:
 *
 * IM_BluetoothKutuphanesi ile bilinen 12 bluetooth aygýtý mac adreslerine göre bulunmakta 
 * ve özellikleri listelere eklenmektedir.
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Loglanmasý istenilenh bluetooth cihazlarýnýn mac adreslerinin tutan listedir.
 * List<String> liste_bluetooth_mac_adresleri = new ArrayList<String>();            
 * 
 * 
 * Bir ölçüm boyunca Bluetooth bilgilerinin tutulacaðý listelerdir.
 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
 * List<Cihaz_Kutuphanesi> bluetooth_liste=new ArrayList<Cihaz_Kutuphanesi>();
 *       
 * 
 * Bulunan bluetooth cihazýnýn rssi deðerinin tutulduðu deðiþkendir.
 * float f_rssi;	
 * 
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 *
 * Bu fonksiyon ile belirlenen mac adresleri listeye eklenerek loglama sýrasýnda kullanýlmak üzere kaydedilmektedir.
 * public void MacAdresleriniAta()
 * 
 * 
 * Bu fonksiyon ile bluetooth taramasý yapýlmakta ve tarama sonuçlarý listeye eklenmektedir.
 * final static BroadcastReceiver b_alici = new BroadcastReceiver() 
 * 
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Furkan GÜNER
 * Tarih: 11.08.2014
 * Güncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 * 
 * 
 * TELÝF HAKKI:
 * 
 * GezkonLogger yazýlýmý Ýnovasyon Mühendislik, Teknoloji Geliþtirme, Danýþmanlýk, San. ve Tic. Ltd. Þti.
 * tarafýndan geliþtirilmiþ olup her hakký saklýdýr. Ýçerisinde bulunan görsel, metin, kod vb. ögelerin
 * tamamýnýn veya bir bölümünün kullanýlmasý ve kamuya açýk olan/olmayan fiizksel veya dijital ortamlarda 
 * paylaþýlmasý/yayýnlanmasý/daðýtýlmasý yasaktýr.
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
	 
	//furkanýn telefon s4 mini 4C:A5:6D:1D:3C:AB
	//fatihin tel gio 22:22:EB:ED:16:0F
	//ÞÝRKETÝN MÝNÝ S4 BC:44:86:F2:C0:00
	//furkanýn tablet 78:A8:73:5A:C6:FF
	//furkanýn bilgisayar E0:B9:A5:F6:2B:E6
	
	
	/**
	 * Loglanmasý istenilenh bluetooth cihazlarýnýn mac adreslerinin tutan listedir.
	 */
	static List<String> liste_bluetooth_mac_adresleri = new ArrayList<String>();
	
	/**
	 * Bir ölçüm boyunca Bluetooth bilgilerinin tutulacaðý listelerdir.
	 * 
	 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
	 */
	 static List<IM_CihazBilgileriKutuphanesi> liste_bluetooth=new ArrayList<IM_CihazBilgileriKutuphanesi>();
	 
	 /**
	  * Bulunan bluetooth cihazýnýn rssi deðerinin tutulduðu deðiþkendir.
	  */
	 static float f_rssi;

	 
	 	/********************************************************************************************
		 * 
		 * FONKSÝYON ADI: 				IM_MacAdresleriniAta </br> </br>
		 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile belirlenen mac adresleri listeye eklenerek
		 * loglama sýrasýnda kullanýlmak üzere kaydedilmektedir. </br> </br>
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
		 * FONKSÝYON ADI: 				BroadcastReceiver </br> </br>
		 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile bluetooth taramasý yapýlmakta ve tarama sonuçlarý listeye eklenmektedir. </br> </br>
		 * <!--
		 * ERÝÞÝM: Public </br> </br>
		 * <!--
		 * PARAMETRELER:
	     * 				ADI							TÝPÝ				AÇIKLAMASI
		 * DÖNÜÞ:	
	     * 				ADI							TÝPÝ				AÇIKLAMASI			
	     * -->
		 * GEREKLÝLÝK: 
		 *
		*********************************************************************************************/
	  final static BroadcastReceiver b_alici = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		    	/**
		   	     * Yeni bluetooth bulunduðunda gelen action stringdir.
		   	     */
		    	String str_durum = intent.getAction();
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(str_durum)) {
		             
		        	/**
			   	     * Bulunan bluetooth cihazýdýr.
			   	     */
		        	 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        	 
		        	 f_rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
		        	 
		        	 if(liste_bluetooth_mac_adresleri.contains(device.getAddress()))
		        	 {
		        		 liste_bluetooth.add(new IM_CihazBilgileriKutuphanesi(device.getAddress(),f_rssi,device.getName()));
		        	 }
		        	
		        }
		    }
		};
}		

