package GIS;

import Geom.Geom_element;

import Geom.Point3D;
/**
 * this class represent a fruit
 * @author Mor Danino and Karin Aharon
 *
 */
public class Fruit {
	/**
	 * the variables of the class
	 */
	Point3D f_place;
	double weight;
	int id;
	String type = "";

	/**
	 * constructor
	 */
	public Fruit () {
		this.weight=1;
		this.id=0;
		this.f_place= new Point3D(0,0);
		this.type = "F";

	}

	/**
	 * setter for the location of the fruit
	 * @param f_place the point which the fruit located
	 */

	public void setF_place(Point3D f_place) {
		this.f_place = f_place;
	}
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * create a new point location for any fruit
	 * @param x the value of x point
	 * @param y the value of y point
	 */
	public Fruit(double x, double y){
		Point3D point = new Point3D(x,y);
		this.f_place = point;
		this.id = 1;
		this.weight = 1;	

	}
	/**
	 * Initialize constructor
	 * @param f_place the point which the fruit located
	 * @param weight the weight of the fruit
	 * @param id the id of the fruit
	 * @param type F
	 */
	public Fruit(Point3D f_place, double weight, int id, String type) {
		this.f_place = f_place;
		this.weight=weight;
		this.type = type;
		this.id = id;
	}

	public Fruit(String csv_line) {

		String [] f = csv_line.split(","); 
		Point3D f_place = new Point3D(Double.parseDouble(f[2]),Double.parseDouble(f[3]),Double.parseDouble(f[4]));
		weight = Double.parseDouble(f[5]);
		id = Integer.parseInt(f[1]);

	}
	/**
	 * String that represent the values that describe the fruit
	 */
	public String toString() {
		return "type:  "+this.type+","
				+" id : "+this.id+","
				+" Lat : "+this.f_place.x()+","
				+" Lon : "+this.f_place.y()+","
				+" Alt : "+this.f_place.z()+","
				+" Weight : "+this.weight + "\n";

	}
	/**
	 * getter of fruit location
	 */

	public Point3D getPoint3D() {
		return f_place;

	}

	/**
	 * copy constructor
	 * @param f any fruit
	 */
	public Fruit(Fruit f) {
		this.f_place = f.f_place;
		this.weight = f.weight;
		this.id = f.id;
		this.type = f.type;	
	}
}
