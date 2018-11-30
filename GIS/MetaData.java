package GIS;

import Geom.Point3D;


public class MetaData implements Meta_data {

	private String time;
	private String mac;
	private String SSID;
	private String AuthMode;
	private long utc;

	public String getMac() {
		return mac;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	@Override
	public long getUTC() {
		this.time = this.time.replaceAll("[.,/,:,-,-, ]", "");
		long time= Long.parseLong(this.time);
		return time;

	}
	public MetaData(){
	}


	public void setTime(String time) {
		this.time = time;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setSSID(String SSID) {
		this.SSID = SSID;
	}

	public void setAuthMode(String authMode) {
		this.AuthMode = authMode;
	}

	public MetaData(String time,String mac, String SSID,String AuthMode) {
		this.time=time;	
		this.mac=mac;
		this.SSID=SSID;
		this.AuthMode= AuthMode;
	}

	@Override
	public String toString() {

		String s = 	"time: "+getUTC()+", mac: "+mac +" , SSID: "+ SSID +", authMode: "+AuthMode ;
		

		return s;

	}

	@Override
	public Point3D get_Orientation() {
	
		return null;
	}
	
	public MetaData(long utc) {
		this.utc = utc;
	}

}
