package student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSalaryEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;
    private IEmployee employee;

    @BeforeEach
    public void setUp() {
        name = "Nami";
        id = "s193";
        payRate = 200000.00;
        ytdEarnings = 17017.00;
        ytdTaxesPaid = 4983.00;
        pretaxDeductions = 1000.00;
        employee = new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Test
    public void testConstructor() {
        assertEquals(name, employee.getName());
        assertEquals(id, employee.getID());
        assertEquals(payRate, employee.getPayRate());
        assertEquals(ytdEarnings, employee.getYTDEarnings());
        assertEquals(ytdTaxesPaid, employee.getYTDTaxesPaid());
        assertEquals(pretaxDeductions, employee.getPretaxDeductions());
        assertEquals("SALARY", employee.getEmployeeType());
    }

    @Test
    public void testGetterMethods() {
        assertEquals(name, employee.getName());
        assertEquals(id, employee.getID());
        assertEquals(payRate, employee.getPayRate());
        assertEquals("SALARY", employee.getEmployeeType());
        assertEquals(ytdEarnings, employee.getYTDEarnings());
        assertEquals(ytdTaxesPaid, employee.getYTDTaxesPaid());
        assertEquals(pretaxDeductions, employee.getPretaxDeductions());
    }

    @Test
    public void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(null, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee("", id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, null, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, "", payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativePayRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, id, -1.0, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativeYtdEarnings() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, id, payRate, -1.0, ytdTaxesPaid, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativeYtdTaxesPaid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, id, payRate, ytdEarnings, -1.0, pretaxDeductions);
        });
    }

    @Test
    public void testConstructorWithNegativePretaxDeductions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, -1.0);
        });
    }

    @Test
    public void testRunPayroll() {
        IPayStub payStub = employee.runPayroll(60.0);  // hours worked doesn't matter for salary
        assertEquals(5672.33, payStub.getPay());
        assertEquals(1661.00, payStub.getTaxesPaid());
    }

    @Test
    public void testRunPayrollWithNegativeHours() {
        assertNull(employee.runPayroll(-1.0));
    }

    @Test
    public void testRunPayrollUpdatesYTD() {
        IPayStub payStub = employee.runPayroll(60.0);
        assertEquals(22689.33, employee.getYTDEarnings());
        assertEquals(6644.00, employee.getYTDTaxesPaid());
    }

    @Test
    public void testToCSV() {
        String expected = String.format("SALARY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
        assertEquals(expected, employee.toCSV());
    }
}