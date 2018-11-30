package Coords;

import java.awt.Point;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	
	//http://cosinekitty.com/compass.html - checking azimut function
	//https://www.omnicalculator.com/other/azimuth - נוסחאות לאזימוט
	//http://tchester.org/sgm/analysis/peaks/how_to_get_view_params.html - נוסחאות לגובה

	private int radius_earth = 6371000;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		double Lon_Norm = Math.cos((gps.x()*Math.PI)/180);

		double x_sin= local_vector_in_meter.x()/radius_earth;
		double x_diff=Math.asin((x_sin*180)/Math.PI);
		double new_x= x_diff+ gps.x();

		double y_sin= local_vector_in_meter.y()/(radius_earth*Lon_Norm);
		double y_diff=Math.asin((y_sin*180)/Math.PI);
		double new_y= y_diff+ gps.y();

		double new_z= gps.z()+local_vector_in_meter.z();

		gps = new Point3D(new_x,new_y,new_z);
		return gps;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {

		Point3D v = vector3D(gps0, gps1);

		double dis =Math.sqrt(v.x()*v.x()+v.y()*v.y()+v.z()*v.z());

		if (dis>100000)

		{
			try {
				throw new Exception("The distance is over more 100 km ");
			} catch (Exception e) {e.printStackTrace();}	
		}

		return dis;
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double Lon_Norm = Math.cos(gps0.x()*Math.PI/180);

		double dis_x= gps1.x()-gps0.x();
		double dis_y= gps1.y()-gps0.y();
		double dis_z= gps1.z()-gps0.z();

		double x_rad= (Math.PI*dis_x)/180;
		double y_rad= (Math.PI*dis_y)/180;

		dis_x= Math.sin(x_rad)*radius_earth;
		dis_y= Math.sin(y_rad)*radius_earth*Lon_Norm;
		Point3D vector3D = new Point3D(dis_x, dis_y, dis_z);
		return vector3D;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double azimut [] = new double [3];
		double distance = distance3d(gps0, gps1);
		double y_dis = gps1.y()-gps0.y();
		double x_dis = gps1.x()-gps0.x();
		
		double a = (Math.sin(Math.toRadians(y_dis)))*(Math.cos(Math.toRadians(gps1.x())));
		double b = (Math.cos(Math.toRadians(gps0.x()))*Math.sin(Math.toRadians(gps1.x())))-(Math.sin(Math.toRadians(gps0.x()))*Math.cos(Math.toRadians(gps1.x()))*Math.cos(Math.toRadians(y_dis)));

		double angle = Math.atan2(a, b);
	
		if(angle<0)
			angle = Math.toDegrees(angle)+360;
		else 
			angle = Math.toDegrees(angle);
		
		double hight = (180/Math.PI)*(((gps1.z()-gps0.z())/distance)-(distance/(2*radius_earth)));

		
		azimut[0]=angle;
		azimut[1]=hight;
		azimut[2]=distance;
		
		return azimut;
		
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {

		if((p.x()>=-90 && p.x()<=90) && (p.y()>=-180 && p.y()<=180) && (p.z()>=-450 && p.z()<=Integer.MAX_VALUE))
			return true;

		return false;
	}

	public static void main(String[] args) {

		MyCoords c = new MyCoords();

		Point3D v1 = new Point3D (32.10332, 35.20904, 670);
		Point3D v2 = new Point3D (32.10635, 35.20523, 650); 

		System.out.println("dis " +c.distance3d(v1, v2));
		System.out.println(c.vector3D(v1, v2));

		Point3D v3 = new Point3D(337.69899206128815, -359.2492067337923,-20.0);
		Point3D gps = new Point3D(32.103315 ,35.209039, 670.0);
		//Point3D gps = new Point3D(32.106352 ,35.205225, 650.0);
		//System.out.println("jjjj"+c.add(gps, v3));

		Point3D a = new Point3D(32.10332, 35.20904, 670);
		Point3D b = new Point3D(32.10635, 35.20523, 650); 
		//Point3D a = new Point3D(37.67094,45.798875,600);
		//Point3D b = new Point3D(47.98654,81.76948,200);
		//Point3D a = new Point3D(35.103,31.209);
		//Point3D b = new Point3D(33.105,29.205);
		double [] azi = c.azimuth_elevation_dist(a, b);
		System.out.println(Arrays.toString(azi));
		
		Point3D v4 = new Point3D (87,120,670);
		Point3D v5 = new Point3D (32,170,650);
		//System.out.println("distance " +c.distance3d(v4, v5));
		
		  Point3D p1=new Point3D(32.103315,35.209039,670);
	      Point3D p2 =new Point3D(32.106352,35.205225,650);
	      double [] azi2 = c.azimuth_elevation_dist(p1, p2);
	      
			System.out.println(Arrays.toString(azi2));
			//Point3D o1= new Point3D (32.103315,35.209039,670);
			//Point3D o2= new Point3D (-90.5,150,600);
			//System.out.println(c.isValid_GPS_Point(o1));
			//System.out.println(c.isValid_GPS_Point(o2));
			

	}

}
