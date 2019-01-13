package GIS;
/**
 * this class represent a packmen
 * @author Mor Danino and Karin Aharon
 */
import java.util.ArrayList;

import Geom.Geom_element;
import Geom.Point3D;

public class Packmen {
	
	/**
	 * the variables of the class
	 */

	double speed;
	public Point3D pack_place;
	int radius;
	int id;
	String type= "";

	/**
	 * constructor
	 */
public Packmen () {
	this.speed=0;
	this.radius=1;
	this.id=0;
	this.pack_place= new Point3D(0,0);
	this.type = "P";
	
}
/**
 * getters and setters for the variables that describe the packmen
 */
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setPac_place(Point3D pac_place) {
		this.pack_place = pac_place;
	}
	public Point3D getPoint3D() {
		return pack_place;

	}

	/**
	 * create a new point location for any packmen
	 * @param x the value of x point
	 * @param y the value of y point
	 */
	public Packmen(int x, int y) {
		Point3D point = new Point3D(x,y);
		this.pack_place = point; 
		this.speed = 1;
		this.radius = 1;
		this.id = 1;
	}
	/**
	 * Initialize constructor
	 * @param pack_place the point which the packmen located
	 * @param radius the radius of the packmen
	 * @param speed the speed of the packmen
	 * @param id the id of the packmen
	 * @param type F
	 */
	public Packmen(double speed, Point3D pack_place, int radius, int id, String type) {
		this.speed = speed;
		this.pack_place = pack_place;
		this.radius = radius;
		this.id = id;
		this.type = type;

	}
	

	public Packmen(String csv_line) {

		String [] f = csv_line.split(","); 
		Point3D f_place = new Point3D(Double.parseDouble(f[2]),Double.parseDouble(f[3]),Double.parseDouble(f[4]));
		speed = Double.parseDouble(f[5]);
		id = Integer.parseInt(f[1]);
		radius = Integer.parseInt(f[6]);

	}
	/**
	 * String that represent the values that describe the packmen
	 */
	public String toString() {
		return "type: "+this.type+","
				+"id: "+this.id+","
				+"Lat: "+this.pack_place.x()+","
				+"Lon: "+this.pack_place.y()+","
				+"Alt: "+this.pack_place.z()+","
				+"Speed: "+this.speed+","
				+"Raduios: "+this.radius+"\n";
	}

}
