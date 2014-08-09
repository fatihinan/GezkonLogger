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
