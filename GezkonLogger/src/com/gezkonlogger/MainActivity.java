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
import android.app.ProgressDialog;
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

	ProgressDialog progressBar;
	static int progressBarStatus;
	/**
	 * Excel dosyasýna kaydedilecek tarih bilgisi için format belirlenmiþtir.
	 */
	SimpleDateFormat sdf_tarih = new SimpleDateFormat("yyyy/MM/dd");
	String str_tarih;
	
	/**
	 * Excel dosyasýna kaydedilecek saat bilgisi için format belirlenmiþtir.
	 */
	SimpleDateFormat sdf_saat = new SimpleDateFormat("HH:mm:ss");
	String str_saat;
	
	/**
	 * Bluetooth sensörlerine ulaþmak için sensör adaptoru ve Bluetooth Kutuphanesi nesnesi oluþturuldu.
	 */
	public static BluetoothAdapter bluetooth_adaptor;
	Bluetooth_Kutuphanesi bluetooth_kutuphanesi = new Bluetooth_Kutuphanesi();
	
	/**
	 * Kullanýcýnýn teste baþlamadan önce textview' lere gireceði verilerin tutulacaðý deðiþkenlerdir.
	 */
	String str_olcum;
	String str_konum_x;
	String str_konum_y;
	String str_kat;
	
	/**
	 * Pil seviyesini yüzde olarak tutan deðiþkendir.
	 */
	String str_pil_seviyesi;
	
	/**
	 * Bir ölçüm boyunca ivmeölçer ve manyetik alan verilerinin tutulacaðý listedir.
	 * 
	 * Ölçüm tamamlandýðýnda veriler bu listelerden çekilerek excel dosyasýna yazdýrýlmaktadýr.
	 */
	ArrayList<String[]> liste_ivmeolcer = new ArrayList<String[]>();
	ArrayList<String[]> liste_manyetik_alan = new ArrayList<String[]>();
	ArrayList<String[]> liste_bluetooth = new ArrayList<String[]>();
	
	/**
	 * Diðer classlarda Toast çalýþtýrmak için gerekli deðiþken oluþturulmuþtur.
	 */
	public static Context icerik;
	
	/**
	 * Loglama iþlemlerinin gerçekleþtirilmesi için excel kütüphanesi türünden nesne oluþturulmuþtur. 
	 */
	ExcelIslemleri_Kutuphanesi excel_kutuphanesi = new ExcelIslemleri_Kutuphanesi();
	
	/**
	 * wifi sensörlerine ulaþmak için sensör yöneticisi oluþturuldu.
	 */
	public static WifiManager wifi_yonetici;
	
	/**
	 * Ývmeölçer ve manyetik alan sensörlerine ulaþmak için sensör yöneticisi oluþturuldu.
	 */
	public static SensorManager sensor_yoneticisi = null;
	ManyetikAlan_Kutuphanesi manyetik_alan_kutuphanesi = new ManyetikAlan_Kutuphanesi();
	Ivmeolcer_Kutuphanesi ivmeolcer_kutuphanesi = new Ivmeolcer_Kutuphanesi();
	
	/**
	 * CheckedTextView lar oluþturuldu.
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
	 * EditText ler oluþturuldu.
	 */
	EditText et_x;
	EditText et_y;
	EditText et_kat;

	/**
	 * Log Al butonu oluþturuldu.
	 */
	Button btn_log_al;

	
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		icerik = getApplicationContext();
		/**
		 * Pil seviyesinin okunabilmesi için sisteme kayýt yapýlmýþtýr.
		 */
		this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		/**
		 * WiFi iþlemleri için wifi_yoneticisi nesnesi ve wifi kutuphanesi oluþturulmuþtur.
		 */
		wifi_yonetici = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		final Wifi_Kutuphanesi wifi_kutuphanesi=new Wifi_Kutuphanesi();
		wifi_yonetici.startScan();
		
		/**
		 * Bluetooth sensörlerine ulaþmak için sensör adaptoru oluþturuldu.
		 */
		bluetooth_adaptor = BluetoothAdapter.getDefaultAdapter();
		bluetooth_kutuphanesi.MacAdresleriniAta();
		
		/**
		 * Ýlk olarak log verilerinin yazýlacaðý excel dosyasý açýlmýþ 
		 * 
		 * ve gerekli sayfalar açýlan dosyaya eklendikten sonra dosya kapatýlmýþtýr.
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
			 * Excel sayfalarýnýn ilk satýrlarýnda belirtilen format sayfaya eklendi.
			 */
			FormatEkle();
		}

		/**
		 * Ývmeölçer ve manyetik alan sensörlerine ulaþmak için sensör yöneticisi oluþturuldu.
		 */
		sensor_yoneticisi = (SensorManager) getSystemService(SENSOR_SERVICE);
		manyetik_alan_kutuphanesi.Baslat();
		ivmeolcer_kutuphanesi.Baslat();
		
		
		/**
		 * CheckedTextView lar layout dosyasýndan çekildi.
		 */
		ctv_wifi = (CheckedTextView) findViewById(R.id.ctv_wifi_testi);
		ctv_bluetooth = (CheckedTextView) findViewById(R.id.ctv_bluetooth_testi);
		ctv_manyetik_alan = (CheckedTextView) findViewById(R.id.ctv_manyetik_alan_testi);
		ctv_ivmeolcer = (CheckedTextView) findViewById(R.id.ctv_ivmeolcer_testi);
		CheckedTextViewTiklanabilirAyarla();

		/**
		 * EditText ler layout dosyasýndan çekildi.
		 */
		et_x = (EditText) findViewById(R.id.et_x);
		et_y = (EditText) findViewById(R.id.et_y);
		et_kat = (EditText) findViewById(R.id.et_kat);

		/**
		 * Log Al butonu layout dosyasýndan çekildi.
		 */
		btn_log_al = (Button) findViewById(R.id.btn_log_al);
		
		/**
		 * Log Al butonuna týklandýðýnda gerçekleþtirilecek iþlemler tanýmlanmýþtýr.
		 */
		btn_log_al.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ProgressBarBaþlat(v);
				
				/**
				 * Log Al butonuna basýldýðýnda hangi parametrelerin loglanacaðý belirlenmiþtir.
				 */
				b_log_wifi = ctv_wifi.isChecked();
				b_log_bluetooth = ctv_bluetooth.isChecked();
				b_log_manyetik_alan = ctv_manyetik_alan.isChecked();
				b_log_ivmeolcer = ctv_ivmeolcer.isChecked();
				
				
				/**
				 * Log Al butonuna týklandýðýnda excel dosyasýna yazdýrýlmak üzere 
				 * 
				 * konum ve kat bilgileri edittext'lerden alýnmýþtýr.
				 */
				str_konum_x = et_x.getText().toString();
				str_konum_y = et_y.getText().toString();
				str_kat = et_kat.getText().toString();
				 
				/**
				  * Her 4 saniyede bir güncel veriler listelere eklenmektedir.
				  * 
				  * Toplam 10 test verisi toplandýðýnda onFinish metoduna giderek dosyaya kaydetme iþlemi gerçekleþtirilmektedir.
				  */
				 new CountDownTimer(44000, 4000) {
				
				 public void onTick(long millisUntilFinished) {
					 /**
					  * Güncel tarih ve saat bilgisi sistemden alýnmaktadýr.
					  */
					 str_tarih = sdf_tarih.format(new Date());
					 str_saat = sdf_saat.format(new Date());
					 
					 if(b_log_ivmeolcer)
					 {
						 IvmeolcerVeriToplama();
					 }
					 if(b_log_manyetik_alan)
					 {
						 ManyetikAlanVeriToplama();
					 }
					 if(b_log_wifi)
					 {
						//wifi_kutuphanesi.WifiTaramaBaslat();
					 }
					 if(b_log_bluetooth)
					 {
						BluetoothTara();
					 }
					 
					 
				     ProgressBarDoldur();
				 }
				 public void onFinish() {
					 
					 ProgressBarKapat();
					 /**
					  * Bir ölçüm sonlandýðýnda toplanan veriler excel dosyasýna yazdýrýlmýþtýr.
					  */
					 if(b_log_ivmeolcer)
					 {
						 excel_kutuphanesi.DosyayaYaz("Ivmeolcer", liste_ivmeolcer);
					 }
					 if(b_log_manyetik_alan)
					 {
						 excel_kutuphanesi.DosyayaYaz("ManyetikAlan", liste_manyetik_alan);
					 }
					 if(b_log_wifi)
					 {
						 //wifi listesini dosyaya yazdýr
					 }
					 if(b_log_bluetooth)
					 {
						   bluetooth_adaptor.cancelDiscovery();
						   /**
							  * ivmeölçer verileri dosyaya eklenmek üzere listeye eklenmiþtir.
							  */
							 String[] bluetooth_hucreleri  = new String[19];
							 bluetooth_hucreleri[0] = "ölçüm";
							 bluetooth_hucreleri[1] = str_tarih;
							 bluetooth_hucreleri[2] = str_saat;
							 bluetooth_hucreleri[3] = str_konum_x;
							 bluetooth_hucreleri[4] = str_konum_y;
							 bluetooth_hucreleri[5] = str_kat;
							 bluetooth_hucreleri[6] = "NaN";
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
							 for(int i=0; i<Bluetooth_Kutuphanesi.liste_bluetooth_mac_adresleri.size(); i++)
							 {
								 for(int j=0; j<Bluetooth_Kutuphanesi.bluetooth_liste.size(); j++)
								 {
									 if(Bluetooth_Kutuphanesi.liste_bluetooth_mac_adresleri.get(i).equals(Bluetooth_Kutuphanesi.bluetooth_liste.get(j).AdresGetir()))
									 {
										 bluetooth_hucreleri[i+6] = Double.toString(Bluetooth_Kutuphanesi.bluetooth_liste.get(j).RssiGetir());
									 }
								 }
							 }
							 bluetooth_hucreleri[18] = str_pil_seviyesi;
							 liste_bluetooth.add(bluetooth_hucreleri);
						 //bluetooth listesini dosyaya yazdýr
						 excel_kutuphanesi.DosyayaYaz("Bluetooth", liste_bluetooth);

					 }
					 
					 /**
					  * Listeler excele kaydedildikten sonra, bir sonraki ölçümelerde kullanýlmak üzere boþaltýlmýþtýr. 
					  */
					 liste_ivmeolcer.clear();
					 liste_manyetik_alan.clear();
					 liste_bluetooth.clear();

				 }
				 }.start();
			}
		});
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				BluetoothTara </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon bluetooth cihazlarý tarama iþlemi baþlatýlmaktadýr.  </br> </br>
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
	public void BluetoothTara() {
		/**
		 * Eðer mevcut tarama iþlemi varsa durdurulduktan sonra yeni tarama iþlemi baþlatýlmaktadýr.
		 */
		   if (bluetooth_adaptor.isDiscovering()) 
		   {
			   bluetooth_adaptor.cancelDiscovery();
			   /**
				  * ivmeölçer verileri dosyaya eklenmek üzere listeye eklenmiþtir.
				  */
				 String[] bluetooth_hucreleri  = new String[19];
				 bluetooth_hucreleri[0] = "ölçüm";
				 bluetooth_hucreleri[1] = str_tarih;
				 bluetooth_hucreleri[2] = str_saat;
				 bluetooth_hucreleri[3] = str_konum_x;
				 bluetooth_hucreleri[4] = str_konum_y;
				 bluetooth_hucreleri[5] = str_kat;
				 bluetooth_hucreleri[6] = "NaN";
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
				 for(int i=0; i<Bluetooth_Kutuphanesi.liste_bluetooth_mac_adresleri.size(); i++)
				 {
					 for(int j=0; j<Bluetooth_Kutuphanesi.bluetooth_liste.size(); j++)
					 {
						 if(Bluetooth_Kutuphanesi.liste_bluetooth_mac_adresleri.get(i).equals(Bluetooth_Kutuphanesi.bluetooth_liste.get(j).AdresGetir()))
						 {
							 bluetooth_hucreleri[i+6] = Double.toString(Bluetooth_Kutuphanesi.bluetooth_liste.get(j).RssiGetir());
						 }
					 }
				 }
				 bluetooth_hucreleri[18] = str_pil_seviyesi;
				 liste_bluetooth.add(bluetooth_hucreleri);
		   }

		   Bluetooth_Kutuphanesi.bluetooth_liste.clear();
		   bluetooth_adaptor.startDiscovery();
		   registerReceiver(Bluetooth_Kutuphanesi.b_alici, new IntentFilter(BluetoothDevice.ACTION_FOUND));
   
	   }
	
	
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				IvmeolcerVeriToplama </br> </br>
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
	public void IvmeolcerVeriToplama()
	{
		/**
		  * ivmeölçer verileri dosyaya eklenmek üzere listeye eklenmiþtir.
		  */
		 String[] ivmeolcer_hucreleri  = new String[10];
		 ivmeolcer_hucreleri[0] = "ölçüm";
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
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				ManyetikAlanVeriToplama </br> </br>
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
	public void ManyetikAlanVeriToplama()
	{
		 /**
		  * manyetik alan verileri dosyaya eklenmek üzere listeye eklenmiþtir.
		  */
		 String[] manyetik_alan_hucreleri  = new String[10];
		 manyetik_alan_hucreleri[0] = "ölçüm";
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
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				ProgressBarBaþlat </br> </br>
	 * FONKSÝYON AÇIKLAMASI: 		Bu fonksiyon ile tarama baþladýðýnda progress bar ekrana çýkmakta ve
	 * tarama sona erene kadar ekranda kalmaktadýr. </br> </br>
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
	public void ProgressBarBaþlat(View v){
		
		// prepare for a progress bar dialog
		progressBar = new ProgressDialog(v.getContext());
		progressBar.setCancelable(true);
		progressBar.setMessage("Loglanýyor ...");
		progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressBar.setProgress(0);
		progressBar.setMax(100);
		progressBar.show();
        progressBar.setCancelable(false);
		//reset progress bar status
		progressBarStatus = 0;
	}
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				ProgressBarDoldur </br> </br>
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
	public void ProgressBarDoldur(){
		
		progressBarStatus = progressBarStatus + 10;
	     
		progressBar.setProgress(progressBarStatus);		 
			
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				ProgressBarKapat </br> </br>
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
	public void ProgressBarKapat()
	{
		if (progressBarStatus >= 100) {
			progressBar.dismiss();
			progressBarStatus = 0;
		}
	}
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				batteryInfoReceiver </br> </br>
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
	private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
		
			/**
			 * pil seviyesi sistemden okunarak deðiþkene aktarýlmýþtýr.
			 */
			int i_pil_seviyesi= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
			
		    str_pil_seviyesi = String.valueOf(i_pil_seviyesi);
		}
	};
	
	
	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				FormatEkle </br> </br>
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
	private void FormatEkle()
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
		wifi_format_hucreleri[6] = "AP1";
		wifi_format_hucreleri[7] = "AP2";
		wifi_format_hucreleri[8] = "PÝL DURUMU";
		wifi_format_hucreleri[9] = "";
		for(int i=10;i<100;i++)
		{
			wifi_format_hucreleri[i] = "";
		}
		wifi_format_hucreleri[100] = "0";
		wifi_format_hucreleri[101] = "1";
	    liste_wifi_format.add(wifi_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("WiFi", liste_wifi_format);
	    
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
		bluetooth_format_hucreleri[6] = "4C:A5:6D:1D:3C:AB";
		bluetooth_format_hucreleri[7] = "22:22:EB:ED:16:0F";
		bluetooth_format_hucreleri[8] = "BC:44:86:F2:C0:00";
		bluetooth_format_hucreleri[9] = "78:A8:73:5A:C6:FF";
		bluetooth_format_hucreleri[10] = "E0:B9:A5:F6:2B:E6";
		bluetooth_format_hucreleri[11] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[12] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[13] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[14] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[15] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[16] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[17] = "XX:XX:XX:XX:XX:XX";
		bluetooth_format_hucreleri[18] = "PÝL DURUMU";
		bluetooth_format_hucreleri[19] = "";
		for(int i=20;i<100;i++)
		{
			bluetooth_format_hucreleri[i] = "";
		}
		bluetooth_format_hucreleri[100] = "0";
		bluetooth_format_hucreleri[101] = "1";
		liste_bluetooth_format.add(bluetooth_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("Bluetooth", liste_bluetooth_format);
	    
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
		manyetik_alan_format_hucreleri[6] = "X";
		manyetik_alan_format_hucreleri[7] = "Y";
		manyetik_alan_format_hucreleri[8] = "Z";
		manyetik_alan_format_hucreleri[9] = "PÝL DURUMU";
		for(int i=10;i<100;i++)
		{
			manyetik_alan_format_hucreleri[i] = "";
		}
		manyetik_alan_format_hucreleri[100] = "0";
		manyetik_alan_format_hucreleri[101] = "1";
		liste_manyetik_alan_format.add(manyetik_alan_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("ManyetikAlan", liste_manyetik_alan_format);
	    
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
		ivmeolcer_format_hucreleri[6] = "X";
		ivmeolcer_format_hucreleri[7] = "Y";
		ivmeolcer_format_hucreleri[8] = "Z";
		ivmeolcer_format_hucreleri[9] = "PÝL DURUMU";
		for(int i=10;i<100;i++)
		{
			ivmeolcer_format_hucreleri[i] = "";
		}
		ivmeolcer_format_hucreleri[100] = "0";
		ivmeolcer_format_hucreleri[101] = "1";
		liste_ivmeolcer_format.add(ivmeolcer_format_hucreleri);
	    excel_kutuphanesi.DosyayaYaz("Ivmeolcer", liste_ivmeolcer_format);
	}


	


	/********************************************************************************************
	 * 
	 * FONKSÝYON ADI: 				CheckedTextViewTiklanabilirAyarla </br> </br>
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
	public void CheckedTextViewTiklanabilirAyarla() {
		/**
		 * WiFi CheckedTextView'i týklanabilir hale getirilmiþtir.
		 */
		ctv_wifi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Bluetooth CheckedTextView'i týklanabilir hale getirilmiþtir.
		 */
		ctv_bluetooth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Manyetik Alan CheckedTextView'i týklanabilir hale getirilmiþtir.
		 */
		ctv_manyetik_alan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});

		/**
		 * Ývmeölçer CheckedTextView'i týklanabilir hale getirilmiþtir.
		 */
		ctv_ivmeolcer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((CheckedTextView) v).toggle();
			}
		});
		
		/**
		 * CheckedTextView larýn baþlangýçta tikli olarak gelmesi saðlanmýþtýr.
		 */
		ctv_wifi.setChecked(true);
		ctv_bluetooth.setChecked(true);
		ctv_manyetik_alan.setChecked(true);
		ctv_ivmeolcer.setChecked(true);
	}

}
