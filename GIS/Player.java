package GIS;

import Geom.Point3D;

public class Player {

	Point3D player_location;
	int ID;
	double speed;
	int radious;
	String type ="";


	public Player() {

		this.player_location = new Point3D(0,0);
	}
	public Player(Player p) {

		this.player_location = p.player_location;
		this.ID = p.ID;
		this.speed = p.speed;
		this.radious = p.radious;
		this.type = p.type;
	}

	public Player(int ID,Point3D point, double speed,int radiuos, String type) {
		this.ID = ID;
		this.speed = speed;
		this.radious = radiuos;
		this.player_location = new Point3D(point.x(),point.y());
		this.type = type;
	}
	public Player(Point3D point) {
		this.player_location = point;
	}
	public Point3D getPlayer_location() {
		return player_location;
	}

	public void setPlayer_location(Point3D player_location) {
		this.player_location = player_location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Player(Point3D p, String type) {

		this.player_location = p;
		this.type ="M";
	}
}