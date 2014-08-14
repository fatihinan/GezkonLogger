/*******************************************************************************
 * IM_ExcelIslemleriKutuphanesi.java
 *
 * AMAÇ:
 * IM_ExcelIslemleriKutuphanesi ile excel dosyasý oluþturma,
 * toplanan test verilerinin excel dosyasýna yazdýrýlmasý ve
 * excel dosyasýnýn güncellenmesi iþlemlerinin gerçekleþtirilmesi hedeflenmektedir.
 *
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Log dosyalarýnýn tutulacaðý klasörün ismini tutan deðiþkendir.
 * String str_klasor_ismi
 * 
 * Excel dosyasý üzerinde deðiþiklik yapýlamadýðý için, dosyanýn tüm içeriði öncelikle 
 * LogDosyasi_temp isimligeçici bir dosyaya aktarýlmakta, deðiþiklikler bu dosya üzerinde yapýlmaktadýr. 
 * Daha sonra geçici dosya kopyalanarak LogDosyasi ismini almaktadýr ve LogDosyasi_temp dosyasý silinmektedir.
 * String str_excel_gecici_dosya_ismi
 * 
 * Cihaz içerisinde bulunan sdcard'ýn yolunu tutan deðiþkendir.
 * File sdcard_adres
 * 
 * Excel dosyasý içerisinde sayfa oluþturmak için kullanýlacak deðiþkendir.
 * excel_sayfasi deðiþkenine sýnýf içerisindeki farklý fonksiyonlar tarafýndan eriþebilmek için 
 * static olarak tanýmlanmýþtýr.
 * static WritableSheet excel_sayfasi
 * 
 * Yeni excel dosyasý oluþturmak için kullanýlacak deðiþkendir.
 * excel_dosyasi_yeni deðiþkenine sýnýf içerisindeki farklý fonksiyonlar tarafýndan eriþebilmek için
 * static olarak tanýmlanmýþtýr.
 * static WritableWorkbook excel_dosyasi_yeni
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 *
 * Bu fonksiyon ile parametre olarak girilen klasörde 
 * parametre olarak girilen isimde excel dosyasý oluþturulmaktadýr. Dosya oluþturma iþlemi baþarýlý 
 * bir þekilde gerçekleþtirilirse true, herhangi bir hata oluþursa false deðerini döndürmektedir. 
 * public boolean IM_ExcelDosyasiOlustur(String str_klasor_ismi, String str_excel_dosya_ismi)
 *
 * Bu fonksiyon var olan bir excel dosyasýný okunabilir halde açmakta ve döndürmektedir. 
 * Parametre olarak dosyanýn bulunduðu klasör ve dosya ismi girilmelidir.
 * private Workbook IM_ExcelDosyasiAc(String str_klasor_ismi, String str_excel_dosya_ismi)
 * 
 * Bu fonksiyon mevcut excel dosyasýnýn sayfa_ismi adlý sayfasýna
 * liste_veriler ArrayList'indeki verileri yazýlmaktadýr.
 * public void IM_ExcelDosyasinayaYaz(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
 *
 * Bu fonksiyon ile mevcut excel dosyasýnýn sayfa_ismi adlý sayfasýndaki
 * format bilgilerini (ilk satýrý) güncellenmektedir.
 * public void IM_WiFiFormatGuncelle(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
 *
 * Bu fonksiyon ile excel dosyasýnýn WiFi sayfasýnda yeni eklenen bir mac adresinin
 * bulunduðu sütunda meydana gelen boþ hücrelerin "NaN" olarak doldurulmasý saðlanmaktadýr.
 * public void IM_WiFiBoslukDoldur()
 * 
 * Bu fonksiyon uygulama baþlatýldýðýnda daha önceden bulunmuþ olan 
 * tüm mac adresleri excel dosyasýndan çekilmektedir.
 * public List<String> IM_KayitliMacAdresleriniAl()
 * 
 * Bu fonksiyon ile mevcut excel dosyasýna str_sayfa_adi isimli sayfa eklenmektedir.
 * public void IM_ExcelDosyasinaSayfaEkle(String str_sayfa_ismi)
 * 
 * Bu fonksiyon ile açýk excel dosyasýnýn kapatýlmasýný saðlamaktadýr.
 * public void IM_ExcelDosyasiKapat()
 * 
 *
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Fatih ÝNAN
 * Tarih: 09.08.2014
 * Güncelleme Tarihi: 13.08.2014
 * Versiyon: v2.0
 * 
 * 
 * TELÝF HAKKI:
 * 
 * GezkonLogger yazýlýmý Ýnovasyon Mühendislik, Teknoloji Geliþtirme, Danýþmanlýk, San. ve Tic. Ltd. Þti.
 * tarafýndan geliþtirilmiþ olup her hakký saklýdýr. Ýçerisinde bulunan görsel, metin, kod vb. ögelerin
 * tamamýnýn veya bir bölümünün kullanýlmasý ve kamuya açýk olan/olmayan fiizksel veya dijital ortamlarda 
 * paylaþýlmasý/yayýnlanmasý/daðýtýlmasý yasaktýr.
 *
 ******************************************************************************/

