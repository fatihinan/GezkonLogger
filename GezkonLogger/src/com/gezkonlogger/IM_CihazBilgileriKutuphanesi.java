/*******************************************************************************
 * IM_CihazBilgileriKutuphanesi.java
 *
 *
 * AMAÇ:
 *
 * IM_CihazBilgileriKutuphanesi ile bulunan wifi ve bluetooth cihazlarýnýn özelliklerinin tutulacaðý bir interface katmaný oluþturulmuþtur.
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Cihaz rssi deðerinin tutulacaðý deðiþkendir.
 * float f_rssi;           
 * 
 * 
 * Cihaz açýk isminin tutulacaðý deðiþkendir.
 * String str_ssid;
 *       
 * 
 * Cihaz mac  adresinin tutulacaðý deðiþkendir.
 * String str_adres;
 * 
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 *
 * Bu fonksiyon ile cihaz bilgileri alýnmaktadýr.
 * public IM_Cihaz_Kutuphanesi(String str_adres, float f_rssi, String str_ssid)
 * 
 * 
 * Bu fonksiyon ile cihazýn rssi deðeri alýnmaktadýr.
 * public float IM_RssiGetir() 
 * 
 * 
 * Bu fonksiyon ile cihazýn rssi deðeri kaydedilmektedir.
 * public void IM_RssiKaydet(float f_rssi)
 * 
 * 
 * Bu fonksiyon ile cihazýn mac adresi alýnmaktadýr.
 * public String IM_AdresGetir()
 * 
 * 
 * Bu fonksiyon ile cihazýn mac adresi kaydedilmektedir.
 * public void IM_AdresKaydet(String str_adres)
 * 
 * 
 * Bu fonksiyon ile cihazýn açýk ismi alýnmaktadýr.
 * public String IM_SsidGetir()
 * 
 * 
 * Bu fonksiyon ile cihazýn açýk ismi kaydedilmektedir.
 * public void IM_SsidKaydet(String str_ssid)
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Furkan GÜNER
 * Tarih: 11.08.2014
 * Güncelleme Tarihi: 13.08.2014
 * Versiyon: v1.1
 *
 ******************************************************************************/

package com.gezkonlogger;

public class IM_CihazBilgileriKutuphanesi {

   /**
   * Cihaz rssi deðerinin tutulacaðý deðiþkendir.
   */
   private float   f_rssi;
   
   /**
    * Cihaz açýk isminin tutulacaðý deðiþkendir.
    */
   private String  str_ssid;
   
   /**
    * Cihaz mac  adresinin tutulacaðý deðiþkendir.
    */
   private String  str_adres;

   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    IM_Cihaz_Kutuphanesi</br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihaz bilgileri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_adres					String		        Cihazýn mac adresi tutulmaktadýr.
	 *			f_rssi						float				Cihazýn rssi deðeri tutulmaktadýr.
	 *			str_ssid				    String				Cihazýn açýk ismi tutulmaktadýr.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: 
	 *
	 *@param Cihazýn mac adresi tutulmaktadýr.
	 *@param Cihazýn rssi deðeri tutulmaktadýr.
	 *@param Cihazýn açýk ismi tutulmaktadýr.
	*********************************************************************************************/ 
   public IM_CihazBilgileriKutuphanesi(String str_adres, float f_rssi, String str_ssid){
      super();
      this.str_adres  = str_adres;
      this.f_rssi = f_rssi;
      this.str_ssid = str_ssid;
   }
   
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    IM_RssiGetir</br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn rssi deðeri alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 *          f_rssi						float		        Cihazýn rssi deðeri tutulmaktadýr.
	 * -->
	 * GEREKLÝLÝK: 
	*********************************************************************************************/ 
   public float IM_RssiGetir(){
      return f_rssi;
   }

   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    IM_RssiKaydet</br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn rssi deðeri kaydedilmektedir. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * 			f_rssi						float				Cihazýn rssi deðeri tutulmaktadýr.
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * -->
	 * GEREKLÝLÝK: 
	 *@param Cihazýn rssi deðeri tutulmaktadýr.
	*********************************************************************************************/
   public void IM_RssiKaydet(float f_rssi){
      this.f_rssi = f_rssi;
   }
   
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    IM_AdresGetir</br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn mac adresi alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			str_adres					String				Cihazýn mac adresi tutulmaktadýr.
	 * -->
	 * GEREKLÝLÝK: 
	*********************************************************************************************/
   public String IM_AdresGetir(){
      return str_adres;
   }
   
   /********************************************************************************************
  	 * 
  	 * FONKSÝYON ADI: 			    IM_AdresKaydet</br> </br>
  	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn mac adresi kaydedilmektedir. </br> </br>
  	 *
  	 * ERÝÞÝM: Public </br> </br>
  	 * <!--
  	 * PARAMETRELER:
  	 * 			ADI							TÝPÝ				AÇIKLAMASI
  	 * 			f_rssi						float				Cihazýn mac adresi tutulmaktadýr.
  	 * DÖNÜÞ:	
  	 * 			ADI							TÝPÝ				AÇIKLAMASI	
  	 * -->
  	 * GEREKLÝLÝK: 
  	 *@param Cihazýn mac adresi tutulmaktadýr.
  	*********************************************************************************************/  
   public void IM_AdresKaydet(String str_adres){
      this.str_adres = str_adres;
   }
   
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    IM_SsidGetir</br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn açýk ismi alýnmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			str_ssid					String				Cihazýn açýk ismi tutulmaktadýr.
	 * -->
	 * GEREKLÝLÝK: 
	*********************************************************************************************/ 
   public String IM_SsidGetir(){
	  return str_ssid;
   }
   
   /********************************************************************************************
 	 * 
 	 * FONKSÝYON ADI: 			    IM_SsidKaydet</br> </br>
 	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile cihazýn açýk ismi kaydedilmektedir. </br> </br>
 	 *
 	 * ERÝÞÝM: Public </br> </br>
 	 * <!--
 	 * PARAMETRELER:
 	 * 			ADI							TÝPÝ				AÇIKLAMASI
 	 * 			str_ssid				    String				Cihazýn açýk ismi tutulmaktadýr.
 	 * DÖNÜÞ:	
 	 * 			ADI							TÝPÝ				AÇIKLAMASI	
 	 * -->
 	 * GEREKLÝLÝK: 
 	 *@param Cihazýn açýk ismi tutulmaktadýr.
 	*********************************************************************************************/
   public void IM_SsidKaydet(String str_ssid){
	  this.str_ssid = str_ssid;
   }
   
}


