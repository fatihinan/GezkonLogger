/*******************************************************************************
 * IM_IvmeolcerKutuphanesi.java
 *
 * AMA�:
 * IM_IvmeolcerKutuphanesi ile x, y ve z y�nlerindeki g�ncel ivme�l�er verilerinin al�nmas� hedeflenmektedir.
 *
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * x y�n�ndeki g�ncel ivme de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_ivmeolcer_x             
 * 
 * y y�n�ndeki g�ncel ivme de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_ivmeolcer_y
 * 
 * z y�n�ndeki g�ncel ivme de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_ivmeolcer_z
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 *
 * Bu fonksiyon ile x y�n�ndeki g�ncel ivme de�eri al�nmaktad�r.
 * public double IM_IvmeolcerGetirX()
 * 
 * Bu fonksiyon ile y y�n�ndeki g�ncel ivme de�eri al�nmaktad�r.
 * public double IM_IvmeolcerGetirY()
 * 
 * Bu fonksiyon ile z y�n�ndeki g�ncel ivme de�eri al�nmaktad�r.
 * public double IM_IvmeolcerGetirZ()
 * 
 * Bu fonksiyon ile ivme�l�er de�erleri sistemden okunmaya ba�lamaktad�r.
 * public void IM_IvmeolcerVeriToplamaBaslat()
 * 
 * �vme de�erlerinde bir de�i�iklik oldu�unda bu fonksiyon �al��arak
 * g�ncel ivme de�erleri al�nmaktad�r.
 * public void onSensorChanged
 * 
 * �vme hassasiyetinde bir de�i�ikli oldu�unda bu fonksiyon �al��maktad�r.
 * public void onAccuracyChanged
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Fatih �NAN
 * Tarih: 09.08.2014
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

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class IM_IvmeolcerKutuphanesi implements SensorEventListener{
	
	/**
	 * x, y ve z y�nlerindeki g�ncel ivme de�erlerinin tutuldu�u de�i�kenlerdir.
	 * 
	 * �vme de�erlerinin birimi m/s^2
	 */
	double d_ivmeolcer_x;
	double d_ivmeolcer_y;
	double d_ivmeolcer_z;
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_IvmeolcerGetirX </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile x y�n�ndeki ivme de�eri al�nmaktad�r. </br> </br>
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
	public double IM_IvmeolcerGetirX()
	{
		return d_ivmeolcer_x;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_IvmeolcerGetirY </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile y y�n�ndeki ivme de�eri al�nmaktad�r. </br> </br>
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
	public double IM_IvmeolcerGetirY()
	{
		return d_ivmeolcer_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_IvmeolcerGetirZ </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile z y�n�ndeki ivme de�eri al�nmaktad�r. </br> </br>
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
	public double IM_IvmeolcerGetirZ()
	{
		return d_ivmeolcer_z;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_IvmeolcerToplamaBaslat </br> </br>
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
	public void IM_IvmeolcerVeriToplamaBaslat()
	{
		SensorManager sensor_yoneticisi_ivmeolcer = IM_AnaUygulama.sensor_yoneticisi;
		
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
