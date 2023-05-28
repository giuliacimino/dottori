package it.prova.dottori.service;

import java.util.List;

import it.prova.dottori.model.Dottore;


public interface DottoreService {
	public List<Dottore> listAllDottori();

	public Dottore caricaSingoloDottore(Long id);

	public Dottore aggiorna(Dottore dottoreInstance);

	public Dottore inserisciNuovo(Dottore dottoreInstance);

	public void rimuovi(Dottore dottoreInstance);
	
	Dottore verificaDisponibilita(String codiceDottoreInstance);
	
	Dottore impostaDottore(Dottore dottoreInstance);
	
	public Dottore ricovera(Dottore dottoreInstance);



}
