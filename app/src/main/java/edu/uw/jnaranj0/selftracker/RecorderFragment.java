package edu.uw.jnaranj0.selftracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecorderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RecorderFragment extends DialogFragment {

    public static final String TAG = "SelfTracker.Recorder";

    private MainActivity activity;

    onEventRecordedListener callback;
    public interface onEventRecordedListener {
        public boolean eventJustRecorded();
    }

    public static RecorderFragment newInstance() {
        return new RecorderFragment();
    }

    public RecorderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (onEventRecordedListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException(context.toString() +
                    " must implement onEventJustRecordedListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage("Record your workout");
        final View rootView = inflater.inflate(R.layout.fragment_recorder, null);
        builder.setView(rootView);
        activity = (MainActivity) getActivity();

        builder.setPositiveButton("Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText descriptionText = (EditText) rootView.findViewById(R.id.activityDesc);
                EditText durationText = (EditText) rootView.findViewById(R.id.minuteCount);
                Log.v(TAG, "Recorded: " + durationText.getText() + " min of " + descriptionText.getText());


                Map<String, String> workout = new HashMap<String, String>();
                workout.put("description", descriptionText.getText().toString());
                workout.put("duration", durationText.getText().toString());
                Long tsLong = System.currentTimeMillis();
                workout.put("timestamp", tsLong.toString());
                activity.workoutsRef.push().setValue(workout);
                Toast.makeText(getActivity(),
                        "Recorded: " + durationText.getText() + " min of " + descriptionText.getText(),
                        Toast.LENGTH_SHORT).show();
                activity.eventJustRecorded = true;
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
