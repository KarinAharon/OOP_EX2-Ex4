import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class MyCoords_Test {

	@Test
	void testAdd() {
		
		MyCoords c = new MyCoords();
		Point3D vector = new Point3D(337.69899206128815, -359.2492067337923,-20.0);
		Point3D gps = new Point3D(32.103315 ,35.209039, 670.0);
		Point3D gps_new =	c.add(gps, vector);
		Point3D expected = new Point3D(32.10635200466716,35.20522499075815,650.0);
		if(!(gps_new.equals(expected)))
			fail("error: the points are not the same");	
	}

	@Test
	void testDistance3d() {
		MyCoords c = new MyCoords();
		Point3D p1 = new Point3D (32.10332, 35.20904, 670);
		Point3D p2 = new Point3D (32.10635, 35.20523, 650); 
		double expected = 492.6509128408381;

		double distance = c.distance3d(p1, p2);
		assertEquals(expected, distance);
	}

	@Test
	void testVector3D() {
		MyCoords c = new MyCoords();
		Point3D p1 = new Point3D (32.10332, 35.20904, 670);
		Point3D p2 = new Point3D (32.10635, 35.20523, 650); 
		Point3D vector = c.vector3D(p1, p2);
		Point3D expected = new Point3D (336.9206275762522,-358.87241832792813,-20.0);
		if(!(vector.equals(expected)))
			fail("error: the vectors are not the same");
	}

	@Test
	void testAzimuth_elevation_dist() {
		MyCoords c = new MyCoords();
		Point3D a = new Point3D(32.10332, 35.20904, 670);
		Point3D b = new Point3D(32.10635, 35.20523, 650); 
		double [] azimut = c.azimuth_elevation_dist(a, b);
		double[] expected = {313.19444380079074, -2.3282346774681675, 492.6509128408381};
		if(!(azimut[0]==expected[0])&&azimut[1]==expected[1]&&azimut[2]==expected[2])
			fail("error: the results are not the same");
	}

	@Test
	void testIsValid_GPS_Point() {
		
		MyCoords c = new MyCoords();
		Point3D p1= new Point3D (32.103315,35.209039,670);
		Point3D p2= new Point3D (-90.5,150,600);
		boolean flag_1 = c.isValid_GPS_Point(p1);
		boolean flag_2 = c.isValid_GPS_Point(p2);
		assertEquals(true, flag_1);
		assertEquals(false, flag_2);
	}

}
