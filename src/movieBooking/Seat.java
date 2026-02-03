package movieBooking;

public class Seat {
    private int row;
    private int col;
    private boolean isBooked;
    private SeatCategory category;

    public Seat(int row, int col, SeatCategory category) {
        this.row = row;
        this.col = col;
        this.category = category;
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public double getPrice(double basePrice) {
        return basePrice * category.getPriceMultiplier();
    }

    public String display() {
        if (isBooked) {
            return "[" + row + "," + col + " " + category.getDisplayName().charAt(0) + " (B)]";
        } else {
            return "[" + row + "," + col + " " + category.getDisplayName().charAt(0) + "]";
        }
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", col=" + col +
                ", isBooked=" + isBooked +
                ", category=" + category +
                '}';
    }
}
