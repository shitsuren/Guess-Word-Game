package guess;

import java.io.IOException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Класс запуска игры
 */

public class GuessGame {
  /** Объект класса Scaper */
  private Scraper scraper;
  /** Объект класса Check */
  private Check check;
  /** Предложенный игроком символ */
  private char guessChar;
  /** Случайно сгенерированное слово */
  private String randomWord;
  /** Значение случайно сгенерированного слова */
  private String randomWordMeaning;
  /** Объект класса JFrame для создания окна */
  private JFrame frame;
  /** Обработчик событий клавиатуры */
  private KeyListener keyListener;

  public static void main(String[] args) throws IOException {
    GuessGame guessGame = new GuessGame();
    guessGame.newGame();
  }
  /**
   * Метод проверки исключения в классе
   */
  public GuessGame() throws IOException {
  }

  public GuessGame(Scraper scraper) throws IOException {
    this.scraper = scraper;
  }

  /**
   * Метод начала новой игры
   */
  public void newGame() throws IOException {
    this.scraper = new Scraper();
    getConvertWord();
    check = new Check(randomWord);
    frame = new JFrame("Word Game");
    displayGame();
  }
  /**
   * Метод создания игрового окна
   */
  private void displayGame() throws IOException {
    frame.setSize(400, 400);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    Container container = frame.getContentPane();
    container.setLayout(new BorderLayout());

    JLabel displayWord = new JLabel();
    JLabel displayLower = new JLabel();
    JLabel displayCenter = new JLabel();

    displayWord.setFont(new Font("Courier", Font.BOLD,40));
    displayWord.setHorizontalAlignment(JLabel.CENTER);
    displayWord.setText(check.initialDisplayWord());

    displayLower.setFont(new Font("Courier", Font.BOLD,20));
    displayLower.setHorizontalAlignment(JLabel.CENTER);
    displayLower.setText("Wrong Guesses: " + check.getWrongGuesses());

    displayCenter.setFont(new Font("Courier", Font.BOLD,40));
    displayCenter.setHorizontalAlignment(JLabel.CENTER);
    displayCenter.setText("Enter Letter");

    JButton button = new JButton("Play Again");
    button.setFont(new Font("Courier", Font.BOLD,20));

    keyListener = new KeyListener() {
      @Override
      /**
       * Метод поиска символа, соответсвующего нажатой кнопке
       * @param e нажатая кнопка
       */
      public void keyTyped(KeyEvent e) {
      }

      @Override
      /**
       * Метод обработки нажатия клавиши
       * @param e нажатая кнопка
       */
      public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        c = Character.toLowerCase(c);
        displayWord.setText(check.checkWord(c));
        if (check.verifyWordToGuesses()) {
          displayCenter.setFont(new Font("Courier", Font.BOLD,20));
          displayCenter.setText("<html><div style='text-align: center;'>"
            + randomWordMeaning + "<br/><br/>" + "Total Wrong Guesses: "
            + check.getWrongGuesses() + "</html>");
          displayLower.setText("");
          removeListener();
          container.add(button, BorderLayout.PAGE_END);
        } else {
          displayCenter.setFont(new Font("Courier", Font.BOLD,140));
          displayCenter.setText(Character.toString(c));
          displayLower.setText("<html><div style='text-align: center;'>"
            + check.getGuesses() + "<br/>Wrong Guesses: "
            + check.getWrongGuesses() + "</html>");
        }
      }

      @Override
      /**
       * Метод обработки отпускания клавиши
       * @param e отпущенная кнопка
       */
      public void keyReleased(KeyEvent e) {
      }
    };

    button.addActionListener(new ActionListener() {
      /**
       * Метод обработки действия для начала новой игры
       * @param e событие
       */
      public void actionPerformed(ActionEvent e) {
        try {
          frame.dispose();
          newGame();
        } catch (IOException error){
          error.printStackTrace();
        }
      }
    });

    container.add(displayWord, BorderLayout.PAGE_START);
    container.add(displayLower, BorderLayout.PAGE_END);
    container.add(displayCenter, BorderLayout.CENTER);
    frame.addKeyListener(keyListener);
    frame.setVisible(true);
  }
  /**
   * Метод получения рандомного слова и его значения
   */
  private void getConvertWord() {
    scraper.execute();
    randomWordMeaning = scraper.getRandomWordMeaning();
    randomWord = scraper.getRandomWord();
  }

  /**
   * Метод удаления обработчика событий
   */
  private void removeListener() {
    frame.removeKeyListener(keyListener);
  }

}
