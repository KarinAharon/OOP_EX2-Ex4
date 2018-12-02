package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import GIS.GIS_element;
import GIS.GisElement;
import GIS.MetaData;
import Geom.Point3D;

/**
 * This class make a kml file from csv file. 
 * @author Mor Danino and Karin Aharon
 *
 */
public class Csv2kml {

	/**
	 * This function received a csv file and keep the data of every element in layer.
	 * @param csvFile the csv file
	 */
	public static void readFile(String csvFile) {
		
		ArrayList<String[]>data = new ArrayList<>();
		String line = "";
		String cvsSplitBy = ",";
		int counter = 0;
		
		Set<GIS_element> layer = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			while(counter<3 && (line = br.readLine()) != null)
				counter++;
			
			while ((line = br.readLine()) != null) 
			{
				String[] csv = line.split(cvsSplitBy);
				data.add(csv);
				MetaData md = new MetaData();
				md.setTime(csv[3]);
				md.setMac(csv[0]);
				md.setSSID(csv[1]);
				md.setAuthMode(csv[2]);
				Point3D point = new Point3D(Double.parseDouble(csv[6]),Double.parseDouble(csv[7]),Double.parseDouble(csv[8]));
				GisElement gis = new GisElement(md,point);
				layer.add(gis);

					
				}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		writeFileKML(data);
		
	}
	
	/**
	 * This function received array list of array of string and (the data of the csv file) and make a kml file.
	 * @param data data of the csv file
	 */
	public static void writeFileKML(ArrayList<String[]> data) {
		
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n <Document> <Folder><name>Wifi Networks</name>\n";
		content.add(kmlstart);

		String kmlend = "</Folder>\n</Document>"+"\n</kml>";
		try{
			FileWriter fw = new FileWriter("OutPut1.kml");
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 1; i < data.size(); i++) {
				String[] info = data.get(i);
				String kmlelement = "<Placemark>\n" +
	                    "<description>"+"mac"+info[0]+", ssid"+info[1]+", AuthMode"+info[2]+", time"+info[3]+
	                    "</description>\n"+
	                    "<Point>\n" +
	                    "<coordinates>"+info[7]+","+info[6]+"</coordinates>" +
	                    "</Point>\n" +
	                    "</Placemark>\n";
				content.add(kmlelement);
			}
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

	}}

