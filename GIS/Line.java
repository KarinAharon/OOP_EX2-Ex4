package GIS;

import java.awt.geom.Line2D;

import Geom.Point3D;

public class Line {
	
	Point3D point1;
	Point3D point2;

	
	public Line() {
		
		this.point1 = new Point3D(0,0);
		this.point2 = new Point3D(0,0);
	}
	
	public Line(Point3D p1, Point3D p2) {
		
		this.point1 = p1;
		this.point2 = p2;
		
	}

	public Point3D getPoint1() {
		return point1;
	}

	public void setPoint1(Point3D point1) {
		this.point1 = point1;
	}

	public Point3D getPoint2() {
		return point2;
	}

	public void setPoint2(Point3D point2) {
		this.point2 = point2;
	}

	public boolean Is_a_Line(Line l1, Line l2) {
		
		if(Line2D.linesIntersect(l1.getPoint1().x(), l1.getPoint2().x(), l1.getPoint1().y(), l1.getPoint2().y(),
				              l2.getPoint1().x(), l2.getPoint2().x(), l2.getPoint1().y(), l2.getPoint2().y())) {
		return true;
		
		}
		else
			return false;
	}
	
	}
	

