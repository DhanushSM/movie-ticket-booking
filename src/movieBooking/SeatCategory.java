package movieBooking;

public enum SeatCategory {
    SILVER("Silver", 1.0),
    GOLD("Gold", 1.25),
    PLATINUM("Platinum", 1.5);

    private final String displayName;
    private final double priceMultiplier;

    SeatCategory(String displayName, double priceMultiplier) {
        this.displayName = displayName;
        this.priceMultiplier = priceMultiplier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}
