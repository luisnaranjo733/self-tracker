package selftracker.jnaranj0.uw.edu.selftracker;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.ValueEventListener;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    public static final String TAG = "SelfTracker.SummaryFrag";

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        // this is ok because we are using a single activity
        final MainActivity activity = (MainActivity) getActivity();

        activity.workoutsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot rootSnapshot) {
                int total_minutes = 0;
                for (DataSnapshot childSnapshot : rootSnapshot.getChildren()) {
                    Workout workout = childSnapshot.getValue(Workout.class);
                    total_minutes += workout.getDuration();
                }
                long avgWorkoutDuration = total_minutes / rootSnapshot.getChildrenCount();
                Log.v(TAG, "Updating average minutes: "
                        + avgWorkoutDuration);

                TextView avgWorkoutDurationView = (TextView)
                        rootView.findViewById(R.id.averageWorkoutDurationView);

                avgWorkoutDurationView.setText("" + avgWorkoutDuration);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return rootView;
    }

}
