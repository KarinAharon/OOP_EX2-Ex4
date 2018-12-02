package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class GisElement implements GIS_element {
	/**
	 * The variables of the class
	 */
	private MetaData data;
	Geom_element geom;

	/**
	 * The  initialize constructor
	 * @param data ant data
	 * @param geom any geom 
	 */
	public GisElement(MetaData data, Geom_element geom) {
		this.data = data;
		this.geom=geom;
	}
	/**
	 * This function return a geom
	 */
	@Override
	public Geom_element getGeom() {
		return this.geom;
	}

	/**
	 * This function return a data
	 */
	@Override
	public Meta_data getData() {

		return this.data;
	}

	/**
	 * This function set a point
	 * @param geom any geom
	 */
	public void setPoint(Geom_element geom) {
		this.geom=geom;
	}

	/**
	 * This function add to any point a vector
	 */
	@Override
	public void translate(Point3D vec) {
		Point3D p = new Point3D(this.getGeom().toString());
		MyCoords mc = new MyCoords();
		Geom_element new_geom =  mc.add(p, vec);
		this.setPoint(new_geom);


	}

}
