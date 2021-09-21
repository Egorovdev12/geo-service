package ru.netology.i18n;

import org.junit.Test;
import ru.netology.entity.Country;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalizationServiceImplTest {

    @Test
    public void text_if_russia_is_country() {
        LocalizationService ls = new LocalizationServiceImpl();
        String result = ls.locale(Country.RUSSIA);
        String expected = "Добро пожаловать";
        assertThat(result.equals(expected)).isTrue();
    }

    @Test
    public void text_if_usa_is_country() {
        LocalizationService ls = new LocalizationServiceImpl();
        String result = ls.locale(Country.USA);
        String expected = "Welcome";
        assertThat(result.equals(expected)).isTrue();
    }
}