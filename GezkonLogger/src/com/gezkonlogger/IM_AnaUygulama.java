/*******************************************************************************
 * IM_AnaUygulama.java
 *
 * AMAÇ:
 * IM_AnaUygulama ile IM_IvmeolcerKutuphanesi, IM_ManyetikAlanKutuphanesi, IMBluetoothKutuphanesi ve 
 * IM_WifiKutuphanesi tarafýndan belirlenen aralýklarla toplanan veriler 
 * IM_ExcelIslemleriKutuphanesi kullanýlarak excel dosyasýna kaydedilmektedir. 
 *
 *
 * ERÝÞÝM: Public
 * 
 * 
 * GLOBAL DEÐÝÞKENLER:
 * 
 * Test devam ettiði sürece ekranda kalacak olan ProgressDialog dur.
 * ProgressDialog progress_dialog
 * 
 * Test devem ettiði sürece ekranda duracak olan ProgressBar'ýn deðerinin tutulduðu deðiþkendir.
 * int i_progress_bar_degeri
 * 
 * Excel dosyasýna kaydedilecek tarih bilgisi için belirlenen formattýr.
 * SimpleDateFormat sdf_tarih
 * 
 * Güncel tarih bilgisinin tutulduðu deðiþkendir.
 * String str_tarih
 * 
 * Excel dosyasýna kaydedilecek saat bilgisi için belirlenen formattýr.
 * SimpleDateFormat sdf_saat
 * 
 * Güncel saat deðerinin tutulduðu deðiþkendir.
 * String str_saat
 * 
 * Bluetooth sensörüne ulaþmak için oluþturulan nesnedir.
 * bluetooth_adapter IM_BluetoothKutuphanesi tarafýndan bluetooth taramasý iþlemlerinde
 * kullanýlacaðý için static olarak tanýmlanmýþtýr.
 * public static BluetoothAdapter bluetooth_adaptor
 * 
 * Bluetooth iþlemlerini gerçekleþtirmek için oluþturulan nesnedir.
 * IM_BluetoothKutuphanesi bluetooth_kutuphanesi
 * 
 * WiFi iþlemlerini gerçekleþtirmek için oluþturulan nesnedir.
 * final IM_WifiKutuphanesi wifi_kutuphanesi
 * 
 * WiFi sensörüne ulaþmak için oluþturulan nesnedir.
 * wifi_yonetici IM_WifiKutuphanesi tarafýndan wifi taramasý sýrasýnda kullanýlacaðý
 * için static olarak tanýmlanmýþtýr.
 * public static WifiManager wifi_yonetici
 * 
 * Kullanýcýnýn teste baþlamadan önce EditText e gireceði x koordinatý deðerini tutan deðiþkenlerdir.
 * String str_konum_x;
 * 
 * Kullanýcýnýn teste baþlamadan önce EditText e gireceði y koordinatý deðerini tutan deðiþkenlerdir.
 * String str_konum_y;
 * 
 * Kullanýcýnýn teste baþlamadan önce EditText e gireceði kat deðerini tutan deðiþkenlerdir.
 * String str_kat;
 * 
 * Pil seviyesini yüzde olarak tutan deðiþkendir.
 * String str_pil_seviyesi
 * 
 * Bir test boyunca toplanan ivmeölçer verilerinin tutulacaðý listedir.
 * ArrayList<String[]> liste_ivmeolcer
 * 
 * Bir test boyunca toplanan manyetik alan verilerinin tutulacaðý listedir.
 * ArrayList<String[]> liste_manyetik_alan
 * 
 * Bir test boyunca toplanan bluetooth verilerinin tutulacaðý listedir.
 * ArrayList<String[]> liste_bluetooth
 * 
 * Bir test boyunca toplanan wifi verilerinin tutulacaðý listedir.
 * ArrayList<String[]> liste_wifi
 * 
 * Loglama iþlemlerinin gerçekleþtirilmesi için IM_ExcelIslemleriKutuphanesi türünden oluþturulan nesnedir.
 * IM_ExcelIslemleriKutuphanesi excel_kutuphanesi
 * 
 * Cihaz içerisinde bulunan sensörlere ulaþmak için gerekli olan sensör yöneticisi nesnesidir.
 * sensor_yoneticisi IM_ManyetikAlanKutuphanesi tarafýndan manyetik alan verilerini okumada,
 * IM_IvmeolcerKutuphanesi tarafýndan ivmeölçer verilerini okumada kullanýlacaðýndan dolayý
 * static olara ktanýmlanmýþtýr.
 * public static SensorManager sensor_yoneticisi
 * 
 * Manyetik alan iþlemlerini gerçekleþtirmek için oluþturulan nesnedir.
 * IM_ManyetikAlanKutuphanesi manyetik_alan_kutuphanesi
 * 
 * Ývmeölçer iþlemlerini gerçekleþtirmek için oluþturulan nesnedir.
 * IM_IvmeolcerKutuphanesi ivmeolcer_kutuphanesi
 * 
 * Test sýrasýnda wifi verisinin loglanýp loglanmamasýnýn seçileceði CheckedTextView dýr.
 * CheckedTextView ctv_wifi
 * 
 * Test sýrasýnda bluetooth verisinin loglanýp loglanmamasýnýn seçileceði CheckedTextView dýr.
 * CheckedTextView ctv_bluetooth
 * 
 * Test sýrasýnda manyetik alan verisinin loglanýp loglanmamasýnýn seçileceði CheckedTextView dýr.
 * CheckedTextView ctv_manyetik_alan
 * 
 * Test sýrasýnda ivmeölçer verisinin loglanýp loglanmamasýnýn seçileceði CheckedTextView dýr.
 * CheckedTextView ctv_ivmeolcer
 * 
 * Log Al butonuna basýldýðýnda wifi deðerlerinin loglanaðýný ve loglanmayacaðýný tutan olan deðiþkendir.
 * boolean b_log_wifi
 * 
 * Log Al butonuna basýldýðýnda bluetooth deðerlerinin loglanaðýný ve loglanmayacaðýný tutan olan deðiþkendir.
 * boolean b_log_bluetooth
 * 
 * Log Al butonuna basýldýðýnda manyetik alan deðerlerinin loglanaðýný ve loglanmayacaðýný tutan olan deðiþkendir.
 * boolean b_log_manyetik_alan
 * 
 * Log Al butonuna basýldýðýnda ivmeölçer deðerlerinin loglanaðýný ve loglanmayacaðýný tutan olan deðiþkendir.
 * boolean b_log_ivmeolcer
 * 
 * Testin alýndýðý konumun x koordinatý bilgisinin girileceði EditText tir.
 * EditText et_x;
 * 
 * Testin alýndýðý konumun y koordinatý bilgisinin girileceði EditText tir.
 * EditText et_y;
 * 
 * Testin alýndýðý konumun kat bilgisinin girileceði EditText tir.
 * EditText et_kat;
 * 
 * Mevcut durumda kaçýncý wifi taramasýnýn yapýlýyor olduðunu tutan deðiþkendir.
 * int i_wifi_sayac
 * 
 * Loglama iþleminin baþlatýlacaðý Log Al butonudur.
 * Button btn_log_al
 * 
 * Bluetooth testi için milisaniye cinsinden belirlenen toplam süredir.
 * int i_bluetooth_toplam_sure
 * 
 * Herbir bluetooth ölçümü için milisaniye cinsinden belirlenen süredir.
 * int i_bluetooth_tek_sure
 * 
 * 
 * FONKSÝYON PROTOTÝPLERÝ:
 * 
 * Uygulama onCreate metodu ile birlikte çalýþmaya baþlar.
 * Uygulama ilk baþladýðýnda onCreate metodu sistem tarafýndan çaðrýlýr.
 * Wifi, Bluetooth, Manyetik Alan, Ývmeölçer, Pil durumu için gerekli izinler alýnmýþtýr.
 * protected void onCreate()
 * 
 * Bu fonksiyon ile WiFi cihazlarý tarama iþlemi baþlatýlmaktadýr.
 * public void IM_WiFiTara()
 * 
 * Bu fonksiyon ile bluetooth cihazlarý tarama iþlemi baþlatýlmaktadýr.
 * public void IM_BluetoothTara()
 * 		
 * Bu fonksiyon ile güncel ivmeölçer verileri listeye eklenmektedir.
 * public void IM_IvmeolcerVeriToplama()
 *
 * Bu fonksiyon ile güncel manyetik alan verileri listeye eklenmektedir.
 * public void IM_ManyetikAlanVeriToplama()
 * 
 * Bu fonksiyon ile tarama baþladýðýnda progress bar ekrana çýkmakta ve
 * tarama sona erene kadar ekranda kalmaktadýr.
 * public void IM_ProgressBarBaslat(View v)
 * 
 * Bu fonksiyon ile tarama süresinde progress bar'ýn iþlemesi saðlanmaktadýr.
 * public void IM_ProgressBarDoldur()	
 * 
 * Bu fonksiyon ile tarama sbittiðinde progress bar kapatýlmaktadýr.
 * public void IM_ProgressBarKapat()
 * 
 * Pil durumu bilgisini almak için kullanýlmaktadýr.
 * BroadcastReceiver PilDurumuSaglayici = new BroadcastReceiveer()	
 * 
 * Bu fonksiyon ile belirlenen formatlar excel log dosyasýna eklenmektedir. 
 * private void IM_FormatEkle()
 * 
 * Bu fonksiyon ile arayüz üzerinde bulunan CheckedTextView larýn CheckBox
 * larý týklanabilir hale getirilmiþtir.
 * public void IM_CheckedTextViewTiklanabilirAyarla()
 * 
 * Bu fonksiyon ile herbir bluetooth ölçümü sonrasýnda gelen veriler
 * excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir. 
 * Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr.
 * public void IM_BluetoothVerileriniListeyeEkle()
 * 
 * Bu fonksiyon ile herbir wifi ölçümü sonrasýnda gelen veriler
 * excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir. 
 * Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr.
 * public void IM_WiFiVerileriniListeyeEkle()
 * 
 * Bu fonksiyon ile arayüz üzerinde girilmesi gereken bilgilerin kontrolü yapýlmaktadýr.
 * Eðer bilgiler eksiksiz ise true, eksik bilgi varsa false deðerini dönmektedir.
 * public boolean IM_BilgilerGecerliMi()
 * 
 * Bu fonksiyon ile arayüz üzerinden hangi testlerin seçili olduðu belirlenmektedir.
 * public void IM_TestleriBelirle(View v)	
 * 
 * Bu fonksiyon güncel tarih ve saat bilgisi sistemden alýnmaktadýr.	
 * public void IM_TarihVeSaatiGuncelle()
 * 
 * Bu fonksiyon bir ölçüm sonlandýðýnda toplanan veriler excel dosyasýna yazdýrýlmaktadýr.	
 * public void IM_SeciliVerileriLogla()	
 * 
 * Bu fonksiyon WiFi sayfasý için güncellenen format sayfaya eklenmektedir.	
 * public void IM_WiFiFormatGuncelle()
 *		
 * 
 * GELÝÞTÝRME GEÇMÝÞÝ:
 *
 * Yazar: Fatih ÝNAN, Furkan GÜNER
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
	 * Test devam ettiði sürece ekranda kalacak olan ProgressDialog dur.
	 */
	ProgressDialog progress_dialog;
	
	/**
	 * Test devem ettiði sürece ekranda duracak olan ProgressBar'ýn deðerinin tutulduðu deðiþkendir.
	 */
	int i_progress_bar_degeri;
	
	/**
	 * Excel dosyasýna kaydedilecek tarih bilgisi için belirlenen formattýr.
	 */
	SimpleDateFormat sdf_tarih = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
	
	/**
	 * Güncel tarih bilgisinin tutulduðu deðiþkendir.
	 */
	String str_tarih;
	
	/**
	 * Excel dosyasýna kaydedilecek saat bilgisi için belirlenen formattýr.
	 */
	SimpleDateFormat sdf_saat = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
	
	/**
	 * Güncel saat deðerinin tutulduðu deðiþkendir.
	 */
	String str_saat;
	
	/**
	 * Bluetooth sensörlerine ulaþmak için bluetooth_adaptor ve IM_BluetoothKutuphanesi nesneleri oluþturuldu.
	 * 
	 * bluetooth_adapter IM_BluetoothKutuphanesi tarafýndan bluetooth taramasý iþlemlerinde
	 * 
	 *  kullanýlacaðý için static olarak tanýmlanmýþtýr.
	 */
	public static BluetoothAdapter bluetooth_adaptor;
	IM_BluetoothKutuphanesi bluetooth_kutuphanesi = new IM_BluetoothKutuphanesi();
	
	/**
	 * Kullanýcýnýn teste baþlamadan önce EditText lere gireceði verilerin tutulacaðý deðiþkenlerdir.
	 */
	String str_konum_x;
	String str_konum_y;
	String str_kat;
	
	/**
	 * Pil seviyesini yüzde olarak tutan deðiþkendir.
	 */
	String str_pil_seviyesi;
	
	/**
	 * Bir test boyunca ivmeölçer, manyetik alan, bluetooth ve wifi verilerinin tutulacaðý listelerdir.
	 * 
	 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
	 */
	ArrayList<String[]> liste_ivmeolcer = new ArrayList<String[]>();
	ArrayList<String[]> liste_manyetik_alan = new ArrayList<String[]>();
	ArrayList<String[]> liste_bluetooth = new ArrayList<String[]>();
	ArrayList<String[]> liste_wifi = new ArrayList<String[]>();
	
	/**
	 * Loglama iþlemlerinin gerçekleþtirilmesi için IM_ExcelIslemleriKutuphanesi türünden nesne oluþturulmuþtur. 
	 */
	IM_ExcelIslemleriKutuphanesi excel_kutuphanesi = new IM_ExcelIslemleriKutuphanesi();
	
	/**
	 * Wifi sensörlerine ulaþmak için oluþturulan nesnelerdir.
	 * 
	 * wifi_yonetici IM_WifiKutuphanesi tarafýndan wifi taramasý sýrasýnda kullanýlacaðý
	 * 
	 * için static olarak tanýmlanmýþtýr.
	 */
	final IM_WifiKutuphanesi wifi_kutuphanesi=new IM_WifiKutuphanesi();
	public static WifiManager wifi_yonetici;
	
	/**
	 * Ývmeölçer ve manyetik alan sensörlerine ulaþmak için sensör yöneticisi oluþturuldu.
	 * 
	 * sensor_yoneticisi IM_ManyetikAlanKutuphanesi tarafýndan manyetik alan verilerini okumada,
	 * 
	 * IM_IvmeolcerKutuphanesi tarafýndan ivmeölçer verilerini okumada kullanýlacaðýndan dolayý
	 * 
	 * static olara ktanýmlanmýþtýr.
	 */
	public static SensorManager sensor_yoneticisi = null;
	IM_ManyetikAlanKutuphanesi manyetik_alan_kutuphanesi = new IM_ManyetikAlanKutuphanesi();
	IM_IvmeolcerKutuphanesi ivmeolcer_kutuphanesi = new IM_IvmeolcerKutuphanesi();
	
	/**
	 * Test sýrasýnda hangi verilerin loglanacaðýnýn seçileceði CheckedTextView lardýr.
	 */
	CheckedTextView ctv_wifi;
	CheckedTextView ctv_bluetooth;
	CheckedTextView ctv_manyetik_alan;
	CheckedTextView ctv_ivmeolcer;
	
	/**
	 * Log Al butonuna basýldýðýnda loglanacak ve loglanmayacak parametreleri tutacak olan deðiþkenlerdir.
	 */
	boolean b_log_wifi;
	boolean b_log_bluetooth;
	boolean b_log_manyetik_alan;
	boolean b_log_ivmeolcer;

	/**
	 * Testin alýndýðý konumun x, y, kat bilgilerinin girileceði EditText lerdir.
	 */
	EditText et_x;
	EditText et_y;
	EditText et_kat;
	
	/**
	 * Mevcut durumda kaçýncý wifi taramasýnýn yapýlýyor olduðunu tutan deðiþkendir.
	 */
	int i_wifi_sayac = 0;

	/**
	 * Loglama iþleminin baþlatýlacaðý Log Al butonudur.
	 */
	Button btn_log_al;
	
	/**
	 * Bluetooth testi için toplam süre ve herbir bluetooth ölçümü için belirlenen süreler tanýmlanmýþtýr.
	 */
	int i_bluetooth_toplam_sure = 66000;
	int i_bluetooth_tek_sure = 6000; 
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				onCreate </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Uygulama onCreate metodu ile birlikte çalýþmaya baþlar.
	 * Uygulama ilk baþladýðýnda onCreate metodu sistem tarafýndan çaðrýlýr.
	 * Wifi, Bluetooth, Manyetik Alan, Ývmeölçer, Pil durumu için gerekli izinler alýnmýþtýr. </br> </br>
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
	protected void onCreate(Bundle savedInstanceState) {
		/**
		 * Uygulama arayüzü layout dosyasýndan çekilmiþtir.
		 */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * Ekranýn otomatik olarak kararma süresi yarým saat olarak ayarlanmýþtýr.
		 * 
		 * 30 dk = 1800000 milisaniye dir.
		 */
		android.provider.Settings.System.putInt(getContentResolver(),
	            Settings.System.SCREEN_OFF_TIMEOUT, 1800000);
		
		/**
		 * Pil seviyesinin okunabilmesi için sisteme kayýt yapýlmýþtýr.
		 */
		this.registerReceiver(this.PilDurumuSaglayici, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		/**
		 * WiFi iþlemleri için wifi_yoneticisi nesnesi ve wifi kutuphanesi oluþturulmuþtur.
		 * 
		 * Test sýrasýnda ilk olarak bluetooth, daha sonra wifi verileri alýnacaðý için
		 * 
		 * bluetooth testi baþlamadan önce wifi kapatýlmýþtýr.
		 */
		wifi_yonetici = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi_yonetici.setWifiEnabled(false);
		
		/**
		 * Bluetooth sensörlerine ulaþmak için sensör adaptoru oluþturulmuþtur.
		 * 
		 * Bluetooth taramasý gerçekleþtirilmesi için bluetooth kapalý ise açýlmýþtýr.
		 * 
		 * Hangi mac adresine sahip bluetooth cihazlarýnýn loglanacaðýný belirlemek için 
		 * 
		 * bluetooth_kutuphanesi.MacAdresleriniAta() fonksiyonu kullanýlmýþtýr.
		 */
		bluetooth_adaptor = BluetoothAdapter.getDefaultAdapter();
		bluetooth_adaptor.enable();
		bluetooth_kutuphanesi.IM_MacAdresleriniAta();
		
		/**
		 * Ýlk olarak log verilerinin yazýlacaðý excel dosyasý açýlmýþ 
		 * 
		 * ve gerekli sayfalar açýlan dosyaya eklendikten sonra dosya kapatýlmýþtýr.
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
			 * Excel sayfalarýnýn ilk satýrlarýnda belirtilen format sayfaya eklendi.
			 */
			IM_FormatEkle();
		}
		
		/**
		 * Uygulama baþladýðýnda daha önceki testlerde bulunan mac adresleri listeye eklenmiþtir.
		 */
		IM_WifiKutuphanesi.liste_mac_adresleri_toplam = excel_kutuphanesi.IM_KayitliMacAdresleriniAl();

		/**
		 * Ývmeölçer ve manyetik alan sensörlerine ulaþmak için sensör yöneticisi oluþturulmuþtur.
		 * 
		 * Ývmeölçer ve manyetik alan verilerini sistemden okumak için gerekli olan fonksiyonlar çaðýrýlmýþtýr.
		 */
		sensor_yoneticisi = (SensorManager) getSystemService(SENSOR_SERVICE);
		manyetik_alan_kutuphanesi.IM_ManyetikAlanVeriToplamaBaslat();
		ivmeolcer_kutuphanesi.IM_IvmeolcerVeriToplamaBaslat();
		
		
		/**
		 * Test sýrasýnda hangi verilerin loglanacaðýný seçen CheckedTextView lar layout dosyasýndan çekilmiþtir.
		 */
		ctv_wifi = (CheckedTextView) findViewById(R.id.ctv_wifi_testi);
		ctv_bluetooth = (CheckedTextView) findViewById(R.id.ctv_bluetooth_testi);
		ctv_manyetik_alan = (CheckedTextView) findViewById(R.id.ctv_manyetik_alan_testi);
		ctv_ivmeolcer = (CheckedTextView) findViewById(R.id.ctv_ivmeolcer_testi);
		IM_CheckedTextViewTiklanabilirAyarla();

		/**
		 * Testin yapýlacaðý yerin x koordinatý, y koordinatý ve kat bilgilerinin girileceði
		 * 
		 * EditText ler layout dosyasýndan çekilmiþtir.
		 */
		et_x = (EditText) findViewById(R.id.et_x);
		et_y = (EditText) findViewById(R.id.et_y);
		et_kat = (EditText) findViewById(R.id.et_kat);

		/**
		 * Loglama iþlemini baþlatacak olan buton layout dosyasýndan çekildi.
		 */
		btn_log_al = (Button) findViewById(R.id.btn_log_al);
		
		/**
		 * Log Al butonuna týklandýðýnda gerçekleþtirilecek iþlemler tanýmlanmýþtýr.
		 */
		btn_log_al.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				
				/**
				 * Konum ve test bilgilerinin girilip girilmediði kontrol edilmekte, 
				 * 
				 * eðer bilgiler eksiksiz girilmiþ ise test baþlatýlmaktadýr.
				 */
				if(IM_BilgilerGecerliMi())
				{
					/**
					 * Arayüz üzerinden hangi testlerin seçili olduðu belirlenmiþtir. 
					 */
					IM_TestleriBelirle(v);
					
					/**
					  * Her 6 saniyede bir güncel veriler listelere eklenmektedir.
					  * 
					  * Toplam 10 test verisi toplandýðýnda onFinish metoduna giderek dosyaya kaydetme iþlemi gerçekleþtirilmektedir.
					  */
					 new CountDownTimer(i_bluetooth_toplam_sure, i_bluetooth_tek_sure) {
					 public void onTick(long millisUntilFinished) {
					
						 /**
						  * Güncel tarih ve saat bilgisi sistemden alýnmaktadýr.
						  */
						 IM_TarihVeSaatiGuncelle();
						 
						 /**
						  * CheckedTextView da seçilen parametreler için tarama iþlemi yapýlmaktadýr.
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
						  * Herbir adýmda ProgressBar ýn deðeri arttýrýlmaktadýr.
						  */
						 IM_ProgressBarDoldur();
					 }
					 public void onFinish() {
						 /**
						  * Güncel tarih ve saat bilgisi sistemden alýnmaktadýr.
						  */
						 IM_TarihVeSaatiGuncelle();
						 
						 /**
						  * Bluetooth, ivmeölçer ve manyetik alan testlerinin tamamlanmasý sonrasýnda ProgressBar kapatýlmýþtýr.
						  */
						 IM_ProgressBarKapat();
						 
						 /**
						  * Seçili olan veriler excel dosyasýna kaydedilmiþtir.
						  */
						 IM_SeciliVerileriLogla();
						 
						 /**
						  * Listeler excele kaydedildikten sonra, bir sonraki ölçümelerde kullanýlmak üzere boþaltýlmýþtýr. 
						  */
						 liste_ivmeolcer.clear();
						 liste_manyetik_alan.clear();
						 liste_bluetooth.clear();
						 
						 /**
						  * Bluetooth, ivmeölçer ve manyetik alan loglamalarý tamamlandýktan sonra, seçili ise
						  * 
						  * wifi loglama iþlemi baþlatýlmaktadýr.
						  */
						 if(b_log_wifi)
						 {
							 /**
							  * Wifi tarama iþlemine baþlamadan önce herhangi bir bluetooth taramasý varsa sonlandýrýlmakta ve 
							  * 
							  * bluetooth kapatýlmaktadýr.
							  */
							 bluetooth_adaptor.cancelDiscovery();
							 bluetooth_adaptor.disable();
							 
							 /**
							  * Bluetooth taramasý sýrasýnda kapatýlan wifi, wifi taramasý baþlamadan önce açýlmaktadýr.
							  */
							 wifi_yonetici.setWifiEnabled(true);
							 
							 i_wifi_sayac = 0;
							 
							 /**
							  * Wifi taramasý wifi ýn açýlmasý beklenmektedir.
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
						  * WiFi testi için ProgressBar oluþturulmuþtur.
						  */
						 IM_ProgressBarBaslat(v, "WiFi");
						 
						
						 /**
						  * 3 er saniye aralýklarla 10 adet wifi test verisi toplanmaktadýr.
						  */
						 
							 new CountDownTimer(48000, 4000) {
								 public void onTick(long millisUntilFinished) 
								 {
									 /**
									  * Güncel tarih ve saat bilgisi sistemden alýnmaktadýr.
									  */
									 IM_TarihVeSaatiGuncelle();
	
									 /**
									  * WiFi taramasý baþlatýlmýþtýr.
									  */
									 IM_WiFiTara();
									 
									 
									 /**
									  * Bir ölçüm içerisinde kaçýncý wifi taramasýnda olunduðunu tutan deðiþkenin deðeri 1 arttýrýlmýþtýr.
									  */
									 i_wifi_sayac++;
									 
									 /**
									  * Her bir ölçüm sonrasý progress bar ýn deðeri arttýrýlmýþtýr.
									  */
									 IM_ProgressBarDoldur();
								 }
								 public void onFinish() 
								 {
									 /**
									  * Güncel tarih ve saat bilgisi sistemden alýnmaktadýr.
									  */
									 IM_TarihVeSaatiGuncelle();
									 
									 /**
									  * Wifi sayfasý için güncellenen format sayfaya eklendi.
									  */
									 IM_WiFiFormatGuncelle();
									 
									    /**
									     * WiFi testi sonucu elde edilen veriler listeye eklenmiþtir.
									     */
									    IM_WiFiVerileriniListeyeEkle();
	
										 /**
										  * WiFi testi tamamlandýðýnda toplanan veriler excel dosyasýnýn WiFi sayfasýna yazdýrýlmýþtýr.
										  */
										 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("WiFi", liste_wifi);
										 	
										 /**
										  * Yeni eklenen mac adresleri için sütunlarda oluþan boþluklar "NaN" ile doldurulmuþtur.
										  */
										 excel_kutuphanesi.IM_WiFiBoslukDoldur();
										 
										 /**
										  * Liste sonraki testlerde kullanýlmak üzere temizlenmiþtir.
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
	 * FONKSÝYON ADI: 				IM_WiFiFormatGuncelle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon WiFi sayfasý için güncellenen format sayfaya eklenmektedir.   </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_WiFiFormatGuncelle()
	{
		/**
		 * Wifi sayfasý için güncellenen format sayfaya eklendi.
		 */
		ArrayList<String[]> liste_wifi_format = new ArrayList<String[]>();
		String[] wifi_format_hucreleri  = new String[102];
		wifi_format_hucreleri[0] = "ÖLÇÜM";
		wifi_format_hucreleri[1] = "TARÝH";
		wifi_format_hucreleri[2] = "ZAMAN";
		wifi_format_hucreleri[3] = "KONUM_X";
		wifi_format_hucreleri[4] = "KONUM_Y";
		wifi_format_hucreleri[5] = "KAT";
		wifi_format_hucreleri[6] = "PÝL DURUMU";
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
	 * FONKSÝYON ADI: 				IM_SeciliVerileriLogla </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon bir ölçüm sonlandýðýnda toplanan veriler excel dosyasýna yazdýrýlmaktadýr.   </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_SeciliVerileriLogla()
	{
		 /**
		  * Bir ölçüm sonlandýðýnda toplanan veriler excel dosyasýna yazdýrýlmýþtýr.
		  */
		 if(b_log_ivmeolcer)
		 {
			 /**
			  * Ývmeölçer verileri excel dosyasýnýn Ivmeolcer sayfasýna yazdýrýlmýþtýr.
			  */
			 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Ivmeolcer", liste_ivmeolcer);
		 }
		 if(b_log_manyetik_alan)
		 {
			 /**
			  * Manyetik alan verileri excel dosyasýnýn ManyetikAlan sayfasýna yazdýrýlmýþtýr.
			  */
			 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("ManyetikAlan", liste_manyetik_alan);
		 }
		 if(b_log_bluetooth)
		 {
			 /**
			  * Devam eden herhangi bir tarama bulunuyorsa iptal edilmiþtir.
			  */
			     bluetooth_adaptor.cancelDiscovery();
			    
			     IM_BluetoothVerileriniListeyeEkle();
			     
			     /**
				  * Bluetooth verileri excel dosyasýnýn Bluetooth sayfasýna yazdýrýlmýþtýr.
				  */
				 excel_kutuphanesi.IM_ExcelDosyasinayaYaz("Bluetooth", liste_bluetooth);

		 }
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_TarihVeSaatiGuncelle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon güncel tarih ve saat bilgisi sistemden alýnmaktadýr.   </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * -->
	*********************************************************************************************/
	public void IM_TarihVeSaatiGuncelle()
	{
		str_tarih = sdf_tarih.format(new Date());
		str_saat = sdf_saat.format(new Date());
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_TestleriBelirle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile arayüz üzerinden hangi testlerin seçili olduðu belirlenmektedir.   </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *			v							View				Pop-up ekranýný gösterileceði arayüze referanstýr.
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * -->
	 * 
	 * @param v Pop-up ekranýný gösterileceði arayüze referanstýr.
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
				str_loglanacak_parametreler += " Ývmeölçer";
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
	 * FONKSÝYON ADI: 				IM_BilgilerGecerliMi </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile arayüz üzerinde girilmesi gereken bilgilerin kontrolü yapýlmaktadýr.
	 * Eðer bilgiler eksiksiz ise true, eksik bilgi varsa false deðerini dönmektedir.  </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * 										boolean				Eðer bilgiler eksiksiz ise true, eksik bilgi varsa false deðerini dönmektedir.			
	 * -->
	 * 
	*********************************************************************************************/
	public boolean IM_BilgilerGecerliMi()
	{
		/**
		 * Log Al butonuna týklandýðýnda excel dosyasýna yazdýrýlmak üzere 
		 * 
		 * konum ve kat bilgileri edittext'lerden alýnmýþtýr.
		 */
		str_konum_x = et_x.getText().toString();
		str_konum_y = et_y.getText().toString();
		str_kat = et_kat.getText().toString();
		
		/**
		 * Log Al butonuna basýldýðýnda hangi parametrelerin loglanacaðý CheckedListView lardan belirlenmiþtir.
		 */
		b_log_wifi = ctv_wifi.isChecked();
		b_log_bluetooth = ctv_bluetooth.isChecked();
		b_log_manyetik_alan = ctv_manyetik_alan.isChecked();
		b_log_ivmeolcer = ctv_ivmeolcer.isChecked();
		
		/**
		 * x koordinatý, y koordinatý, kat bilgilerinin girilip girilmediði kontrol edilmektedir.
		 * 
		 * En az bir  testin seçili olup olmadýðý kontrol edilmektedir.
		 * 
		 * Eðer x koordinatý, y koordinatý, kat bilgileri girilmiþ ve en az bir test seçilmiþse 
		 * 
		 * loglama iþlemi baþlatýlmakta, aksi taktirde uyarý verilerel loglama iþlemi baþlatýlmamaktadýr.
		 */
		if(str_konum_x.contentEquals("") || str_konum_y.contentEquals("") || str_kat.contentEquals("") || !(b_log_wifi || b_log_bluetooth || b_log_manyetik_alan || b_log_ivmeolcer))
		{
			Toast.makeText(getApplicationContext(), "Lütfen x koordinatý,  y koordinatý ve kat bilgilerinin eksiksiz doldurunuz ve en az bir test seçiniz.", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_WiFiTara </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile WiFi cihazlarý tarama iþlemi baþlatýlmaktadýr.  </br> </br>
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
	public void IM_WiFiTara()
	{
		/**
		 * Herbir wifi ölçümü sonrasýnda gelen veriler excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir.
		 */
		IM_WiFiVerileriniListeyeEkle();
		
		IM_WifiKutuphanesi.cihaz_bilgiler_guncel.clear();
		
		wifi_kutuphanesi.IM_WifiTaramaBaslat();
		
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_WiFiVerileriniListeyeEkle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile herbir wifi ölçümü sonrasýnda gelen veriler
	 *  excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir. 
	 *  Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr. </br> </br>
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
	public void IM_WiFiVerileriniListeyeEkle()
	{
		/**
		 * Herbir wifi ölçümü sonrasýnda gelen veriler excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir.
		 * 
		 * Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr.
		 */
		if(i_wifi_sayac>1)
		{
			String[] wifi_hucreleri  = new String[IM_WifiKutuphanesi.liste_mac_adresleri_toplam.size()+7];
		    wifi_hucreleri[0] = "ölçüm";
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
	 * FONKSÝYON ADI: 				IM_BluetoothTara </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile bluetooth cihazlarý tarama iþlemi baþlatýlmaktadýr.  </br> </br>
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
	public void IM_BluetoothTara() 
	{
		/**
		 * Eðer mevcut tarama iþlemi varsa durdurulduktan sonra yeni tarama iþlemi baþlatýlmaktadýr.
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
	 * FONKSÝYON ADI: 				IM_BluetoothVerileriniListeyeEkle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile herbir bluetooth ölçümü sonrasýnda gelen veriler
	 *  excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir. 
	 *  Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr. </br> </br>
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
	public void IM_BluetoothVerileriniListeyeEkle()
	{
		 /**
		 * Herbir bluetooth ölçümü sonrasýnda gelen veriler excelde bulunan mac adreslerine göre sýralanarak listeye eklenmektedir.
		 * 
		 * Test tamamlandýðýnda bu veriler listeden okunarak excel dosyasýna yazdrýlmaktadýr.
		 */
		 String[] bluetooth_hucreleri  = new String[19];
		 bluetooth_hucreleri[0] = "ölçüm";
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
	 * FONKSÝYON ADI: 				IM_IvmeolcerVeriToplama </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile güncel ivmeölçer verileri listeye eklenmektedir. </br> </br>
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
	public void IM_IvmeolcerVeriToplama()
	{
		/**
		  * Ývmeölçer verileri dosyaya eklenmek üzere listeye eklenmiþtir.
		  */
		 String[] ivmeolcer_hucreleri  = new String[10];
		 ivmeolcer_hucreleri[0] = "ölçüm";
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
	 * FONKSÝYON ADI: 				IM_ManyetikAlanVeriToplama </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile güncel manyetik alan verileri listeye eklenmektedir. </br> </br>
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
	public void IM_ManyetikAlanVeriToplama()
	{
		 /**
		  * Manyetik alan verileri dosyaya eklenmek üzere listeye eklenmiþtir.
		  */
		 String[] manyetik_alan_hucreleri  = new String[10];
		 manyetik_alan_hucreleri[0] = "ölçüm";
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
	 * FONKSÝYON ADI: 				IM_ProgressBarBaslat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile tarama baþladýðýnda progress bar ekrana çýkmakta ve
	 * tarama sona erene kadar ekranda kalmaktadýr. </br> </br>
	 *
	 * ERÝÞÝM: Public </br> </br>
	 * <!--
	 * PARAMETRELER:
	 * 			ADI							TÝPÝ				AÇIKLAMASI
	 * 			v							View				Uygulamanýn çalýþacaðý ekranýn referans görüntüsüdür.
	 * 			str_test_ismi				String				"str_test_ismi Loglanýyor" þeklinde görüntülenecek mesajdýr.
	 *
	 * DÖNÜÞ:	
	 * 			ADI							TÝPÝ				AÇIKLAMASI			
	 * -->
	 * 
	 * @param v Uygulamanýn çalýþacaðý ekranýn referans görüntüsüdür.
	 * @param str_test_ismi "str_test_ismi Loglanýyor" þeklinde görüntülenecek mesajdýr.
	*********************************************************************************************/
	public void IM_ProgressBarBaslat(View v, String str_test_ismi)
	{
		if(v != null && str_test_ismi != null)
		{
			/**
			 * ProgressBar oluþturulmuþtur.
			 */
			progress_dialog = new ProgressDialog(v.getContext());
			
			/**
			 * ProgressBar yatay olacak þekilde ve mesajý "Loglanýyor ..." olacak þekilde ayarlanmþtýr.
			 */
			progress_dialog.setMessage(str_test_ismi + " Loglanýyor ...");
			progress_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			
			/**
			 * ProgressBar ýn alabileceði maksimum ve minimum deðerler belirlenmiþtir.
			 */
			progress_dialog.setProgress(0);
			progress_dialog.setMax(100);
			
			/**
			 * ProgressBar arayüz üzerinde gösterilmiþ ve iptal edilebilirliði false olarak ayarlanmýþtýr.
			 */
			progress_dialog.show();
			progress_dialog.setCancelable(false);
			
	        /**
	         * ProgressBar ýn baþlangýç deðeri 0 olarak ayarlanmýþtýr.
	         */
	        i_progress_bar_degeri = 0;
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ProgressBarDoldur </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile tarama süresinde progress bar'ýn iþlemesi saðlanmaktadýr. </br> </br>
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
	public void IM_ProgressBarDoldur()
	{
		/**
		 * ProgressBar ýn deðeri arttýrýlmýþ ve arayüz üzerinde güncellenmiþtir.
		 */
		i_progress_bar_degeri = i_progress_bar_degeri + 10;
	     
		progress_dialog.setProgress(i_progress_bar_degeri);		 
			
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_ProgressBarKapat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile tarama sbittiðinde progress bar kapatýlmaktadýr. </br> </br>
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
	public void IM_ProgressBarKapat()
	{
		/**
		 * ProgressBar dolduðunda (loglama iþlemi tamamlandýðýnda) kapatýlmýþtýr.
		 */
		if (i_progress_bar_degeri >= 100) 
		{
			progress_dialog.dismiss();
			i_progress_bar_degeri = 0;
		}
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				PilDurumuSaglayici </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Pil durumu bilgisini almak için kullanýlmaktadýr. </br> </br>
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
	private BroadcastReceiver PilDurumuSaglayici = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{
		
			/**
			 * pil seviyesi sistemden okunarak deðiþkene aktarýlmýþtýr.
			 */
			int i_pil_seviyesi= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
			
		    str_pil_seviyesi = String.valueOf(i_pil_seviyesi);
		}
	};
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IM_FormatEkle </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile belirlenen formatlar excel log dosyasýna eklenmektedir.  </br> </br>
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
	private void IM_FormatEkle()
	{
		/**
		 * Wifi sayfasý için belirlenen format sayfaya eklendi.
		 */
		ArrayList<String[]> liste_wifi_format = new ArrayList<String[]>();
		String[] wifi_format_hucreleri  = new String[102];
		wifi_format_hucreleri[0] = "ÖLÇÜM";
		wifi_format_hucreleri[1] = "TARÝH";
		wifi_format_hucreleri[2] = "ZAMAN";
		wifi_format_hucreleri[3] = "KONUM_X";
		wifi_format_hucreleri[4] = "KONUM_Y";
		wifi_format_hucreleri[5] = "KAT";
		wifi_format_hucreleri[6] = "PÝL DURUMU";
		for(int i=7;i<100;i++)
		{
			wifi_format_hucreleri[i] = "";
		}
		wifi_format_hucreleri[100] = "0";
		wifi_format_hucreleri[101] = "1";
	    liste_wifi_format.add(wifi_format_hucreleri);
	    excel_kutuphanesi.IM_ExcelDosyasinayaYaz("WiFi", liste_wifi_format);
	    
	    /**
		 * Bluetooth sayfasý için belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_bluetooth_format = new ArrayList<String[]>();
		String[] bluetooth_format_hucreleri  = new String[102];
		bluetooth_format_hucreleri[0] = "ÖLÇÜM";
		bluetooth_format_hucreleri[1] = "TARÝH";
		bluetooth_format_hucreleri[2] = "ZAMAN";
		bluetooth_format_hucreleri[3] = "KONUM_X";
		bluetooth_format_hucreleri[4] = "KONUM_Y";
		bluetooth_format_hucreleri[5] = "KAT";
		bluetooth_format_hucreleri[6] = "PÝL DURUMU";
		bluetooth_format_hucreleri[7] = "4C:A5:6D:1D:3C:AB(Furkan-s4Mini)";
		bluetooth_format_hucreleri[8] = "22:22:EB:ED:16:0F(Fatih-Gio)";
		bluetooth_format_hucreleri[9] = "BC:44:86:F2:C0:00(Ýnovasyon-s4Mini)";
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
		 * Manyetik Alan sayfasý için belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_manyetik_alan_format = new ArrayList<String[]>();
		String[] manyetik_alan_format_hucreleri  = new String[102];
		manyetik_alan_format_hucreleri[0] = "ÖLÇÜM";
		manyetik_alan_format_hucreleri[1] = "TARÝH";
		manyetik_alan_format_hucreleri[2] = "ZAMAN";
		manyetik_alan_format_hucreleri[3] = "KONUM_X";
		manyetik_alan_format_hucreleri[4] = "KONUM_Y";
		manyetik_alan_format_hucreleri[5] = "KAT";
		manyetik_alan_format_hucreleri[6] = "PÝL DURUMU";
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
		 * Ývmeölçer sayfasý için belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_ivmeolcer_format = new ArrayList<String[]>();
		String[] ivmeolcer_format_hucreleri  = new String[102];
		ivmeolcer_format_hucreleri[0] = "ÖLÇÜM";
		ivmeolcer_format_hucreleri[1] = "TARÝH";
		ivmeolcer_format_hucreleri[2] = "ZAMAN";
		ivmeolcer_format_hucreleri[3] = "KONUM_X";
		ivmeolcer_format_hucreleri[4] = "KONUM_Y";
		ivmeolcer_format_hucreleri[5] = "KAT";
		ivmeolcer_format_hucreleri[6] = "PÝL DURUMU";
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
	 * FONKSÝYON ADI: 				IM_CheckedTextViewTiklanabilirAyarla </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile arayüz üzerinde bulunan CheckedTextView larýn CheckBox
	 * larý týklanabilir hale getirilmiþtir.  </br> </br>
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
	public void IM_CheckedTextViewTiklanabilirAyarla() 
	{
		/**
		 * WiFi CheckedTextView'i týklanabilir hale getirilmiþtir.
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
		 * Bluetooth CheckedTextView'i týklanabilir hale getirilmiþtir.
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
		 * Manyetik Alan CheckedTextView'i týklanabilir hale getirilmiþtir.
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
		 * Ývmeölçer CheckedTextView'i týklanabilir hale getirilmiþtir.
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
		 * CheckedTextView larýn uygulama baþladýðýnda seçili olarak gelmesi saðlanmýþtýr.
		 */
		ctv_wifi.setChecked(true);
		ctv_bluetooth.setChecked(true);
		ctv_manyetik_alan.setChecked(true);
		ctv_ivmeolcer.setChecked(true);
	}

}
