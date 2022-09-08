import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

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
    @ValueSource(strings = {"", " ", "  ", "   ", "\t", "\n"})
    public void blankNameExceptions(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1., 1.));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "   ", "\t", "\n"})
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

    @Test
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("horse", 1., 1.);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("horse", nameValue);
    }

    @Test
    public void getSpeedTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("horse", 1., 1.);
        assertEquals("horse", horse.getName());
    }

    @Test
    public void getDistanceTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("horse", 1., 1.);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistanceZeroTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("horse", 1.);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveTest() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("horse", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

}
