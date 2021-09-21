package ru.netology.geo;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


public class GeoServiceImplTest {

    @Test
    public void get_russia_byIp() {
        GeoService gs = new GeoServiceImpl();
        Location expected = new Location(null, Country.RUSSIA, null, 0);
        Location result = gs.byIp("172.0.32.11");
        assertThat(expected.getCountry() == result.getCountry()).isTrue();
    }

    @Test
    public void get_USA_byIp() {
        GeoService gs = new GeoServiceImpl();
        Location expected = new Location(null, Country.USA, null, 0);
        Location result = gs.byIp("96.44.183.149");
        assertThat(expected.getCountry() == result.getCountry()).isTrue();
    }
}