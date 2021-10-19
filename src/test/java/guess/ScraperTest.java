package guess;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperTest {

    @Test public void testgetWordReturnsString() {
      Document document = Jsoup.parse("<html><head></head><body><div id='random_word'>test</div></body><html>");
      Scraper scraperMock = spy(Scraper.class);
      when(scraperMock.getDocumentHelper()).thenReturn(document);
      scraperMock.execute();
      assertEquals("test", scraperMock.getRandomWord());
    }
}
