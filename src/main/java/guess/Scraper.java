package guess;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Класс для получения слова и значения слова с сайта
 */

public class Scraper {
  /** Поле ссылки на сайт */
  private String url;
  /** Случайно сгенерированное слово */
  private String randomWord;
  /** Значение случайно сгенерированного слова */
  private String randomWordMeaning;
  /** Файл для записи xml данных сайта */
  private Document doc;
  /**
   * Метод задания url сайта, с которого берутся слова и значения
   */
  public Scraper() {
    this.url = "https://www.randomword.com";
  }
  /**
   * Метод получения url
   */
  public Scraper(String url) {
    this.url = url;
  }

  /**
   * Метод получения слова и значения слова из html файла сайта
   */
  public void execute() {
    doc = getDocumentHelper();
    Elements word = doc.select("#random_word");
    Elements wordMeaning = doc.select("#random_word_definition");
    randomWordMeaning = wordMeaning.text();
    randomWord = word.text();
  }

  /**
   * Метод получения данных документа через url
   * @return документ
   */
  public Document getDocumentHelper() {
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return doc;
  }
  /**
   * Метод получения случайно сгенерированного слова
   * @return случайно сгенерированное слово
   */
  public String getRandomWord() {
    return randomWord;
  }
  /**
   * Метод получения значения случайно сгенерированного слова
   * @return значение случайно сгенерированного слово
   */
  public String getRandomWordMeaning() {
    return randomWordMeaning;
  }

}
