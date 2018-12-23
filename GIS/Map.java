package GIS;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Coords.MyCoords;

import Geom.Point3D;
/**
 * this class converting pixel point to gps point and 
 * gps point to pixel point,
 * it also calculate the distance between two pixel point and it azimuth
 * code: https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
 * @author Mor Danino and Karin Aharon
 */
public class Map{

	/**
	 * the variables of the class
	 */
	String imgMapFilename = "Ariel1.png";
	ImageIcon imgBck = new ImageIcon(imgMapFilename);
	static final Point3D map_start=new Point3D(32.106046,35.202574);
	static final Point3D map_end=new Point3D(32.101858,35.212405);
	static final Point3D map_upleft=new Point3D(map_start.x()-map_end.x(),map_end.y()-map_start.y());
	private int mapWidth;
	private int mapHeight;
	
	/**
	 * constructor
	 * @param mapWidth
	 * @param mapHeight
	 */
	public Map(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
	
	/**
	 * this function convert a gps point to a pixel point
	 * @param imgBck
	 * @param latitude the value x
	 * @param longitude the value y
	 * @return pixel point
	 */
	public Point3D Gps2Pixel(ImageIcon imgBck,double latitude, double longitude){
		
	   longitude = longitude -map_start.y();
	   latitude = map_start.x()-latitude;

	    int x = (int) (mapWidth*(longitude/map_upleft.y()));
	    int y = (int) (mapHeight*(latitude/map_upleft.x()));

	    return new Point3D(x, y);
	}
	
	/**
	 * this function convert a pixel point to a gps point
	 * @param imgBck
	 * @param Pixelx the value x
	 * @param Pixely the value y
	 * @return gps point
	 */
	public Point3D Pixel2Gps(ImageIcon imgBck,double Pixelx, double Pixely){

	    double y=((Pixelx*map_upleft.y())/mapWidth)+map_start.y();
	    double x=map_start.x()-((Pixely*map_upleft.x())/mapHeight);

	    return new Point3D(x, y);

	}
	/**
	 * this function calculate the distance between two pixel point and it azimuth
	 * @param imgBck
	 * @param point1 the first point
	 * @param point2 the second point
	 * @return distance, angle and height
	 */
	public double[] angle(ImageIcon imgBck, Point3D point1, Point3D point2){
		MyCoords c = new MyCoords();
		Point3D new_point1 = Pixel2Gps(imgBck, point1.x(), point1.y());
		Point3D new_point2 = Pixel2Gps(imgBck, point2.x(), point2.y());
		double arr_new[] = new double[3];
		arr_new = c.azimuth_elevation_dist(new_point1, new_point2);
		return arr_new;	
	}

}