package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import Algoritmim.MultiCSV;
import File_format.Csv2kml;
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
	public ArrayList<Ghost> ghost_list=new ArrayList<>();
	public ArrayList<Box> box_list=new ArrayList<>();
	public Player player = new Player();

	/**
	 * constructor
	 */
	public Game() {
		this.fruit_list=new ArrayList<>();
		this.packmen_list=new ArrayList<>();
		this.ghost_list = new ArrayList<>();
		this.box_list = new ArrayList<>();
		this.player = new Player();
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
							(int)Double.parseDouble(csv[6]),
							(int)Double.parseDouble(csv[1]),
							csv[0]);

					packmen_list.add(p);
				}
				else if(csv[0].equals("F")) {
					Point3D p_2 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),
							Double.parseDouble(csv[4]));
					Point3D p2 = m.Gps2Pixel(imgBck, p_2.x(), p_2.y());
					Fruit f = new Fruit(p2,
							Double.parseDouble(csv[5]),
							(int)Double.parseDouble(csv[1]),
							csv[0]);
					fruit_list.add(f);
				//	fruit_list2.add(f);

				}

				else if (csv[0].equals("G")) {
					Point3D p_3 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),
							Double.parseDouble(csv[4]));
					Point3D p3 = m.Gps2Pixel(imgBck, p_3.x(), p_3.y());
					Ghost g = new Ghost(Double.parseDouble(csv[5]),
							p3,
							(int)Double.parseDouble(csv[6]),
							(int)Double.parseDouble(csv[1]),
							csv[0]);
					ghost_list.add(g);
				}

				else if (csv[0].equals("B")) {
					Point3D p_4 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),
							Double.parseDouble(csv[4]));
					Point3D p_5 = new Point3D(Double.parseDouble(csv[5]),
							Double.parseDouble(csv[6]),
							Double.parseDouble(csv[7]));
					Point3D p4 = m.Gps2Pixel(imgBck, p_4.x(), p_4.y());
					Point3D p5 = m.Gps2Pixel(imgBck, p_5.x(), p_5.y());
					Box b = new Box(p4,
							p5,
							csv[0],
							(int)Double.parseDouble(csv[1]));
					box_list.add(b);

				}
				
				else if(csv[0].equals("M")) {
					Point3D p_1 = new Point3D(Double.parseDouble(csv[2]),
							Double.parseDouble(csv[3]),	Double.parseDouble(csv[4]));

					Point3D p1 = m.Gps2Pixel(imgBck, p_1.x(), p_1.y());

					this.player = new Player((int)Double.parseDouble(csv[1]),
							p1,
							Double.parseDouble(csv[5]),
							(int)Double.parseDouble(csv[6]),
							csv[0]);
				
				}

			}
			System.out.println("Packmen list size: "+packmen_list.size());
			System.out.println("Fruits list size: "+fruit_list.size());
			System.out.println("Box list size: " +box_list.size());
			System.out.println("Ghost list size: " +ghost_list.size());

		}catch (Exception e) {
		}	
		return;
	}
	public ArrayList<Ghost> getGohst_list() {
		return ghost_list;
	}

	public void setGohst_list(ArrayList<Ghost> gohst_list) {
		this.ghost_list = gohst_list;
	}

	public ArrayList<Box> getBox_list() {
		return box_list;
	}

	public void setBox_list(ArrayList<Box> box_list) {
		this.box_list = box_list;
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


	public Game(ArrayList<String> csvFile) {

		
		ArrayList<String[]>data = new ArrayList<>();
		String cvsSplitBy = ",";
		int counter = 0;
		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());
		int i = 0;
		
		while (i < csvFile.size()) {
			String line = csvFile.get(i);
			String[] csv = line.split(cvsSplitBy);
			data.add(csv);
			if(csv[0].equals("M")) {
				Point3D p_0 = new Point3D(Double.parseDouble(csv[2]),
						Double.parseDouble(csv[3]),	Double.parseDouble(csv[4]));

				Point3D p0 = m.Gps2Pixel(imgBck, p_0.x(), p_0.y());

				this.player = new Player((int)Double.parseDouble(csv[1]),
						p0,
						Double.parseDouble(csv[5]),
						(int)Double.parseDouble(csv[6]),
						csv[0]);
			
			}
			
			if(csv[0].equals("P")) {
				Point3D p_1 = new Point3D(Double.parseDouble(csv[2]),
						Double.parseDouble(csv[3]),	Double.parseDouble(csv[4]));

				Point3D p1 = m.Gps2Pixel(imgBck, p_1.x(), p_1.y());

				Packmen p = new Packmen(Double.parseDouble(csv[5]),
						p1,
						(int)Double.parseDouble(csv[6]),
						(int)Double.parseDouble(csv[1]),
						csv[0]);

				packmen_list.add(p);
			}
			else if(csv[0].equals("F")) {
				Point3D p_2 = new Point3D(Double.parseDouble(csv[2]),
						Double.parseDouble(csv[3]),
						Double.parseDouble(csv[4]));
				Point3D p2 = m.Gps2Pixel(imgBck, p_2.x(), p_2.y());
				Fruit f = new Fruit(p2,
						Double.parseDouble(csv[5]),
						(int)Double.parseDouble(csv[1]),
						csv[0]);
				fruit_list.add(f);
			//	fruit_list2.add(f);

			}

			else if (csv[0].equals("G")) {
				Point3D p_3 = new Point3D(Double.parseDouble(csv[2]),
						Double.parseDouble(csv[3]),
						Double.parseDouble(csv[4]));
				Point3D p3 = m.Gps2Pixel(imgBck, p_3.x(), p_3.y());
				Ghost g = new Ghost(Double.parseDouble(csv[5]),
						p3,
						(int)Double.parseDouble(csv[6]),
						(int)Double.parseDouble(csv[1]),
						csv[0]);
				ghost_list.add(g);
			}

			else if (csv[0].equals("B")) {
				Point3D p_4 = new Point3D(Double.parseDouble(csv[2]),
						Double.parseDouble(csv[3]),
						Double.parseDouble(csv[4]));
				Point3D p_5 = new Point3D(Double.parseDouble(csv[5]),
						Double.parseDouble(csv[6]),
						Double.parseDouble(csv[7]));
				Point3D p4 = m.Gps2Pixel(imgBck, p_4.x(), p_4.y());
				Point3D p5 = m.Gps2Pixel(imgBck, p_5.x(), p_5.y());
				Box b = new Box(p4,
						p5,
						csv[0],
						(int)Double.parseDouble(csv[1]));
				box_list.add(b);

			}
			i++;
		}
		System.out.println("Packmen list size: "+packmen_list.size());
		System.out.println("Fruits list size: "+fruit_list.size());
		System.out.println("Box list size: " +box_list.size());
		System.out.println("Ghost list size: " +ghost_list.size());


		return;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

