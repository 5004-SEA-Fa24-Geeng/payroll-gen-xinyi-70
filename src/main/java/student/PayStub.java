package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a pay stub that records an employee's pay information.
 * Implements the IPayStub interface to provide pay-related information.
 */
public class PayStub implements IPayStub {
    /** stores the reference to employee. */
    private final IEmployee employee;
    /** stores the net pay amount as BigDecimal for precision. */
    private final BigDecimal netPay;
    /** stores the taxes amount as BigDecimal for precision. */
    private final BigDecimal taxes;

    /**
     * Constructs a PayStub for a specific employee.
     *
     * @param employee the employee associated with this pay stub
     * @param netPay the net pay amount
     * @param taxes the taxes amount
     * @throws IllegalArgumentException if the employee is null
     */
    public PayStub(IEmployee employee, double netPay, double taxes) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        this.employee = employee;
        this.netPay = new BigDecimal(netPay).setScale(2, RoundingMode.HALF_UP);
        this.taxes = new BigDecimal(taxes).setScale(2, RoundingMode.HALF_UP);

    }

    /**
     * Gets the net pay.
     *
     * @return the net pay amount as double, rounded to two decimal places
     */
    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    /**
     * Gets the taxes paid.
     *
     * @return the taxes paid amount as double, rounded to two decimal places
     */
    @Override
    public double getTaxesPaid() {
        return taxes.doubleValue();
    }

    /**
     * Converts the pay stub to a CSV string format.
     *
     * @return the CSV formatted string
     */
    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(),
                netPay.doubleValue(),
                taxes.doubleValue(),
                employee.getYTDEarnings(),
                employee.getYTDTaxesPaid());
    }
}
