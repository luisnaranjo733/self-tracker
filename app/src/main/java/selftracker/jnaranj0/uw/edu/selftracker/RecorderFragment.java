package selftracker.jnaranj0.uw.edu.selftracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Map;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecorderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RecorderFragment extends DialogFragment {

    public static final String TAG = "SelfTracker.Recorder";

    public static RecorderFragment newInstance() {
        return new RecorderFragment();
    }

    public RecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage("Record your workout");
        final View rootView = inflater.inflate(R.layout.fragment_recorder, null);
        builder.setView(rootView);
        final MainActivity activity = (MainActivity) getActivity();
        builder.setPositiveButton("Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText descriptionText = (EditText) rootView.findViewById(R.id.activityDesc);
                EditText durationText = (EditText) rootView.findViewById(R.id.minuteCount);
                Log.v(TAG, "Recorded: " + durationText.getText() + " min of " + descriptionText.getText());
                Toast.makeText(getActivity(),
                        "Recorded: " + durationText.getText() + " min of " + descriptionText.getText(),
                        Toast.LENGTH_SHORT).show();

                Firebase workoutsRef = activity.ref.child(activity.FIREBASE_WORKOUTS_ARRAY);
                Map<String, String> workout = new HashMap<String, String>();
                workout.put("description", descriptionText.getText().toString());
                workout.put("duration", durationText.getText().toString());
                workoutsRef.push().setValue(workout);


            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
