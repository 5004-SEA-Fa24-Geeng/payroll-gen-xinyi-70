package student;

/**
 * Represents a time card that records an employee's id and worked hours.
 */
public class TimeCard implements ITimeCard {
    /** stores the employee ID. */
    private final String employeeID;
    /** stores the hours worked. */
    private final double hoursWorked;

    /**
     * Constructs a new TimeCard.
     *
     * @param employeeID the ID of the employee
     * @param hoursWorked the number of hours worked by the employee
     * @throws IllegalArgumentException if employeeID is null or empty, or if hoursWorked is negative
     */
    public TimeCard(String employeeID, double hoursWorked) {
        if (employeeID == null || employeeID.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative");
        }

        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the hours worked by the employee.
     *
     * @return the hours worked by the employee
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
