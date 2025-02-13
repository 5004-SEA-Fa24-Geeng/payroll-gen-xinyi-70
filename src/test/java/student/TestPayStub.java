package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestPayStub {
    private IEmployee mockEmployee;
    private double netPay;
    private double taxes;
    private IPayStub payStub;
    private static final double TAX_RATE = 0.2265;

    @BeforeEach
    public void setUp() {
        mockEmployee = new IEmployee() {
            @Override
            public String getName() { return "Luffy"; }
            @Override
            public String getID() { return "s192"; }
            @Override
            public double getPayRate() { return 30.0; }
            @Override
            public String getEmployeeType() { return "HOURLY"; }
            @Override
            public double getYTDEarnings() { return 20000.0; }
            @Override
            public double getYTDTaxesPaid() { return 4530.0; }
            @Override
            public double getPretaxDeductions() { return 0.0; }
            @Override
            public IPayStub runPayroll(double hoursWorked) { return null; }
            @Override
            public String toCSV() { return ""; }
        };

        netPay = 1102.24;
        taxes = 322.76;
        payStub = new PayStub(mockEmployee, netPay, taxes);
    }

    @Test
    public void testGetPay() {
        assertEquals(netPay, payStub.getPay());
    }

    @Test
    public void testGetTaxesPaid() {
        assertEquals(taxes, payStub.getTaxesPaid());
    }

    @Test
    public void testToCSV() {
        String expected = String.format("%s,%.2f,%.2f,%.2f,%.2f",
                mockEmployee.getName(),
                netPay,
                taxes,
                mockEmployee.getYTDEarnings(),
                mockEmployee.getYTDTaxesPaid());
        assertEquals(expected, payStub.toCSV());
    }

    @Test
    public void testConstructorWithNullEmployee() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PayStub(null, netPay, taxes);
        });
    }
}