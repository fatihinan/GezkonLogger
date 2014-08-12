package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;
import android.net.wifi.ScanResult;
import android.widget.Toast;

public class Wifi_Kutuphanesi {

	
	/**
	 * Bir �l��m boyunca wifi bilgilerinin tutulaca�� listelerdir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 */
    List<ScanResult> wifi_liste;
    static List<Cihaz_Kutuphanesi> cihaz_bilgiler_guncel = new ArrayList<Cihaz_Kutuphanesi>();
    static List<String> liste_mac_adresleri_toplam = new ArrayList<String>();

	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				WifiTaramaBaslat </br> </br>
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
	public void WifiTaramaBaslat()
	  {
	    wifi_liste = MainActivity.wifi_yonetici.getScanResults();
		for(int i = 0; i < wifi_liste.size(); i++)
		{
			cihaz_bilgiler_guncel.add(new Cihaz_Kutuphanesi(wifi_liste.get(i).BSSID,(float) wifi_liste.get(i).level, wifi_liste.get(i).SSID));
			
			if(!liste_mac_adresleri_toplam.contains(wifi_liste.get(i).BSSID))
			{
				liste_mac_adresleri_toplam.add(wifi_liste.get(i).BSSID);
			}
		}
	     //Toast.makeText(MainActivity.icerik,String.valueOf( wifi_liste.get(2).level), Toast.LENGTH_LONG).show();
	     wifi_liste.clear();
	     MainActivity.wifi_yonetici.startScan();
	  }
}
