package com.gezkonlogger;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Ivmeolcer_Kutuphanesi implements SensorEventListener{
	
	double d_ivmeolcer_x;
	double d_ivmeolcer_y;
	double d_ivmeolcer_z;
	
	public double IvmeolcerGetirX()
	{
		return d_ivmeolcer_x;
	}
	public double IvmeolcerGetirY()
	{
		return d_ivmeolcer_y;
	}
	public double IvmeolcerGetirZ()
	{
		return d_ivmeolcer_z;
	}
	
	public void Baslat()
	{
		SensorManager sensor_yoneticisi_ivmeolcer = MainActivity.sensor_yoneticisi;
		
		sensor_yoneticisi_ivmeolcer.registerListener(this,
				sensor_yoneticisi_ivmeolcer.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
	}

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
	
	public String IvmeolcerAl()
    {
    	return "X:"+Double.toString(d_ivmeolcer_x) + 
    			" Y:" + Double.toString(d_ivmeolcer_y) +
    			" Z:" + Double.toString(d_ivmeolcer_z);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
