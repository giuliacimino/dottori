package it.prova.dottori.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.dottore.web.api.exception.DottoreInServizioException;
import it.prova.dottore.web.api.exception.DottoreInVisitaException;
import it.prova.dottori.model.Dottore;
import it.prova.dottori.repository.dottore.DottoreRepository;


@Service
public class DottoreServiceImpl implements DottoreService{
	
	@Autowired
	DottoreRepository repository;

	@Override
	public List<Dottore> listAllDottori() {
		return (List<Dottore>) repository.findAll();

	}

	@Override
	public Dottore caricaSingoloDottore(Long id) {
		return repository.findById(id).orElse(null);

	}

	@Override
	public Dottore aggiorna(Dottore dottoreInstance) {
		return repository.save(dottoreInstance);

	}

	@Override
	public Dottore inserisciNuovo(Dottore dottoreInstance) {
		return repository.save(dottoreInstance);

	}

	@Override
	public void rimuovi(Dottore dottoreInstance) {
		if (dottoreInstance.isInServizio()== true ) {
			throw new DottoreInServizioException("non è possibile rimuovere un dottore in servizio.");
		}
		if (dottoreInstance.isInVisita()== true) {
			throw new DottoreInVisitaException("non è possibile rimuovere un dottore in visita");
		}
		
	}

	@Override
	public Dottore verificaDisponibilita(String codiceDottoreInstance) {
		return repository.findByCodiceDottore(codiceDottoreInstance);

	}

	@Override
	public Dottore impostaDottore(Dottore dottoreInstance) {
		Dottore result = repository.findByCodiceDottore(dottoreInstance.getCodiceDottore());
		result.setCodFiscalePazienteAttualmenteInVisita(dottoreInstance.getCodFiscalePazienteAttualmenteInVisita());
		return repository.save(result);
	}

	@Override
	public Dottore ricovera(Dottore dottoreInstance) {
		Dottore result = repository.findByCodiceDottore(dottoreInstance.getCodiceDottore());
		result.setCodFiscalePazienteAttualmenteInVisita(null);
		result.setInVisita(false);
		return repository.save(result);
	}
	
	

}
