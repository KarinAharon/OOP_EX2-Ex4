package Algoritmim;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Map;
import GIS.Packmen;
import GIS.Path;
import GIS.timeToeat;
import GUI.MyFrame;
import Geom.Point3D;

public class ShortestPathAlgo  {
	/**
	 * the variables of the class
	 */

	public static ArrayList<Path> AllPath= new ArrayList<Path>();
	public ArrayList<timeToeat> list = new ArrayList<timeToeat>();
	/**
	 * constructor
	 */
	public ShortestPathAlgo(MyFrame m) {

		AllPath = new ArrayList<Path>();
	}
	/**
	 * constructor which received a game and calculates the minimum time that every packmen eat any fruit
	 * it saves the eating path of every packmen in array list and also it saves the time that took to every packmen to eat the fruits
	 * @param game any game
	 */
	public ShortestPathAlgo(Game game) {

		for (int i = 0; i<game.packmen_list.size(); i++) {
			Path path= new Path(game.packmen_list.get(i));
			this.AllPath.add(path);
			
		}

		MyCoords c = new MyCoords(); 
		Iterator <Fruit> f = game.fruit_list.iterator();
		int j = 0;
		String imgMapFilename = "Ariel1.png";
		ImageIcon imgBck = new ImageIcon(imgMapFilename);
		Map m = new Map(imgBck.getIconWidth(),imgBck.getIconHeight());

		
		while(j<AllPath.size()) {
			Fruit f_new = new Fruit(game.packmen_list.get(j).getPoint3D(), 1, j, "P");
			AllPath.get(j).fruits.add(f_new);
			j++;
		}

		int y = 0;
		double dis = 0;
		double t2e = Double.MAX_VALUE;

		while(!game.fruit_list.isEmpty()) {
			t2e = Double.MAX_VALUE;
			dis = 0;
			for (int i=0; i<AllPath.size(); i++) {
				t2e = Double.MAX_VALUE;
				y = 0;
				for (int k = 0; k<game.fruit_list.size(); k++) {
					Point3D pack = m.Pixel2Gps(imgBck, AllPath.get(i).packmen.pack_place.x(), AllPath.get(i).packmen.pack_place.y());
					Point3D fru = m.Pixel2Gps(imgBck, game.fruit_list.get(k).getPoint3D().x(), game.fruit_list.get(k).getPoint3D().y());
					dis = Math.sqrt(((pack.y()-fru.y())*(pack.y()-fru.y()))+((pack.x()-fru.x())*(pack.x()-fru.x())));
					dis = dis-(AllPath.get(i).packmen.getRadius()/AllPath.get(i).packmen.getSpeed());

					if (dis<=t2e) {
						t2e=dis;
						y = k;
					}
				}
				timeToeat eat = new timeToeat(AllPath.get(i).packmen,game.fruit_list.get(y),t2e);
				list.add(eat);

			}

			double min = Double.MAX_VALUE;
			int n = -1;

			for(int t = 0; t<list.size(); t++) {
				if(list.get(t).getTime()<=min) {
					min = list.get(t).getTime();
					n = t;
				}	
			}
			
			for(int u = 0; u<game.fruit_list.size(); u++) {
				if(game.fruit_list.get(u).getId() == list.get(n).getFruit().getId()) {
					AllPath.get(n).fruits.add(game.fruit_list.get(u));
					Double time =  min;
					AllPath.get(n).time.add(time);
					AllPath.get(n).packmen.setPac_place(game.fruit_list.get(u).getPoint3D());
					game.fruit_list.remove(game.fruit_list.get(u));
				}
			}
			for(int l = list.size()-1; l >= 0; l--) {
				list.remove(l);	
			}

		}
		
		for (int i =0 ; i<AllPath.size();i++) {
//			myThread t = new myThread(AllPath.get(i).fruits);
		}

	}

	/**
	 * function that calcaluate the minimum time and return array that contain the minimum time
	 * and the number of the packmen that eat the fruit in the minimum time
	 * @param arr arr that contain the times that took to every packmen to eat some fruit
	 * @return array that contain the minimum time and the number of the packmen that eat the fruit in this time
	 */


	/**
	 * String which represent the path of every packmen
	 */
	public String toString() {

		String s = "";
		Iterator it_path = AllPath.iterator();
		while(it_path.hasNext()) {
			Path p = (Path) it_path.next();
			for (int i = 0; i<p.fruits.size(); i++) {
				s = s + p.fruits.get(i);
			}
		}
		return s ;
	}
	
	public class myThread extends Thread{

	MyFrame mf = new MyFrame();
		ArrayList<Fruit> p = new ArrayList() ;
		Packmen packmen;

		public myThread(ArrayList path_packmen,MyFrame mf, Packmen pack) {
			 packmen = pack;
			this.p = path_packmen;
			this.mf=mf;
		}
		
		@Override
		public void run() {
			for(int i = 0; i<p.size(); i++) {
				packmen.setPac_place(p.get(i).getPoint3D());
				//mf.PaintAgain();
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
