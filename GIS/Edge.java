package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class Edge {

	Line line;
	//ArrayList<Box> list_point = new ArrayList<>();
	
	public Edge(Point3D p1, Point3D p2) {
		
		this.line = new Line(p1, p2);
	}
	
	//יצירת צלעות של הבוקסים
	
	public ArrayList<Line> list_line(ArrayList<Box> list) {
		ArrayList<Line> new_list = new ArrayList<>();
		Iterator<Box>it_list = list.iterator();
		while (it_list.hasNext()) {
			Box b = it_list.next();
			Point3D p1 = new Point3D(b.getUp().x(), b.getDown().y());
			Point3D p2 = new Point3D(b.getDown().x(), b.getUp().y());
			Line line1 = new Line(b.getUp(), p1);
			Line line2 = new Line(b.getUp(), p2);
			Line line3 = new Line(b.getDown(), p1);
			Line line4 = new Line(b.getDown(), p2);
			new_list.add(line1);
			new_list.add(line2);
			new_list.add(line3);
			new_list.add(line4);
		}
		return new_list;
	}
	
	// מקבלת רשימה של קווים וקו מוסים ובודקת האם הוא נתתך איתם
	
	public ArrayList<Edge> list_line(ArrayList<Line> list_line,Line l2) {
		ArrayList<Edge> new_list = new ArrayList<>();
		Iterator<Line>it_list = list_line.iterator();
		while(it_list.hasNext()) {
			Line l1 = it_list.next();
			if(line.Is_a_Line(l1, l2)==true) {
				Edge e = new Edge(l1.getPoint1(), l1.getPoint2());
				new_list.add(e);
			}
		}
		return new_list;
	}
}
