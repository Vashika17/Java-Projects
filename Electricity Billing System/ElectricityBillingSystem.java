import java.util.*;

public class ElectricityBillingSystem {
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Electricity Billing System ---");
            System.out.println("1. Add Customer and Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCustomer(sc);
                    break;
                case 2:
                    displayAllBills();
                    break;
                case 3:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addCustomer(Scanner sc) {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Meter Number : ");
        int meterNo = sc.nextInt();
        System.out.print("Enter Previous Reading: ");
        int prev = sc.nextInt();
        System.out.print("Enter Current Reading : ");
        int curr = sc.nextInt();

        if (curr < prev) {
            System.out.println("Current reading cannot be less than previous reading.");
            return;
        }

        Customer c = new Customer(name, meterNo, prev, curr);
        customers.add(c);
        c.displayBill();
    }

    public static void displayAllBills() {
        if (customers.isEmpty()) {
            System.out.println("No customer bills available.");
        } else {
            for (Customer c : customers) {
                c.displayBill();
            }
        }
    }
}