package com.gezkonlogger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import android.os.Environment;

public class IM_ExcelIslemleriKutuphanesi {
	
	/**
	 * Log dosyalarýnýn tutulacaðý klasörün ismidir.
	 */
	String str_klasor_ismi = "GezkonLogger";
	
	/**
	 * Excel dosyasý üzerinde deðiþiklik yapýlamadýðý için, dosyanýn tüm içeriði öncelikle 
	 * 
	 * LogDosyasi_temp isimligeçici bir dosyaya aktarýlmakta, deðiþiklikler bu dosya üzerinde yapýlmaktadýr. 
	 * 
	 * Daha sonra geçici dosya kopyalanarak LogDosyasi ismini almaktadýr ve LogDosyasi_temp dosyasý silinmektedir.
	 */
	String str_excel_gecici_dosya_ismi = "LogDosyasi_temp.xls";
	
	/**
	 * Cihaz içerisinde bulunan sdcard'ýn yolunu tutan deðiþkendir.
	 */
	File sdcard_adres = Environment.getExternalStorageDirectory();
	
	/**
	 * Excel dosyasý içerisinde sayfa oluþturmak için kullanýlacak deðiþkendir.
	 * 
	 * excel_sayfasi deðiþkenine sýnýf içerisindeki farklý fonksiyonlar tarafýndan eriþebilmek için
	 * 
	 * static olarak tanýmlanmýþtýr.
	 */
	static WritableSheet excel_sayfasi;
	
