public class Reservation {
    private String passengerName;
    private int busNo;
    private String date;

    public Reservation(String passengerName, int busNo, String date) {
        this.passengerName = passengerName;
        this.busNo = busNo;
        this.date = date;
    }

    public int getBusNo() {
        return busNo;
    }

    public String getDate() {
        return date;
    }

    public void displayReservation() {
        System.out.println("Passenger: " + passengerName + ", Bus No: " + busNo + ", Date: " + date);
    }
}