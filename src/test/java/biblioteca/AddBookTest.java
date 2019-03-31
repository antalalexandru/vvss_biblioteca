package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;


public class AddBookTest {

    private BibliotecaCtrl bibliotecaCtrl;

    @Before
    public void beforeEach() {
        this.bibliotecaCtrl = new BibliotecaCtrl(new CartiRepoMock());
    }

    @Test
    public void TC_1() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test(expected = Exception.class)
    public void TC_2() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "1945", "Adevarul", Arrays.asList("japonia", "+123")));
    }

    @Test(expected = Exception.class)
    public void TC_3() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "qwerty", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test(expected = Exception.class)
    public void TC_4() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "-985", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }


    @Test(expected = Exception.class)
    public void TC_5() throws Exception {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), String.valueOf(currentYear + 1), "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test(expected = Exception.class)
    public void TC_6() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("", Arrays.asList("James Clavell"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test
    public void TC_7() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("M", Arrays.asList("James Clavell"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test(expected = Exception.class)
    public void TC_8() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "-1", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test
    public void TC_9() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "0", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }

    @Test
    public void TC_10() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "1", "Adevarul", Arrays.asList("japonia", "ostatici")));
    }


}
