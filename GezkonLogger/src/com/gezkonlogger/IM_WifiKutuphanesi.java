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
 * Bir ölçüm boyunca bulunan wifi noktalarýnýn tutulacaðý listedir. Her ölçümde güncellenmektedir.3
 * cihaz_bilgiler_guncel IM_AnaUygulama tarafýndan loglama iþlemi sýrasýnda wifi rssi verilerinin 
 * okunmasý iþleminde kullanýlacaðý için static olarak tanýmlanmýþtýr.
 * static List<Cihaz_Kutuphanesi> cihaz_bilgiler_guncel
 *       
 * 
 * Programda bulunan tüm mac adreslerinin tutulacaðý listedir.
 * liste_mac_adresleri_toplam IM_AnaUygulama tarafýndan rssi verilerinin mac adreslerine göre sýralanmasý 
 * iþleminde kullanýlacaðýndan dolayý static olarak tanýmlanmýþtýr
 * static List<String> liste_mac_adresleri_toplam 	
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 * Bu fonksiyon ile wifi taramasý baþlatýlmakta ve tarama sonuçlarý listeye eklenmektir.
 * public void IM_WifiTaramaBaslat()
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
import android.net.wifi.ScanResult;

public class IM_WifiKutuphanesi {

	
	/**
	 * Bir ölçüm boyunca wifi noktasýnýn (rss,ssid vb.) bilgilerinin tutulacaðý listedir.
	 */
    List<ScanResult> liste_wifi;
    
    /**
	 * Bir ölçüm boyunca bulunan wifi noktalarýnýn tutulacaðý listedir. Her ölçümde güncellenmektedir.
	 * 
	 * cihaz_bilgiler_guncel IM_AnaUygulama tarafýndan loglama iþlemi sýrasýnda wifi rssi verilerinin 
	 * 
	 * okunmasý iþleminde kullanýlacaðý için static olarak tanýmlanmýþtýr.
	 */
    static List<IM_CihazBilgileriKutuphanesi> cihaz_bilgiler_guncel = new ArrayList<IM_CihazBilgileriKutuphanesi>();
    
    /**
   	 * Programda bulunan tüm mac adreslerinin tutulacaðý listedir.
   	 * 
   	 * liste_mac_adresleri_toplam IM_AnaUygulama tarafýndan rssi verilerinin mac adreslerine göre sýralanmasý 
	 * 
	 * iþleminde kullanýlacaðýndan dolayý static olarak tanýmlanmýþtýr.
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
