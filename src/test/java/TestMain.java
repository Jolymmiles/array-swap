import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public TestMain() {
        Locale.setDefault(new Locale("en", "US"));
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainFirst() {
        String data = "5 1 3\r\n1\r\n2\r\n3\r\n4\r\n5";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{1, 4, 3, 2, 5};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r,\\n]", " ")
                        .split(" ")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainSecond() {
        String data = "1 2 3\r\n1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "NO";
        String actual = outContent.toString().replaceAll("[\\r\\n]", "");

        assertEquals(expected, actual);
    }

    @Test
    public void testMainThird() {
        String data = "5 0 5\r\n100\r\n11\r\n2\r\n59\r\n123";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "NO";
        String actual = outContent.toString().replaceAll("[\\r\\n]", "");

        assertEquals(expected, actual);
    }

    @Test
    public void testMainForth() {
        String data = "2 0 1\r\n1\r\n2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{2, 1};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r,\\n]", " ")
                        .split(" ")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainFifth() {
        String data = "4 4 4\r\n1\r\n2\r\n3\r\n4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "NO";
        String actual = outContent.toString().replaceAll("[\\r\\n]", "");

        assertEquals(expected, actual);
    }
}
