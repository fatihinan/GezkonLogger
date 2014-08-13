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
	 * Log verileri öncelikle LogDosyasi_temp isimli dosyaya yüklenmekte
	 * 
	 * dosya kapatýldýkdan sonra LogDosyasi ismini almaktadýr.
	 */
	String str_excel_gecici_dosya_ismi = "LogDosyasi_temp.xls";
	
	/**
	 * Log verilerini içerecek olan dosyadýr.
	 */
	String str_silinecek_excel_dosya_ismi = "LogDosyasi.xls";
	
	/**
	 * Cihaz içerisinde bulunan sdcard'ýn yolunu tutan deðiþkendir.
	 */
	File sdcard_adres = Environment.getExternalStorageDirectory();
	
	/**
	 * Excel dosyasý içerisinde sayfa oluþturmak için kullanýlacak deðiþkendir.
	 */
	static WritableSheet excel_sayfasi;
	
	/**
	 * Excel dosyasý oluþturmak için kullanýlacak deðiþkendir.
	 */
	static WritableWorkbook excel_dosyasi_yeni;
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				DosyaOlustur </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile istenilen klasörde istenilen isimde 
	 * excel dosyasý oluþturulmaktadýr.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_klasor_ismi				String				Oluþturulacak dosyanýyý içerek olan klasörün yoludur.
	 *			str_excel_dosya_ismi		String				Oluþtuulacak dosyanýn ismidir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI		
	 * 										boolean				Fonksiyon eðer dosya var ise false, yok ise true dönmektedir.	
	 * -->
	 *
	 *@param str_klasor_ismi Oluþturulacak dosyanýyý içerek olan klasörün yoludur.
	 *@param str_excel_dosya_ismi Oluþtuulacak dosyanýn ismidir.
	*********************************************************************************************/
	public boolean DosyaOlustur(String str_klasor_ismi, String str_excel_dosya_ismi)
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
		return false;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				DosyaAc </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon var olan bir excel dosyasý açýlýr.  </br> </br>
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
	private Workbook DosyaAc(String str_klasor_ismi, String str_excel_dosya_ismi)
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
		  * Excel dosyasý sdcard içerisinde bulunark açýlmýþtýr.
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
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				DosyayaYaz </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon mevcut excel dosyasýnýn sayfa_ismi adlý sayfasýna
	 * liste_veriler ArrayList'indeki veriler yazýlmaktadýr.  </br> </br>
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
	public void DosyayaYaz(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		/**
		 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
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
		}
		
		/**
		 * Dosya üzerinde yapýlan deðiþikliklerin kaydedileceði yeni excel dosyasý oluþturulmuþtur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, str_silinecek_excel_dosya_ismi );
		
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
	
	
	
	
	
	
	public void WiFiFormatGuncelle(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		/**
		 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
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
		}
		
		/**
		 * Dosya üzerinde yapýlan deðiþikliklerin kaydedileceði yeni excel dosyasý oluþturulmuþtur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalarý_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalarý_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, str_silinecek_excel_dosya_ismi );
		
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
	
	
	
	public void WiFiBoslukDoldur()
	{
		/**
		 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
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
		File silinecek_kayit_dosyasi = new File(log_dosyalarý_klasoru, str_silinecek_excel_dosya_ismi );
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
		 * Loglarý dosyaya yazma sýrasýnda en son hangi satýrda kalýndýðý ve 
		 * 
		 * kaç numaralý ölçümün yapýldýðý log dosyasýndan çekilmektedir.
		 */
		//Cell cell_satir_sayisi = sayfa.getCell(100,0); 
		//String str_satir_sayisi = cell_satir_sayisi.getContents(); 
		//i_satir_sayisi = Integer.parseInt(str_satir_sayisi);
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
		}
		

		
		try {
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
	
	
	
	public List<String> KayitliMacAdresleriniAl()
	{
		/**
		 * Dosyaya yazmak için dosya, DosyaAc fonksiyonu ile açýlmýþtýr. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
		Sheet sayfa = excel_dosyasi_acik.getSheet("WiFi");
		Cell hucre_mac_adresi;
		String str_mac_adresi;
		List<String> liste_kayitli_mac_adresleri = new ArrayList<String>();
		
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
		}
		return liste_kayitli_mac_adresleri;
		
		
	}
	

	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				SayfaEkle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon mevcut excel dosyasýna bir sayfa eklenmektedir.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			str_sayfa_adi				String				Excel dosyasýna eklenecek sayfanýn ismidir.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * GEREKLÝLÝK: Android Cihazýn SDCard içerisinde bulunan GezkonLogger klasörü ve içerisindeki dosyalarýn
	 * isimleri deðiþtirilmemeli ve dosyalar silinmemelidir. Bu fonksiyon çalýþtýrýlmadan önce DosyaOlustur
	 * fonksiyonunu çalýþtýrýlmýþ olmasý gerekmektedir.
	 *
	 *@param str_sayfa_adi Excel dosyasýna eklenecek sayfanýn ismidir.
	*********************************************************************************************/
	public void SayfaEkle(String str_sayfa_adi)
	{
		/**
		 * Excel dosyasýna parametre olarak verilen isimdeki sayfa eklenmiþtir.
		 */
		excel_sayfasi = excel_dosyasi_yeni.createSheet(str_sayfa_adi, 0);
	}

	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				DosyaKapat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon açýk excel dosyasýnýn kapatýlmasýný saðlamaktadýr.  </br> </br>
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
	public void DosyaKapat()
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
