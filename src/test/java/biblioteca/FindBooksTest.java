package biblioteca;

import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindBooksTest {
    private CartiRepoMock cartiRepoMock;

    @Before
    public void setUp() {
        cartiRepoMock = new CartiRepoMock();
    }

    @Test
    public void test_F02_TC01() {
        Assert.assertEquals(cartiRepoMock.cautaCarte("Rebreanu").size(), 0);
    }

    @Test
    public void test_F02_TC02() {
        cartiRepoMock.adaugaCarte(new Carte("Shogun", new ArrayList<String>(), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        Assert.assertEquals(cartiRepoMock.cautaCarte("Rebreanu").size(), 0);
    }

    @Test
    public void test_F02_TC03() {
        cartiRepoMock.adaugaCarte(new Carte("Shogun", Arrays.asList("Sadoveanu"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        Assert.assertEquals(cartiRepoMock.cautaCarte("Rebreanu").size(), 0);
    }

    @Test
    public void test_F02_TC04() {
        cartiRepoMock.adaugaCarte(new Carte("Shogun", Arrays.asList("Rebreanu"), "1975", "Adevarul", Arrays.asList("japonia", "ostatici")));
        List<Carte> result = cartiRepoMock.cautaCarte("Rebreanu");
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getTitlu(), "Shogun");
    }
}
