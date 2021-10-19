package guess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Класс проверки вводимых игроков значений на наличие в слове и подсчет очков
 */

public class Check {
  /** Поле загаданного слова */
  private String word = "";
  /** Поле отображаемого слова */
  private String displayWord = "";

  private String guesses;
  /** Текущий символ */
  private char currentGuess;
  /** Поле для проверки наличия символа в слове */
  private boolean addWrongGuess;
  /** Поле заработанных ошибками очков */
  private int wrongGuesses = 0;
  /** Массив симоволов загаданного слова */
  private char[] wordChars;
  /** Массив симоволов, предложенных игроком */
  private char[] arrayGuesses;
  /** Поле массива отображаемых символов */
  private char[] displayWordArray;

  /**
   * Метод перевода букв слова в нижний регистр
   * @param randomWord слово
   */
  public Check(String randomWord) {
    this.word = randomWord.toLowerCase();
    convertWord();
  }
  /**
   * Метод для вызова проверки наличия символа в слове и его отображения
   * @param guessChar выбранный игроком символ
   * @return вызов функции отображения слова
   */
  public String checkWord(char guessChar) {
    guessChars(guessChar);
    return displayWord();
  }

  /**
   * Метод отображения загаданного слова
   * @return отображаемое слово
   */
  public char[] convertWord() {
    wordChars = word.toCharArray();
    displayWordArray = new char[wordChars.length];
    return wordChars;
  }

  /**
   * Метод проверки наличия символа в слове
   * @param guessChar предложенный игроком символ
   * @return массив предложнных игроком символов
   */
  public char[] guessChars(char guessChar) {
    if (guesses == null) {
      currentGuess = guessChar;
      guesses = Character.toString(currentGuess);
      arrayGuesses = guesses.toCharArray();
    } else if ((guesses.indexOf(guessChar) == -1)) {
      currentGuess = guessChar;
      guesses += currentGuess;
      arrayGuesses = guesses.toCharArray();
    }
    return arrayGuesses;
  }

  /**
   * Метод отображения загаданного слова
   * @return отображаемое слово
   */
  public String displayWord() {
    addWrongGuess = true;
    displayWord = "";
    for (int i = 0; i < wordChars.length; i++) {
      for (int j = 0; j < arrayGuesses.length; j++) {
        if (wordChars[i] == arrayGuesses[j]) {
          if (arrayGuesses[j] == currentGuess) {
            addWrongGuess = false;
          }
          displayWordArray[i] = wordChars[i];
        } else {
          if (displayWordArray[i] == 0) {
            displayWordArray[i] = '*';
          }
        }
      }
    }

    if (addWrongGuess) {
      wrongGuesses += 1;
    }
    displayWord = new String(displayWordArray);

    return displayWord;
  }

  /**
   * Метод проверки, разгадано ли слово
   * @return значение true если отображаемое слово разгадано, false если нет
   */
  public boolean verifyWordToGuesses() {
    return (displayWord.equals(word));
  }

  /**
   * Метод отображения строки загаданного слова в зашифрованном звездочками виде
   * @return зашифрованное звездочками слово
   */
  public String initialDisplayWord() {
    String str = "*";
    return str.repeat(word.length());
  }

  public int getWrongGuesses() {
    return wrongGuesses;
  }

  /**
   * Метод для сортировки предложенных игроком символов
   * @return отсортированный массив символов
   */
  public String getGuesses() {
    Arrays.sort(arrayGuesses);
    return new String(arrayGuesses);
  }
}
