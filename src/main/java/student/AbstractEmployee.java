package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract base class for all employee types.
 * Contains common functionality for both hourly and salary employees.
 */
public abstract class AbstractEmployee implements IEmployee {
    /** stores the employee name. */
    private final String name;
    /** stores the employee ID. */
    private final String id;
    /** stores the pay rate (hourly rate or annual salary). */
    private final double payRate;
    /** stores the year-to-date earnings. */
    private double ytdEarnings;
    /** stores the year-to-date taxes paid. */
    private double ytdTaxesPaid;
    /** stores the pretax deductions. */
    private final double pretaxDeductions;
    /** tax rate for all deductions combined. */
    protected static final double TAX_RATE = 0.2265;

    /**
     * Creates a new employee.
     *
     * @param name the employee name
     * @param id the employee ID
     * @param payRate the pay rate (depends on employee type)
     * @param ytdEarnings the year-to-date earnings
     * @param ytdTaxesPaid the year-to-date taxes paid
     * @param pretaxDeductions the pretax deductions
     * @throws IllegalArgumentException if name or id is null or empty, if payRate is negative,
     *         or if any monetary value is negative
     */
    protected AbstractEmployee(String name, String id, double payRate, double ytdEarnings,
                               double ytdTaxesPaid, double pretaxDeductions) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (payRate < 0) {
            throw new IllegalArgumentException("Pay rate cannot be negative");
        }
        if (ytdEarnings < 0) {
            throw new IllegalArgumentException("YTD earnings cannot be negative");
        }
        if (ytdTaxesPaid < 0) {
            throw new IllegalArgumentException("YTD taxes paid cannot be negative");
        }
        if (pretaxDeductions < 0) {
            throw new IllegalArgumentException("Pretax deductions cannot be negative");
        }

        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Gets the employee's name.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the ID of the employee
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return the pay rate of the employee
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the employee's Type as a string.
     * Either "HOURLY" or "SALARY" depending on the type of employee.
     *
     * @return the type of the employee as a string
     */
    @Override
    public abstract String getEmployeeType();

    /**
     * Gets the YTD earnings of the employee.
     *
     * @return the YTD earnings of the employee
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the YTD taxes paid by the employee.
     *
     * @return the YTD taxes paid by the employee
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Gets pretax deductions for the employee.
     *
     * @return the pretax deductions for the employee
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Calculates the gross pay for the current pay period. The specific implementation method
     * is different for hourly and salaried employees.
     *
     * @param hoursWorked the hours worked in the pay period
     * @return the gross pay for the period
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Runs the employee's payroll. This will calculate the pay for the current pay, update the YTD earnings,
     * and update the taxes paid YTD.
     * Calculations are performed using BigDecimal for precision and only converted to double when necessary.
     *
     * @param hoursWorked the hours worked for the pay period
     * @return the pay stub for the current pay period, or null if hours worked is negative
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        // Calculate gross pay
        double grossPay = calculateGrossPay(hoursWorked);

        // Calculate taxes and net pay using BigDecimal
        BigDecimal taxableAmount = new BigDecimal(grossPay).subtract(new BigDecimal(pretaxDeductions));
        BigDecimal taxes = taxableAmount.multiply(new BigDecimal(TAX_RATE))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal netPay = taxableAmount.subtract(taxes)
                .setScale(2, RoundingMode.HALF_UP);

        // Update YTD values using BigDecimal to maintain precision
        BigDecimal newYtdEarnings = new BigDecimal(ytdEarnings).add(netPay)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal newYtdTaxesPaid = new BigDecimal(ytdTaxesPaid).add(taxes)
                .setScale(2, RoundingMode.HALF_UP);

        ytdEarnings = newYtdEarnings.doubleValue();
        ytdTaxesPaid = newYtdTaxesPaid.doubleValue();

        return new PayStub(this, netPay.doubleValue(), taxes.doubleValue());
    }

    /**
     * Converts the employee to a CSV string.
     * Format of the String s as follows: employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     *
     * @return the employee as a CSV string
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
