package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetCartiOrdonateDinAnulTest {

    private BibliotecaCtrl bibliotecaCtrl;

    @Before
    public void setup() {
        bibliotecaCtrl = new BibliotecaCtrl(new CartiRepoMock());
    }

    @Test(expected = Exception.class)
    public void testInvalidYear() throws Exception {
        bibliotecaCtrl.getCartiOrdonateDinAnul("N/A");
    }

    @Test
    public void testEmptyResult() throws Exception {
        Assert.assertEquals(bibliotecaCtrl.getCartiOrdonateDinAnul("1997").size(), 0);
        bibliotecaCtrl.adaugaCarte(new Carte("Shogun", new ArrayList<>(), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        Assert.assertEquals(bibliotecaCtrl.getCartiOrdonateDinAnul("1997").size(), 0);
        Assert.assertEquals(bibliotecaCtrl.getCartiOrdonateDinAnul("1975").size(), 1);
    }

    @Test
    public void testSortingByTitle() throws Exception {
        bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("A", "B"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        bibliotecaCtrl.adaugaCarte(new Carte("Codul lui da vinci", Arrays.asList("A", "B"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));

        List<Carte> result = bibliotecaCtrl.getCartiOrdonateDinAnul("1975");

        Assert.assertEquals(result.get(0).getTitlu(), "Codul lui da vinci");
        Assert.assertEquals(result.get(1).getTitlu(), "Shogun");
    }

    @Test
    public void testSortingByAuthors() throws Exception {
        bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("B", "B"), "2019", "Adevarul", Arrays.asList("japonia", "ostatici")));
        bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("C", "D"), "2019", "Adevarul", Arrays.asList("japonia", "ostatici")));
        bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Arrays.asList("A", "D"), "2019", "Adevarul", Arrays.asList("japonia", "ostatici")));

        List<Carte> result = bibliotecaCtrl.getCartiOrdonateDinAnul("2019");

        Assert.assertEquals(result.get(0).getReferenti().get(0), "A");
        Assert.assertEquals(result.get(1).getReferenti().get(0), "B");
        Assert.assertEquals(result.get(2).getReferenti().get(0), "C");

    }
}
