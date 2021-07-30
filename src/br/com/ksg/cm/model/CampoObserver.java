package br.com.ksg.cm.model;

@FunctionalInterface
public interface CampoObserver {

	public void eventEmit(Campo campo, CampoEvent event);
}
