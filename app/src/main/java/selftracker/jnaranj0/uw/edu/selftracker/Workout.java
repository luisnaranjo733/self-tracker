package selftracker.jnaranj0.uw.edu.selftracker;

/**
 * Created by luis on 1/23/16.
 */
public class Workout {
    private String title;
    private int length;

    public Workout(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String toString() {
        return "" + this.length + " min";
    }

    public String getDescription() {
        return this.title;
    }

    public int getDuration() {
        return this.length;
    }
}
