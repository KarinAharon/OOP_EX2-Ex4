package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import Algoritmim.MultiCSV;
import Geom.Point3D;
/**
 * this class represent a game
 * @author Mor Danino and Karin Aharon
 *
 */
public class Game {
 /**
  * the variables of the class
  */
	public ArrayList<Fruit> fruit_list=new ArrayList<>();
	public ArrayList<Fruit> fruit_list2=new ArrayList<>();
	public ArrayList<Packmen> packmen_list=new ArrayList<>();

/**
 * constructor
 */
	public Game() {
	this.fruit_list=new ArrayList<>();
	this.packmen_list=new ArrayList<>();
	} 

	/**
	 * constructor which received a String of csv file and read the data and save it in the arrays
	 * @param csvFile any csv file
	 */
	public Game(String csvFile) {
		
		ArrayList<String[]>data = new ArrayList<>();
		String line = "";
		String cvsSplitBy = ",";
		int counter = 0;
		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			while(counter<1 && (line = br.readLine()) != null)
				counter++;

			while ((line = br.readLine()) != null) 
			{
				
				String[] csv = line.split(cvsSplitBy);
				data.add(csv);
				if(csv[0].equals("P")) {
					Point3D p_1 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),	Double.parseDouble(csv[4]));
					
					Point3D p1 = m.Gps2Pixel(imgBck, p_1.x(), p_1.y());
					
					Packmen p = new Packmen(Double.parseDouble(csv[5]),
							p1,
							Integer.parseInt(csv[6]),
							Integer.parseInt(csv[1]),
							(csv[0]));
					
					packmen_list.add(p);
				}
				else if(csv[0].equals("F")) {
					Point3D p_2 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),
							Double.parseDouble(csv[4]));
					Point3D p2 = m.Gps2Pixel(imgBck, p_2.x(), p_2.y());
					Fruit f = new Fruit(p2,
							Double.parseDouble(csv[5]),
							Integer.parseInt(csv[1]),
							csv[0]);
					fruit_list.add(f);
					fruit_list2.add(f);
					
				}

			}
			System.out.println("Packmen list size: "+packmen_list.size());
			System.out.println("Fruits list size: "+fruit_list.size());

		}catch (Exception e) {
		}	
		return;
		}
	public ArrayList<Fruit> getFruit_list() {
		return fruit_list;
	}

	public void setFruit_list(ArrayList<Fruit> fruit_list) {
		this.fruit_list = fruit_list;
	}
	

	public ArrayList<Packmen> getPackmen_list() {
		return packmen_list;
	}

	public void setPackmen_list(ArrayList<Packmen> packmen_list) {
		this.packmen_list = packmen_list;
	}

	/**
	 * String that describe the data on the arrays lists
	 */
	public String toString() {
		String s1= "";
		//int k=0;
		for(int i = 0; i<packmen_list.size(); i++) {
			s1 += "type: "+packmen_list.get(i).type +
					" id: "+this.packmen_list.get(i).id+
					" Lat: "+this.packmen_list.get(i).pack_place.x()+
					" Lon: "+this.packmen_list.get(i).pack_place.y()+
					" Alt: "+this.packmen_list.get(i).pack_place.z()+
					" Speed: "+this.packmen_list.get(i).speed+
					" Raduios: "+this.packmen_list.get(i).radius+"\n";	
		}
		
		for(int j = 0; j<fruit_list.size(); j++) {
			s1+=  "type: "+fruit_list.get(j).type+
					" id: "+this.fruit_list.get(j).id+
					" Lat: "+this.fruit_list.get(j).f_place.x()+
					" Lon: "+this.fruit_list.get(j).f_place.y()+
					" Alt: "+this.fruit_list.get(j).f_place.z()+
					" Weight: "+this.fruit_list.get(j).weight+"\n";
		}
		return s1;
	}
	
/**
 * this function create a csv file which contain the data of the game
 * @param game any game
 */
public void write2csv(Game game) {
	
	   PrintWriter pw;
	try {
		pw = new PrintWriter(new File("Game.csv"));
	
       StringBuilder sb = new StringBuilder();
       sb.append("Type");
       sb.append(',');
       sb.append("id");
       sb.append(',');
       sb.append("Lat");
       sb.append(',');
       sb.append("Lon");
       sb.append(',');
       sb.append("Alt");
       sb.append(',');
       sb.append("Speed"+"\"Weight");
       sb.append(',');
       sb.append("Radious");
      sb.append('\n');
       
       for (int i = 0; i<game.packmen_list.size(); i++) {
    	   sb.append(game.packmen_list.get(i).type);
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).id);
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).pack_place.x());
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).pack_place.y());
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).pack_place.z());
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).speed);
    	   sb.append(',');
    	   sb.append(game.packmen_list.get(i).radius);
    	   sb.append("\n");
    	
       }
       
       for(int j = 0; j<game.fruit_list.size(); j++) {
    	   sb.append(game.fruit_list.get(j).type);
    	   sb.append(',');
    	   sb.append(game.fruit_list.get(j).id);
    	   sb.append(',');
    	   sb.append(game.fruit_list.get(j).f_place.x());
    	   sb.append(',');
    	   sb.append(game.fruit_list.get(j).f_place.y());
    	   sb.append(',');
    	   sb.append(game.fruit_list.get(j).f_place.z());
    	   sb.append(',');
    	   sb.append(game.fruit_list.get(j).weight);
    	   sb.append("\n");
       }

       pw.write(sb.toString());
       pw.close();
       System.out.println("done!");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}
	
