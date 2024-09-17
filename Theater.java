package movieBooking;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String name;
    private String location;
    private List<Screen> screens;

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
        this.screens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", screens=" + screens +
                '}';
    }
}
