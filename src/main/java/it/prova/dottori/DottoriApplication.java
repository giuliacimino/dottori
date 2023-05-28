package it.prova.dottori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.dottori.model.Dottore;
import it.prova.dottori.repository.dottore.DottoreRepository;

@SpringBootApplication
public class DottoriApplication implements CommandLineRunner{
	
	@Autowired
	DottoreRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DottoriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		repository.save(Dottore.builder().nome("Mario").cognome("Rossi").codiceDottore("RSSMR").codFiscalePazienteAttualmenteInVisita("BNCSF").inServizio(true).inVisita(true).build());
		repository.save(Dottore.builder().nome("Alessia").cognome("Alessi").codiceDottore("LSSALSS").codFiscalePazienteAttualmenteInVisita("CMNGL").inServizio(true).inVisita(false).build());
		repository.save(Dottore.builder().nome("Dante").cognome("Alighieri").codiceDottore("LGHDNT").codFiscalePazienteAttualmenteInVisita("DHHSH").inServizio(false).inVisita(false).build());
		
	}

}
