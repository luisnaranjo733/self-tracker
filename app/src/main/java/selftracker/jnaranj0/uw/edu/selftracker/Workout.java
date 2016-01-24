package selftracker.jnaranj0.uw.edu.selftracker;

/**
 * Created by luis on 1/23/16.
 */
public class Workout {
    private String description;
    private int duration;

    public Workout() {}

    public Workout(String description, int duration) {
        this.description = description;
        this.duration = duration;
    }

    public String toString() {
        return "" + getDuration() + " min of " +  getDescription();
    }

    public String getDescription() {
        return this.description;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean equals(Workout other) {
        if (other.getDescription().equals(this.getDescription())
                && other.getDuration() == this.getDuration()) {
            return true;
        }
        return false;
    }

}
