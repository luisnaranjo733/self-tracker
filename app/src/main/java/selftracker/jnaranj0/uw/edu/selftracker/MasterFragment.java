package selftracker.jnaranj0.uw.edu.selftracker;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    public static final String TAG = "SelfTracker.MasterFrag";
    private ArrayAdapter<Workout> adapter;

    private onWorkoutSelectedListener callback;
    private MainActivity activity;

    public interface onWorkoutSelectedListener {
        public void onWorkoutSelected(Workout workout);
        public void onShowDialog(RecorderFragment frag);
    }


    public MasterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "Attached to new activity");
        super.onAttach(context);

        try {
            callback = (onWorkoutSelectedListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException(context.toString() +
                    " must implement onWorkoutSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master, container, false);

        activity = (MainActivity) getActivity();

        final ArrayList<Workout> list = new ArrayList<Workout>();
        adapter = new WorkoutAdapter(getActivity(), R.layout.list_item, list);

        AdapterView listView = (AdapterView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        activity.workoutsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot rootSnapshot) {
                for (DataSnapshot childSnapshot : rootSnapshot.getChildren()) {
                    Workout workout = childSnapshot.getValue(Workout.class);
                    list.add(workout);
                    adapter.notifyDataSetChanged();
                    Log.v(TAG, workout.toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //respond to item clicking
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Workout item = (Workout) parent.getItemAtPosition(position);
                Log.v(TAG, "selected: " + item);

                // swap the fragments
                ((onWorkoutSelectedListener) getActivity()).onWorkoutSelected(item);
            }
        });

        Button button = (Button) rootView.findViewById(R.id.addWorkoutButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v(TAG, "Button pressed!");
                RecorderFragment recorderFrag = RecorderFragment.newInstance();

                ((onWorkoutSelectedListener) getActivity()).onShowDialog(recorderFrag);
                //recorderFrag.show(getFragmentManager(),  "dialog");

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    public class WorkoutAdapter extends  ArrayAdapter<Workout> {
        private final Context context;
        private final ArrayList<Workout> data;
        private final int layoutResourceId;


        public WorkoutAdapter(Context context, int layoutResourceId, ArrayList<Workout> data) {
            super(context, layoutResourceId, data);
            this.context = context;
            this.data = data;
            this.layoutResourceId = layoutResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Workout workout = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            // Lookup view for data population
            TextView duration = (TextView) convertView.findViewById(R.id.workoutDuration);
            TextView description = (TextView) convertView.findViewById(R.id.workoutDescription);
            // Populate the data into the template view using the data object
            duration.setText("" + workout.getDuration());
            description.setText(workout.getDescription());
            // Return the completed view to render on screen
            return convertView;
        }
    }

}
