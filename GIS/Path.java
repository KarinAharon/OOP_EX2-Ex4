package GIS;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Coords.MyCoords;
import Geom.Point3D;
import GIS.Map;

/**
 * this class represent a path
 * @author Mor Danino and Karin Aharon
 * 
 */
public class Path {

	/**
	 * the variables of the class
	 */
	
	public Packmen packmen;
	public ArrayList<Fruit> fruits;
	public ArrayList<Double> time;

	/**
	 * constructor
	 */
	public Path() {
		this.packmen = new Packmen();
		this.fruits=new ArrayList<Fruit>();
		this.time=new ArrayList<Double>();
	}
	/**
	 * constructor which received some packmen
	 * @param p any packmen
	 */
	public Path(Packmen p) {
		this.packmen = p;
		this.fruits = new ArrayList<Fruit>();
		this.time=new ArrayList<Double>();
	}
	/**
	 * constructor which received any packmen and any array list of fruits
	 * @param p any packmen
	 * @param f any array list of fruits
	 */
	public Path(Packmen p, ArrayList<Fruit> f) {
		this.packmen = p;
		this.fruits = f;
		this.time=new ArrayList<Double>();
	}
	public void setTime(double time) {
		this.time.add(time);
	}

	/**
	 * function that calculate distance between two pints
	 * @param first
	 * @param second
	 * @return the distance
	 */
	public double distance(Point3D first, Point3D second) {
		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());
		Point3D point_1 = m.Pixel2Gps(imgBck, first.x(), first.y());
		Point3D point_2 = m.Pixel2Gps(imgBck, second.x(), second.y());
		MyCoords c = new MyCoords();
		double SumOfDistance = c.distance3d(point_1, point_2);
	
		return SumOfDistance;
	}
	/**
	 * String that represent path of any packmen
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i<this.fruits.size(); i++) {
			s = s +fruits.get(i);
		}
		s = s+"Packmen: "+ this.packmen;
		return s;
		
	}

}
