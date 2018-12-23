package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class timeToeat {

	/**
	 * the variables of the class
	 */
	Packmen packmen;
	Fruit fruit;
	double time;

	public timeToeat() {
		this.time = 0;
	}

	/**
	 * Initialize constructor
	 */
	public timeToeat(Packmen p, Fruit f, double time) {
		this.packmen = p;
		this.fruit = f;
		this.time = time;
	}
	
	public timeToeat(Point3D p, Point3D f, double time) {
		this.packmen.pack_place = p;
		this.fruit.f_place = f;
		this.time = time;
	}

	/**
	 * getters and setters
	 * @return
	 */
	public Packmen getPackmen() {
		return packmen;
	}

	public void setPackmen(Packmen p) {
		this.packmen = p;
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Point3D fru) {
		this.fruit.f_place = fru;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setPackmen(Point3D pack) {
		this.packmen.pack_place = pack;
		
	}

	public void getFruit(Point3D fru) {
		this.fruit.f_place = fru;
		
	}
	public Point3D getPackmenLocation() {
	return this.packmen.pack_place;
	}

	

}
