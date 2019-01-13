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

/**
 * In this class we create an algorithm which use 2 classes: Line and Edge in order to create a graph that contain
 * player,packmen, fruit and path between them. The path composed of lines that are not intersect, edges.
 * After we create graph for each fruit, we used the Dijkstra, an algorithm which solves the problem 
 * of finding the shortest path in order to find the minimum distance from our player to its closet fruit
 */
public class Algo {

	/**
	 * The variables of the class
	 */
	ArrayList<Point3D> list_points = new ArrayList<Point3D>();
	ArrayList<Line> list_line = new ArrayList<Line>();
	ArrayList<Edge> list_edge = new ArrayList<Edge>();

	/**
	 * constructor
	 */
	public Algo() {

		this.list_points = new ArrayList<Point3D>();
		this.list_line = new ArrayList<Line>();
		this.list_edge = new ArrayList<Edge>();
	}

	/**
	 * getters
	 */
	public ArrayList<Point3D> getList_points() {
		return list_points;
	}


	public ArrayList<Line> getList_line() {
		return list_line;
	}


	public ArrayList<Edge> getList_edge() {
		return list_edge;
	}


	/**
	 * function that received a game and find the minimum path for the player
	 * @param game any game
	 */
	public Algo(Game game) {


		// create lines between the boxes and the player and add them to the array list of lines

		for(int i = 0; i<game.box_list.size(); i++) {

			Line line1 = new Line(game.player.getPlayer_location(), game.box_list.get(i).getDown());
			Line line2 = new Line(game.player.getPlayer_location(), game.box_list.get(i).getUp());
			Point3D p1 = new Point3D( game.box_list.get(i).getDown().x(), game.box_list.get(i).getDown().y());
			Point3D p2 = new Point3D( game.box_list.get(i).getUp().x(), game.box_list.get(i).getUp().y());
			Line line3 = new Line(game.player.getPlayer_location(), p1);
			Line line4 = new Line(game.player.getPlayer_location(), p2);
			list_line.add(line1);
			list_line.add(line2);
			list_line.add(line3);
			list_line.add(line4);
		}

		// create lines between the boxes and add them to the array list of lines
		for(int i = 0; i<game.box_list.size(); i++) {

			Point3D p1 = new Point3D(game.box_list.get(i).getDown().x(), game.box_list.get(i).getDown().y());
			Point3D p2 = new Point3D(game.box_list.get(i).getUp().x(), game.box_list.get(i).getUp().y());
			Point3D p3 = new Point3D(game.box_list.get(i).getUp().x(), game.box_list.get(i).getDown().y());
			Point3D p4 = new Point3D(game.box_list.get(i).getDown().x(), game.box_list.get(i).getUp().y());

			Line line1 = new Line(p2, p3);
			Line line2 = new Line(p3, p1);
			Line line3 = new Line(p1, p4);
			Line line4 = new Line(p4, p2);
			list_line.add(line1);
			list_line.add(line2);
			list_line.add(line3);
			list_line.add(line4);
		}

		// check if the lines are intersect. if they not create a edge and add it to the array list of edges.
		Line l = new Line();
		boolean _flag = true;
		for(int i = 0 ; i<list_line.size(); i++) {
			for(int j = 0 ; j<list_line.size(); j++) {
				if(l.Is_a_Line(list_line.get(i), list_line.get(j))==true){
					_flag = false;
				}
			}
			if(_flag == true) {
				Edge e = new Edge(list_line.get(i).getPoint1(), list_line.get(i).getPoint2());
				list_edge.add(e);
			}
		}
	
		// if the line between the player and the fruit is not intersect with any line, change the location of the player
		Algo algo = new Algo();
		Fruit f = algo.f(game);
		boolean flag = algo.flag(f, game.player, list_line);
		if(flag == true) {
			game.player.setPlayer_location(f.getPoint3D());
		}
		
		//else, find path from the player to its closet fruit
		else {

			for(int i = 0; i<list_line.size(); i++) {
				Line l1 = new Line(game.player.getPlayer_location(), list_line.get(i).getPoint1());
				Line l2 = new Line(game.player.getPlayer_location(), list_line.get(i).getPoint2());
				Line l3 = new Line(f.getPoint3D(), list_line.get(i).getPoint1());
				Line l4 = new Line(f.getPoint3D(), list_line.get(i).getPoint2());
				list_line.add(l1);
				list_line.add(l2);
				list_line.add(l3);
				list_line.add(l4);
			}

			Graph G = new Graph(); 
			String source = "player";
			String target = "target";
			G.add(new Node(source)); 
			Iterator<Box>it_list = game.box_list.iterator();
			int i = 0;

			while (it_list.hasNext()) {
				Node d1 = new Node(""+i);
				Node d2 = new Node(""+i++);
				Node d3 = new Node(""+i++);
				Node d4 = new Node(""+i++);
				G.add(d1);
				G.add(d2);
				G.add(d3);
				G.add(d4);
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

			G.add(new Node(target)); 
			Point3D player_point = new Point3D(game.player.getPlayer_location().x(),game.player.getPlayer_location().y());
			list_points.add(player_point);

		}}

	/**
	 * this function checks which fruit closet to the player
	 * @param game any game
	 * @return fruit
	 */
	public Fruit f(Game game) {
		MyCoords mc = new MyCoords();
		double min = Double.MAX_VALUE;
		Fruit f = new Fruit(0,0);
		for(int i = 0; i<game.fruit_list.size(); i++) {
			if(mc.distance3d(game.player.getPlayer_location(), game.fruit_list.get(i).getPoint3D())<min) {
				min = mc.distance3d(game.player.getPlayer_location(), game.fruit_list.get(i).getPoint3D());
				f = new Fruit(game.fruit_list.get(i));
			}
		}
		return f;
	}

	/**
	 * function which checks if the line between the player to its closet fruit intersect with any line of the boxes
	 * @param f fruit
	 * @param p player
	 * @param line_Box the lines of the boxes
	 * @return true or false
	 */
	public boolean flag(Fruit f, Player p, ArrayList<Line> line_Box) {

		boolean b = true;
		Line l = new Line(p.getPlayer_location(),f.getPoint3D());
		for(int i = 0; i<line_Box.size(); i++) {
			if(l.Is_a_Line(l, line_Box.get(i))==true) {
				b = false;
			}
			else 
				b = true;
		}
		return b;	
	}
}

