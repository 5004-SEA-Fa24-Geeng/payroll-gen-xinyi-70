package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTimeCard {
    private String validEmployeeId;
    private double validHours;
    private ITimeCard timeCard;

    @BeforeEach
    public void setUp() {
        validEmployeeId = "s192";
        validHours = 45.0;
        timeCard = new TimeCard(validEmployeeId, validHours);
    }

    @Test
    public void testGetEmployeeID() {
        assertEquals(validEmployeeId, timeCard.getEmployeeID());
    }

    @Test
    public void testGetHoursWorked() {
        assertEquals(validHours, timeCard.getHoursWorked());
    }

    @Test
    public void testConstructorWithNullEmployeeId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard(null, validHours);
        });
    }

    @Test
    public void testConstructorWithEmptyEmployeeId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard("", validHours);
        });
    }

    @Test
    public void testConstructorWithNegativeHours() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard(validEmployeeId, -1.0);
        });
    }
}