package GIS;

import java.io.File;
import java.io.FileNotFoundException;

import Algoritmim.*;
import File_format.Csv2kml;

public class test {
	public static MultiCSV m;
	//public static Csv2kml r;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		MultiCSV c = new MultiCSV();
		create_Layer create_Lay = new create_Layer();
		
		String folder = "C:\\Users\\karin\\eclipse-workspace\\OOP_EX2-Ex4";
		//GIS_layer g = (GisLayer) create_Lay.csv2layer("WigleWifi_20171201110209.csv");
		GIS_project p = c.scanFolder(folder);
		c.project2kml(p);
		
		//create kml file from csv
		//String file = "WigleWifi_20171201110209.csv";
		//r.readFile(file);
		

	}

}
