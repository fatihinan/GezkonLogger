package com.gezkonlogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	/**
	 * Excel dosyas�na kaydedilecek tarih bilgisi i�in format belirlenmi�tir.
	 */
	SimpleDateFormat sdf_tarih = new SimpleDateFormat("yyyy/MM/dd");
	String str_tarih;
	
	/**
	 * Excel dosyas�na kaydedilecek saat bilgisi i�in format belirlenmi�tir.
	 */
	SimpleDateFormat sdf_saat = new SimpleDateFormat("HH:mm:ss");
	String str_saat;
	
	/**
	 * Bluetooth sens�rlerine ula�mak i�in sens�r adaptoru ve Bluetooth Kutuphanesi nesnesi olu�turuldu.
	 */
	public static BluetoothAdapter bluetooth_adaptor;
	Bluetooth_Kutuphanesi bluetooth_kutuphanesi = new Bluetooth_Kutuphanesi();
	
	/**
	 * Kullan�c�n�n teste ba�lamadan �nce textview' lere girece�i verilerin tutulaca�� de�i�kenlerdir.
	 */
	String str_olcum;
	String str_konum_x;
	String str_konum_y;
	String str_kat;
	
	/**
	 * Pil seviyesini y�zde olarak tutan de�i�kendir.
	 */
	String str_pil_seviyesi;
	
	/**
	 * Bir �l��m boyunca ivme�l�er ve manyetik alan verilerinin tutulaca�� listedir.
	 * 
	 * �l��m tamamland���nda veriler bu listelerden �ekilerek excel dosyas�na yazd�r�lmaktad�r.
	 */
	ArrayList<String[]> liste_ivmeolcer = new ArrayList<String[]>();
	ArrayList<String[]> liste_manyetik_alan = new ArrayList<String[]>();
	
	/**
	 * Di�er classlarda Toast �al��t�rmak i�in gerekli de�i�ken olu�turulmu�tur.
	 */
	public static Context icerik;
	
	/**
	 * Loglama i�lemlerinin ger�ekle�tirilmesi i�in excel k�t�phanesi t�r�nden nesne olu�turulmu�tur. 
	 */
	ExcelIslemleri_Kutuphanesi excel_kutuphanesi = new ExcelIslemleri_Kutuphanesi();
	
	/**
	 * wifi sens�rlerine ula�mak i�in sens�r y�neticisi olu�turuldu.
	 */
	public static WifiManager wifi_yonetici;
	
	/**
	 * �vme�l�er ve manyetik alan sens�rlerine ula�mak i�in sens�r y�neticisi olu�turuldu.
	 */
	public static SensorManager sensor_yoneticisi = null;
	ManyetikAlan_Kutuphanesi manyetik_alan_kutuphanesi = new ManyetikAlan_Kutuphanesi();
	Ivmeolcer_Kutuphanesi ivmeolcer_kutuphanesi = new Ivmeolcer_Kutuphanesi();
	
	/**
	 * CheckedTextView lar olu�turuldu.
	 */
	CheckedTextView ctv_wifi;
	CheckedTextView ctv_bluetooth;
	CheckedTextView ctv_manyetik_alan;
	CheckedTextView ctv_ivmeolcer;

	/**
	 * EditText ler olu�turuldu.
	 */
	EditText et_x;
	EditText et_y;
	EditText et_kat;

	/**
	 * Log Al butonu olu�turuldu.
	 */
	Button btn_log_al;

	
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * Pil seviyesinin okunabilmesi i�in sisteme kay�t yap�lm��t�r.
		 */
		this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		/**
		 * WiFi i�lemleri i�in wifi_yoneticisi nesnesi ve wifi kutuphanesi olu�turulmu�tur.
		 */
		wifi_yonetici = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		final Wifi_Kutuphanesi wifi_kutuphanesi=new Wifi_Kutuphanesi();
		wifi_yonetici.startScan();
		
		/**
		 * Bluetooth sens�rlerine ula�mak i�in sens�r adaptoru olu�turuldu.
		 */
		bluetooth_adaptor = BluetoothAdapter.getDefaultAdapter();
		
		/**
		 * �lk olarak log verilerinin yaz�laca�� excel dosyas� a��lm�� 
		 * 
		 * ve gerekli sayfalar a��lan dosyaya eklendikten sonra dosya kapat�lm��t�r.
		 */
		boolean b_dosya_acildi = excel_kutuphanesi.DosyaOlustur("GezkonLogger", "LogDosyasi.xls");
		if(b_dosya_acildi)
		{
			excel_kutuphanesi.SayfaEkle("Ivmeolcer");
			excel_kutuphanesi.SayfaEkle("ManyetikAlan");
			excel_kutuphanesi.SayfaEkle("Bluetooth");
			excel_kutuphanesi.SayfaEkle("WiFi");
			excel_kutuphanesi.DosyaKapat();
			
			/**
			 * Excel sayfalar�n�n ilk sat�rlar�nda belirtilen format sayfaya eklendi.
			 */
			WiFiFormatEkle();
		}

		/**
		 * �vme�l�er ve manyetik alan sens�rlerine ula�mak i�in sens�r y�neticisi olu�turuldu.
		 */
		sensor_yoneticisi = (SensorManager) getSystemService(SENSOR_SERVICE);
		manyetik_alan_kutuphanesi.Baslat();
		ivmeolcer_kutuphanesi.Baslat();
		
		
		/**
		 * CheckedTextView lar layout dosyas�ndan �ekildi.
		 */
		ctv_wifi = (CheckedTextView) findViewById(R.id.ctv_wifi_testi);
		ctv_bluetooth = (CheckedTextView) findViewById(R.id.ctv_bluetooth_testi);
		ctv_manyetik_alan = (CheckedTextView) findViewById(R.id.ctv_manyetik_alan_testi);
		ctv_ivmeolcer = (CheckedTextView) findViewById(R.id.ctv_ivmeolcer_testi);
		CheckedTextViewTiklanabilirAyarla();

		/**
		 * EditText ler layout dosyas�ndan �ekildi.
		 */
		et_x = (EditText) findViewById(R.id.et_x);
		et_y = (EditText) findViewById(R.id.et_y);
		et_kat = (EditText) findViewById(R.id.et_kat);

		/**
		 * Log Al butonu layout dosyas�ndan �ekildi.
		 */
		btn_log_al = (Button) findViewById(R.id.btn_log_al);
		
		/**
		 * Log Al butonuna t�kland���nda ger�ekle�tirilecek i�lemler tan�mlanm��t�r.
		 */
		btn_log_al.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * Log Al butonuna t�kland���nda excel dosyas�na yazd�r�lmak �zere 
				 * 
				 * konum ve kat bilgileri edittext'lerden al�nm��t�r.
				 */
				str_konum_x = et_x.getText().toString();
				str_konum_y = et_y.getText().toString();
				str_kat = et_kat.getText().toString();
				 
				/**
				  * Her 4 saniyede bir g�ncel veriler listelere eklenmektedir.
				  * 
				  * Toplam 10 test verisi topland���nda onFinish metoduna giderek dosyaya kaydetme i�lemi ger�ekle�tirilmektedir.
				  * 
				  */
				 new CountDownTimer(44000, 4000) {
				
				 public void onTick(long millisUntilFinished) {
					 /**
					  * G�ncel tarih ve saat bilgisi sistemden al�nmaktad�r.
					  */
					 str_tarih = sdf_tarih.format(new Date());
					 str_saat = sdf_saat.format(new Date());
					 
					 /**
					  * Loglan�yor tostu log i�lemi sonland�r�lana kadar ekranda g�sterilmektedir.
					  */
					 Toast.makeText(getApplicationContext(), "Loglan�yor", Toast.LENGTH_SHORT).show();
					 
					 /**
					  * ivme�l�er verileri dosyaya eklenmek �zere listeye eklenmi�tir.
					  */
					 String[] ivmeolcer_hucreleri  = new String[10];
					 ivmeolcer_hucreleri[0] = "�l��m";
					 ivmeolcer_hucreleri[1] = str_tarih;
					 ivmeolcer_hucreleri[2] = str_saat;
					 ivmeolcer_hucreleri[3] = str_konum_x;
					 ivmeolcer_hucreleri[4] = str_konum_y;
					 ivmeolcer_hucreleri[5] = str_kat;
					 ivmeolcer_hucreleri[6] = String.valueOf(ivmeolcer_kutuphanesi.IvmeolcerGetirX());
					 ivmeolcer_hucreleri[7] = String.valueOf(ivmeolcer_kutuphanesi.IvmeolcerGetirY());
					 ivmeolcer_hucreleri[8] = String.valueOf(ivmeolcer_kutuphanesi.IvmeolcerGetirZ());
					 ivmeolcer_hucreleri[9] = str_pil_seviyesi;
					 liste_ivmeolcer.add(ivmeolcer_hucreleri);
					 
					 /**
					  * manyetik alan verileri dosyaya eklenmek �zere listeye eklenmi�tir.
					  */
					 String[] manyetik_alan_hucreleri  = new String[10];
					 manyetik_alan_hucreleri[0] = "�l��m";
					 manyetik_alan_hucreleri[1] = str_tarih;
					 manyetik_alan_hucreleri[2] = str_saat;
					 manyetik_alan_hucreleri[3] = str_konum_x;
					 manyetik_alan_hucreleri[4] = str_konum_y;
					 manyetik_alan_hucreleri[5] = str_kat;
					 manyetik_alan_hucreleri[6] = String.valueOf(manyetik_alan_kutuphanesi.ManyetikAlanGetirX());
					 manyetik_alan_hucreleri[7] = String.valueOf(manyetik_alan_kutuphanesi.ManyetikAlanGetirY());
					 manyetik_alan_hucreleri[8] = String.valueOf(manyetik_alan_kutuphanesi.ManyetikAlanGetirZ());
					 manyetik_alan_hucreleri[9] = str_pil_seviyesi;
					 liste_manyetik_alan.add(manyetik_alan_hucreleri);
				     
				 
					//wifi_kutuphanesi.WifiTaramaBaslat();
					//BluetoothTara();
				 }
				 public void onFinish() {
					 /**
					  * Bir �l��m sonland���nda toplanan veriler excel dosyas�na yazd�r�lm��t�r.
					  */
					 excel_kutuphanesi.DosyayaYaz("Ivmeolcer", liste_ivmeolcer);
					 excel_kutuphanesi.DosyayaYaz("ManyetikAlan", liste_manyetik_alan);
					 
					 /**
					  * Listeler excele kaydedildikten sonra, bir sonraki �l��melerde kullan�lmak �zere bo�alt�lm��t�r. 
					  */
					 liste_ivmeolcer.clear();
					 liste_manyetik_alan.clear();
					 
					 /**
					  * �l��m tamamland���nda tost ile belirtilmi�tir
					  */
					 Toast.makeText(getApplicationContext(), "Tamamland�", Toast.LENGTH_LONG).show();
				 }
				 }.start();
			}
		});
	}
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				batteryInfoReceiver </br> </br>
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
	private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
		
			/**
			 * pil seviyesi sistemden okunarak de�i�kene aktar�lm��t�r.
			 */
			int i_pil_seviyesi= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
			
		    str_pil_seviyesi = String.valueOf(i_pil_seviyesi);
		}
	};
	
	
	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				WiFiFormatEkle </br> </br>
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
	private void WiFiFormatEkle()
	{
		/**
		 * Wifi sayfas� i�in belirlenen format sayfaya eklendi.
		 */
		ArrayList<String[]> liste_wifi_format = new ArrayList<String[]>();
		String[] wifi_format_hucreleri  = new String[12];
		wifi_format_hucreleri[0] = "�L��M";
		wifi_format_hucreleri[1] = "TAR�H";
		wifi_format_hucreleri[2] = "ZAMAN";
		wifi_format_hucreleri[3] = "KONUM_X";
		wifi_format_hucreleri[4] = "KONUM_Y";
		wifi_format_hucreleri[5] = "KAT";
		wifi_format_hucreleri[6] = "AP1";
		wifi_format_hucreleri[7] = "AP2";
		wifi_format_hucreleri[8] = "P�L DURUMU";
		wifi_format_hucreleri[9] = "";
		wifi_format_hucreleri[10] = "0";
		wifi_format_hucreleri[11] = "1";
	    liste_wifi_format.add(wifi_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("WiFi", liste_wifi_format);
	    
	    /**
		 * Bluetooth sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_bluetooth_format = new ArrayList<String[]>();
		String[] bluetooth_format_hucreleri  = new String[12];
		bluetooth_format_hucreleri[0] = "�L��M";
		bluetooth_format_hucreleri[1] = "TAR�H";
		bluetooth_format_hucreleri[2] = "ZAMAN";
		bluetooth_format_hucreleri[3] = "KONUM_X";
		bluetooth_format_hucreleri[4] = "KONUM_Y";
		bluetooth_format_hucreleri[5] = "KAT";
		bluetooth_format_hucreleri[6] = "AP1";
		bluetooth_format_hucreleri[7] = "AP2";
		bluetooth_format_hucreleri[8] = "P�L DURUMU";
		bluetooth_format_hucreleri[9] = "";
		bluetooth_format_hucreleri[10] = "0";
		bluetooth_format_hucreleri[11] = "1";
		liste_bluetooth_format.add(bluetooth_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("Bluetooth", liste_bluetooth_format);
	    
	    /**
		 * Manyetik Alan sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_manyetik_alan_format = new ArrayList<String[]>();
		String[] manyetik_alan_format_hucreleri  = new String[12];
		manyetik_alan_format_hucreleri[0] = "�L��M";
		manyetik_alan_format_hucreleri[1] = "TAR�H";
		manyetik_alan_format_hucreleri[2] = "ZAMAN";
		manyetik_alan_format_hucreleri[3] = "KONUM_X";
		manyetik_alan_format_hucreleri[4] = "KONUM_Y";
		manyetik_alan_format_hucreleri[5] = "KAT";
		manyetik_alan_format_hucreleri[6] = "X";
		manyetik_alan_format_hucreleri[7] = "Y";
		manyetik_alan_format_hucreleri[8] = "Z";
		manyetik_alan_format_hucreleri[9] = "P�L DURUMU";
		manyetik_alan_format_hucreleri[10] = "0";
		manyetik_alan_format_hucreleri[11] = "1";
		liste_manyetik_alan_format.add(manyetik_alan_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("ManyetikAlan", liste_manyetik_alan_format);
	    
	    /**
		 * �vme�l�er sayfas� i�in belirlenen format sayfaya eklendi.
		 */
	    ArrayList<String[]> liste_ivmeolcer_format = new ArrayList<String[]>();
		String[] ivmeolcer_format_hucreleri  = new String[12];
		ivmeolcer_format_hucreleri[0] = "�L��M";
		ivmeolcer_format_hucreleri[1] = "TAR�H";
		ivmeolcer_format_hucreleri[2] = "ZAMAN";
		ivmeolcer_format_hucreleri[3] = "KONUM_X";
		ivmeolcer_format_hucreleri[4] = "KONUM_Y";
		ivmeolcer_format_hucreleri[5] = "KAT";
		ivmeolcer_format_hucreleri[6] = "X";
		ivmeolcer_format_hucreleri[7] = "Y";
		ivmeolcer_format_hucreleri[8] = "Z";
		ivmeolcer_format_hucreleri[9] = "P�L DURUMU";
		ivmeolcer_format_hucreleri[10] = "0";
		ivmeolcer_format_hucreleri[11] = "1";
		liste_ivmeolcer_format.add(ivmeolcer_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("Ivmeolcer", liste_ivmeolcer_format);
	}


	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				BluetoothTara </br> </br>
	 * FONKS�YON A�IKLAMASI: 		Bu fonksiyon bluetooth cihazlar� tarama i�lemi ba�lat�lmaktad�r.  </br> </br>
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
	public void BluetoothTara() {
		/**
		 * E�er mevcut tarama i�lemi varsa durdurulduktan sonra yeni tarama i�lemi ba�lat�lmaktad�r.
		 */
		   if (bluetooth_adaptor.isDiscovering()) 
		   {
			   bluetooth_adaptor.cancelDiscovery();
		   }
		   else 
		   {
			    Bluetooth_Kutuphanesi.bluetooth_liste.clear();
				bluetooth_adaptor.startDiscovery();
				registerReceiver(Bluetooth_Kutuphanesi.b_alici, new IntentFilter(BluetoothDevice.ACTION_FOUND));
			}    
	   }


	/********************************************************************************************
	 * 
	 * FONKS�YON ADI: 				CheckedTextViewTiklanabilirAyarla </br> </br>
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
	public void CheckedTextViewTiklanabilirAyarla() {
		/**
		 * WiFi CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_wifi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Bluetooth CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_bluetooth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Manyetik Alan CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_manyetik_alan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * �vme�l�er CheckedTextView'i t�klanabilir hale getirilmi�tir.
		 */
		ctv_ivmeolcer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});
		
		/**
		 * CheckedTextView lar�n ba�lang��ta tikli olarak gelmesi sa�lanm��t�r.
		 */
		ctv_wifi.setChecked(true);
		ctv_bluetooth.setChecked(true);
		ctv_manyetik_alan.setChecked(true);
		ctv_ivmeolcer.setChecked(true);
	}

}
