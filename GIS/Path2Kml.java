package GIS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Algoritmim.ShortestPathAlgo;
import Geom.Point3D;
/**
 * this class create a kml csv which represent the paths of the packmens
 * @author Mor Danino and Karin Aharon
 *
 */
public class Path2Kml {

public static void writeFileKML(ArrayList<Path> path) {
	

	String imgMapFilename = "Ariel1.png";
	ImageIcon imgBck = new ImageIcon(imgMapFilename);
	Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());
	
		
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n <Document> <Folder><name>Wifi Networks</name>\n";
		content.add(kmlstart);

		String kmlend = "</Folder>\n</Document>"+"\n</kml>";
		try{
			FileWriter fw = new FileWriter("Game1.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<Path> it = path.iterator();
			
			while(it.hasNext()) {
				Path temp = it.next();
				int i = 0;
				while(i<temp.fruits.size()) {
					Point3D gps_p = m.Pixel2Gps(imgBck, temp.fruits.get(i).getPoint3D().x(), temp.fruits.get(i).getPoint3D().y());
				Fruit info = temp.fruits.get(i);
				String kmlelement = "<Placemark>\n" +
	                    "<description>"+"Pacmen ID"+temp.packmen.id+
	                    "</description>\n"+
	                    "<Point>\n" +
	                    "<coordinates>"+
	                    gps_p.y()+
	                    ","+ gps_p.x()+
	                    ","+ gps_p.z()+"</coordinates>" +
	                    "</Point>\n" +
	                    "</Placemark>\n";
				         content.add(kmlelement);	
				         i++;
				}}
			content.add(kmlend);
			String csv = "";
			csv = csv+content.get(0);
			for (int i = 2; i<content.size(); i++) {
				csv = csv+content.get(i);
			}
			bw.write(csv);
			bw.close();
			System.out.println("Done!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}


