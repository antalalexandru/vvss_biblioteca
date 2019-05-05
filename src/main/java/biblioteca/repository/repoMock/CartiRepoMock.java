package biblioteca.repository.repoMock;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CartiRepoMock implements CartiRepoInterface {

    private List<Carte> carti;

    public CartiRepoMock() {
        carti = new ArrayList<Carte>();

        /*carti.add(Carte.getCarteFromString("Povesti;Mihai Eminescu,Ion Caragiale,Ion Creanga;1973;Corint;povesti,povestiri"));
        carti.add(Carte.getCarteFromString("Poezii;Sadoveanu;1973;Corint;poezii"));
        carti.add(Carte.getCarteFromString("Enigma Otiliei;George Calinescu;1948;Litera;enigma,otilia"));
        carti.add(Carte.getCarteFromString("Dale carnavalului;Caragiale Ion;1948;Litera;caragiale,carnaval"));
        carti.add(Carte.getCarteFromString("Intampinarea crailor;Mateiu Caragiale;1948;Litera;mateiu,crailor"));
        carti.add(Carte.getCarteFromString("Test;Calinescu,Tetica;1992;Pipa;am,casa"));*/
    }

    @Override
    public void adaugaCarte(Carte c) {
        carti.add(c);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Carte> cautaCarte(String ref) {
        List<Carte> carti = getCarti();                         // (1)
        List<Carte> cartiGasite = new ArrayList<Carte>();       // (1)
        int i = 0;                                              // (1)
        while (i < carti.size()) {                              // <2>
            boolean flag = false;                               // (3)
            List<String> lref = carti.get(i).getReferenti();    // (3)
            int j = 0;                                          // (3)
            while (j < lref.size()) {                           // <4>
                if (lref.get(j).toLowerCase().contains(ref.toLowerCase())) {    // <5>
                    flag = true;                                // (6)
                    break;                                      // (6)
                }
                j++;                                            // (7)
            }
            if (flag) {                                         // <8>
                cartiGasite.add(carti.get(i));                  // (9)
            }
            i++;                                                // (10)
        }
        return cartiGasite;                                     // (11)
    }

    @Override
    public List<Carte> getCarti() {
        return carti;
    }

    @Override
    public List<Carte> getCartiOrdonateDinAnul(String an) {
        return getCarti()
                .stream()
                .filter(carte -> carte.getAnAparitie().equals(an))
                .sorted(Comparator.comparing(Carte::getTitlu).thenComparing(carte -> carte.getReferenti().get(0)))
                .collect(Collectors.toList());
    }

}
