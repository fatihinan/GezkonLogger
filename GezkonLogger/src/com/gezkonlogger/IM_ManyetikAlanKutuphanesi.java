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
	 * FONKS�YON ADI: 				ManyetikAlanGetirX </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile x y�nendeki manyetik alan de�eri al�nmaktad�r. </br> </br>
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
	public double ManyetikAlanGetirX()
	{
		return d_manyetik_alan_x;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				ManyetikAlanGetirY </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile y y�nendeki manyetik alan de�eri al�nmaktad�r. </br> </br>
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
	public double ManyetikAlanGetirY()
	{
		return d_manyetik_alan_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				ManyetikAlanGetirZ </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile z y�nendeki manyetik alan de�eri al�nmaktad�r. </br> </br>
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
	public double ManyetikAlanGetirZ()
	{
		return d_manyetik_alan_z;
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				Baslat </br> </br>
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
    public void Baslat() 
    {
    	SensorManager sensor_yoneticisi_manyetik_alan = IM_AnaUygulama.sensor_yoneticisi;

        // Register magnetic sensor
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