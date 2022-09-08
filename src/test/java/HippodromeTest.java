import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullInContructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullListInContructorWithMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void nullListInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void nullListInConstructorWithMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse(("" + i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
       new Hippodrome(horses).move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest(){
        Horse horse1 = new Horse("horse1", 1, 1);
        Horse horse2 = new Horse("horse2", 1, 2);
        Horse horse3 = new Horse("horse3", 1, 3);
        Horse horse4 = new Horse("horse4", 1, 4);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertSame(horse4,hippodrome.getWinner());
    }


}
