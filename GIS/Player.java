package GIS;

import Geom.Point3D;

/**
 * This class represent a player
 */
public class Player {

	/**
	 * the variables of the class
	 */
	
	Point3D player_location;
	int ID;
	double speed;
	int radious;
	String type ="";

/**
 * constructor
 */
	public Player() {

		this.player_location = new Point3D(0,0);
	}
	
	/**
	 * copy constructor
	 * @param p any player
	 */
	public Player(Player p) {

		this.player_location = p.player_location;
		this.ID = p.ID;
		this.speed = p.speed;
		this.radious = p.radious;
		this.type = p.type;
	}

	/**
	 * Initialize constructor
	 * @param ID any id
	 * @param point any point
	 * @param speed any speed
	 * @param radiuos any radiuos
	 * @param type type M
	 */
	public Player(int ID,Point3D point, double speed,int radiuos, String type) {
		this.ID = ID;
		this.speed = speed;
		this.radious = radiuos;
		this.player_location = new Point3D(point.x(),point.y());
		this.type = type;
	}
	
	/**
	 * getters and setters
	 */
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
/**
 * Constructor which received point and type
 * @param p any point
 * @param type type M
 */
	public Player(Point3D p, String type) {

		this.player_location = p;
		this.type ="M";
	}
}