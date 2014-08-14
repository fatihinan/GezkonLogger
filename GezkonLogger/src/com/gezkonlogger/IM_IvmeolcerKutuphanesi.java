/*******************************************************************************
 * IM_IvmeolcerKutuphanesi.java
 *
 * AMAÇ:
 * IM_IvmeolcerKutuphanesi ile x, y ve z yönlerindeki güncel ivmeölçer verilerinin alýnmasý hedeflenmektedir.
 *
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * x yönündeki güncel ivme deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_ivmeolcer_x             
 * 
 * y yönündeki güncel ivme deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_ivmeolcer_y
 * 
 * z yönündeki güncel ivme deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_ivmeolcer_z
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 * Bu fonksiyon ile x yönündeki güncel ivme deðeri alýnmaktadýr.
 * public double IM_IvmeolcerGetirX()
 * 
 * Bu fonksiyon ile y yönündeki güncel ivme deðeri alýnmaktadýr.
 * public double IM_IvmeolcerGetirY()
 * 
 * Bu fonksiyon ile z yönündeki güncel ivme deðeri alýnmaktadýr.
 * public double IM_IvmeolcerGetirZ()
 * 
 * Bu fonksiyon ile ivmeölçer deðerleri sistemden okunmaya baþlamaktadýr.
 * public void IM_IvmeolcerVeriToplamaBaslat()
 * 
 * Ývme deðerlerinde bir deðiþiklik olduðunda bu fonksiyon çalýþarak
 * güncel ivme deðerleri alýnmaktadýr.
 * public void onSensorChanged
 * 
 * Ývme hassasiyetinde bir deðiþikli olduðunda bu fonksiyon çalýþmaktadýr.
 * public void onAccuracyChanged
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Fatih ÝNAN
 * Tarih: 09.08.2014
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

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class IM_IvmeolcerKutuphanesi implements SensorEventListener{
	
	/**
	 * x, y ve z yönlerindeki güncel ivme deðerlerinin tutulduðu deðiþkenlerdir.
	 * 
	 * Ývme deðerlerinin birimi m/s^2
	 */
	double d_ivmeolcer_x;
	double d_ivmeolcer_y;
	double d_ivmeolcer_z;
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_IvmeolcerGetirX </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile x yönündeki ivme deðeri alýnmaktadýr. </br> </br>
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
	public double IM_IvmeolcerGetirX()
	{
		return d_ivmeolcer_x;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_IvmeolcerGetirY </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile y yönündeki ivme deðeri alýnmaktadýr. </br> </br>
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
	public double IM_IvmeolcerGetirY()
	{
		return d_ivmeolcer_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_IvmeolcerGetirZ </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile z yönündeki ivme deðeri alýnmaktadýr. </br> </br>
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
	public double IM_IvmeolcerGetirZ()
	{
		return d_ivmeolcer_z;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_IvmeolcerToplamaBaslat </br> </br>
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
	public void IM_IvmeolcerVeriToplamaBaslat()
	{
		SensorManager sensor_yoneticisi_ivmeolcer = IM_AnaUygulama.sensor_yoneticisi;
		
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
