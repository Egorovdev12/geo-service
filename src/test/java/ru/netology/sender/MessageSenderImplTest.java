package ru.netology.sender;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageSenderImplTest {

    @Test
    public void send_string_is_russian() {
        Map<String, String> mapa = new HashMap<String, String>();
        mapa.put("x-real-ip", "172.0.32.11");

        LocalizationService ls = Mockito.mock(LocalizationServiceImpl.class);
        GeoService gs = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gs.byIp("172.0.32.11")).thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(gs.byIp("96.44.183.149")).thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(ls.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(ls.locale(Country.USA)).thenReturn("Welcome");

        MessageSender ms = new MessageSenderImpl(gs, ls);
        String strLanguage = ms.send(mapa);

        // Посимвольно разбираем строку и смотим из символов кириллицы она состоит или нет
        int counter = 0;
        for (int i = 0; i < strLanguage.length(); i++) {
            if ((Character.UnicodeBlock.of(strLanguage.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) || (strLanguage.charAt(i) == ' ') || (strLanguage.charAt(i) == '-')) {
                counter++;
            }
        }

        boolean result;
        if (counter == strLanguage.length()){
            result = true;
        }
        else {
            result = false;
        }

        assertThat(result).isTrue();
    }

    @Test
    public void send_string_is_english() {
        Map<String, String> mapa = new HashMap<String, String>();
        mapa.put("x-real-ip", "96.44.183.149");

        LocalizationService ls = Mockito.mock(LocalizationServiceImpl.class);
        GeoService gs = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gs.byIp("172.0.32.11")).thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(gs.byIp("96.44.183.149")).thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(ls.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(ls.locale(Country.USA)).thenReturn("Welcome");

        MessageSender ms = new MessageSenderImpl(gs, ls);
        String strLanguage = ms.send(mapa);

        // Посимвольно разбираем строку и смотим из символов кириллицы она состоит или нет
        int counter = 0;
        for (int i = 0; i < strLanguage.length(); i++) {
            if ((Character.UnicodeBlock.of(strLanguage.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN)) || (strLanguage.charAt(i) == ' ') || (strLanguage.charAt(i) == '-')) {
                counter++;
            }
        }

        boolean result;
        if (counter == strLanguage.length()){
            result = true;
        }
        else {
            result = false;
        }

        assertThat(result).isTrue();
    }
}