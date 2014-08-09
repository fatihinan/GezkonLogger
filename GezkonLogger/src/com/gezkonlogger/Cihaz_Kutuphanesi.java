package com.gezkonlogger;

public class Cihaz_Kutuphanesi {

   /**
   * Cihaz rssi deðerinin tutulacaðý deðiþkendir.
   * 
   * Cihaz açýk isminin tutulacaðý deðiþkendir.
   *  
   * Cihaz mac  adresinin tutulacaðý deðiþkendir.
   */
   private float             f_rssi;
   private String            str_ssid;
   private String            str_adres;

   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    Cihaz_Kutuphanesi</br> </br>
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
   public Cihaz_Kutuphanesi(String str_adres, float f_rssi, String str_ssid){
      super();
      this.str_adres  = str_adres;
      this.f_rssi = f_rssi;
      this.str_ssid = str_ssid;
   }
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    RssiGetir</br> </br>
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
   public float RssiGetir(){
      return f_rssi;
   }

   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    RssiKaydet</br> </br>
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
   public void RssiKaydet(float f_rssi){
      this.f_rssi = f_rssi;
   }
   
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    AdresGetir</br> </br>
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
   public String AdresGetir(){
      return str_adres;
   }
   /********************************************************************************************
  	 * 
  	 * FONKSÝYON ADI: 			    AdresKaydet</br> </br>
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
   public void AdresKaydet(String str_adres){
      this.str_adres = str_adres;
   }
   
   /********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 			    SsidGetir</br> </br>
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
   public String SsidGetir(){
	  return str_ssid;
   }
   /********************************************************************************************
 	 * 
 	 * FONKSÝYON ADI: 			    SsidKaydet</br> </br>
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
   public void SsidKaydet(String str_ssid){
	  this.str_ssid = str_ssid;
   }
   
}


