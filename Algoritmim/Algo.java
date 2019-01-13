package Algoritmim;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.Box;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.Line;
import GIS.Packmen;
import GIS.Player;
import graph.Graph;
import graph.Node;
import Geom.*;
import GIS.Edge;

public class Algo {
	
	public Algo(Game game) {
		
		ArrayList<Point3D> list_points = new ArrayList<Point3D>();
		ArrayList<Line> list_line = new ArrayList<Line>();
		ArrayList<Edge> list_edge = new ArrayList<Edge>();
		
		//בדיקה מי הפרי שהכי קרוב לשחקן שלי כרגע
		MyCoords mc = new MyCoords();
		double min = Double.MAX_VALUE;
		Fruit f = new Fruit(0,0);
		for(int i = 0; i<game.fruit_list.size(); i++) {
			if(mc.distance3d(game.player.getPlayer_location(), game.fruit_list.get(i).getPoint3D())<min) {
				min = mc.distance3d(game.player.getPlayer_location(), game.fruit_list.get(i).getPoint3D());
				f = new Fruit(game.fruit_list.get(i));
			}
		}
		
		//הוספת מיקום הפרי לרשימת הנקודות הכללית
		Point3D p = new Point3D(f.getPoint3D().x(),f.getPoint3D().y());
		list_points.add(p);
		
		Graph G = new Graph(); 
		String source = "player";
		String target = "target";
		G.add(new Node(source)); 
		Iterator<Box>it_list = game.box_list.iterator();
		int i = 0;
		
		//הוספת כל הנקודות של המלבנים לרשימת הנקודות הכללית
		while (it_list.hasNext()) {
			Node d = new Node(""+i);
			G.add(d);
			Box b = it_list.next();
			Point3D p1 = new Point3D(b.getUp().x(), b.getDown().y());
			Point3D p2 = new Point3D(b.getDown().x(), b.getUp().y());
			Point3D p3 = new Point3D(b.getDown().x(), b.getDown().y());
			Point3D p4 = new Point3D(b.getUp().x(), b.getUp().y());
			list_points.add(p1);
			list_points.add(p2);
			list_points.add(p3);
			list_points.add(p4);
			i++;
		}
		
		//הוספת הנקודה של השחקן שלי לרשימת הנקודות הכללית
		G.add(new Node(target)); // Node "b" (15)
		Point3D player_point = new Point3D(game.player.getPlayer_location().x(),game.player.getPlayer_location().y());
		list_points.add(player_point);
		
		//יצירת כלל הקווים
		for(int j = 0; j<list_points.size(); j++) {
			for(int k = 0; k<list_points.size(); k++) {
			Line l = new Line(list_points.get(j), list_points.get(k));
			list_line.add(l);
			}}
		
		Line temp;
		Edge temp2 = null;
		
		//יצירת הצלעות שלא נחתכות
		for(int j = 0; j<list_line.size(); j++) {
				list_edge = temp2.list_line(list_line,list_line.get(j));
			}
		for(int j = 0; j<list_edge.size(); j++) {
			G.addEdge(a, b, list_edge.get(j));
		}
		}
	}

