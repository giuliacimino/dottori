package it.prova.dottori.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.dottori.model.Dottore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DottoreDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;
	private boolean inVisita;
	private boolean inServizio;

	
	public Dottore buildDottoreModel() {
		return Dottore.builder().nome(this.nome).cognome(this.cognome).codiceDottore(this.codiceDottore).codFiscalePazienteAttualmenteInVisita(this.codFiscalePazienteAttualmenteInVisita).inVisita(this.inVisita).inServizio(this.inServizio).build();
	}
	
	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		DottoreDTO result = DottoreDTO.builder().nome(dottoreModel.getNome()).cognome(dottoreModel.getCognome()).codiceDottore(dottoreModel.getCodiceDottore()).codFiscalePazienteAttualmenteInVisita(dottoreModel.getCodFiscalePazienteAttualmenteInVisita()).inVisita(dottoreModel.isInVisita()).inServizio(dottoreModel.isInServizio()).build();
		return result;
	}
	
	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreEntity);
			
			return result;
		}).collect(Collectors.toList());
	}
	
	


 

}
