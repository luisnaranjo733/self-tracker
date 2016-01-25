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

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();

        TextView title = (TextView) rootView.findViewById(R.id.workoutTitle);
        title.setText(bundle.getString("title"));

        return rootView;
    }

    public void update(Workout workout) {
        TextView title = (TextView) getActivity().findViewById(R.id.workoutTitle);
        title.setText(workout.toString());
        Log.v(TAG, workout.toString());
    }

}
