package integration;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BigBangIntegrationTest {

    private CartiRepoMock cartiRepoMock;
    private BibliotecaCtrl bibliotecaCtrl;

    @Before
    public void setup() {
        cartiRepoMock = new CartiRepoMock();
        bibliotecaCtrl = new BibliotecaCtrl(cartiRepoMock);
    }

    // F01 unit test
    @Test
    public void addBookTest() {
        this.cartiRepoMock.adaugaCarte(new Carte("Shogun", Arrays.asList("James Clavell"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        Assert.assertEquals(cartiRepoMock.getCarti().size(), 1);
        Assert.assertEquals(cartiRepoMock.getCarti().get(0).getTitlu(), "Shogun");
    }

    // F02 unit test
    @Test
    public void findBookSuccessTest() throws Exception {
        cartiRepoMock.adaugaCarte(new Carte("Shogun", Arrays.asList("Rebreanu"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        List<Carte> result = cartiRepoMock.cautaCarte("Rebreanu");
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getTitlu(), "Shogun");
    }

    // F03 unit test
    @Test
    public void testSortingByTitle() throws Exception {
        cartiRepoMock.adaugaCarte(new Carte("Shogun", Arrays.asList("A", "B"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        cartiRepoMock.adaugaCarte(new Carte("Codul lui da vinci", Arrays.asList("A", "B"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));

        List<Carte> result = cartiRepoMock.getCartiOrdonateDinAnul("1975");

        Assert.assertEquals(result.get(0).getTitlu(), "Codul lui da vinci");
        Assert.assertEquals(result.get(1).getTitlu(), "Shogun");
    }

    // Integration test
    @Test
    public void integrationTest() throws Exception {
        this.bibliotecaCtrl.adaugaCarte(new Carte("Shogun", Collections.singletonList("James Clavell"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        this.bibliotecaCtrl.adaugaCarte(new Carte("Codul lui Da Vinci", Collections.singletonList("Dan Brown"), "2004", "Rao", Arrays.asList("paris", "arta")));

        Assert.assertEquals(bibliotecaCtrl.cautaCarte("James Clavell").get(0).getTitlu(), "Shogun");
        Assert.assertEquals(bibliotecaCtrl.cautaCarte("Brown").get(0).getTitlu(), "Codul lui Da Vinci");
        Assert.assertEquals(bibliotecaCtrl.cautaCarte("Dan").get(0).getTitlu(), "Codul lui Da Vinci");

        Assert.assertEquals(bibliotecaCtrl.getCartiOrdonateDinAnul("1975").get(0).getTitlu(), "Shogun");
        Assert.assertEquals(bibliotecaCtrl.getCartiOrdonateDinAnul("2004").get(0).getTitlu(), "Codul lui Da Vinci");
    }
}
