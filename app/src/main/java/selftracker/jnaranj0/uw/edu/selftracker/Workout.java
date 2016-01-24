package selftracker.jnaranj0.uw.edu.selftracker;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luis on 1/23/16.
 */
public class Workout {
    private String description;
    private int duration;
    private long timestamp;

    public Workout() {}

    public Workout(String description, int duration) {
        this.description = description;
        this.duration = duration;
        this.timestamp = 0;
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

    public String getTimestamp() {
        DateFormat sdf = new SimpleDateFormat("ka MM/dd");
        Date netDate = (new Date(timestamp));
        return sdf.format(netDate);
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Workout other) {
        if (other.getDescription().equals(this.getDescription())
                && other.getDuration() == this.getDuration()) {
            return true;
        }
        return false;
    }

}
