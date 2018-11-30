package GIS;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GisLayer implements GIS_layer {
	Set<GIS_element> s = new HashSet<>();
	private long creationTime;
	
	 public GisLayer() {
		 
		this.creationTime = System.currentTimeMillis();
	}
	 
	@Override
	public boolean add(GIS_element arg0) {
		
		return s.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		
		return s.addAll(arg0);
	}

	@Override
	public void clear() {
		
		s.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		
		return s.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		
		return s.contains(arg0);
	}

	@Override
	public boolean isEmpty() {
		
		return s.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return s.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		
		return s.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		
		return s.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		
		return s.retainAll(arg0);
	}

	@Override
	public int size() {
		
		return s.size();
	}

	@Override
	public Object[] toArray() {
		
		return s.toArray();
		}

	@Override
	public <T> T[] toArray(T[] arg0) {
		
		return s.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		
		return new MetaData(creationTime);
	}

}
