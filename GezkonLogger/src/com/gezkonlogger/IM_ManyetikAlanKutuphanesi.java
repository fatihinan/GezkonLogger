/*******************************************************************************
 * IM_ManyetikAlanKutuphanesi.java
 *
 * AMA�:
 * IM_ManyetikAlanKutuphanesi ile x, y ve z y�nlerindeki g�ncel manyetik alan verilerinin al�nmas� hedeflenmektedir.
 *
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * x y�n�ndeki g�ncel manyetik alan de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_manyetik_alan_x             
 * 
 * y y�n�ndeki g�ncel manyetik alan de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_manyetik_alan_y
 * 
 * z y�n�ndeki g�ncel manyetik alan de�erlerinin tutuldu�u de�i�kenlerdir.
 * double d_manyetik_alan_z
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 *
 * Bu fonksiyon ile x y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r.
 * public double IM_ManyetikAlanGetirX()
 * 
 * Bu fonksiyon ile y y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r.
 * public double IM_ManyetikAlanGetirY()
 * 
 * Bu fonksiyon ile z y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r.
 * public double IM_ManyetikAlanGetirZ()
 * 
 * Bu fonksiyon ile manyetik alan de�erleri sistemden okunmaya ba�lamaktad�r.
 * public void IM_ManyetikAlanVeriToplamaBaslat()
 * 
 * Manyetik alan de�erlerinde bir de�i�iklik oldu�unda bu fonksiyon �al��arak
 * g�ncel manyetik alan de�erleri al�nmaktad�r.
 * public void onSensorChanged
 * 
 * Manyetik alan hassasiyetinde bir de�i�ikli oldu�unda bu fonksiyon �al��maktad�r.
 * public void onAccuracyChanged
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Fatih �NAN
 * Tarih: 09.08.2014
 * G�ncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 *
 ******************************************************************************/

package com.gezkonlogger;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class IM_ManyetikAlanKutuphanesi implements SensorEventListener {

	/**
	 * x, y ve z y�nlerindeki manyetik alan de�erlerinin tutuldu�u de�i�kenlerdir.
	 */
	double d_manyetik_alan_x;
	double d_manyetik_alan_y;
	double d_manyetik_alan_z;

	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ManyetikAlanGetirX </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile x y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_manyetik_alan_x			double				x y�nendeki manyetik alan de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirX()
	{
		return d_manyetik_alan_x;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ManyetikAlanGetirY </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile y y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_manyetik_alan_y			double				y y�nendeki manyetik alan de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirY()
	{
		return d_manyetik_alan_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ManyetikAlanGetirZ </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile z y�n�ndeki g�ncel manyetik alan de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			d_manyetik_alan_z			double				z y�nendeki manyetik alan de�eridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirZ()
	{
		return d_manyetik_alan_z;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ManyetikAlanVeriToplamaBaslat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile manyetik alan de�erleri sistemden okunmaya ba�lamaktad�r. </br> </br>
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
    public void IM_ManyetikAlanVeriToplamaBaslat() 
    {
    	SensorManager sensor_yoneticisi_manyetik_alan = IM_AnaUygulama.sensor_yoneticisi;

    	sensor_yoneticisi_manyetik_alan.registerListener(this,
    			sensor_yoneticisi_manyetik_alan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				onSensorChanged </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Manyetik alan de�erlerinde bir de�i�iklik oldu�unda bu fonksiyon �al��arak
	 * g�ncel de�erleri al�nmaktad�r. </br> </br>
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
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) 
            {
            	d_manyetik_alan_x = sensorEvent.values[0];
            	d_manyetik_alan_y = sensorEvent.values[1];
            	d_manyetik_alan_z = sensorEvent.values[2];
            }
        }
    }
    
    /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				onAccuracyChanged </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Manyetik alan hassasiyetinde bir de�i�ikli oldu�unda bu fonksiyon �al��maktad�r. </br> </br>
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