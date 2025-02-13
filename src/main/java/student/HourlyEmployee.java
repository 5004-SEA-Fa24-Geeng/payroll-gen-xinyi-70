package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee who is paid based on hours worked.
 * The hourly employee's pay is calculated as hours worked times pay rate,
 * with overtime (hours > 40) paid at 1.5 times the normal rate.
 */
public class HourlyEmployee extends AbstractEmployee {

    /**
     * Creates a new hourly employee.
     *
     * @param name the employee name
     * @param id the employee ID
     * @param payRate the hourly pay rate
     * @param ytdEarnings the year-to-date earnings
     * @param ytdTaxesPaid the year-to-date taxes paid
     * @param pretaxDeductions the pretax deductions
     * @throws IllegalArgumentException if name or id is null or empty, if payRate is negative,
     *         or if any monetary value is negative
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings,
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
        return "HOURLY";
    }

    /**
     * Calculates the gross pay for an hourly employee.
     * For the first 40 hours, pay is calculated as payRate * hoursWorked.
     * For hours over 40, payRate * 1.5 * (hoursWorked - 40) for overtime.
     * Uses BigDecimal for calculations to maintain precision, and converted to double when returning results.
     *
     * @param hoursWorked the hours worked in the pay period
     * @return the gross pay for the period
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        BigDecimal hourlyRate = new BigDecimal(getPayRate());

        if (hoursWorked <= 40) {
            return hourlyRate.multiply(new BigDecimal(hoursWorked))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        } else {
            BigDecimal regularPay = hourlyRate.multiply(new BigDecimal(40));
            BigDecimal overtimePay = hourlyRate.multiply(new BigDecimal(1.5))
                    .multiply(new BigDecimal(hoursWorked - 40));
            return regularPay.add(overtimePay)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
    }
}