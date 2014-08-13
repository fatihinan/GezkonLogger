/*******************************************************************************
 * IM_ManyetikAlanKutuphanesi.java
 *
 * AMAÇ:
 * IM_ManyetikAlanKutuphanesi ile x, y ve z yönlerindeki güncel manyetik alan verilerinin alýnmasý hedeflenmektedir.
 *
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * x yönündeki güncel manyetik alan deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_manyetik_alan_x             
 * 
 * y yönündeki güncel manyetik alan deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_manyetik_alan_y
 * 
 * z yönündeki güncel manyetik alan deðerlerinin tutulduðu deðiþkenlerdir.
 * double d_manyetik_alan_z
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 * Bu fonksiyon ile x yönündeki güncel manyetik alan deðeri alýnmaktadýr.
 * public double IM_ManyetikAlanGetirX()
 * 
 * Bu fonksiyon ile y yönündeki güncel manyetik alan deðeri alýnmaktadýr.
 * public double IM_ManyetikAlanGetirY()
 * 
 * Bu fonksiyon ile z yönündeki güncel manyetik alan deðeri alýnmaktadýr.
 * public double IM_ManyetikAlanGetirZ()
 * 
 * Bu fonksiyon ile manyetik alan deðerleri sistemden okunmaya baþlamaktadýr.
 * public void IM_ManyetikAlanVeriToplamaBaslat()
 * 
 * Manyetik alan deðerlerinde bir deðiþiklik olduðunda bu fonksiyon çalýþarak
 * güncel manyetik alan deðerleri alýnmaktadýr.
 * public void onSensorChanged
 * 
 * Manyetik alan hassasiyetinde bir deðiþikli olduðunda bu fonksiyon çalýþmaktadýr.
 * public void onAccuracyChanged
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Fatih ÝNAN
 * Tarih: 09.08.2014
 * Güncelleme Tarihi: 13.08.2014
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
	 * x, y ve z yönlerindeki manyetik alan deðerlerinin tutulduðu deðiþkenlerdir.
	 */
	double d_manyetik_alan_x;
	double d_manyetik_alan_y;
	double d_manyetik_alan_z;

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ManyetikAlanGetirX </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile x yönündeki güncel manyetik alan deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_manyetik_alan_x			double				x yönendeki manyetik alan deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirX()
	{
		return d_manyetik_alan_x;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ManyetikAlanGetirY </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile y yönündeki güncel manyetik alan deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_manyetik_alan_y			double				y yönendeki manyetik alan deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirY()
	{
		return d_manyetik_alan_y;
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ManyetikAlanGetirZ </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile z yönündeki güncel manyetik alan deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			d_manyetik_alan_z			double				z yönendeki manyetik alan deðeridir.
	 * -->
	 * 
	*********************************************************************************************/
	public double IM_ManyetikAlanGetirZ()
	{
		return d_manyetik_alan_z;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ManyetikAlanVeriToplamaBaslat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile manyetik alan deðerleri sistemden okunmaya baþlamaktadýr. </br> </br>
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
    public void IM_ManyetikAlanVeriToplamaBaslat() 
    {
    	SensorManager sensor_yoneticisi_manyetik_alan = IM_AnaUygulama.sensor_yoneticisi;

    	sensor_yoneticisi_manyetik_alan.registerListener(this,
    			sensor_yoneticisi_manyetik_alan.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				onSensorChanged </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Manyetik alan deðerlerinde bir deðiþiklik olduðunda bu fonksiyon çalýþarak
	 * güncel deðerleri alýnmaktadýr. </br> </br>
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
	 * FONKSÝYON ADI: 				onAccuracyChanged </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Manyetik alan hassasiyetinde bir deðiþikli olduðunda bu fonksiyon çalýþmaktadýr. </br> </br>
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