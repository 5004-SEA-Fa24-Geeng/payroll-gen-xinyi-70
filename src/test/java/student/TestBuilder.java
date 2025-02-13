package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestBuilder {

    @Test
    public void testBuildHourlyEmployee() {
        String csv = "HOURLY,Luffy,s192,30.00,0.00,20000.00,4530.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertEquals("Luffy", employee.getName());
        assertEquals("s192", employee.getID());
        assertEquals(30.00, employee.getPayRate());
        assertEquals("HOURLY", employee.getEmployeeType());
    }

    @Test
    public void testBuildSalaryEmployee() {
        String csv = "SALARY,Nami,s193,200000.00,1000.00,17017.00,4983.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertEquals("Nami", employee.getName());
        assertEquals("s193", employee.getID());
        assertEquals(200000.00, employee.getPayRate());
        assertEquals("SALARY", employee.getEmployeeType());
    }

    @Test
    public void testBuildEmployeeWithInvalidCSV() {
        String csv = "HOURLY,Luffy,s192"; // missing fields
        assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(csv);
        });
    }

    @Test
    public void testBuildTimeCard() {
        String csv = "s192,45.0";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csv);

        assertEquals("s192", timeCard.getEmployeeID());
        assertEquals(45.0, timeCard.getHoursWorked());
    }

    @Test
    public void testBuildTimeCardWithInvalidCSV() {
        String csv = "s192"; // missing hours
        assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildTimeCardFromCSV(csv);
        });
    }
}