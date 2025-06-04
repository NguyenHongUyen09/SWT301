import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uyen.example.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    static Calculator calculator;

    @BeforeAll
    static void initAll() {
        calculator = new Calculator();
    }

    @AfterAll
    static void cleanupAll() {
        calculator = null;
    }

    //Calculator calculator = new Calculator();
    @DisplayName("Kiểm tra phép cộng với hai số dương")
    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3), "Addition should return 5");
    }

}