	/**
	 * Yeni excel dosyasý oluþturmak için kullanýlacak deðiþkendir.
	 * 
	 * excel_dosyasi_yeni deðiþkenine sýnýf içerisindeki farklý fonksiyonlar tarafýndan eriþebilmek için
	 * 
	 * static olarak tanýmlanmýþtýr.
	 */
	static WritableWorkbook excel_dosyasi_yeni;
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ExcelDosyasiOlustur </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile parametre olarak girilen klasörde 
	 * parametre olarak girilen isimde excel dosyasý oluþturulmaktadýr. Dosya oluþturma iþlemi baþarýlý 
	 * bir þekilde gerçekleþtirilirse true, herhangi bir hata oluþursa false deðerini döndürmektedir. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_klasor_ismi				String				Oluþturulacak dosyanýyý içerecek olan klasörün yoludur.
	 *			str_excel_dosya_ismi		String				Oluþtuulacak dosyanýn ismidir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI		
	 * 										boolean				Fonksiyon eðer dosya var ise false, yok ise true dönmektedir.	
	 * -->
	 *
	 *@param str_klasor_ismi Oluþturulacak dosyanýyý içerecek olan klasörün yoludur.
	 *@param str_excel_dosya_ismi Oluþturulacak dosyanýn ismidir.
	*********************************************************************************************/
	public boolean IM_ExcelDosyasiOlustur(String str_klasor_ismi, String str_excel_dosya_ismi)
	{
		if(str_klasor_ismi != null && str_excel_dosya_ismi != null)
		{
			/**
			 * Parametre olarak girilen klasör sdcard içerisinde yoksa oluþturulmuþtur.
			 */
			 File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
			 log_dosyalarý_klasoru.mkdirs();
			 
			 /**
			  * Parametre olarak girilen dosya, belirtilen yolda bulunmuyorsa oluþturulmuþtur.
			  */
			 File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_dosya_ismi );
			 if(!kayit_dosyasi.exists())
			 {
				 /**
				  * Excel dili türkçe olarak ayarlanmýþtýr.
				  */
				 WorkbookSettings excel_dosya_ayarlari = new WorkbookSettings();
				 excel_dosya_ayarlari.setLocale(new Locale("tr", "TR"));
				 
				 /**
				  * Yeni excel dosyasý oluþturulmuþtur.
				  */
				 excel_dosyasi_yeni = null;
				 try {
					 excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosya_ayarlari);
						return true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 else
			 {
				 return false;
			 }
		}

		return false;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ExcelDosyasiAc </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon var olan bir excel dosyasýný okunabilir halde açmakta ve döndürmektedir. 
	 * Parametre olarak dosyanýn bulunduðu klasör ve dosya ismi girilmelidir.</br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_klasor_ismi				String				Açýlacak dosyayý içeren klasörün ismidir.
	 *			str_excel_dosya_ismi		String				Açýlacak dosyanýn ismidir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI		
	 * 			excel_dosyasi_acik			Workbook			Var olan excel dosyasý açýlýp döndürülmektedir.	
	 * -->
	 *
	 *@param str_klasor_ismi Açýlacak dosyayý içeren klasörün ismidir.
	 *@param str_excel_dosya_ismi Açýlacak dosyanýn ismidir.
	*********************************************************************************************/
	private Workbook IM_ExcelDosyasiAc(String str_klasor_ismi, String str_excel_dosya_ismi)
	{
		if(str_klasor_ismi != null && str_excel_dosya_ismi != null)
		{
			/**
			 * Yeni excel dosyasý oluþturulmuþtur.
			 */
			 Workbook excel_dosyasi_acik = null;
			 
			 /**
			  * Ýstenilen excel dosyasý sdcard içerisinde bulunmuþtur.
			  */
			 File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
			 log_dosyalarý_klasoru.mkdirs();
			 File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_dosya_ismi );
			 
			 /**
			  * Excel dosyasý sdcard içerisinde bulunarak açýlmýþtýr.
			  */
			 if(kayit_dosyasi.exists())
			 {
				 excel_dosyasi_acik = null;
				try {
					excel_dosyasi_acik = Workbook.getWorkbook(kayit_dosyasi);
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			 }
			 
			 /**
			  * Açýlan excel dosyasý geri döndürülmüþtür.
			  */
			return excel_dosyasi_acik;
		}
		return null;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ExcelDosyasinayaYaz </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon mevcut excel dosyasýnýn sayfa_ismi adlý sayfasýna
	 * liste_veriler ArrayList'indeki verileri yazýlmaktadýr.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_sayfa_ismi				String				Excel dosyasýnýn hangi sayfasýna kayýt yapýlacaðýný belirtir.
	 *			liste_veriler				ArrayList<String[]>	Kayýt yapýlacak verilerin tutulduðu listedir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir.
	 *
	 *@param str_sayfa_ismi Excel dosyasýnýn hangi sayfasýna kayýt yapýlacaðýný belirtir.
	 *@param liste_veriler Kayýt yapýlacak verilerin tutulduðu listedir.
	*********************************************************************************************/
	public void IM_ExcelDosyasinayaYaz(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		if(str_sayfa_ismi != null && liste_veriler != null)
		{
			/**
			 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
			 */
			Workbook excel_dosyasi_acik = IM_ExcelDosyasiAc("GezkonLogger", "LogDosyasi.xls");
			
			/**
			 * Excel dosyasýnda ilk yazýlacak satýr 0. satýr olarak ayarlanmýþtýr.
			 * 
			 * ilk ölçüm için deðer 0 olarak belirlenmiþtir.
			 */
			int i_satir_sayisi = 0;
			int i_olcum_sayisi = 0;
			
			/**
			 * Loglarý dosyaya yazma sýrasýnda en son hangi satýrda kalýndýðý ve 
			 * 
			 * kaç numaralý ölçümün yapýldýðý log dosyasýndanh çekilmektedir.
			 */
			try
			{
				Sheet sayfa = excel_dosyasi_acik.getSheet(str_sayfa_ismi);
				Cell cell_satir_sayisi = sayfa.getCell(100,0); 
				String str_satir_sayisi = cell_satir_sayisi.getContents(); 
				i_satir_sayisi = Integer.parseInt(str_satir_sayisi);
				
				Cell cell_olcum_sayisi = sayfa.getCell(101,0); 
				String str_olcum_sayisi = cell_olcum_sayisi.getContents(); 
				i_olcum_sayisi = Integer.parseInt(str_olcum_sayisi);
			}
			catch(Exception e)
			{
				i_satir_sayisi = 0;
				e.printStackTrace();
			}
			
			/**
			 * Dosya üzerinde yapýlan deðiþikliklerin kaydedileceði yeni excel dosyasý oluþturulmuþtur.
			 */
			WritableWorkbook excel_dosyasi_yeni = null;
			File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
			File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_gecici_dosya_ismi );
			File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, "LogDosyasi.xls" );
			
			try {
				excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
				
				WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet(str_sayfa_ismi);
	
				/**
				 * Veriler bu hücre kullanýlarak tek tek excel dosyasýna kaydedilmektedir.
				 */
				Label lbl_hucre;
				
				/**
				 * liste_veriler listesindeki veriler sýrasýyla çekilip ilgili sayfaya yazdýrýlmaktadýr.
				 */
				for(int i=0; i<liste_veriler.size(); i++)
				{
					if(i_satir_sayisi!=0)
					{
						lbl_hucre = new Label(0, i_satir_sayisi, String.valueOf(i_olcum_sayisi));
					}
					else
					{
						lbl_hucre = new Label(0, i_satir_sayisi, "ÖLÇÜM");
					}
					yazdirma_sayfasi.addCell(lbl_hucre);
					
					for(int j=1; j<liste_veriler.get(i).length; j++)
					{
						lbl_hucre = new Label(j, i_satir_sayisi, liste_veriler.get(i)[j]);
						yazdirma_sayfasi.addCell(lbl_hucre);
					}
					/**
					 * Her bir satýr yazýldýktan sonra i_satir_sayisi deðiþkeninin deðeri bir 
					 * 
					 * arttýrýlarak, bir sonraki kayýtýn sýradaki satýra yazdýrýlmasý saðlanmýþtýr.
					 */
					i_satir_sayisi++;   
				}
				/**
				 * listedeki tüm veriler dosyaya yazdýrýldýðýnda i_olcum_sayisi deðiþkeninin deðeri bir arttýrýlacak
				 * 
				 * bir sonraki testte ölçüm sayýsýnýn bir artmasý saðlanmýþtýr.
				 */
				i_olcum_sayisi++;
				
				/**
				 * Son olarak yazýlan satýr sayýsý excel dosyasýna kaydedilmiþtir.
				 * 
				 * Bir sonraki yazma iþleminde hangi satýrdan devam edileceði excel dosyasýndan okunarak belirlenecektir.
				 */
				lbl_hucre = new Label(100, 0, String.valueOf(i_satir_sayisi));
				yazdirma_sayfasi.addCell(lbl_hucre);
				
				/**
				 * Son olarak kaç numaralý ölçümün yapýldýðý excel dosyasýna kaydedilmiþtir.
				 * 
				 * Bir sonraki ölçümün numarasý excel dosyasýndan okunarak belirlenecektir.
				 */
				lbl_hucre = new Label(101, 0, String.valueOf(i_olcum_sayisi));
				yazdirma_sayfasi.addCell(lbl_hucre);
	
				/**
				 * Yapýlan deðiþiklikler excel dosyasýna kaydedilmiþtir.
				 */
				excel_dosyasi_yeni.write();
				excel_dosyasi_yeni.close();
				excel_dosyasi_acik.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * Geçici dosyaya yazýlan veriler, yazma iþleminin tamamlanmasýnýn ardýndan orjinal dosyaya taþýnmýþtýr.
			 */
			boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
			if(b_silindi_mi)
			{
				File dosya_eski = new File(log_dosyalarý_klasoru,"LogDosyasi_temp.xls");
			    File dosya_yeni = new File(log_dosyalarý_klasoru,"LogDosyasi.xls");
			    if(dosya_eski.exists())
			    {
			    	dosya_eski.renameTo(dosya_yeni);
			    }
			}
		}
	}
	
	
	
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_WiFiFormatGuncelle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile mevcut excel dosyasýnýn sayfa_ismi adlý sayfasýndaki
	 * format bilgilerini (ilk satýrý) güncellenmektedir.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_sayfa_ismi				String				Excel dosyasýnýn hangi sayfasýna kayýt yapýlacaðýný belirtir.
	 *			liste_veriler				ArrayList<String[]>	Güncel format verilerinin tutulduðu listedir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir.
	 *
	 *@param str_sayfa_ismi Excel dosyasýnýn hangi sayfasýna kayýt yapýlacaðýný belirtir.
	 *@param liste_veriler Güncel format verilerin tutulduðu listedir.
	*********************************************************************************************/
	public void IM_WiFiFormatGuncelle(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		if(str_sayfa_ismi != null && liste_veriler != null)
		{
			/**
			 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
			 */
			Workbook excel_dosyasi_acik = IM_ExcelDosyasiAc("GezkonLogger", "LogDosyasi.xls");
			
			/**
			 * Excel dosyasýnda ilk yazýlacak satýr 0. satýr olarak ayarlanmýþtýr.
			 * 
			 * ilk ölçüm için deðer 0 olarak belirlenmiþtir.
			 */
			int i_satir_sayisi = 0;
			int i_olcum_sayisi = 0;
			
			/**
			 * Loglarý dosyaya yazma sýrasýnda en son hangi satýrda kalýndýðý ve 
			 * 
			 * kaç numaralý ölçümün yapýldýðý log dosyasýndanh çekilmektedir.
			 */
			try
			{
				Sheet sayfa = excel_dosyasi_acik.getSheet(str_sayfa_ismi);
				Cell cell_satir_sayisi = sayfa.getCell(100,0); 
				String str_satir_sayisi = cell_satir_sayisi.getContents(); 
				i_satir_sayisi = Integer.parseInt(str_satir_sayisi);
				
				Cell cell_olcum_sayisi = sayfa.getCell(101,0); 
				String str_olcum_sayisi = cell_olcum_sayisi.getContents(); 
				i_olcum_sayisi = Integer.parseInt(str_olcum_sayisi);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				i_satir_sayisi = 0;
			}
			
			/**
			 * Dosya üzerinde yapýlan deðiþikliklerin kaydedileceði yeni excel dosyasý oluþturulmuþtur.
			 */
			WritableWorkbook excel_dosyasi_yeni = null;
			File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
			File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_gecici_dosya_ismi );
			File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, "LogDosyasi.xls" );
			
			try {
				excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
				
				WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet(str_sayfa_ismi);
	
				/**
				 * Veriler bu hücre kullanýlarak tek tek excel dosyasýna kaydedilmektedir.
				 */
				Label lbl_hucre;
				
				/**
				 * liste_veriler listesindeki veriler sýrasýyla çekilip ilgili sayfaya yazdýrýlmaktadýr.
				 */
				for(int i=0; i<liste_veriler.size(); i++)
				{
					lbl_hucre = new Label(0, 0, "ÖLÇÜM");
	
					yazdirma_sayfasi.addCell(lbl_hucre);
					
					for(int j=1; j<liste_veriler.get(i).length; j++)
					{
						lbl_hucre = new Label(j, 0, liste_veriler.get(i)[j]);
						yazdirma_sayfasi.addCell(lbl_hucre);
					}  
				}
				
				/**
				 * Son olarak yazýlan satýr sayýsý excel dosyasýna kaydedilmiþtir.
				 * 
				 * Bir sonraki yazma iþleminde hangi satýrdan devam edileceði excel dosyasýndan okunarak belirlenecektir.
				 */
				lbl_hucre = new Label(100, 0, String.valueOf(i_satir_sayisi));
				yazdirma_sayfasi.addCell(lbl_hucre);
				
				/**
				 * Son olarak kaç numaralý ölçümün yapýldýðý excel dosyasýna kaydedilmiþtir.
				 * 
				 * Bir sonraki ölçümün numarasý excel dosyasýndan okunarak belirlenecektir.
				 */
				lbl_hucre = new Label(101, 0, String.valueOf(i_olcum_sayisi));
				yazdirma_sayfasi.addCell(lbl_hucre);
	
				/**
				 * Yapýlan deðiþiklikler excel dosyasýna kaydedilmiþtir.
				 */
				excel_dosyasi_yeni.write();
				excel_dosyasi_yeni.close();
				excel_dosyasi_acik.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * Geçici dosyaya yazýlan veriler, yazma iþleminin tamamlanmasýnýn ardýndan orjinal dosyaya taþýnmýþtýr.
			 */
			boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
			if(b_silindi_mi)
			{
				File dosya_eski = new File(log_dosyalarý_klasoru,"LogDosyasi_temp.xls");
			    File dosya_yeni = new File(log_dosyalarý_klasoru,"LogDosyasi.xls");
			    if(dosya_eski.exists())
			    {
			    	dosya_eski.renameTo(dosya_yeni);
			    }
			}
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_WiFiBoslukDoldur </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile excel dosyasýnýn WiFi sayfasýnda yeni eklenen bir mac adresinin
	 * bulunduðu sütunda meydana gelen boþ hücrelerin "NaN" olarak doldurulmasý saðlanmaktadýr.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * 
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir.
	*********************************************************************************************/
	public void IM_WiFiBoslukDoldur()
	{
		/**
		 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = IM_ExcelDosyasiAc("GezkonLogger", "LogDosyasi.xls");
		
		/**
		 * Excel dosyasýnda ilk yazýlacak satýr 0. satýr olarak ayarlanmýþtýr.
		 * 
		 * ilk ölçüm için deðer 0 olarak belirlenmiþtir.
		 */
		int i_satir_sayisi_bas = 1;
		int i_sutun_sayisi = 7;
		
		Sheet sayfa = excel_dosyasi_acik.getSheet("WiFi");
		
		/**
		 * Dosya üzerinde yapýlan deðiþikliklerin kaydedileceði yeni excel dosyasý oluþturulmuþtur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, "LogDosyasi.xls" );
		try {
			excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet("WiFi");
		
		/**
		 * Veriler bu hücre kullanýlarak tek tek excel dosyasýna kaydedilmektedir.
		 */
		Label lbl_hucre;
		
		/**
		 * Loglarý dosyasýna yeni eklenen mac adresinin bulunduðu sütundaki boþ hücreler 
		 * 
		 * belirlenerek "NaN" deðeri ile doldurulmaktadýr.
		 */
		try
		{
			while(!(sayfa.getCell(i_sutun_sayisi,0).getContents()).equals(""))
			{
				while((sayfa.getCell(i_sutun_sayisi, i_satir_sayisi_bas).getContents()).equals(""))
				{
					lbl_hucre = new Label(i_sutun_sayisi, i_satir_sayisi_bas, "NaN");
					yazdirma_sayfasi.addCell(lbl_hucre);
					
					i_satir_sayisi_bas++;
				}
				i_sutun_sayisi++;
				i_satir_sayisi_bas = 1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/**
		 * Yapýlan deðiþiklikler excel dosyasýna kaydedilmiþtir.
		 */
		try {
			excel_dosyasi_yeni.write();
			excel_dosyasi_yeni.close();
			excel_dosyasi_acik.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * Geçici dosyaya yazýlan veriler, yazma iþleminin tamamlanmasýnýn ardýndan orjinal dosyaya taþýnmýþtýr.
		 */
		boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
		if(b_silindi_mi)
		{
			File dosya_eski = new File(log_dosyalarý_klasoru,"LogDosyasi_temp.xls");
		    File dosya_yeni = new File(log_dosyalarý_klasoru,"LogDosyasi.xls");
		    if(dosya_eski.exists())
		    {
		    	dosya_eski.renameTo(dosya_yeni);
		    }
		}
	}
	
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_KayitliMacAdresleriniAl </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon uygulama baþlatýldýðýnda daha önceden bulunmuþ olan 
	 * tüm mac adresleri excel dosyasýndan çekilmektedir. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * 
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI	
	 * 			liste_kayitli_mac_adresleri List<String>		Excel dosyasýnda bulunan tüm mac adreslerini bulunduran listedir.	
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir.
	*********************************************************************************************/
	public List<String> IM_KayitliMacAdresleriniAl()
	{
		/**
		 * Dosyadan veri okumak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = IM_ExcelDosyasiAc("GezkonLogger", "LogDosyasi.xls");
		
		/**
		 * Verinin okunacaðý sayfa belirlenmiþtir.
		 */
		Sheet sayfa = excel_dosyasi_acik.getSheet("WiFi");
		
		/**
		 * Bulunan mac adreslerinin tutulduðu listedir.
		 */
		List<String> liste_kayitli_mac_adresleri = new ArrayList<String>();
		
		/**
		 * Tüm mac adreslerinin bulunmasý için ilk mac adresinin yazýlý olduðu 7. sütundan itibaren tarama baþlatýlmýþtýr.
		 */
		try
		{
			int i_baslangic = 7;
			while(!sayfa.getCell(i_baslangic,0).getContents().equals(""))
			{
				liste_kayitli_mac_adresleri.add(sayfa.getCell(i_baslangic,0).getContents());
				i_baslangic++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/**
		 * Bulunan mac adresleri geri döndürülmüþtür.
		 */
		return liste_kayitli_mac_adresleri;
	}
	

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ExcelDosyasinaSayfaEkle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile mevcut excel dosyasýna str_sayfa_ismi isimli sayfa eklenmektedir.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_sayfa_ismi				String				Excel dosyasýna eklenecek sayfanýn ismidir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir. Bu fonksiyon çalýþtýrýlmadan önce DosyaOlustur
	 * fonksiyonunu çalýþtýrýlmýþ olmasý gerekmektedir.
	 *
	 *@param str_sayfa_ismi Excel dosyasýna eklenecek sayfanýn ismidir.
	*********************************************************************************************/
	public void IM_ExcelDosyasinaSayfaEkle(String str_sayfa_ismi)
	{
		if(str_sayfa_ismi != null)
		{
			/**
			 * Excel dosyasýna parametre olarak verilen isimdeki sayfa eklenmiþtir.
			 */
			excel_sayfasi = excel_dosyasi_yeni.createSheet(str_sayfa_ismi, 0);
		}
	}

	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ExcelDosyasiKapat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile açýk excel dosyasýnýn kapatýlmasýný saðlamaktadýr.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir. Bu fonksiyon çalýþtýrýlmadan önce DosyaOlustur
	 * fonksiyonunu çalýþtýrýlmýþ olmasý gerekmektedir.
	 * 
	*********************************************************************************************/
	public void IM_ExcelDosyasiKapat()
	{
		/**
		 * Excel dosyasý deðiþiklikler kaydedilerek kapatýlmýþtýr.
		 */
		try {
			excel_dosyasi_yeni.write();
			excel_dosyasi_yeni.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
