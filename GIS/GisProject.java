package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GisProject implements GIS_project {
	Set<GIS_layer> project = new HashSet<>();
	private long creationTime;

	 public GisProject() {
		this.creationTime = System.currentTimeMillis();
	}
	@Override
	public boolean add(GIS_layer arg0) {
		
		return project.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		
		return project.addAll(arg0);
	}

	@Override
	public void clear() {
		
	project.clear();	
	}

	@Override
	public boolean contains(Object arg0) {
		
		return project.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		
		return project.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
	
		return isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		
		return project.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		
		return project.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
	
		return removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		
		return retainAll(arg0);
	}

	@Override
	public int size() {
		
		return project.size();
	}

	@Override
	public Object[] toArray() {
		
		return project.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {

		return project.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		
		return new MetaData(creationTime);
	}

}
