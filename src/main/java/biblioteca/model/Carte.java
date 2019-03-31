package biblioteca.model;


import java.util.ArrayList;
import java.util.List;

public class Carte {
	
	private String titlu;
	private List<String> referenti;
	private String anAparitie;
	private String editura;
	private List<String> cuvinteCheie;

	public Carte(String titlu, List<String> referenti, String anAparitie, String editura, List<String> cuvinteCheie) {
		this.titlu = titlu;
		this.referenti = referenti;
		this.anAparitie = anAparitie;
		this.cuvinteCheie = cuvinteCheie;
		this.editura = editura;
	}

	public Carte(){
		titlu = "";
		referenti = new ArrayList<String>();
		anAparitie = "";
		cuvinteCheie = new ArrayList<String>();
	}

    public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public List<String> getReferenti() {
		return referenti;
	}

	public String getAnAparitie() {
		return anAparitie;
	}

	public void setAnAparitie(String anAparitie) {
		this.anAparitie = anAparitie;
	}

	public List<String> getCuvinteCheie() {
		return cuvinteCheie;
	}

	public String getEditura() {
		return editura;
	}

	public void setEditura(String editura) {
		this.editura = editura;
	}

	@Override
	public String toString(){
		StringBuilder ref = new StringBuilder();
		String cuvCheie = "";
		
		for(int i=0;i<referenti.size();i++){
			if(i==referenti.size()-1)
				ref.append(referenti.get(i));
			else
				ref.append(referenti.get(i)).append(",");
		}
		
		for(int i=0;i<cuvinteCheie.size();i++){
			if(i==cuvinteCheie.size()-1)
				cuvCheie+=cuvinteCheie.get(i);
			else
				cuvCheie+=cuvinteCheie.get(i)+",";
		}
		
		return titlu+";"+ref+";"+anAparitie+";"+cuvCheie;
	}
	
	public static Carte getCarteFromString(String carte){
		Carte c = new Carte();
		String []atr = carte.split(";");
		String []referenti = atr[1].split(",");
		String []cuvCheie = atr[4].split(",");
		
		c.titlu=atr[0];
		for(String s:referenti){
			c.getReferenti().add(s);
		}
		c.anAparitie = atr[2];
		for(String s:cuvCheie){
			c.getCuvinteCheie().add(s);
		}
		
		return c;
	}
	
}
