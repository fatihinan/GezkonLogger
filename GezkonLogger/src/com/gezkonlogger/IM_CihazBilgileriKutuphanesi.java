/*******************************************************************************
 * IM_CihazBilgileriKutuphanesi.java
 *
 *
 * AMA�:
 *
 * IM_CihazBilgileriKutuphanesi ile bulunan wifi ve bluetooth cihazlar�n�n �zelliklerinin tutulaca�� bir interface katman� olu�turulmu�tur.
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * Cihaz rssi de�erinin tutulaca�� de�i�kendir.
 * float f_rssi;           
 * 
 * 
 * Cihaz a��k isminin tutulaca�� de�i�kendir.
 * String str_ssid;
 *       
 * 
 * Cihaz mac  adresinin tutulaca�� de�i�kendir.
 * String str_adres;
 * 
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 *
 *
 * Bu fonksiyon ile cihaz bilgileri al�nmaktad�r.
 * public IM_Cihaz_Kutuphanesi(String str_adres, float f_rssi, String str_ssid)
 * 
 * 
 * Bu fonksiyon ile cihaz�n rssi de�eri al�nmaktad�r.
 * public float IM_RssiGetir() 
 * 
 * 
 * Bu fonksiyon ile cihaz�n rssi de�eri kaydedilmektedir.
 * public void IM_RssiKaydet(float f_rssi)
 * 
 * 
 * Bu fonksiyon ile cihaz�n mac adresi al�nmaktad�r.
 * public String IM_AdresGetir()
 * 
 * 
 * Bu fonksiyon ile cihaz�n mac adresi kaydedilmektedir.
 * public void IM_AdresKaydet(String str_adres)
 * 
 * 
 * Bu fonksiyon ile cihaz�n a��k ismi al�nmaktad�r.
 * public String IM_SsidGetir()
 * 
 * 
 * Bu fonksiyon ile cihaz�n a��k ismi kaydedilmektedir.
 * public void IM_SsidKaydet(String str_ssid)
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Furkan G�NER
 * Tarih: 11.08.2014
 * G�ncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 *
 ******************************************************************************/

package com.gezkonlogger;

public class IM_CihazBilgileriKutuphanesi {

   /**
   * Cihaz rssi de�erinin tutulaca�� de�i�kendir.
   */
   private float   f_rssi;
   
   /**
    * Cihaz a��k isminin tutulaca�� de�i�kendir.
    */
   private String  str_ssid;
   
   /**
    * Cihaz mac  adresinin tutulaca�� de�i�kendir.
    */
   private String  str_adres;

   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    IM_Cihaz_Kutuphanesi</br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz bilgileri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			str_adres					String		        Cihaz�n mac adresi tutulmaktad�r.
	 *			f_rssi						float				Cihaz�n rssi de�eri tutulmaktad�r.
	 *			str_ssid				    String				Cihaz�n a��k ismi tutulmaktad�r.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * GEREKL�L�K: 
	 *
	 *@param Cihaz�n mac adresi tutulmaktad�r.
	 *@param Cihaz�n rssi de�eri tutulmaktad�r.
	 *@param Cihaz�n a��k ismi tutulmaktad�r.
	*********************************************************************************************/ 
   public IM_CihazBilgileriKutuphanesi(String str_adres, float f_rssi, String str_ssid){
      super();
      this.str_adres  = str_adres;
      this.f_rssi = f_rssi;
      this.str_ssid = str_ssid;
   }
   
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    IM_RssiGetir</br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n rssi de�eri al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 *          f_rssi						float		        Cihaz�n rssi de�eri tutulmaktad�r.
	 * -->
	 * GEREKL�L�K: 
	*********************************************************************************************/ 
   public float IM_RssiGetir(){
      return f_rssi;
   }

   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    IM_RssiKaydet</br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n rssi de�eri kaydedilmektedir. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * 			f_rssi						float				Cihaz�n rssi de�eri tutulmaktad�r.
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * -->
	 * GEREKL�L�K: 
	 *@param Cihaz�n rssi de�eri tutulmaktad�r.
	*********************************************************************************************/
   public void IM_RssiKaydet(float f_rssi){
      this.f_rssi = f_rssi;
   }
   
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    IM_AdresGetir</br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n mac adresi al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			str_adres					String				Cihaz�n mac adresi tutulmaktad�r.
	 * -->
	 * GEREKL�L�K: 
	*********************************************************************************************/
   public String IM_AdresGetir(){
      return str_adres;
   }
   
   /********************************************************************************************
  	 * 
  	 * FONKS�YON ADI: 			    IM_AdresKaydet</br> </br>
  	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n mac adresi kaydedilmektedir. </br> </br>
  	 *
  	 * ER���M: Public </br> </br>
  	 * <!--
  	 * PARAMETRELER:
  	 * 			ADI							T�P�				A�IKLAMASI
  	 * 			f_rssi						float				Cihaz�n mac adresi tutulmaktad�r.
  	 * D�N��:	
  	 * 			ADI							T�P�				A�IKLAMASI	
  	 * -->
  	 * GEREKL�L�K: 
  	 *@param Cihaz�n mac adresi tutulmaktad�r.
  	*********************************************************************************************/  
   public void IM_AdresKaydet(String str_adres){
      this.str_adres = str_adres;
   }
   
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    IM_SsidGetir</br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n a��k ismi al�nmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI	
	 * 			str_ssid					String				Cihaz�n a��k ismi tutulmaktad�r.
	 * -->
	 * GEREKL�L�K: 
	*********************************************************************************************/ 
   public String IM_SsidGetir(){
	  return str_ssid;
   }
   
   /********************************************************************************************
 	 * 
 	 * FONKS�YON ADI: 			    IM_SsidKaydet</br> </br>
 	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile cihaz�n a��k ismi kaydedilmektedir. </br> </br>
 	 *
 	 * ER���M: Public </br> </br>
 	 * <!--
 	 * PARAMETRELER:
 	 * 			ADI							T�P�				A�IKLAMASI
 	 * 			str_ssid				    String				Cihaz�n a��k ismi tutulmaktad�r.
 	 * D�N��:	
 	 * 			ADI							T�P�				A�IKLAMASI	
 	 * -->
 	 * GEREKL�L�K: 
 	 *@param Cihaz�n a��k ismi tutulmaktad�r.
 	*********************************************************************************************/
   public void IM_SsidKaydet(String str_ssid){
	  this.str_ssid = str_ssid;
   }
   
}


