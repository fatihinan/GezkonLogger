/*******************************************************************************
 * IM_WifiKutuphanesi.java
 *
 *
 * AMA�:
 *
 * IM_WifiKutuphanesi ile ortamda bulunan wifi noktalar� bulunmakta ve �zellikleri listelere eklenmektedir.
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * Bir �l��m boyunca wifi noktas�n�n (rss,ssid vb.) bilgilerinin tutulaca�� listedir.
 * List<ScanResult> liste_wifi;           
 * 
 * 
 * Bir �l��m boyunca bulunan wifi noktalar�n�n tutulaca�� listedir. Her �l��mde g�ncellenmektedir.3
 * cihaz_bilgiler_guncel IM_AnaUygulama taraf�ndan loglama i�lemi s�ras�nda wifi rssi verilerinin 
 * okunmas� i�leminde kullan�laca�� i�in static olarak tan�mlanm��t�r.
 * static List<Cihaz_Kutuphanesi> cihaz_bilgiler_guncel
 *       
 * 
 * Programda bulunan t�m mac adreslerinin tutulaca�� listedir.
 * liste_mac_adresleri_toplam IM_AnaUygulama taraf�ndan rssi verilerinin mac adreslerine g�re s�ralanmas� 
 * i�leminde kullan�laca��ndan dolay� static olarak tan�mlanm��t�r
 * static List<String> liste_mac_adresleri_toplam 	
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 *
 * Bu fonksiyon ile wifi taramas� ba�lat�lmakta ve tarama sonu�lar� listeye eklenmektir.
 * public void IM_WifiTaramaBaslat()
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
import android.net.wifi.ScanResult;

public class IM_WifiKutuphanesi {

	
	/**
	 * Bir �l��m boyunca wifi noktas�n�n (rss,ssid vb.) bilgilerinin tutulaca�� listedir.
	 */
    List<ScanResult> liste_wifi;
    
    /**
	 * Bir �l��m boyunca bulunan wifi noktalar�n�n tutulaca�� listedir. Her �l��mde g�ncellenmektedir.
	 * 
	 * cihaz_bilgiler_guncel IM_AnaUygulama taraf�ndan loglama i�lemi s�ras�nda wifi rssi verilerinin 
	 * 
	 * okunmas� i�leminde kullan�laca�� i�in static olarak tan�mlanm��t�r.
	 */
    static List<IM_CihazBilgileriKutuphanesi> cihaz_bilgiler_guncel = new ArrayList<IM_CihazBilgileriKutuphanesi>();
    
    /**
   	 * Programda bulunan t�m mac adreslerinin tutulaca�� listedir.
   	 * 
   	 * liste_mac_adresleri_toplam IM_AnaUygulama taraf�ndan rssi verilerinin mac adreslerine g�re s�ralanmas� 
	 * 
	 * i�leminde kullan�laca��ndan dolay� static olarak tan�mlanm��t�r.
   	 */
    static List<String> liste_mac_adresleri_toplam = new ArrayList<String>();

	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_WifiTaramaBaslat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile wifi taramas� yap�lmakta ve tarama sonu�lar� listeye y�klenmektedir. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 				ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 				ADI							T�P�				A�IKLAMASI			
	 * -->
	 * GEREKL�L�K: Wifi a��k olmas� gerekmektedir.
	 *
	*********************************************************************************************/
	public void IM_WifiTaramaBaslat()
	  {
		
	    liste_wifi = IM_AnaUygulama.wifi_yonetici.getScanResults();
	    
		for(int i = 0; i < liste_wifi.size(); i++)
		{
			cihaz_bilgiler_guncel.add(new IM_CihazBilgileriKutuphanesi(liste_wifi.get(i).BSSID + "(" + liste_wifi.get(i).SSID + ")",(float) liste_wifi.get(i).level, liste_wifi.get(i).SSID));
			
			if(!liste_mac_adresleri_toplam.contains(liste_wifi.get(i).BSSID + "(" + liste_wifi.get(i).SSID + ")"))
			{
				liste_mac_adresleri_toplam.add(liste_wifi.get(i).BSSID + "(" + liste_wifi.get(i).SSID + ")");
			}
		}
	     
		 liste_wifi.clear();
		 IM_AnaUygulama.wifi_yonetici.startScan();
	     
	  }
}
