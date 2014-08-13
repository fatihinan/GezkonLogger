/*******************************************************************************
 * ImKAB_AStarKutuphanesi.java
 *
 *
 * AMAÇ:
 *
 * ImKAB_AStarKutuphanesi ile navigasyon ekraný içerisinde rota planlama iþlemleri gerçekleþtirilmektedir.
 * Bu kütüphane ile baþlangýç ve bitiþ düðümleri arasýndaki en kýsa yol A* algoritmasýna göre hesaplanmaktadýr.
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Graph'da bulunan düðüm sayýsýný belirtir.
 * Düðüm sayýsý kroki dosyasýný atrýþtýran kütüphaneden alýnmýþtýr. 
 * int i_dugum_sayisi              
 * 
 * 
 * Graph'da bulunan noktalarýn iliþki matrisinin tutulduðu deðiþken oluþturulmuþtur.
 * Eðer iki düðüm arasýnda iliþki varsa 1, yoksa -1 deðeri atanacaktýr.
 * int[][] i_komsuluk_matrisi 
 *       
 * 
 * A Star algoritmasýnýn çalýþmasýný saðlayan açýk listedir.
 * Açýk listede kontrol edilecek düðümler bulunur.
 * ArrayList<Dugum> acik_liste		
 * 
 * 
 * A Star algoritmasýnýn çalýþmasýný saðlayan kapalý listedir.
 * Kapalý listede kontrol edilmiþ düðümler bulunur.
 * ArrayList<Dugum> kapali_liste    
 * 
 * 
 * Aralarýnda baðlantý olan düðümler arasýndaki ayrýt özelliklerinin tutulduðu matris oluþturulmuþtur.
 * Ayrit[][] ayrit_matrisi          
 * 
 * 
 * Graph'daki düðümlerin tutulacaðý dizidir.
 * Dugum[] dugumler					
 * 
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 *
 * Bu fonksiyon ile komþu olan düðümler arasýndaki deðerler 1,
 * komþu olmayan düðümler arasýndaki deðerler -1 olarak atanmýþtýr. 
 * Krokide bulunan nokta sayýsý kadar Düðüm oluþturulmuþ ve ilk deðerleri atanmýþtýr.
 * Dosyadan okunan, düðüme ait özellik, x koordinatý ve y koordinatý bilgileri düðümlere atanmýþtýr.
 * public void Im_DugumVeKomsulukMatrisiOlustur()
 * 
 * 
 * Parametre olarak aldýðý noktalar arasýndaki uzaklýðý hesaplayan fonksiyondur.
 * public double Im_IkiNoktaArasindakiUzaklik( int Dugum1, int Dugum2)
 * 
 * 
 * Açýk listedeki en küçük d_toplam_maaliyet deðerine sahip düðümü bulup, numarasýný döndüren fonksiyondur.
 * public int Im_ListedekiEnkucukToplamMaaliyet()
 * 
 * 
 * Bu fonksiyon ile parametre olarak numarasýsý girilen düðümün komþularý açýk listeye eklenmektedir.
 * public void Im_KomsulariAcikListeyeEkle(int dugum)
 * 
 * 
 * Açýk listedeki düðümlerin toplam maaliyet deðerlerini hesaplayan fonksiyondur.
 * Burada toplam maaliyet deðeri  toplam maaliyet = mevcut maaliyet + sezgisel maaliyet denklemine göre hesaplanýr.
 * public void Im_ToplamMaaliyetiGuncelle()
 * 
 * 
 * Bu fonksiyon en kýsa yolu hesaplayan ve yol üzerindeki düðümlerin 
 * numaralarýný string olarak döndüren fonksiyondur.
 * public String Im_EnKisaYolBulma(int baslangic, int bitis) 
 * 
 * 
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Fatih ÝNAN
 * Tarih: 16.09.2013
 * Güncelleme Tarihi: 29.11.2013
 * Versiyon: v3.2
 *
 ******************************************************************************/

package com.gezkonlogger;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Ivmeolcer_Kutuphanesi implements SensorEventListener{
	
	/**
	 * x, y ve z yönlerindeki ivme deðerlerinin tutulduðu deðiþkenlerdir.
	 */
	double d_ivmeolcer_x;
	double d_ivmeolcer_y;
	double d_ivmeolcer_z;
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IvmeolcerGetirX </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile x yönendeki ivme deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_ivmeolcer_x				double				x yönendeki ivme deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirX()
	{
		return d_ivmeolcer_x;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IvmeolcerGetirY </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile y yönendeki ivme deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_ivmeolcer_y				double				y yönendeki ivme deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirY()
	{
		return d_ivmeolcer_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IvmeolcerGetirZ </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile z yönendeki ivme deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_ivmeolcer_x				double				z yönendeki ivme deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirZ()
	{
		return d_ivmeolcer_z;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				Baslat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile ivmeölçer deðerleri sistemden okunmaya baþlamaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * -->
	 * 
	*********************************************************************************************/
	public void Baslat()
	{
		SensorManager sensor_yoneticisi_ivmeolcer = MainActivity.sensor_yoneticisi;
		
		sensor_yoneticisi_ivmeolcer.registerListener(this,
				sensor_yoneticisi_ivmeolcer.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
	}

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				onSensorChanged </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Ývme deðerlerinde bir deðiþiklik olduðunda bu fonksiyon çalýþarak
	 * güncel ivme deðerleri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * -->
	 * 
	*********************************************************************************************/
	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		// TODO Auto-generated method stub
		synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) 
            {
            	d_ivmeolcer_x = sensorEvent.values[0];
            	d_ivmeolcer_y = sensorEvent.values[1];
            	d_ivmeolcer_z = sensorEvent.values[2];
            }
        }
	}

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				onAccuracyChanged </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Ývme hassasiyetinde bir deðiþikli olduðunda bu fonksiyon çalýþmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * -->
	 * 
	*********************************************************************************************/
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
