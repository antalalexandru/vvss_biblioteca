package biblioteca.util;

import biblioteca.model.Carte;

import java.util.Calendar;

public class Validator {
	
	public static boolean isStringOK(String s) {
		return s.matches("[a-zA-Z ]+");
	}

	public static boolean validKeyword(String keyword) {
		return keyword.matches("[a-z]+");
	}
	
	public static void validateCarte(Carte c)throws Exception{
		if(c.getCuvinteCheie()==null){
			throw new Exception("Lista cuvinte cheie vida!");
		}
		if(c.getReferenti()==null){
			throw new Exception("Lista autori vida!");
		}
		if(!isStringOK(c.getTitlu()))
			throw new Exception("Titlu invalid!");

		for(String s:c.getReferenti()){
			if(!isStringOK(s))
				throw new Exception("Autor invalid!");
		}
		for(String s:c.getCuvinteCheie()){
			if(!validKeyword(s))
				throw new Exception("Cuvant cheie invalid!");
		}
		if(!Validator.isNumber(c.getAnAparitie()) || !validYear(Integer.parseInt(c.getAnAparitie())))
			throw new Exception("An aparitie invalid!");
	}
	
	public static boolean isNumber(String s){
		return s.matches("[0-9]+");
	}

	public static boolean validYear(int year) {
		return 0 <= year && year <= Calendar.getInstance().get(Calendar.YEAR);
	}

	
}
