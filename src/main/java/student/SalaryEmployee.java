package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a salaried employee who is paid a fixed annual salary.
 * The salary employee's pay is calculated as annual salary divided by 24
 * for two payments every month.
 */
public class SalaryEmployee extends AbstractEmployee {
    /** number of pay periods per year. */
    private static final int PAY_PERIODS = 24;

    /**
     * Creates a new salary employee.
     *
     * @param name the employee name
     * @param id the employee ID
     * @param payRate the annual salary
     * @param ytdEarnings the year-to-date earnings
     * @param ytdTaxesPaid the year-to-date taxes paid
     * @param pretaxDeductions the pretax deductions
     * @throws IllegalArgumentException if name or id is null or empty, if payRate is negative,
     *         or if any monetary value is negative
     */
    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Gets the employee's Type as a string.
     *
     * @return the type of the employee as a string
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    /**
     * Calculates the gross pay for a salary employee.
     * Pay is calculated as annual salary divided by 24 for two payments every month.
     * Uses BigDecimal for calculations to maintain precision, and converted to double when returning results.
     *
     * @param hoursWorked the hours worked in the pay period (not used in calculation for salary employee)
     * @return the gross pay for the period
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return new BigDecimal(getPayRate())
                .divide(new BigDecimal(PAY_PERIODS), 2, RoundingMode.HALF_UP).doubleValue();
    }
}
