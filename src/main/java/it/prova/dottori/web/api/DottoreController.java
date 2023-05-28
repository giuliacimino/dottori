package it.prova.dottori.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import it.prova.dottore.web.api.exception.DottoreNonDisponibileException;
import it.prova.dottore.web.api.exception.DottoreNotFoundException;
import it.prova.dottore.web.api.exception.IdNotNullForInsertException;
import it.prova.dottori.dto.DottoreDTO;
import it.prova.dottori.dto.DottorePazienteDTO;
import it.prova.dottori.model.Dottore;
import it.prova.dottori.service.DottoreService;

@RestController
@RequestMapping("api/dottore")
public class DottoreController {
	
	@Autowired
	 DottoreService dottoreService;
	
	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAllDottori());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void inserisciDottore(@RequestBody DottoreDTO dottoreInstance) {

		if (dottoreInstance.getId() != null)
			throw new IdNotNullForInsertException("impossibile creare un id per la creazione.");

		dottoreService.inserisciNuovo(dottoreInstance.buildDottoreModel());

	}
	
	
	@PutMapping
	public void update(@RequestBody DottoreDTO dottoreInstance) {
		

		dottoreService.aggiorna(dottoreInstance.buildDottoreModel());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Dottore dottoreDaEliminare = dottoreService.caricaSingoloDottore(id);

		if (dottoreDaEliminare == null)
			throw new DottoreNotFoundException("non è stato trovato alcun dottore.");

		dottoreService.rimuovi(dottoreDaEliminare);
	}
	
	@GetMapping("/assegnaPaziente/{codiceDottore}")
	public DottoreDTO assegnaPaziente(@PathVariable(required = true) String codiceDottore) {
		Dottore result = dottoreService.verificaDisponibilita(codiceDottore);

		if (result == null)
			throw new DottoreNotFoundException("dottore non trovato");

		if (!result.isInServizio() || result.isInVisita())
			throw new DottoreNonDisponibileException("errore: dottore non disponibile.");

		return DottoreDTO.buildDottoreDTOFromModel(result);
	}
	
	
	@PostMapping("/impostaVisita")
	public DottorePazienteDTO impostaVisita(@RequestBody DottorePazienteDTO dottorePazienteDTO) {

		Dottore dottore = Dottore.builder().codiceDottore(dottorePazienteDTO.getCodiceDottore()).codFiscalePazienteAttualmenteInVisita(dottorePazienteDTO.getCodFiscalePazienteAttualmenteInVisita()).build();

		return DottorePazienteDTO.buildDottoreDTOFromModel(dottoreService.impostaDottore(dottore));
	}
	
	
	@PostMapping("/ricovera")
	public DottorePazienteDTO ricovera(@RequestBody DottorePazienteDTO dottorePazienteDTO) {
		Dottore dottore = Dottore.builder().codiceDottore(dottorePazienteDTO.getCodiceDottore()).codFiscalePazienteAttualmenteInVisita(dottorePazienteDTO.getCodFiscalePazienteAttualmenteInVisita()).build();

		return DottorePazienteDTO.buildDottoreDTOFromModel(dottoreService.ricovera(dottore));
	}
	
	
	
	
	
	

}
