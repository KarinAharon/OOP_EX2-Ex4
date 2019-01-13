package GIS;

import Geom.Point3D;

/**
 * This class represent a ghost
 */

public class Ghost {

	/**
	 * the variables of the class
	 */
	
	double speed;
	public Point3D Ghost_location;
	int radius;
	int id;
	String type= "";

	/**
	 * constructor
	 */
	public Ghost() {

		this.speed = 10;
		this.radius = 1;
		this.id = 0;
		this.Ghost_location = new Point3D(0,0);
		this.type = "G";

	}

	/**
	 * Initialize constructor
	 * @param speed any speed
	 * @param Ghost_location the point which the ghost located
	 * @param radius any radios
	 * @param id any id
	 * @param type the type G
	 */
	public Ghost(double speed,Point3D Ghost_location,int radius,int id,String type) {

		this.speed = speed;
		this.Ghost_location = Ghost_location;
		this.radius = radius;
		this.id = 0;
		this.type = "G";
	}
	
	/**
	 * getters and setters
	 */
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Point3D getGhost_location() {
		return Ghost_location;
	}

	public void setGhost_location(Point3D ghost_location) {
		Ghost_location = ghost_location;
	}

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

	public String getType() {
		return type;
	}

	public Point3D getPoint3D() {
		return Ghost_location;
	}
}
