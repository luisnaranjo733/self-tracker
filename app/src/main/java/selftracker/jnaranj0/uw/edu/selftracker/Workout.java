package selftracker.jnaranj0.uw.edu.selftracker;


import android.text.format.DateFormat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Workout {
    private String description;
    private int duration;
    public long timestamp;

    public static final String TAG = "SelfTracker.Workout";

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

        SimpleDateFormat sdf = new SimpleDateFormat("ka MM/dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        Date netDate = new Date(timestamp);
        return sdf.format(netDate);
    }


    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Workout other) {
        boolean titleEqual = other.getDescription().equals(this.getDescription());
        boolean timestampEqual = other.timestamp == this.timestamp;

        Log.v(TAG, "title: " + titleEqual + " time:  " + timestampEqual);

        if (titleEqual && timestampEqual) {
            return true;
        } else {
            return false;
        }
    }

}
