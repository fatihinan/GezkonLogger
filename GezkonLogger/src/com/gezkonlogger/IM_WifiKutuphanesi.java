package com.gezkonlogger;

import java.util.ArrayList;
import java.util.List;
import android.net.wifi.ScanResult;
import android.widget.Toast;

public class IM_WifiKutuphanesi {

	
	/**
	 * Bir ölçüm boyunca wifi bilgilerinin tutulacaðý listelerdir.
	 * 
	 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
	 */
    List<ScanResult> wifi_liste;
    static List<IM_CihazBilgileriKutuphanesi> cihaz_bilgiler_guncel = new ArrayList<IM_CihazBilgileriKutuphanesi>();
    static List<String> liste_mac_adresleri_toplam = new ArrayList<String>();

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				WifiTaramaBaslat </br> </br>
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
	public void WifiTaramaBaslat()
	  {
	    wifi_liste = IM_AnaUygulama.wifi_yonetici.getScanResults();
		for(int i = 0; i < wifi_liste.size(); i++)
		{
			cihaz_bilgiler_guncel.add(new IM_CihazBilgileriKutuphanesi(wifi_liste.get(i).BSSID,(float) wifi_liste.get(i).level, wifi_liste.get(i).SSID));
			
			if(!liste_mac_adresleri_toplam.contains(wifi_liste.get(i).BSSID))
			{
				liste_mac_adresleri_toplam.add(wifi_liste.get(i).BSSID);
			}
		}
	     //Toast.makeText(MainActivity.icerik,String.valueOf( wifi_liste.get(2).level), Toast.LENGTH_LONG).show();
	     wifi_liste.clear();
	     IM_AnaUygulama.wifi_yonetici.startScan();
	  }
}
