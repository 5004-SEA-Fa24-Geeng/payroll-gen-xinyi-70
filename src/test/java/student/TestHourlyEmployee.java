package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHourlyEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;
    private IEmployee employee;

    @BeforeEach
    public void setUp() {
        name = "Luffy";
        id = "s192";
        payRate = 30.00;
        ytdEarnings = 20000.00;
        ytdTaxesPaid = 4530.00;
        pretaxDeductions = 0.00;
        employee = new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Test
    public void testConstructor() {
        assertEquals(name, employee.getName());
        assertEquals(id, employee.getID());
        assertEquals(payRate, employee.getPayRate());
        assertEquals(ytdEarnings, employee.getYTDEarnings());
        assertEquals(ytdTaxesPaid, employee.getYTDTaxesPaid());
        assertEquals(pretaxDeductions, employee.getPretaxDeductions());
        assertEquals("HOURLY", employee.getEmployeeType());
    }

    @Test
    public void testGetterMethods() {
        assertEquals(name, employee.getName());
        assertEquals(id, employee.getID());
        assertEquals(payRate, employee.getPayRate());
        assertEquals("HOURLY", employee.getEmployeeType());
        assertEquals(ytdEarnings, employee.getYTDEarnings());
        assertEquals(ytdTaxesPaid, employee.getYTDTaxesPaid());
        assertEquals(pretaxDeductions, employee.getPretaxDeductions());
    }

    @Test
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(null, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee("", id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, null, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, "", payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativePayRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, id, -1.0, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativeYtdEarnings() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, id, payRate, -1.0, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativeYtdTaxesPaid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, id, payRate, ytdEarnings, -1.0, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativePretaxDeductions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, -1.0);
        });
    }

    @Test
    public void testRunPayrollWithNormalHours() {
        IPayStub payStub = employee.runPayroll(40.0);
        double grossPay = 40.0 * payRate;
        double taxableAmount = grossPay - pretaxDeductions;
        double expectedTaxes = taxableAmount * 0.2265;
        double expectedNetPay = taxableAmount - expectedTaxes;

        assertEquals(expectedNetPay, payStub.getPay());
        assertEquals(expectedTaxes, payStub.getTaxesPaid());
    }

    @Test
    public void testRunPayrollWithOvertime() {
        IPayStub payStub = employee.runPayroll(45.0);
        // From pay_stubs_solution_to_original.csv
        assertEquals(1102.24, payStub.getPay());
        assertEquals(322.76, payStub.getTaxesPaid());
    }

    @Test
    public void testRunPayrollWithNegativeHours() {
        assertNull(employee.runPayroll(-1.0));
    }

    @Test
    public void testRunPayrollUpdatesYTD() {
        IPayStub payStub = employee.runPayroll(40.0);
        double oldYtdEarnings = ytdEarnings;
        double oldYtdTaxesPaid = ytdTaxesPaid;
        assertEquals(oldYtdEarnings + payStub.getPay(), employee.getYTDEarnings());
        assertEquals(oldYtdTaxesPaid + payStub.getTaxesPaid(), employee.getYTDTaxesPaid());
    }

    @Test
    public void testToCSV() {
        String expected = String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
        assertEquals(expected, employee.toCSV());
    }
}