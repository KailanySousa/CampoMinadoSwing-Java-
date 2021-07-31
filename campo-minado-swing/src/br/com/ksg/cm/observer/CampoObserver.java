package br.com.ksg.cm.observer;

import br.com.ksg.cm.event.CampoEvent;
import br.com.ksg.cm.model.Campo;

@FunctionalInterface
public interface CampoObserver {

	public void eventEmit(Campo campo, CampoEvent event);
}
