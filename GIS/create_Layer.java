package GIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Algoritmim.MultiCSV;
import File_format.Csv2kml;
import Geom.Point3D;

/**
 * This class create layer from a csv file and also create a kml file from the layer.
 * @author Mor Danino and Karin Aharon
 *
 */
public class create_Layer {
	/**
	 * The variable of the class
	 */
	static int count=0;


	/**
	 * This function received a string of csv file and create a layer from the data of the file.
	 * @param csvFile the name of the csv file
	 * @return layer
	 * @throws Exception
	 */
	public static GIS_layer csv2layer(String csvFile) throws Exception {

		ArrayList<String[]>data = new ArrayList<>();
		String line = "";
		String cvsSplitBy = ",";
		int counter = 0;
		GIS_layer layer = new GisLayer();

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
				Point3D point = new Point3D(Double.parseDouble(csv[7]),Double.parseDouble(csv[6]),Double.parseDouble(csv[8]));
				GisElement gis = new GisElement(md,point);
				layer.add(gis);

			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return layer;
	}

	/**
	 * This function received a layer and create a kml file from it data.
	 * @param layer any layer
	 */
	public static void layer2kml(GIS_layer layer) {
		try {
			PrintWriter writer = new PrintWriter(new File("OutPut2 "+(int)count+++".kml"));
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n <Document> <Folder><name>Wifi Networks</name>\n");
			for (GIS_element element : layer) {		
				sb.append(
						"<Placemark>\n" +
								"<description>"+element.getData().toString()+
								"</description>\n"+
								"<Point>\n" +
								"<coordinates>"+element.getGeom()+"</coordinates>" +
								"</Point>\n" +
						"</Placemark>\n");
			}
			sb.append("</Folder>\n</Document>\n</kml>");
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");

		}
		catch(Exception e) {}	
	}}
