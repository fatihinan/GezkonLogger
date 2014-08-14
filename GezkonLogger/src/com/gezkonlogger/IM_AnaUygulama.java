/*******************************************************************************
 * IM_AnaUygulama.java
 *
 * AMA�:
 * IM_AnaUygulama ile IM_IvmeolcerKutuphanesi, IM_ManyetikAlanKutuphanesi, IMBluetoothKutuphanesi ve 
 * IM_WifiKutuphanesi taraf�ndan belirlenen aral�klarla toplanan veriler 
 * IM_ExcelIslemleriKutuphanesi kullan�larak excel dosyas�na kaydedilmektedir. 
 *
 *
 * ER���M: Public
 * 
 * 
 * GLOBAL DE���KENLER:
 * 
 * Test devam etti�i s�rece ekranda kalacak olan ProgressDialog dur.
 * ProgressDialog progress_dialog
 * 
 * Test devem etti�i s�rece ekranda duracak olan ProgressBar'�n de�erinin tutuldu�u de�i�kendir.
 * int i_progress_bar_degeri
 * 
 * Excel dosyas�na kaydedilecek tarih bilgisi i�in belirlenen formatt�r.
 * SimpleDateFormat sdf_tarih
 * 
 * G�ncel tarih bilgisinin tutuldu�u de�i�kendir.
 * String str_tarih
 * 
 * Excel dosyas�na kaydedilecek saat bilgisi i�in belirlenen formatt�r.
 * SimpleDateFormat sdf_saat
 * 
 * G�ncel saat de�erinin tutuldu�u de�i�kendir.
 * String str_saat
 * 
 * Bluetooth sens�r�ne ula�mak i�in olu�turulan nesnedir.
 * bluetooth_adapter IM_BluetoothKutuphanesi taraf�ndan bluetooth taramas� i�lemlerinde
 * kullan�laca�� i�in static olarak tan�mlanm��t�r.
 * public static BluetoothAdapter bluetooth_adaptor
 * 
 * Bluetooth i�lemlerini ger�ekle�tirmek i�in olu�turulan nesnedir.
 * IM_BluetoothKutuphanesi bluetooth_kutuphanesi
 * 
 * WiFi i�lemlerini ger�ekle�tirmek i�in olu�turulan nesnedir.
 * final IM_WifiKutuphanesi wifi_kutuphanesi
 * 
 * WiFi sens�r�ne ula�mak i�in olu�turulan nesnedir.
 * wifi_yonetici IM_WifiKutuphanesi taraf�ndan wifi taramas� s�ras�nda kullan�laca��
 * i�in static olarak tan�mlanm��t�r.
 * public static WifiManager wifi_yonetici
 * 
 * Kullan�c�n�n teste ba�lamadan �nce EditText e girece�i x koordinat� de�erini tutan de�i�kenlerdir.
 * String str_konum_x;
 * 
 * Kullan�c�n�n teste ba�lamadan �nce EditText e girece�i y koordinat� de�erini tutan de�i�kenlerdir.
 * String str_konum_y;
 * 
 * Kullan�c�n�n teste ba�lamadan �nce EditText e girece�i kat de�erini tutan de�i�kenlerdir.
 * String str_kat;
 * 
 * Pil seviyesini y�zde olarak tutan de�i�kendir.
 * String str_pil_seviyesi
 * 
 * Bir test boyunca toplanan ivme�l�er verilerinin tutulaca�� listedir.
 * ArrayList<String[]> liste_ivmeolcer
 * 
 * Bir test boyunca toplanan manyetik alan verilerinin tutulaca�� listedir.
 * ArrayList<String[]> liste_manyetik_alan
 * 
 * Bir test boyunca toplanan bluetooth verilerinin tutulaca�� listedir.
 * ArrayList<String[]> liste_bluetooth
 * 
 * Bir test boyunca toplanan wifi verilerinin tutulaca�� listedir.
 * ArrayList<String[]> liste_wifi
 * 
 * Loglama i�lemlerinin ger�ekle�tirilmesi i�in IM_ExcelIslemleriKutuphanesi t�r�nden olu�turulan nesnedir.
 * IM_ExcelIslemleriKutuphanesi excel_kutuphanesi
 * 
 * Cihaz i�erisinde bulunan sens�rlere ula�mak i�in gerekli olan sens�r y�neticisi nesnesidir.
 * sensor_yoneticisi IM_ManyetikAlanKutuphanesi taraf�ndan manyetik alan verilerini okumada,
 * IM_IvmeolcerKutuphanesi taraf�ndan ivme�l�er verilerini okumada kullan�laca��ndan dolay�
 * static olara ktan�mlanm��t�r.
 * public static SensorManager sensor_yoneticisi
 * 
 * Manyetik alan i�lemlerini ger�ekle�tirmek i�in olu�turulan nesnedir.
 * IM_ManyetikAlanKutuphanesi manyetik_alan_kutuphanesi
 * 
 * �vme�l�er i�lemlerini ger�ekle�tirmek i�in olu�turulan nesnedir.
 * IM_IvmeolcerKutuphanesi ivmeolcer_kutuphanesi
 * 
 * Test s�ras�nda wifi verisinin loglan�p loglanmamas�n�n se�ilece�i CheckedTextView d�r.
 * CheckedTextView ctv_wifi
 * 
 * Test s�ras�nda bluetooth verisinin loglan�p loglanmamas�n�n se�ilece�i CheckedTextView d�r.
 * CheckedTextView ctv_bluetooth
 * 
 * Test s�ras�nda manyetik alan verisinin loglan�p loglanmamas�n�n se�ilece�i CheckedTextView d�r.
 * CheckedTextView ctv_manyetik_alan
 * 
 * Test s�ras�nda ivme�l�er verisinin loglan�p loglanmamas�n�n se�ilece�i CheckedTextView d�r.
 * CheckedTextView ctv_ivmeolcer
 * 
 * Log Al butonuna bas�ld���nda wifi de�erlerinin loglana��n� ve loglanmayaca��n� tutan olan de�i�kendir.
 * boolean b_log_wifi
 * 
 * Log Al butonuna bas�ld���nda bluetooth de�erlerinin loglana��n� ve loglanmayaca��n� tutan olan de�i�kendir.
 * boolean b_log_bluetooth
 * 
 * Log Al butonuna bas�ld���nda manyetik alan de�erlerinin loglana��n� ve loglanmayaca��n� tutan olan de�i�kendir.
 * boolean b_log_manyetik_alan
 * 
 * Log Al butonuna bas�ld���nda ivme�l�er de�erlerinin loglana��n� ve loglanmayaca��n� tutan olan de�i�kendir.
 * boolean b_log_ivmeolcer
 * 
 * Testin al�nd��� konumun x koordinat� bilgisinin girilece�i EditText tir.
 * EditText et_x;
 * 
 * Testin al�nd��� konumun y koordinat� bilgisinin girilece�i EditText tir.
 * EditText et_y;
 * 
 * Testin al�nd��� konumun kat bilgisinin girilece�i EditText tir.
 * EditText et_kat;
 * 
 * Mevcut durumda ka��nc� wifi taramas�n�n yap�l�yor oldu�unu tutan de�i�kendir.
 * int i_wifi_sayac
 * 
 * Loglama i�leminin ba�lat�laca�� Log Al butonudur.
 * Button btn_log_al
 * 
 * Bluetooth testi i�in milisaniye cinsinden belirlenen toplam s�redir.
 * int i_bluetooth_toplam_sure
 * 
 * Herbir bluetooth �l��m� i�in milisaniye cinsinden belirlenen s�redir.
 * int i_bluetooth_tek_sure
 * 
 * 
 * FONKS�YON PROTOT�PLER�:
 * 
 * Uygulama onCreate metodu ile birlikte �al��maya ba�lar.
 * Uygulama ilk ba�lad���nda onCreate metodu sistem taraf�ndan �a�r�l�r.
 * Wifi, Bluetooth, Manyetik Alan, �vme�l�er, Pil durumu i�in gerekli izinler al�nm��t�r.
 * protected void onCreate()
 * 
 * Bu fonksiyon ile WiFi cihazlar� tarama i�lemi ba�lat�lmaktad�r.
 * public void IM_WiFiTara()
 * 
 * Bu fonksiyon ile bluetooth cihazlar� tarama i�lemi ba�lat�lmaktad�r.
 * public void IM_BluetoothTara()
 * 		
 * Bu fonksiyon ile g�ncel ivme�l�er verileri listeye eklenmektedir.
 * public void IM_IvmeolcerVeriToplama()
 *
 * Bu fonksiyon ile g�ncel manyetik alan verileri listeye eklenmektedir.
 * public void IM_ManyetikAlanVeriToplama()
 * 
 * Bu fonksiyon ile tarama ba�lad���nda progress bar ekrana ��kmakta ve
 * tarama sona erene kadar ekranda kalmaktad�r.
 * public void IM_ProgressBarBaslat(View v)
 * 
 * Bu fonksiyon ile tarama s�resinde progress bar'�n i�lemesi sa�lanmaktad�r.
 * public void IM_ProgressBarDoldur()	
 * 
 * Bu fonksiyon ile tarama sbitti�inde progress bar kapat�lmaktad�r.
 * public void IM_ProgressBarKapat()
 * 
 * Pil durumu bilgisini almak i�in kullan�lmaktad�r.
 * BroadcastReceiver PilDurumuSaglayici = new BroadcastReceiveer()	
 * 
 * Bu fonksiyon ile belirlenen formatlar excel log dosyas�na eklenmektedir. 
 * private void IM_FormatEkle()
 * 
 * Bu fonksiyon ile aray�z �zerinde bulunan CheckedTextView lar�n CheckBox
 * lar� t�klanabilir hale getirilmi�tir.
 * public void IM_CheckedTextViewTiklanabilirAyarla()
 * 
 * Bu fonksiyon ile herbir bluetooth �l��m� sonras�nda gelen veriler
 * excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir. 
 * Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r.
 * public void IM_BluetoothVerileriniListeyeEkle()
 * 
 * Bu fonksiyon ile herbir wifi �l��m� sonras�nda gelen veriler
 * excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir. 
 * Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r.
 * public void IM_WiFiVerileriniListeyeEkle()
 * 
 * Bu fonksiyon ile aray�z �zerinde girilmesi gereken bilgilerin kontrol� yap�lmaktad�r.
 * E�er bilgiler eksiksiz ise true, eksik bilgi varsa false de�erini d�nmektedir.
 * public boolean IM_BilgilerGecerliMi()
 * 
 * Bu fonksiyon ile aray�z �zerinden hangi testlerin se�ili oldu�u belirlenmektedir.
 * public void IM_TestleriBelirle(View v)	
 * 
 * Bu fonksiyon g�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.	
 * public void IM_TarihVeSaatiGuncelle()
 * 
 * Bu fonksiyon bir �l��m sonland���nda toplanan veriler excel dosyas�na yazd�r�lmaktad�r.	
 * public void IM_SeciliVerileriLogla()	
 * 
 * Bu fonksiyon WiFi sayfas� i�in g�ncellenen format sayfaya eklenmektedir.	
 * public void IM_WiFiFormatGuncelle()
 *		
 * 
 * GEL��T�RME GE�M���:
 *
 * Yazar: Fatih �NAN, Furkan G�NER
 * Tarih: 09.08.2014
 * G�ncelleme Tarihi: 13.08.2014
 * Versiyon: v2.0
 * 
 * 
 * TEL�F HAKKI:
 * 
 * GezkonLogger yaz�l�m� �novasyon M�hendislik, Teknoloji Geli�tirme, Dan��manl�k, San. ve Tic. Ltd. �ti.
 * taraf�ndan geli�tirilmi� olup her hakk� sakl�d�r. ��erisinde bulunan g�rsel, metin, kod vb. �gelerin
 * tamam�n�n veya bir b�l�m�n�n kullan�lmas� ve kamuya a��k olan/olmayan fiizksel veya dijital ortamlarda 
 * payla��lmas�/yay�nlanmas�/da��t�lmas� yasakt�r.
 *
 ******************************************************************************/

