import java.util.*;

public class BusReservationSystem {
    static List<Bus> buses = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        buses.add(new Bus(101, true, 2));
        buses.add(new Bus(102, false, 3));

        while (true) {
            System.out.println("\n--- Bus Reservation System ---");
            System.out.println("1. Display Available Buses");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayBuses();
                    break;
                case 2:
                    makeReservation(sc);
                    break;
                case 3:
                    displayReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void displayBuses() {
        for (Bus b : buses) {
            b.displayBusInfo();
        }
    }

    public static void makeReservation(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter Bus No: ");
        int busNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        long count = reservations.stream()
                .filter(r -> r.getBusNo() == busNo && r.getDate().equals(date))
                .count();

        Optional<Bus> busOpt = buses.stream()
                .filter(b -> b.getBusNo() == busNo)
                .findFirst();

        if (busOpt.isPresent()) {
            Bus bus = busOpt.get();
            if (count < bus.getCapacity()) {
                reservations.add(new Reservation(name, busNo, date));
                System.out.println("Reservation successful!");
            } else {
                System.out.println("Sorry, bus is full on this date.");
            }
        } else {
            System.out.println("Invalid Bus No.");
        }
    }

    public static void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation r : reservations) {
                r.displayReservation();
            }
        }
    }
}