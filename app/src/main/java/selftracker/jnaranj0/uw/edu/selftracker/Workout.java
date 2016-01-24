package selftracker.jnaranj0.uw.edu.selftracker;

/**
 * Created by luis on 1/23/16.
 */
public class Workout {
    private String description;
    private int duration;

    public Workout(String description, int duration) {
        this.description = description;
        this.duration = duration;
    }

    public String toString() {
        return "" + this.duration + " min";
    }

    public String getDescription() {
        return this.description;
    }

    public int getDuration() {
        return this.duration;
    }
}
