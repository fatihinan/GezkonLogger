/*******************************************************************************
 * ImKAB_AStarKutuphanesi.java
 *
 *
 * AMA�:
 *
 * ImKAB_AStarKutuphanesi ile navigasyon ekran� i�erisinde rota planlama i�lemleri ger�ekle�tirilmektedir.
 * Bu k�t�phane ile ba�lang�� ve biti� d���mleri aras�ndaki en k�sa yol A* algoritmas�na g�re hesaplanmaktad�r.
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * Graph'da bulunan d���m say�s�n� belirtir.
 * D���m say�s� kroki dosyas�n� atr��t�ran k�t�phaneden al�nm��t�r. 
 * int i_dugum_sayisi              
 * 
 * 
 * Graph'da bulunan noktalar�n ili�ki matrisinin tutuldu�u de�i�ken olu�turulmu�tur.
 * E�er iki d���m aras�nda ili�ki varsa 1, yoksa -1 de�eri atanacakt�r.
 * int[][] i_komsuluk_matrisi 
 *       
 * 
 * A Star algoritmas�n�n �al��mas�n� sa�layan a��k listedir.
 * A��k listede kontrol edilecek d���mler bulunur.
 * ArrayList<Dugum> acik_liste		
 * 
 * 
 * A Star algoritmas�n�n �al��mas�n� sa�layan kapal� listedir.
 * Kapal� listede kontrol edilmi� d���mler bulunur.
 * ArrayList<Dugum> kapali_liste    
 * 
 * 
 * Aralar�nda ba�lant� olan d���mler aras�ndaki ayr�t �zelliklerinin tutuldu�u matris olu�turulmu�tur.
 * Ayrit[][] ayrit_matrisi          
 * 
 * 
 * Graph'daki d���mlerin tutulaca�� dizidir.
 * Dugum[] dugumler					
 * 
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 *
 *
 * Bu fonksiyon ile kom�u olan d���mler aras�ndaki de�erler 1,
 * kom�u olmayan d���mler aras�ndaki de�erler -1 olarak atanm��t�r. 
 * Krokide bulunan nokta say�s� kadar D���m olu�turulmu� ve ilk de�erleri atanm��t�r.
 * Dosyadan okunan, d���me ait �zellik, x koordinat� ve y koordinat� bilgileri d���mlere atanm��t�r.
 * public void Im_DugumVeKomsulukMatrisiOlustur()
 * 
 * 
 * Parametre olarak ald��� noktalar aras�ndaki uzakl��� hesaplayan fonksiyondur.
 * public double Im_IkiNoktaArasindakiUzaklik( int Dugum1, int Dugum2)
 * 
 * 
 * A��k listedeki en k���k d_toplam_maaliyet de�erine sahip d���m� bulup, numaras�n� d�nd�ren fonksiyondur.
 * public int Im_ListedekiEnkucukToplamMaaliyet()
 * 
 * 
 * Bu fonksiyon ile parametre olarak numaras�s� girilen d���m�n kom�ular� a��k listeye eklenmektedir.
 * public void Im_KomsulariAcikListeyeEkle(int dugum)
 * 
 * 
 * A��k listedeki d���mlerin toplam maaliyet de�erlerini hesaplayan fonksiyondur.
 * Burada toplam maaliyet de�eri  toplam maaliyet = mevcut maaliyet + sezgisel maaliyet denklemine g�re hesaplan�r.
 * public void Im_ToplamMaaliyetiGuncelle()
 * 
 * 
 * Bu fonksiyon en k�sa yolu hesaplayan ve yol �zerindeki d���mlerin 
 * numaralar�n� string olarak d�nd�ren fonksiyondur.
 * public String Im_EnKisaYolBulma(int baslangic, int bitis) 
 * 
 * 
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Fatih �NAN
 * Tarih: 16.09.2013
 * G�ncelleme Tarihi: 29.11.2013
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
	 * x, y ve z y�nlerindeki ivme de�erlerinin tutuldu�u de�i�kenlerdir.
	 */
	double d_ivmeolcer_x;
	double d_ivmeolcer_y;
	double d_ivmeolcer_z;
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IvmeolcerGetirX </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile x y�nendeki ivme de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_ivmeolcer_x				double				x y�nendeki ivme de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirX()
	{
		return d_ivmeolcer_x;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IvmeolcerGetirY </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile y y�nendeki ivme de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_ivmeolcer_y				double				y y�nendeki ivme de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirY()
	{
		return d_ivmeolcer_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IvmeolcerGetirZ </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile z y�nendeki ivme de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_ivmeolcer_x				double				z y�nendeki ivme de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IvmeolcerGetirZ()
	{
		return d_ivmeolcer_z;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Baslat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile ivme�l�er de�erleri sistemden okunmaya ba�lamaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
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
	 * FONKS�YON ADI: 				onSensorChanged </br> </br>
	 * FONKS�YON A�IKLAMASI: 		�vme de�erlerinde bir de�i�iklik oldu�unda bu fonksiyon �al��arak
	 * g�ncel ivme de�erleri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
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
	 * FONKS�YON ADI: 				onAccuracyChanged </br> </br>
	 * FONKS�YON A�IKLAMASI: 		�vme hassasiyetinde bir de�i�ikli oldu�unda bu fonksiyon �al��maktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * -->
	 * 
	*********************************************************************************************/
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
