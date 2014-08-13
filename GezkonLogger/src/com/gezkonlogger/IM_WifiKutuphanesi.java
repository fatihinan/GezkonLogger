/*******************************************************************************
 * IM_WifiKutuphanesi.java
 *
 *
 * AMAÇ:
 *
 * IM_WifiKutuphanesi ile ortamda bulunan wifi noktalarý bulunmakta ve özellikleri listelere eklenmektedir.
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Bir ölçüm boyunca wifi noktasýnýn (rss,ssid vb.) bilgilerinin tutulacaðý listedir.
 * List<ScanResult> liste_wifi;           
 * 
 * 
 * Bir ölçüm boyunca bulunan wifi noktalarýnýn tutulacaðý listedir. Her ölçümde güncellenmektedir.
 * static List<Cihaz_Kutuphanesi> cihaz_bilgiler_guncel
 *       
 * 
 * Programda bulunan tüm mac adreslerinin tutulacaðý listedir.
 * static List<String> liste_mac_adresleri_toplam 	
 * 
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 *
 * Bu fonksiyon ile wifi taramasý baþlatýlmakta ve tarama sonuçlarý listeye eklenmektir.
 * public void IM_WifiTaramaBaslat()
 * 
 * 
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Furkan GÜNER
 * Tarih: 11.08.2014
 * Güncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 *
 ******************************************************************************/

package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;
import android.net.wifi.ScanResult;

public class IM_WifiKutuphanesi {

	
	/**
	 * Bir ölçüm boyunca wifi noktasýnýn (rss,ssid vb.) bilgilerinin tutulacaðý listedir.
	 */
    List<ScanResult> liste_wifi;
    
    /**
	 * Bir ölçüm boyunca bulunan wifi noktalarýnýn tutulacaðý listedir. Her ölçümde güncellenmektedir.
	 */
    static List<IM_CihazBilgileriKutuphanesi> cihaz_bilgiler_guncel = new ArrayList<IM_CihazBilgileriKutuphanesi>();
    
    /**
   	 * Programda bulunan tüm mac adreslerinin tutulacaðý listedir.
   	 */
    static List<String> liste_mac_adresleri_toplam = new ArrayList<String>();

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_WifiTaramaBaslat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile wifi taramasý yapýlmakta ve tarama sonuçlarý listeye yüklenmektedir. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 				ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 				ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Wifi açýk olmasý gerekmektedir.
	 *
	*********************************************************************************************/
	public void IM_WifiTaramaBaslat()
	  {
		
	    liste_wifi = IM_AnaUygulama.wifi_yonetici.getScanResults();
		for(int i = 0; i < liste_wifi.size(); i++)
		{
			cihaz_bilgiler_guncel.add(new IM_CihazBilgileriKutuphanesi(liste_wifi.get(i).BSSID,(float) liste_wifi.get(i).level, liste_wifi.get(i).SSID));
			
			if(!liste_mac_adresleri_toplam.contains(liste_wifi.get(i).BSSID))
			{
				liste_mac_adresleri_toplam.add(liste_wifi.get(i).BSSID);
			}
		}
	     //Toast.makeText(MainActivity.icerik,String.valueOf( wifi_liste.get(2).level), Toast.LENGTH_LONG).show();
		 liste_wifi.clear();
		 IM_AnaUygulama.wifi_yonetici.startScan();
	     
	  }
}
