package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class GisElement implements GIS_element {
	private MetaData data;
	Geom_element geom;

	public GisElement(MetaData data, Geom_element geom) {
		this.data = data;
		this.geom=geom;
	}

	@Override
	public Geom_element getGeom() {
		return this.geom;
	}

	@Override
	public Meta_data getData() {

		return this.data;
	}

	public void setPoint(Geom_element geom) {
		this.geom=geom;
	}

	@Override
	public void translate(Point3D vec) {
		Point3D p = new Point3D(this.getGeom().toString());
		MyCoords mc = new MyCoords();
		Geom_element new_geom =  mc.add(p, vec);
		this.setPoint(new_geom);


	}

}
