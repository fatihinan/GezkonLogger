package com.gezkonlogger;

public class Cihaz_Kutuphanesi {

   /**
   * Cihaz rssi de�erinin tutulaca�� de�i�kendir.
   * 
   * Cihaz a��k isminin tutulaca�� de�i�kendir.
   *  
   * Cihaz mac  adresinin tutulaca�� de�i�kendir.
   */
   private float             f_rssi;
   private String            str_ssid;
   private String            str_adres;

   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    Cihaz_Kutuphanesi</br> </br>
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
   public Cihaz_Kutuphanesi(String str_adres, float f_rssi, String str_ssid){
      super();
      this.str_adres  = str_adres;
      this.f_rssi = f_rssi;
      this.str_ssid = str_ssid;
   }
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    RssiGetir</br> </br>
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
   public float RssiGetir(){
      return f_rssi;
   }

   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    RssiKaydet</br> </br>
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
   public void RssiKaydet(float f_rssi){
      this.f_rssi = f_rssi;
   }
   
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    AdresGetir</br> </br>
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
   public String AdresGetir(){
      return str_adres;
   }
   /********************************************************************************************
  	 * 
  	 * FONKS�YON ADI: 			    AdresKaydet</br> </br>
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
   public void AdresKaydet(String str_adres){
      this.str_adres = str_adres;
   }
   
   /********************************************************************************************
	 * 
	 * FONKS�YON ADI: 			    SsidGetir</br> </br>
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
   public String SsidGetir(){
	  return str_ssid;
   }
   /********************************************************************************************
 	 * 
 	 * FONKS�YON ADI: 			    SsidKaydet</br> </br>
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
   public void SsidKaydet(String str_ssid){
	  this.str_ssid = str_ssid;
   }
   
}


