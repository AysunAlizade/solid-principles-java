interface Payable {
    double calculateSalary();
}

interface PaidLeaveEligible {
    void takePaidLeave(int days);
}

interface InsuranceEligible {
    void enrollInHealthInsurance();
}

// Contractors are only responsible for earning their contract payment
class Contractor implements Payable {

    @Override
    public double calculateSalary() {
        return 5000.0;
    }
}

// Full-time employees receive all three benefits
class FullTimeEmployee implements Payable, PaidLeaveEligible, InsuranceEligible {

    @Override
    public double calculateSalary() {
        return 4000.0;
    }

    @Override
    public void takePaidLeave(int days) {
        System.out.println("Full-time employee takes " + days + " days of paid leave.");
    }

    @Override
    public void enrollInHealthInsurance() {
        System.out.println("Full-time employee enrolled in health insurance.");
    }
}

public class Starter {

    // Processes payroll for any worker who can be paid
    public static void runPayroll(Payable worker) {
        System.out.println("Salary: $" + worker.calculateSalary());
    }

    public static void main(String[] args) {
        Contractor contractor = new Contractor();
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();

        // Payroll works for both types of workers
        runPayroll(contractor);
        runPayroll(fullTimeEmployee);

        // Only the full-time employee can access these methods
        fullTimeEmployee.takePaidLeave(2);
        fullTimeEmployee.enrollInHealthInsurance();
    }
}
