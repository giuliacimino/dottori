package it.prova.dottore.web.api.exception;

public class DottoreInServizioException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DottoreInServizioException(String message) {
		super(message);
	}

}
