package edu.uw.jnaranj0.selftracker;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static final String TAG = "SelfTracker.DetailFrag";

    private TextView title;
    private TextView timestamp;
    private TextView duration;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();

        title = (TextView) rootView.findViewById(R.id.displayWorkoutTitle);
        timestamp = (TextView) rootView.findViewById(R.id.displayWorkoutTimestamp);
        duration = (TextView) rootView.findViewById(R.id.displayWorkoutDuration);

        String description = bundle.getString("description");
        int duration =  Integer.parseInt(bundle.getString("duration"));
        long timestamp = bundle.getLong("timestamp");
        Workout workout = new Workout(description, duration);
        workout.setTimestamp(timestamp);

        update(workout);

        return rootView;
    }

    public void update(Workout workout) {
        title.setText(workout.getDescription());
        timestamp.setText(workout.getTimestamp("h a MM/dd/y"));
        duration.setText("" + workout.getDuration() + " min");
        Log.v(TAG, workout.toString());
    }

}
