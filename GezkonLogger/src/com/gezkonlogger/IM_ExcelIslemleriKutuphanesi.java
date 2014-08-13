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
	 * Log dosyalar�n�n tutulaca�� klas�r�n ismidir.
	 */
	String str_klasor_ismi = "GezkonLogger";
	
	/**
	 * Log verileri �ncelikle LogDosyasi_temp isimli dosyaya y�klenmekte
	 * 
	 * dosya kapat�ld�kdan sonra LogDosyasi ismini almaktad�r.
	 */
	String str_excel_gecici_dosya_ismi = "LogDosyasi_temp.xls";
	
	/**
	 * Log verilerini i�erecek olan dosyad�r.
	 */
	String str_silinecek_excel_dosya_ismi = "LogDosyasi.xls";
	
	/**
	 * Cihaz i�erisinde bulunan sdcard'�n yolunu tutan de�i�kendir.
	 */
	File sdcard_adres = Environment.getExternalStorageDirectory();
	
	/**
	 * Excel dosyas� i�erisinde sayfa olu�turmak i�in kullan�lacak de�i�kendir.
	 */
	static WritableSheet excel_sayfasi;
	
	/**
	 * Excel dosyas� olu�turmak i�in kullan�lacak de�i�kendir.
	 */
	static WritableWorkbook excel_dosyasi_yeni;
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				DosyaOlustur </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile istenilen klas�rde istenilen isimde 
	 * excel dosyas� olu�turulmaktad�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			str_klasor_ismi				String				Olu�turulacak dosyan�y� i�erek olan klas�r�n yoludur.
	 *			str_excel_dosya_ismi		String				Olu�tuulacak dosyan�n ismidir.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI		
	 * 										boolean				Fonksiyon e�er dosya var ise false, yok ise true d�nmektedir.	
	 * -->
	 *
	 *@param str_klasor_ismi Olu�turulacak dosyan�y� i�erek olan klas�r�n yoludur.
	 *@param str_excel_dosya_ismi Olu�tuulacak dosyan�n ismidir.
	*********************************************************************************************/
	public boolean DosyaOlustur(String str_klasor_ismi, String str_excel_dosya_ismi)
	{		 
		/**
		 * Parametre olarak girilen klas�r sdcard i�erisinde yoksa olu�turulmu�tur.
		 */
		 File log_dosyalar�_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		 log_dosyalar�_klasoru.mkdirs();
		 
		 /**
		  * Parametre olarak girilen dosya, belirtilen yolda bulunmuyorsa olu�turulmu�tur.
		  */
		 File kayit_dosyasi = new File(log_dosyalar�_klasoru, str_excel_dosya_ismi );
		 if(!kayit_dosyasi.exists())
		 {
			 /**
			  * Excel dili t�rk�e olarak ayarlanm��t�r.
			  */
			 WorkbookSettings excel_dosya_ayarlari = new WorkbookSettings();
			 excel_dosya_ayarlari.setLocale(new Locale("tr", "TR"));
			 
			 /**
			  * Yeni excel dosyas� olu�turulmu�tur.
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
	 * FONKS�YON ADI: 				DosyaAc </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon var olan bir excel dosyas� a��l�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			str_klasor_ismi				String				A��lacak dosyay� i�eren klas�r�n ismidir.
	 *			str_excel_dosya_ismi		String				A��lacak dosyan�n ismidir.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI		
	 * 			excel_dosyasi_acik			Workbook			Var olan excel dosyas� a��l�p d�nd�r�lmektedir.	
	 * -->
	 *
	 *@param str_klasor_ismi A��lacak dosyay� i�eren klas�r�n ismidir.
	 *@param str_excel_dosya_ismi A��lacak dosyan�n ismidir.
	*********************************************************************************************/
	private Workbook DosyaAc(String str_klasor_ismi, String str_excel_dosya_ismi)
	{
		/**
		 * Yeni excel dosyas� olu�turulmu�tur.
		 */
		 Workbook excel_dosyasi_acik = null;
		 
		 /**
		  * �stenilen excel dosyas� sdcard i�erisinde bulunmu�tur.
		  */
		 File log_dosyalar�_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		 log_dosyalar�_klasoru.mkdirs();
		 File kayit_dosyasi = new File(log_dosyalar�_klasoru, str_excel_dosya_ismi );
		 
		 /**
		  * Excel dosyas� sdcard i�erisinde bulunark a��lm��t�r.
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
		  * A��lan excel dosyas� geri d�nd�r�lm��t�r.
		  */
		return excel_dosyasi_acik;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				DosyayaYaz </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon mevcut excel dosyas�n�n sayfa_ismi adl� sayfas�na
	 * liste_veriler ArrayList'indeki veriler yaz�lmaktad�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			str_sayfa_ismi				String				Excel dosyas�n�n hangi sayfas�na kay�t yap�laca��n� belirtir.
	 *			liste_veriler				ArrayList<String[]>	Kay�t yap�lacak verilerin tutuldu�u listedir.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * GEREKL�L�K: Android Cihaz�n SDCard i�erisinde bulunan GezkonLogger klas�r� ve i�erisindeki dosyalar�n
	 * isimleri de�i�tirilmemeli ve dosyalar silinmemelidir.
	 *
	 *@param str_sayfa_ismi Excel dosyas�n�n hangi sayfas�na kay�t yap�laca��n� belirtir.
	 *@param liste_veriler Kay�t yap�lacak verilerin tutuldu�u listedir.
	*********************************************************************************************/
	public void DosyayaYaz(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		/**
		 * Dosyaya yazmak i�in dosya, DosyaAc fonksiyonu ile a��lm��t�r. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
		/**
		 * Excel dosyas�nda ilk yaz�lacak sat�r 0. sat�r olarak ayarlanm��t�r.
		 * 
		 * ilk �l��m i�in de�er 0 olarak belirlenmi�tir.
		 */
		int i_satir_sayisi = 0;
		int i_olcum_sayisi = 0;
		
		/**
		 * Loglar� dosyaya yazma s�ras�nda en son hangi sat�rda kal�nd��� ve 
		 * 
		 * ka� numaral� �l��m�n yap�ld��� log dosyas�ndanh �ekilmektedir.
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
		 * Dosya �zerinde yap�lan de�i�ikliklerin kaydedilece�i yeni excel dosyas� olu�turulmu�tur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalar�_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalar�_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalar�_klasoru, str_silinecek_excel_dosya_ismi );
		
		try {
			excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
			
			WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet(str_sayfa_ismi);

			/**
			 * Veriler bu h�cre kullan�larak tek tek excel dosyas�na kaydedilmektedir.
			 */
			Label lbl_hucre;
			
			/**
			 * liste_veriler listesindeki veriler s�ras�yla �ekilip ilgili sayfaya yazd�r�lmaktad�r.
			 */
			for(int i=0; i<liste_veriler.size(); i++)
			{
				if(i_satir_sayisi!=0)
				{
					lbl_hucre = new Label(0, i_satir_sayisi, String.valueOf(i_olcum_sayisi));
				}
				else
				{
					lbl_hucre = new Label(0, i_satir_sayisi, "�L��M");
				}
				yazdirma_sayfasi.addCell(lbl_hucre);
				
				for(int j=1; j<liste_veriler.get(i).length; j++)
				{
					lbl_hucre = new Label(j, i_satir_sayisi, liste_veriler.get(i)[j]);
					yazdirma_sayfasi.addCell(lbl_hucre);
				}
				/**
				 * Her bir sat�r yaz�ld�ktan sonra i_satir_sayisi de�i�keninin de�eri bir 
				 * 
				 * artt�r�larak, bir sonraki kay�t�n s�radaki sat�ra yazd�r�lmas� sa�lanm��t�r.
				 */
				i_satir_sayisi++;   
			}
			/**
			 * listedeki t�m veriler dosyaya yazd�r�ld���nda i_olcum_sayisi de�i�keninin de�eri bir artt�r�lacak
			 * 
			 * bir sonraki testte �l��m say�s�n�n bir artmas� sa�lanm��t�r.
			 */
			i_olcum_sayisi++;
			
			/**
			 * Son olarak yaz�lan sat�r say�s� excel dosyas�na kaydedilmi�tir.
			 * 
			 * Bir sonraki yazma i�leminde hangi sat�rdan devam edilece�i excel dosyas�ndan okunarak belirlenecektir.
			 */
			lbl_hucre = new Label(100, 0, String.valueOf(i_satir_sayisi));
			yazdirma_sayfasi.addCell(lbl_hucre);
			
			/**
			 * Son olarak ka� numaral� �l��m�n yap�ld��� excel dosyas�na kaydedilmi�tir.
			 * 
			 * Bir sonraki �l��m�n numaras� excel dosyas�ndan okunarak belirlenecektir.
			 */
			lbl_hucre = new Label(101, 0, String.valueOf(i_olcum_sayisi));
			yazdirma_sayfasi.addCell(lbl_hucre);

			/**
			 * Yap�lan de�i�iklikler excel dosyas�na kaydedilmi�tir.
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
		 * Ge�ici dosyaya yaz�lan veriler, yazma i�leminin tamamlanmas�n�n ard�ndan orjinal dosyaya ta��nm��t�r.
		 */
		boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
		if(b_silindi_mi)
		{
			File dosya_eski = new File(log_dosyalar�_klasoru,"LogDosyasi_temp.xls");
		    File dosya_yeni = new File(log_dosyalar�_klasoru,"LogDosyasi.xls");
		    if(dosya_eski.exists())
		    {
		    	dosya_eski.renameTo(dosya_yeni);
		    }
		}
	}
	
	
	
	
	
	
	public void WiFiFormatGuncelle(String str_sayfa_ismi, ArrayList<String[]> liste_veriler)
	{
		/**
		 * Dosyaya yazmak i�in dosya, DosyaAc fonksiyonu ile a��lm��t�r. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
		/**
		 * Excel dosyas�nda ilk yaz�lacak sat�r 0. sat�r olarak ayarlanm��t�r.
		 * 
		 * ilk �l��m i�in de�er 0 olarak belirlenmi�tir.
		 */
		int i_satir_sayisi = 0;
		int i_olcum_sayisi = 0;
		
		/**
		 * Loglar� dosyaya yazma s�ras�nda en son hangi sat�rda kal�nd��� ve 
		 * 
		 * ka� numaral� �l��m�n yap�ld��� log dosyas�ndanh �ekilmektedir.
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
		 * Dosya �zerinde yap�lan de�i�ikliklerin kaydedilece�i yeni excel dosyas� olu�turulmu�tur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalar�_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalar�_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalar�_klasoru, str_silinecek_excel_dosya_ismi );
		
		try {
			excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
			
			WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet(str_sayfa_ismi);

			/**
			 * Veriler bu h�cre kullan�larak tek tek excel dosyas�na kaydedilmektedir.
			 */
			Label lbl_hucre;
			
			/**
			 * liste_veriler listesindeki veriler s�ras�yla �ekilip ilgili sayfaya yazd�r�lmaktad�r.
			 */
			for(int i=0; i<liste_veriler.size(); i++)
			{
				lbl_hucre = new Label(0, 0, "�L��M");

				yazdirma_sayfasi.addCell(lbl_hucre);
				
				for(int j=1; j<liste_veriler.get(i).length; j++)
				{
					lbl_hucre = new Label(j, 0, liste_veriler.get(i)[j]);
					yazdirma_sayfasi.addCell(lbl_hucre);
				}  
			}
			
			/**
			 * Son olarak yaz�lan sat�r say�s� excel dosyas�na kaydedilmi�tir.
			 * 
			 * Bir sonraki yazma i�leminde hangi sat�rdan devam edilece�i excel dosyas�ndan okunarak belirlenecektir.
			 */
			lbl_hucre = new Label(100, 0, String.valueOf(i_satir_sayisi));
			yazdirma_sayfasi.addCell(lbl_hucre);
			
			/**
			 * Son olarak ka� numaral� �l��m�n yap�ld��� excel dosyas�na kaydedilmi�tir.
			 * 
			 * Bir sonraki �l��m�n numaras� excel dosyas�ndan okunarak belirlenecektir.
			 */
			lbl_hucre = new Label(101, 0, String.valueOf(i_olcum_sayisi));
			yazdirma_sayfasi.addCell(lbl_hucre);

			/**
			 * Yap�lan de�i�iklikler excel dosyas�na kaydedilmi�tir.
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
		 * Ge�ici dosyaya yaz�lan veriler, yazma i�leminin tamamlanmas�n�n ard�ndan orjinal dosyaya ta��nm��t�r.
		 */
		boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
		if(b_silindi_mi)
		{
			File dosya_eski = new File(log_dosyalar�_klasoru,"LogDosyasi_temp.xls");
		    File dosya_yeni = new File(log_dosyalar�_klasoru,"LogDosyasi.xls");
		    if(dosya_eski.exists())
		    {
		    	dosya_eski.renameTo(dosya_yeni);
		    }
		}
	}
	
	
	
	public void WiFiBoslukDoldur()
	{
		/**
		 * Dosyaya yazmak i�in dosya, DosyaAc fonksiyonu ile a��lm��t�r. 
		 */
		Workbook excel_dosyasi_acik = DosyaAc("GezkonLogger", "LogDosyasi.xls");
		
		/**
		 * Excel dosyas�nda ilk yaz�lacak sat�r 0. sat�r olarak ayarlanm��t�r.
		 * 
		 * ilk �l��m i�in de�er 0 olarak belirlenmi�tir.
		 */
		int i_satir_sayisi_bas = 1;
		int i_sutun_sayisi = 7;
		
		Sheet sayfa = excel_dosyasi_acik.getSheet("WiFi");
		
		/**
		 * Dosya �zerinde yap�lan de�i�ikliklerin kaydedilece�i yeni excel dosyas� olu�turulmu�tur.
		 */
		WritableWorkbook excel_dosyasi_yeni = null;
		File log_dosyalar�_klasoru = new File (sdcard_adres.getAbsolutePath() + "/" + str_klasor_ismi);
		File kayit_dosyasi = new File(log_dosyalar�_klasoru, str_excel_gecici_dosya_ismi );
		File silinecek_kayit_dosyasi = new File(log_dosyalar�_klasoru, str_silinecek_excel_dosya_ismi );
		try {
			excel_dosyasi_yeni = Workbook.createWorkbook(kayit_dosyasi, excel_dosyasi_acik);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet yazdirma_sayfasi = excel_dosyasi_yeni.getSheet("WiFi");
		
		/**
		 * Veriler bu h�cre kullan�larak tek tek excel dosyas�na kaydedilmektedir.
		 */
		Label lbl_hucre;
		
		/**
		 * Loglar� dosyaya yazma s�ras�nda en son hangi sat�rda kal�nd��� ve 
		 * 
		 * ka� numaral� �l��m�n yap�ld��� log dosyas�ndan �ekilmektedir.
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
			 * Yap�lan de�i�iklikler excel dosyas�na kaydedilmi�tir.
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
		 * Ge�ici dosyaya yaz�lan veriler, yazma i�leminin tamamlanmas�n�n ard�ndan orjinal dosyaya ta��nm��t�r.
		 */
		boolean b_silindi_mi = silinecek_kayit_dosyasi.delete();
		if(b_silindi_mi)
		{
			File dosya_eski = new File(log_dosyalar�_klasoru,"LogDosyasi_temp.xls");
		    File dosya_yeni = new File(log_dosyalar�_klasoru,"LogDosyasi.xls");
		    if(dosya_eski.exists())
		    {
		    	dosya_eski.renameTo(dosya_yeni);
		    }
		}
	}
	
	
	
	public List<String> KayitliMacAdresleriniAl()
	{
		/**
		 * Dosyaya yazmak i�in dosya, DosyaAc fonksiyonu ile a��lm��t�r. 
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
	 * FONKS�YON ADI: 				SayfaEkle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon mevcut excel dosyas�na bir sayfa eklenmektedir.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			str_sayfa_adi				String				Excel dosyas�na eklenecek sayfan�n ismidir.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * GEREKL�L�K: Android Cihaz�n SDCard i�erisinde bulunan GezkonLogger klas�r� ve i�erisindeki dosyalar�n
	 * isimleri de�i�tirilmemeli ve dosyalar silinmemelidir. Bu fonksiyon �al��t�r�lmadan �nce DosyaOlustur
	 * fonksiyonunu �al��t�r�lm�� olmas� gerekmektedir.
	 *
	 *@param str_sayfa_adi Excel dosyas�na eklenecek sayfan�n ismidir.
	*********************************************************************************************/
	public void SayfaEkle(String str_sayfa_adi)
	{
		/**
		 * Excel dosyas�na parametre olarak verilen isimdeki sayfa eklenmi�tir.
		 */
		excel_sayfasi = excel_dosyasi_yeni.createSheet(str_sayfa_adi, 0);
	}

	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				DosyaKapat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon a��k excel dosyas�n�n kapat�lmas�n� sa�lamaktad�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * GEREKL�L�K: Android Cihaz�n SDCard i�erisinde bulunan GezkonLogger klas�r� ve i�erisindeki dosyalar�n
	 * isimleri de�i�tirilmemeli ve dosyalar silinmemelidir. Bu fonksiyon �al��t�r�lmadan �nce DosyaOlustur
	 * fonksiyonunu �al��t�r�lm�� olmas� gerekmektedir.
	 * 
	*********************************************************************************************/
	public void DosyaKapat()
	{
		/**
		 * Excel dosyas� de�i�iklikler kaydedilerek kapat�lm��t�r.
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
