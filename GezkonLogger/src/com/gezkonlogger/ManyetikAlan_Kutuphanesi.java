package com.gezkonlogger;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ManyetikAlan_Kutuphanesi implements SensorEventListener {

	double d_manyetik_alan_x;
	double d_manyetik_alan_y;
	double d_manyetik_alan_z;

	public double ManyetikAlanGetirX()
	{
		return d_manyetik_alan_x;
	}
	public double ManyetikAlanGetirY()
	{
		return d_manyetik_alan_y;
	}
	public double ManyetikAlanGetirZ()
	{
		return d_manyetik_alan_z;
	}
	
    public void Baslat() 
    {
    	SensorManager sensor_yoneticisi_manyetik_alan = MainActivity.sensor_yoneticisi;

        // Register magnetic sensor
    	sensor_yoneticisi_manyetik_alan.registerListener(this,
    			sensor_yoneticisi_manyetik_alan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }


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
    
    public String ManyetikAlanAl()
    {
    	return "X:"+Double.toString(d_manyetik_alan_x) + 
    			" Y:" + Double.toString(d_manyetik_alan_y) +
    			" Z:" + Double.toString(d_manyetik_alan_z);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}