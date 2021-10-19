package guess;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CheckTest {

    // #convertWord
    @Test public void testConvertWordReturnWordArray() {
      Check check = new Check("test");
      char[] wordTestReturn = {'t', 'e', 's', 't'};

      assertEquals(Arrays.toString(wordTestReturn), Arrays.toString(check.convertWord()));
    }

    // #guessChars
    @Test public void testGuessCharsReturnGuessesArray() {
      Check check = new Check("test");
      check.guessChars('a');
      check.guessChars('b');
      check.guessChars('c');
      char[] charsTestReturn = {'a', 'b', 'c', 'd'};

      assertEquals(Arrays.toString(charsTestReturn), Arrays.toString(check.guessChars('d')));
    }

    // #displayWord
    @Test public void testDisplayWordReturnsString() {
      Check check = new Check("test");
      check.convertWord();
      check.guessChars('t');
      String displayReturn = "t**t";

      assertEquals(displayReturn, check.displayWord());
    }

    // #verifyWordToGuesses
    @Test public void testVerifyWordToGuessesReturnsFalseBoolean() {
      Check check = new Check("test");
      check.convertWord();
      check.guessChars('t');
      check.displayWord();

      assertEquals(false, check.verifyWordToGuesses());
    }

    @Test public void testVerifyWordToGuessesReturnsTrueBoolean() {
      Check check = new Check("test");
      check.convertWord();
      check.guessChars('t');
      check.guessChars('e');
      check.guessChars('s');
      check.displayWord();

      assertEquals(true, check.verifyWordToGuesses());
    }

    // #initialDisplayWord
    @Test public void testInitialDisplayWordReturnsCorrectString() {
      Check check = new Check("test");
      assertEquals("****", check.initialDisplayWord());
    }

    @Test public void testInitialDisplayWordReturnsCorrectLongerString() {
      Check check = new Check("testing");
      assertEquals("*******", check.initialDisplayWord());
    }
}
