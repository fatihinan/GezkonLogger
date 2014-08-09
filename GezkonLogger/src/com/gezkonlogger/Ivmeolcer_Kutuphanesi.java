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
