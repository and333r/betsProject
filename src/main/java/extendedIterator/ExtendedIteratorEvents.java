package extendedIterator;

import java.util.Vector;

import domain.Evento;

public class ExtendedIteratorEvents implements ExtendedIterator<Evento>{

	private Vector<Evento> eventos;
	private int indice=0;
	
	public ExtendedIteratorEvents(Vector<Evento> e) {
		this.eventos=e;
	
		}

	@Override
	public int size() {
		return eventos.size();
	}
	
	@Override
	public boolean hasNext() {
		return indice<eventos.size();
	}

	@Override
	public Evento next() {
			Evento ev=eventos.get(indice);
			indice++;
			return ev;
	}

	@Override
	public Evento previous() {
		Evento ev=eventos.get(indice);
		indice--;
		return ev;
	}

	@Override
	public boolean hasPrevious() {
		return indice>=0;
	}

	@Override
	public void goFirst() {
		indice=0;
		
	}

	@Override
	public void goLast() {
		indice=eventos.size()-1;
		
		
	}

}
