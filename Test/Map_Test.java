import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import GIS.Map;
import Geom.Point3D;

class Map_Test {

	@Test
	void Gps2Pixel() {

		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

		Point3D gps = new Point3D(32.1045513,35.2035022);
		Point3D pixel = m.Gps2Pixel(imgBck, gps.x(), gps.y());
		Point3D expected = new Point3D(135.0,229.0,0.0);

		assertEquals(expected.x(), pixel.x());
		assertEquals(expected.y(), pixel.y());


	}
	@Test
	void Pixel2Gps() {

		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

		Point3D pixel = m.Pixel2Gps(imgBck,410,935);
		Point3D gps = m.Pixel2Gps(imgBck, pixel.x(), pixel.y());
		Point3D expected = new Point3D(32.10581634243018,35.20279421952237,0.0);

		assertEquals(expected.x(),gps.x());
		assertEquals(expected.y(), gps.y());


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
}
