package GIS;

import Geom.Point3D;
/**
 * This class represent a box
 */
public class Box {
	
	/**
	 * the variables of the class
	 */
	public Point3D up;
	public Point3D down;
	int id;
	String type= "";

	/**
	 * getters and setters for the variables that describe the packmen
	 */
	
	public Point3D getUp() {
		return up;
	}

	public void setUp(Point3D up) {
		this.up = up;
	}

	public Point3D getDown() {
		return down;
	}

	public void setDown(Point3D down) {
		this.down = down;
	}

	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * constructor
	 */
	public Box() {
		
		this.up = new Point3D(0,0);
		this.down = new Point3D(0,0);
	}

	/**
	 * Initialize constructor
	 * @param p1 first point
	 * @param p2 second point
	 * @param type the type B
	 * @param id any ID
	 */
	public Box(Point3D p1, Point3D p2, String type, int id) {
		this.up = p2;
		this.down = p1;
		this.type = type;
		this.id = id;
	}

	/**
	 * Calculate the area
	 * @param p1 first point
	 * @param p2 second point
	 * @return area
	 */
	public double area(Point3D p1, Point3D p2) {
		
		return Math.abs((p1.x()-p2.x())*(p2.y()-p1.y()));
		
	}

}

