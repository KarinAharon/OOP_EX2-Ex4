package GIS;

import Geom.Point3D;


public class MetaData implements Meta_data {
	
	/**
	 * The variables of the class
	 */

	private String time;
	private String mac;
	private String SSID;
	private String AuthMode;
	private long utc;

	/**
	 * This function return the mac address
	 * @return mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * This function return the ssid
	 * @return ssid
	 */
	public String getSSID() {
		return SSID;
	}

	/**
	 * This function return the AuthMode
	 * @return AuthMode
	 */
	public String getAuthMode() {
		return AuthMode;
	}

	/**
	 * This function return the time as long
	 */
	@Override
	public long getUTC() {
		this.time = this.time.replaceAll("[.,/,:,-,-, ]", "");
		long time= Long.parseLong(this.time);
		return time;

	}
	public MetaData(){
	}


	/**
	 * This function set the time
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * This function set the mac address
	 * @param mac address
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * This function set the ssid
	 * @param ssid
	 */
	public void setSSID(String SSID) {
		this.SSID = SSID;
	}

	/**
	 * This function set the AuthMode
	 * @param time
	 */
	public void setAuthMode(String authMode) {
		this.AuthMode = authMode;
	}

	/**
	 * Initialize constructor
	 * @param time
	 * @param mac
	 * @param SSID
	 * @param AuthMode
	 */
	public MetaData(String time,String mac, String SSID,String AuthMode) {
		this.time=time;	
		this.mac=mac;
		this.SSID=SSID;
		this.AuthMode= AuthMode;
	}

	/**
	 * This function return a string
	 */
	@Override
	public String toString() {

		String s = 	"time: "+getUTC()+", mac: "+mac +" , SSID: "+ SSID +", authMode: "+AuthMode ;
		

		return s;

	}

	@Override
	public Point3D get_Orientation() {
	
		return null;
	}
	/**
	 * This function initialize the utc
	 * @param utc
	 */
	public MetaData(long utc) {
		this.utc = utc;
	}

}
