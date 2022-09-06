import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1., 1.));
    }

    @Test
    public void nullNameExceptionWithMessage() {
        try {
            new Horse(null, 1., 1.);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings =  {"", " ", "  ", "   ", "\t", "\n"})
    public void blankNameExceptions(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1., 1.));
    }

    @ParameterizedTest
    @ValueSource (strings = {"", " ", "  ", "   ", "\t", "\n"})
    public void blankNameWithMessage(String name) {
        try {
            new Horse(name, 1., 1.);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public void speedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1., 1.));
    }

    @Test
    public void speedExceptionWithMessage() {
        try {
            new Horse("name", -1., 1.);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void distanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1., -1.));
    }

    @Test
    public void distanceExceptionWithMessage() {
        try {
            new Horse("name", 1., -1.);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
}