package com.gezkonlogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class IM_AnaUygulama extends Activity {

	/**
	 * Test devam etti�i s�rece ekranda kalacak olan ProgressDialog dur.
	 */
	ProgressDialog progress_dialog;
	
	/**
	 * Test devem etti�i s�rece ekranda duracak olan ProgressBar'�n de�erinin tutuldu�u de�i�kendir.
	 */
	int i_progress_bar_degeri;
	
	/**
	 * Excel dosyas�na kaydedilecek tarih bilgisi i�in belirlenen formatt�r.
	 */
	SimpleDateFormat sdf_tarih = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
	
	/**
	 * G�ncel tarih bilgisinin tutuldu�u de�i�kendir.
	 */
	String str_tarih;
	
	/**
	 * Excel dosyas�na kaydedilecek saat bilgisi i�in belirlenen formatt�r.
	 */
	SimpleDateFormat sdf_saat = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
	
	/**
	 * G�ncel saat de�erinin tutuldu�u de�i�kendir.
	 */
	String str_saat;
	
	/**
	 * Bluetooth sens�rlerine ula�mak i�in bluetooth_adaptor ve IM_BluetoothKutuphanesi nesneleri olu�turuldu.
	 * 
	 * bluetooth_adapter IM_BluetoothKutuphanesi taraf�ndan bluetooth taramas� i�lemlerinde
	 * 
	 *  kullan�laca�� i�in static olarak tan�mlanm��t�r.
	 */
	public static BluetoothAdapter bluetooth_adaptor;
	IM_BluetoothKutuphanesi bluetooth_kutuphanesi = new IM_BluetoothKutuphanesi();
	
	/**
	 * Kullan�c�n�n teste ba�lamadan �nce EditText lere girece�i verilerin tutulaca�� de�i�kenlerdir.
	 */
	String str_konum_x;
	String str_konum_y;
	String str_kat;
	
	/**
	 * Pil seviyesini y�zde olarak tutan de�i�kendir.
	 */
	String str_pil_seviyesi;
	
	/**
	 * Bir test boyunca ivme�l�er, manyetik alan, bluetooth ve wifi verilerinin tutulaca�� listelerdir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 */
	ArrayList<String[]> liste_ivmeolcer = new ArrayList<String[]>();
	ArrayList<String[]> liste_manyetik_alan = new ArrayList<String[]>();
	ArrayList<String[]> liste_bluetooth = new ArrayList<String[]>();
	ArrayList<String[]> liste_wifi = new ArrayList<String[]>();
	
	/**
	 * Loglama i�lemlerinin ger�ekle�tirilmesi i�in IM_ExcelIslemleriKutuphanesi t�r�nden nesne olu�turulmu�tur. 
	 */
	IM_ExcelIslemleriKutuphanesi excel_kutuphanesi = new IM_ExcelIslemleriKutuphanesi();
	
	/**
	 * Wifi sens�rlerine ula�mak i�in olu�turulan nesnelerdir.
	 * 
	 * wifi_yonetici IM_WifiKutuphanesi taraf�ndan wifi taramas� s�ras�nda kullan�laca��
	 * 
	 * i�in static olarak tan�mlanm��t�r.
	 */
	final IM_WifiKutuphanesi wifi_kutuphanesi=new IM_WifiKutuphanesi();
	public static WifiManager wifi_yonetici;
	
	/**
	 * �vme�l�er ve manyetik alan sens�rlerine ula�mak i�in sens�r y�neticisi olu�turuldu.
	 * 
	 * sensor_yoneticisi IM_ManyetikAlanKutuphanesi taraf�ndan manyetik alan verilerini okumada,
	 * 
	 * IM_IvmeolcerKutuphanesi taraf�ndan ivme�l�er verilerini okumada kullan�laca��ndan dolay�
	 * 
	 * static olara ktan�mlanm��t�r.
	 */
	public static SensorManager sensor_yoneticisi = null;
	IM_ManyetikAlanKutuphanesi manyetik_alan_kutuphanesi = new IM_ManyetikAlanKutuphanesi();
	IM_IvmeolcerKutuphanesi ivmeolcer_kutuphanesi = new IM_IvmeolcerKutuphanesi();
	
	/**
	 * Test s�ras�nda hangi verilerin loglanaca��n�n se�ilece�i CheckedTextView lard�r.
	 */
	CheckedTextView ctv_wifi;
	CheckedTextView ctv_bluetooth;
	CheckedTextView ctv_manyetik_alan;
	CheckedTextView ctv_ivmeolcer;
	
	/**
	 * Log Al butonuna bas�ld���nda loglanacak ve loglanmayacak parametreleri tutacak olan de�i�kenlerdir.
	 */
	boolean b_log_wifi;
	boolean b_log_bluetooth;
	boolean b_log_manyetik_alan;
	boolean b_log_ivmeolcer;

	/**
	 * Testin al�nd��� konumun x, y, kat bilgilerinin girilece�i EditText lerdir.
	 */
	EditText et_x;
	EditText et_y;
	EditText et_kat;
	
	/**
	 * Mevcut durumda ka��nc� wifi taramas�n�n yap�l�yor oldu�unu tutan de�i�kendir.
	 */
	int i_wifi_sayac = 0;

	/**
	 * Loglama i�leminin ba�lat�laca�� Log Al butonudur.
	 */
	Button btn_log_al;
	
	/**
	 * Bluetooth testi i�in toplam s�re ve herbir bluetooth �l��m� i�in belirlenen s�reler tan�mlanm��t�r.
	 */
	int i_bluetooth_toplam_sure = 66000;
	int i_bluetooth_tek_sure = 6000; 
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				onCreate </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Uygulama onCreate metodu ile birlikte �al��maya ba�lar.
	 * Uygulama ilk ba�lad���nda onCreate metodu sistem taraf�ndan �a�r�l�r.
	 * Wifi, Bluetooth, Manyetik Alan, �vme�l�er, Pil durumu i�in gerekli izinler al�nm��t�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/**
		 * Uygulama aray�z� layout dosyas�ndan �ekilmi�tir.
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * Ekran�n otomatik olarak kararma s�resi yar�m saat olarak ayarlanm��t�r.
		 * 
		 * 30 dk = 1800000 milisaniye dir.
		 */
		android.provider.Settings.System.putInt(getContentResolver(),
	            Settings.System.SCREEN_OFF_TIMEOUT, 1800000);
		
		/**
		 * Pil seviyesinin okunabilmesi i�in sisteme kay�t yap�lm��t�r.
		 */
		this.registerReceiver(this.PilDurumuSaglayici, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		/**
		 * WiFi i�lemleri i�in wifi_yoneticisi nesnesi ve wifi kutuphanesi olu�turulmu�tur.
		 * 
		 * Test s�ras�nda ilk olarak bluetooth, daha sonra wifi verileri al�naca�� i�in
		 * 
		 * bluetooth testi ba�lamadan �nce wifi kapat�lm��t�r.
		 */
		wifi_yonetici = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi_yonetici.setWifiEnabled(false);
		
		/**
		 * Bluetooth sens�rlerine ula�mak i�in sens�r adaptoru olu�turulmu�tur.
		 * 
		 * Bluetooth taramas� ger�ekle�tirilmesi i�in bluetooth kapal� ise a��lm��t�r.
		 * 
		 * Hangi mac adresine sahip bluetooth cihazlar�n�n loglanaca��n� belirlemek i�in 
		 * 
		 * bluetooth_kutuphanesi.MacAdresleriniAta() fonksiyonu kullan�lm��t�r.
		 */
		bluetooth_adaptor = BluetoothAdapter.getDefaultAdapter();
		bluetooth_adaptor.enable();
		bluetooth_kutuphanesi.IM_MacAdresleriniAta();
		
		/**
		 * �lk olarak log verilerinin yaz�laca�� excel dosyas� a��lm�� 
		 * 
		 * ve gerekli sayfalar a��lan dosyaya eklendikten sonra dosya kapat�lm��t�r.
		 */
		boolean b_dosya_acildi = excel_kutuphanesi.IM_ExcelDosyasiOlustur("GezkonLogger", "LogDosyasi.xls");
		if(b_dosya_acildi)
		{
			excel_kutuphanesi.IM_ExcelDosyasinaSayfaEkle("Ivmeolcer");
			excel_kutuphanesi.IM_ExcelDosyasinaSayfaEkle("ManyetikAlan");
			excel_kutuphanesi.IM_ExcelDosyasinaSayfaEkle("Bluetooth");
			excel_kutuphanesi.IM_ExcelDosyasinaSayfaEkle("WiFi");
			excel_kutuphanesi.IM_ExcelDosyasiKapat();
			
			/**
			 * Excel sayfalar�n�n ilk sat�rlar�nda belirtilen format sayfaya eklendi.
			 */
			IM_FormatEkle();
		}
		
		/**
		 * Uygulama ba�lad���nda daha �nceki testlerde bulunan mac adresleri listeye eklenmi�tir.
		 */
		IM_WifiKutuphanesi.liste_mac_adresleri_toplam = excel_kutuphanesi.IM_KayitliMacAdresleriniAl();

		/**
		 * �vme�l�er ve manyetik alan sens�rlerine ula�mak i�in sens�r y�neticisi olu�turulmu�tur.
		 * 
		 * �vme�l�er ve manyetik alan verilerini sistemden okumak i�in gerekli olan fonksiyonlar �a��r�lm��t�r.
		 */
		sensor_yoneticisi = (SensorManager) getSystemService(SENSOR_SERVICE);
		manyetik_alan_kutuphanesi.IM_ManyetikAlanVeriToplamaBaslat();
		ivmeolcer_kutuphanesi.IM_IvmeolcerVeriToplamaBaslat();
		
		
		/**
		 * Test s�ras�nda hangi verilerin loglanaca��n� se�en CheckedTextView lar layout dosyas�ndan �ekilmi�tir.
		 */
		ctv_wifi = (CheckedTextView) findViewById(R.id.ctv_wifi_testi);
		ctv_bluetooth = (CheckedTextView) findViewById(R.id.ctv_bluetooth_testi);
		ctv_manyetik_alan = (CheckedTextView) findViewById(R.id.ctv_manyetik_alan_testi);
		ctv_ivmeolcer = (CheckedTextView) findViewById(R.id.ctv_ivmeolcer_testi);
		IM_CheckedTextViewTiklanabilirAyarla();

		/**
		 * Testin yap�laca�� yerin x koordinat�, y koordinat� ve kat bilgilerinin girilece�i
		 * 
		 * EditText ler layout dosyas�ndan �ekilmi�tir.
		 */
		et_x = (EditText) findViewById(R.id.et_x);
		et_y = (EditText) findViewById(R.id.et_y);
		et_kat = (EditText) findViewById(R.id.et_kat);

		/**
		 * Loglama i�lemini ba�latacak olan buton layout dosyas�ndan �ekildi.
		 */
		btn_log_al = (Button) findViewById(R.id.btn_log_al);
		
		/**
		 * Log Al butonuna t�kland���nda ger�ekle�tirilecek i�lemler tan�mlanm��t�r.
		 */
		btn_log_al.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				
				/**
				 * Konum ve test bilgilerinin girilip girilmedi�i kontrol edilmekte, 
				 * 
				 * e�er bilgiler eksiksiz girilmi� ise test ba�lat�lmaktad�r.
				 */
				if(IM_BilgilerGecerliMi())
				{
					/**
					 * Aray�z �zerinden hangi testlerin se�ili oldu�u belirlenmi�tir. 
					 */
					IM_TestleriBelirle(v);
					
					/**
					  * Her 6 saniyede bir g�ncel veriler listelere eklenmektedir.
					  * 
					  * Toplam 10 test verisi topland���nda onFinish metoduna giderek dosyaya kaydetme i�lemi ger�ekle�tirilmektedir.
					  */
					 new CountDownTimer(i_bluetooth_toplam_sure, i_bluetooth_tek_sure) {
					 public void onTick(long millisUntilFinished) {
					
						 /**
						  * G�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.
						  */
						 IM_TarihVeSaatiGuncelle();
						 
						 /**
						  * CheckedTextView da se�ilen parametreler i�in tarama i�lemi yap�lmaktad�r.
						  */
						 if(b_log_ivmeolcer)
						 {
							 IM_IvmeolcerVeriToplama();
						 }
						 if(b_log_manyetik_alan)
						 {
							 IM_ManyetikAlanVeriToplama();
						 }
						 if(b_log_bluetooth)
						 {
							 IM_BluetoothTara();
						 }
						 
						 /**
						  * Herbir ad�mda ProgressBar �n de�eri artt�r�lmaktad�r.
						  */
						 IM_ProgressBarDoldur();
					 }
					 public void onFinish() {
						 /**
						  * G�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.
						  */
						 IM_TarihVeSaatiGuncelle();
						 
						 /**
						  * Bluetooth, ivme�l�er ve manyetik alan testlerinin tamamlanmas� sonras�nda ProgressBar kapat�lm��t�r.
						  */
						 IM_ProgressBarKapat();
						 
						 /**
						  * Se�ili olan veriler excel dosyas�na kaydedilmi�tir.
						  */
						 IM_SeciliVerileriLogla();
						 
						 /**
						  * Listeler excele kaydedildikten sonra, bir sonraki �l��melerde kullan�lmak �zere bo�alt�lm��t�r. 
						  */
						 liste_ivmeolcer.clear();
						 liste_manyetik_alan.clear();
						 liste_bluetooth.clear();
						 
						 /**
						  * Bluetooth, ivme�l�er ve manyetik alan loglamalar� tamamland�ktan sonra, se�ili ise
						  * 
						  * wifi loglama i�lemi ba�lat�lmaktad�r.
						  */
						 if(b_log_wifi)
						 {
							 /**
							  * Wifi tarama i�lemine ba�lamadan �nce herhangi bir bluetooth taramas� varsa sonland�r�lmakta ve 
							  * 
							  * bluetooth kapat�lmaktad�r.
							  */
							 bluetooth_adaptor.cancelDiscovery();
							 bluetooth_adaptor.disable();
							 
							 /**
							  * Bluetooth taramas� s�ras�nda kapat�lan wifi, wifi taramas� ba�lamadan �nce a��lmaktad�r.
							  */
							 wifi_yonetici.setWifiEnabled(true);
							 
							 i_wifi_sayac = 0;
							 
							 /**
							  * Wifi taramas� wifi �n a��lmas� beklenmektedir.
							  */
							 new CountDownTimer(4000, 2000) {
								 public void onTick(long millisUntilFinished) 
								 {
								 }
								 public void onFinish() 
								 {
								 } 
							 }.start();
						 
						 /**
						  * WiFi testi i�in ProgressBar olu�turulmu�tur.
						  */
						 IM_ProgressBarBaslat(v, "WiFi");
						 
						
						 /**
						  * 3 er saniye aral�klarla 10 adet wifi test verisi toplanmaktad�r.
						  */
						 
							 new CountDownTimer(48000, 4000) {
								 public void onTick(long millisUntilFinished) 
								 {
									 /**
									  * G�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.
									  */
									 IM_TarihVeSaatiGuncelle();
	
									 /**
									  * WiFi taramas� ba�lat�lm��t�r.
									  */
									 IM_WiFiTara();
									 
									 
									 /**
									  * Bir �l��m i�erisinde ka��nc� wifi taramas�nda olundu�unu tutan de�i�kenin de�eri 1 artt�r�lm��t�r.
									  */
									 i_wifi_sayac++;
									 
									 /**
									  * Her bir �l��m sonras� progress bar �n de�eri artt�r�lm��t�r.
									  */
									 IM_ProgressBarDoldur();
								 }
								 public void onFinish() 
								 {
									 /**
									  * G�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.
									  */
									 IM_TarihVeSaatiGuncelle();
									 
									 /**
									  * Wifi sayfas� i�in g�ncellenen format sayfaya eklendi.
									  */
									 IM_WiFiFormatGuncelle();
									 
									    /**
									     * WiFi testi sonucu elde edilen veriler listeye eklenmi�tir.
									     */
									    IM_WiFiVerileriniListeyeEkle();
	
										 /**
										  * WiFi testi tamamland���nda toplanan veriler excel dosyas�n�n WiFi sayfas�na yazd�r�lm��t�r.
										  */
										 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("WiFi", liste_wifi);
										 	
										 /**
										  * Yeni eklenen mac adresleri i�in s�tunlarda olu�an bo�luklar "NaN" ile doldurulmu�tur.
										  */
										 excel_kutuphanesi.IM_WiFiBoslukDoldur();
										 
										 /**
										  * Liste sonraki testlerde kullan�lmak �zere temizlenmi�tir.
										  */
										 liste_wifi.clear();
									     IM_ProgressBarKapat();
									     bluetooth_adaptor.enable();
								 }
		
							 }.start();
						 }
					 }
					 }.start();
				}
			}
		});
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_WiFiFormatGuncelle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon WiFi sayfas� i�in g�ncellenen format sayfaya eklenmektedir.   </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_WiFiFormatGuncelle()
	{
		/**
		 * Wifi sayfas� i�in g�ncellenen format sayfaya eklendi.
		 */
		ArrayList<String[]> liste_wifi_format = new ArrayList<String[]>();
		String[] wifi_format_hucreleri  = new String[102];
		wifi_format_hucreleri[0] = "�L��M";
		wifi_format_hucreleri[1] = "TAR�H";
		wifi_format_hucreleri[2] = "ZAMAN";
		wifi_format_hucreleri[3] = "KONUM_X";
		wifi_format_hucreleri[4] = "KONUM_Y";
		wifi_format_hucreleri[5] = "KAT";
		wifi_format_hucreleri[6] = "P�L DURUMU";
		int i_liste_boyutu = IM_WifiKutuphanesi.liste_mac_adresleri_toplam.size() + 7;
		for(int i=7; i<i_liste_boyutu; i++)
		{
			wifi_format_hucreleri[i] = IM_WifiKutuphanesi.liste_mac_adresleri_toplam.get(i-7);
		}
		for(int i=7+i_liste_boyutu;i<100;i++)
		{
			wifi_format_hucreleri[i] = "";
		}
		wifi_format_hucreleri[100] = "";
		wifi_format_hucreleri[101] = "";
	    liste_wifi_format.add(wifi_format_hucreleri);
	    excel_kutuphanesi.IM_WiFiFormatGuncelle("WiFi", liste_wifi_format);

	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_SeciliVerileriLogla </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon bir �l��m sonland���nda toplanan veriler excel dosyas�na yazd�r�lmaktad�r.   </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_SeciliVerileriLogla()
	{
		 /**
		  * Bir �l��m sonland���nda toplanan veriler excel dosyas�na yazd�r�lm��t�r.
		  */
		 if(b_log_ivmeolcer)
		 {
			 /**
			  * �vme�l�er verileri excel dosyas�n�n Ivmeolcer sayfas�na yazd�r�lm��t�r.
			  */
			 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Ivmeolcer", liste_ivmeolcer);
		 }
		 if(b_log_manyetik_alan)
		 {
			 /**
			  * Manyetik alan verileri excel dosyas�n�n ManyetikAlan sayfas�na yazd�r�lm��t�r.
			  */
			 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("ManyetikAlan", liste_manyetik_alan);
		 }
		 if(b_log_bluetooth)
		 {
			 /**
			  * Devam eden herhangi bir tarama bulunuyorsa iptal edilmi�tir.
			  */
			     bluetooth_adaptor.cancelDiscovery();
			    
			     IM_BluetoothVerileriniListeyeEkle();
			     
			     /**
				  * Bluetooth verileri excel dosyas�n�n Bluetooth sayfas�na yazd�r�lm��t�r.
				  */
				 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Bluetooth", liste_bluetooth);

		 }
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_TarihVeSaatiGuncelle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon g�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.   </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_TarihVeSaatiGuncelle()
	{
		str_tarih = sdf_tarih.format(new Date());
		str_saat = sdf_saat.format(new Date());
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_TestleriBelirle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile aray�z �zerinden hangi testlerin se�ili oldu�u belirlenmektedir.   </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *			v							View				Pop-up ekran�n� g�sterilece�i aray�ze referanst�r.
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI
	 * -->
	 * 
	 * @param v Pop-up ekran�n� g�sterilece�i aray�ze referanst�r.
	*********************************************************************************************/
	public void IM_TestleriBelirle(View v)
	{
		if(v != null)
		{
			String str_loglanacak_parametreler = "";
			if(b_log_bluetooth)
			{
				str_loglanacak_parametreler += "Bluetooth,";
			}
			if(b_log_manyetik_alan)
			{
				str_loglanacak_parametreler += " Manyetik Alan,";
			}
			if(b_log_ivmeolcer)
			{
				str_loglanacak_parametreler += " �vme�l�er";
			}
	
			
			if(b_log_bluetooth || b_log_manyetik_alan || b_log_ivmeolcer)
			{
				IM_ProgressBarBaslat(v, str_loglanacak_parametreler);
			}
			else
			{
				i_bluetooth_toplam_sure = 100;
				i_bluetooth_tek_sure = 100;
			}
		}
	}
	

	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_BilgilerGecerliMi </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile aray�z �zerinde girilmesi gereken bilgilerin kontrol� yap�lmaktad�r.
	 * E�er bilgiler eksiksiz ise true, eksik bilgi varsa false de�erini d�nmektedir.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI
	 * 										boolean				E�er bilgiler eksiksiz ise true, eksik bilgi varsa false de�erini d�nmektedir.			
	 * -->
	 * 
	*********************************************************************************************/
	public boolean IM_BilgilerGecerliMi()
	{
		/**
		 * Log Al butonuna t�kland���nda excel dosyas�na yazd�r�lmak �zere 
		 * 
		 * konum ve kat bilgileri edittext'lerden al�nm��t�r.
		 */
		str_konum_x = et_x.getText().toString();
		str_konum_y = et_y.getText().toString();
		str_kat = et_kat.getText().toString();
		
		/**
		 * Log Al butonuna bas�ld���nda hangi parametrelerin loglanaca�� CheckedListView lardan belirlenmi�tir.
		 */
		b_log_wifi = ctv_wifi.isChecked();
		b_log_bluetooth = ctv_bluetooth.isChecked();
		b_log_manyetik_alan = ctv_manyetik_alan.isChecked();
		b_log_ivmeolcer = ctv_ivmeolcer.isChecked();
		
		/**
		 * x koordinat�, y koordinat�, kat bilgilerinin girilip girilmedi�i kontrol edilmektedir.
		 * 
		 * En az bir  testin se�ili olup olmad��� kontrol edilmektedir.
		 * 
		 * E�er x koordinat�, y koordinat�, kat bilgileri girilmi� ve en az bir test se�ilmi�se 
		 * 
		 * loglama i�lemi ba�lat�lmakta, aksi taktirde uyar� verilerel loglama i�lemi ba�lat�lmamaktad�r.
		 */
		if(str_konum_x.contentEquals("") || str_konum_y.contentEquals("") || str_kat.contentEquals("") || !(b_log_wifi || b_log_bluetooth || b_log_manyetik_alan || b_log_ivmeolcer))
		{
			Toast.makeText(getApplicationContext(), "L�tfen x koordinat�,  y koordinat� ve kat bilgilerinin eksiksiz doldurunuz ve en az bir test se�iniz.", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_WiFiTara </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile WiFi cihazlar� tarama i�lemi ba�lat�lmaktad�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_WiFiTara()
	{
		/**
		 * Herbir wifi �l��m� sonras�nda gelen veriler excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir.
		 */
		IM_WiFiVerileriniListeyeEkle();
		
		IM_WifiKutuphanesi.cihaz_bilgiler_guncel.clear();
		
		wifi_kutuphanesi.IM_WifiTaramaBaslat();
		
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_WiFiVerileriniListeyeEkle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile herbir wifi �l��m� sonras�nda gelen veriler
	 *  excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir. 
	 *  Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_WiFiVerileriniListeyeEkle()
	{
		/**
		 * Herbir wifi �l��m� sonras�nda gelen veriler excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir.
		 * 
		 * Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r.
		 */
		if(i_wifi_sayac>1)
		{
			String[] wifi_hucreleri  = new String[IM_WifiKutuphanesi.liste_mac_adresleri_toplam.size()+7];
		    wifi_hucreleri[0] = "�l��m";
		    wifi_hucreleri[1] = str_tarih;
		    wifi_hucreleri[2] = str_saat;
		    wifi_hucreleri[3] = str_konum_x;
		    wifi_hucreleri[4] = str_konum_y;
		    wifi_hucreleri[5] = str_kat;
		    wifi_hucreleri[6] = str_pil_seviyesi;
		    for(int i=7; i<IM_WifiKutuphanesi.liste_mac_adresleri_toplam.size()+7; i++)
		    {
		    	wifi_hucreleri[i] = "NaN";
		    }
			for(int i=0; i<IM_WifiKutuphanesi.liste_mac_adresleri_toplam.size(); i++)
			{
				 for(int j=0; j<IM_WifiKutuphanesi.cihaz_bilgiler_guncel.size(); j++)
				 {
					 if(IM_WifiKutuphanesi.liste_mac_adresleri_toplam.get(i).equals(IM_WifiKutuphanesi.cihaz_bilgiler_guncel.get(j).IM_AdresGetir()))
					 {
						 wifi_hucreleri[i+7] = Double.toString(IM_WifiKutuphanesi.cihaz_bilgiler_guncel.get(j).IM_RssiGetir());
					 }
				 }
			}
			liste_wifi.add(wifi_hucreleri);
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_BluetoothTara </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile bluetooth cihazlar� tarama i�lemi ba�lat�lmaktad�r.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_BluetoothTara() 
	{
		/**
		 * E�er mevcut tarama i�lemi varsa durdurulduktan sonra yeni tarama i�lemi ba�lat�lmaktad�r.
		 */
		   if (bluetooth_adaptor.isDiscovering()) 
		   {
			   bluetooth_adaptor.cancelDiscovery();
			   IM_BluetoothVerileriniListeyeEkle();
		   }
		   IM_BluetoothKutuphanesi.liste_bluetooth.clear();
		   bluetooth_adaptor.startDiscovery();
		   registerReceiver(IM_BluetoothKutuphanesi.b_alici, new IntentFilter(BluetoothDevice.ACTION_FOUND));
	   }
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_BluetoothVerileriniListeyeEkle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile herbir bluetooth �l��m� sonras�nda gelen veriler
	 *  excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir. 
	 *  Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_BluetoothVerileriniListeyeEkle()
	{
		 /**
		 * Herbir bluetooth �l��m� sonras�nda gelen veriler excelde bulunan mac adreslerine g�re s�ralanarak listeye eklenmektedir.
		 * 
		 * Test tamamland���nda bu veriler listeden okunarak excel dosyas�na yazdr�lmaktad�r.
		 */
		 String[] bluetooth_hucreleri  = new String[19];
		 bluetooth_hucreleri[0] = "�l��m";
		 bluetooth_hucreleri[1] = str_tarih;
		 bluetooth_hucreleri[2] = str_saat;
		 bluetooth_hucreleri[3] = str_konum_x;
		 bluetooth_hucreleri[4] = str_konum_y;
		 bluetooth_hucreleri[5] = str_kat;
		 bluetooth_hucreleri[6] = str_pil_seviyesi;
		 bluetooth_hucreleri[7] = "NaN";
		 bluetooth_hucreleri[8] = "NaN";
		 bluetooth_hucreleri[9] = "NaN";
		 bluetooth_hucreleri[10] = "NaN";
		 bluetooth_hucreleri[11] = "NaN";
		 bluetooth_hucreleri[12] = "NaN";
		 bluetooth_hucreleri[13] = "NaN";
		 bluetooth_hucreleri[14] = "NaN";
		 bluetooth_hucreleri[15] = "NaN";
		 bluetooth_hucreleri[16] = "NaN";
		 bluetooth_hucreleri[17] = "NaN";
		 bluetooth_hucreleri[18] = "NaN";
		 for(int i=0; i<IM_BluetoothKutuphanesi.liste_bluetooth_mac_adresleri.size(); i++)
		 {
			 for(int j=0; j<IM_BluetoothKutuphanesi.liste_bluetooth.size(); j++)
			 {
				 if(IM_BluetoothKutuphanesi.liste_bluetooth_mac_adresleri.get(i).equals(IM_BluetoothKutuphanesi.liste_bluetooth.get(j).IM_AdresGetir()))
				 {
					 bluetooth_hucreleri[i+7] = Double.toString(IM_BluetoothKutuphanesi.liste_bluetooth.get(j).IM_RssiGetir());
				 }
			 }
		 }
		 liste_bluetooth.add(bluetooth_hucreleri);
	}
	
	
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_IvmeolcerVeriToplama </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile g�ncel ivme�l�er verileri listeye eklenmektedir. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_IvmeolcerVeriToplama()
	{
		/**
		  * �vme�l�er verileri dosyaya eklenmek �zere listeye eklenmi�tir.
		  */
		 String[] ivmeolcer_hucreleri  = new String[10];
		 ivmeolcer_hucreleri[0] = "�l��m";
		 ivmeolcer_hucreleri[1] = str_tarih;
		 ivmeolcer_hucreleri[2] = str_saat;
		 ivmeolcer_hucreleri[3] = str_konum_x;
		 ivmeolcer_hucreleri[4] = str_konum_y;
		 ivmeolcer_hucreleri[5] = str_kat;
		 ivmeolcer_hucreleri[6] = str_pil_seviyesi;
		 ivmeolcer_hucreleri[7] = String.valueOf(ivmeolcer_kutuphanesi.IM_IvmeolcerGetirX());
		 ivmeolcer_hucreleri[8] = String.valueOf(ivmeolcer_kutuphanesi.IM_IvmeolcerGetirY());
		 ivmeolcer_hucreleri[9] = String.valueOf(ivmeolcer_kutuphanesi.IM_IvmeolcerGetirZ());
		 liste_ivmeolcer.add(ivmeolcer_hucreleri);
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ManyetikAlanVeriToplama </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile g�ncel manyetik alan verileri listeye eklenmektedir. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_ManyetikAlanVeriToplama()
	{
		 /**
		  * Manyetik alan verileri dosyaya eklenmek �zere listeye eklenmi�tir.
		  */
		 String[] manyetik_alan_hucreleri  = new String[10];
		 manyetik_alan_hucreleri[0] = "�l��m";
		 manyetik_alan_hucreleri[1] = str_tarih;
		 manyetik_alan_hucreleri[2] = str_saat;
		 manyetik_alan_hucreleri[3] = str_konum_x;
		 manyetik_alan_hucreleri[4] = str_konum_y;
		 manyetik_alan_hucreleri[5] = str_kat;
		 manyetik_alan_hucreleri[6] = str_pil_seviyesi;
		 manyetik_alan_hucreleri[7] = String.valueOf(manyetik_alan_kutuphanesi.IM_ManyetikAlanGetirX());
		 manyetik_alan_hucreleri[8] = String.valueOf(manyetik_alan_kutuphanesi.IM_ManyetikAlanGetirY());
		 manyetik_alan_hucreleri[9] = String.valueOf(manyetik_alan_kutuphanesi.IM_ManyetikAlanGetirZ()); 
		 liste_manyetik_alan.add(manyetik_alan_hucreleri);
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ProgressBarBaslat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile tarama ba�lad���nda progress bar ekrana ��kmakta ve
	 * tarama sona erene kadar ekranda kalmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 * 			v							View				Uygulaman�n �al��aca�� ekran�n referans g�r�nt�s�d�r.
	 * 			str_test_ismi				String				"str_test_ismi Loglan�yor" �eklinde g�r�nt�lenecek mesajd�r.
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	 * @param v Uygulaman�n �al��aca�� ekran�n referans g�r�nt�s�d�r.
	 * @param str_test_ismi "str_test_ismi Loglan�yor" �eklinde g�r�nt�lenecek mesajd�r.
	*********************************************************************************************/
	public void IM_ProgressBarBaslat(View v, String str_test_ismi)
	{
		if(v != null && str_test_ismi != null)
		{
			/**
			 * ProgressBar olu�turulmu�tur.
			 */
			progress_dialog = new ProgressDialog(v.getContext());
			
			/**
			 * ProgressBar yatay olacak �ekilde ve mesaj� "Loglan�yor ..." olacak �ekilde ayarlanm�t�r.
			 */
			progress_dialog.setMessage(str_test_ismi + " Loglan�yor ...");
			progress_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			
			/**
			 * ProgressBar �n alabilece�i maksimum ve minimum de�erler belirlenmi�tir.
			 */
			progress_dialog.setProgress(0);
			progress_dialog.setMax(100);
			
			/**
			 * ProgressBar aray�z �zerinde g�sterilmi� ve iptal edilebilirli�i false olarak ayarlanm��t�r.
			 */
			progress_dialog.show();
			progress_dialog.setCancelable(false);
			
	        /**
	         * ProgressBar �n ba�lang�� de�eri 0 olarak ayarlanm��t�r.
	         */
	        i_progress_bar_degeri = 0;
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ProgressBarDoldur </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile tarama s�resinde progress bar'�n i�lemesi sa�lanmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_ProgressBarDoldur()
	{
		/**
		 * ProgressBar �n de�eri artt�r�lm�� ve aray�z �zerinde g�ncellenmi�tir.
		 */
		i_progress_bar_degeri = i_progress_bar_degeri + 10;
	     
		progress_dialog.setProgress(i_progress_bar_degeri);		 
			
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_ProgressBarKapat </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile tarama sbitti�inde progress bar kapat�lmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_ProgressBarKapat()
	{
		/**
		 * ProgressBar doldu�unda (loglama i�lemi tamamland���nda) kapat�lm��t�r.
		 */
		if (i_progress_bar_degeri >= 100) 
		{
			progress_dialog.dismiss();
			i_progress_bar_degeri = 0;
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				PilDurumuSaglayici </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Pil durumu bilgisini almak i�in kullan�lmaktad�r. </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	private BroadcastReceiver PilDurumuSaglayici = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{
		
			/**
			 * pil seviyesi sistemden okunarak de�i�kene aktar�lm��t�r.
			 */
			int i_pil_seviyesi= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
			
		    str_pil_seviyesi = String.valueOf(i_pil_seviyesi);
		}
	};
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_FormatEkle </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile belirlenen formatlar excel log dosyas�na eklenmektedir.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	private void IM_FormatEkle()
	{
		/**
		 * Wifi sayfas� i�in belirlenen format sayfaya eklendi.
		 */
		ArrayList<String[]> liste_wifi_format = new ArrayList<String[]>();
		String[] wifi_format_hucreleri  = new String[102];
		wifi_format_hucreleri[0] = "�L��M";
		wifi_format_hucreleri[1] = "TAR�H";
		wifi_format_hucreleri[2] = "ZAMAN";
		wifi_format_hucreleri[3] = "KONUM_X";
		wifi_format_hucreleri[4] = "KONUM_Y";
		wifi_format_hucreleri[5] = "KAT";
		wifi_format_hucreleri[6] = "P�L DURUMU";
		for(int i=7;i<100;i++)
		{
			wifi_format_hucreleri[i] = "";
		}
		wifi_format_hucreleri[100] = "0";
		wifi_format_hucreleri[101] = "1";
	    liste_wifi_format.add(wifi_format_hucreleri);
	    excel_kutuphanesi.IM_ExcelDosyasinayaYaz("WiFi", liste_wifi_format);
	    
	    /**
		 * Bluetooth sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_bluetooth_format = new ArrayList<String[]>();
		String[] bluetooth_format_hucreleri  = new String[102];
		bluetooth_format_hucreleri[0] = "�L��M";
		bluetooth_format_hucreleri[1] = "TAR�H";
		bluetooth_format_hucreleri[2] = "ZAMAN";
		bluetooth_format_hucreleri[3] = "KONUM_X";
		bluetooth_format_hucreleri[4] = "KONUM_Y";
		bluetooth_format_hucreleri[5] = "KAT";
		bluetooth_format_hucreleri[6] = "P�L DURUMU";
		bluetooth_format_hucreleri[7] = "4C:A5:6D:1D:3C:AB(Furkan-s4Mini)";
		bluetooth_format_hucreleri[8] = "22:22:EB:ED:16:0F(Fatih-Gio)";
		bluetooth_format_hucreleri[9] = "BC:44:86:F2:C0:00(�novasyon-s4Mini)";
		bluetooth_format_hucreleri[10] = "78:A8:73:5A:C6:FF(Furkan-Tablet)";
		bluetooth_format_hucreleri[11] = "E0:B9:A5:F6:2B:E6(Furkan-Bilgisayar)";
		bluetooth_format_hucreleri[12] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[13] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[14] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[15] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[16] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[17] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[18] = "XX:XX:XX:XX:XX:XX(SSID)";
		bluetooth_format_hucreleri[19] = "";
		for(int i=20;i<100;i++)
		{
			bluetooth_format_hucreleri[i] = "";
		}
		bluetooth_format_hucreleri[100] = "0";
		bluetooth_format_hucreleri[101] = "1";
		liste_bluetooth_format.add(bluetooth_format_hucreleri);
	    excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Bluetooth", liste_bluetooth_format);
	    
	    /**
		 * Manyetik Alan sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_manyetik_alan_format = new ArrayList<String[]>();
		String[] manyetik_alan_format_hucreleri  = new String[102];
		manyetik_alan_format_hucreleri[0] = "�L��M";
		manyetik_alan_format_hucreleri[1] = "TAR�H";
		manyetik_alan_format_hucreleri[2] = "ZAMAN";
		manyetik_alan_format_hucreleri[3] = "KONUM_X";
		manyetik_alan_format_hucreleri[4] = "KONUM_Y";
		manyetik_alan_format_hucreleri[5] = "KAT";
		manyetik_alan_format_hucreleri[6] = "P�L DURUMU";
		manyetik_alan_format_hucreleri[7] = "X";
		manyetik_alan_format_hucreleri[8] = "Y";
		manyetik_alan_format_hucreleri[9] = "Z";
		for(int i=10;i<100;i++)
		{
			manyetik_alan_format_hucreleri[i] = "";
		}
		manyetik_alan_format_hucreleri[100] = "0";
		manyetik_alan_format_hucreleri[101] = "1";
		liste_manyetik_alan_format.add(manyetik_alan_format_hucreleri);
	    excel_kutuphanesi.IM_ExcelDosyasinayaYaz("ManyetikAlan", liste_manyetik_alan_format);
	    
	    /**
		 * �vme�l�er sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_ivmeolcer_format = new ArrayList<String[]>();
		String[] ivmeolcer_format_hucreleri  = new String[102];
		ivmeolcer_format_hucreleri[0] = "�L��M";
		ivmeolcer_format_hucreleri[1] = "TAR�H";
		ivmeolcer_format_hucreleri[2] = "ZAMAN";
		ivmeolcer_format_hucreleri[3] = "KONUM_X";
		ivmeolcer_format_hucreleri[4] = "KONUM_Y";
		ivmeolcer_format_hucreleri[5] = "KAT";
		ivmeolcer_format_hucreleri[6] = "P�L DURUMU";
		ivmeolcer_format_hucreleri[7] = "X";
		ivmeolcer_format_hucreleri[8] = "Y";
		ivmeolcer_format_hucreleri[9] = "Z";
		for(int i=10;i<100;i++)
		{
			ivmeolcer_format_hucreleri[i] = "";
		}
		ivmeolcer_format_hucreleri[100] = "0";
		ivmeolcer_format_hucreleri[101] = "1";
		liste_ivmeolcer_format.add(ivmeolcer_format_hucreleri);
	    excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Ivmeolcer", liste_ivmeolcer_format);
	}


	


	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				IM_CheckedTextViewTiklanabilirAyarla </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon ile aray�z �zerinde bulunan CheckedTextView lar�n CheckBox
	 * lar� t�klanabilir hale getirilmi�tir.  </br> </br>
	 *
	 * ER���M: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							T�P�				A�IKLAMASI
	 *
	 * D�N��:	
	 * 			ADI							T�P�				A�IKLAMASI			
	 * -->
	 * 
	*********************************************************************************************/
	public void IM_CheckedTextViewTiklanabilirAyarla() 
	{
		/**
		 * WiFi CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_wifi.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Bluetooth CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_bluetooth.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Manyetik Alan CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_manyetik_alan.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * �vme�l�er CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_ivmeolcer.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});
		
		/**
		 * CheckedTextView lar�n uygulama ba�lad���nda se�ili olarak gelmesi sa�lanm��t�r.
		 */
		ctv_wifi.setChecked(true);
		ctv_bluetooth.setChecked(true);
		ctv_manyetik_alan.setChecked(true);
		ctv_ivmeolcer.setChecked(true);
	}

}
