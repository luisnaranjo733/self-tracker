package selftracker.jnaranj0.uw.edu.selftracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecorderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RecorderFragment extends DialogFragment {

    public static RecorderFragment newInstance() {
        return new RecorderFragment();
    }

    public RecorderFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // what did you do
        // for how many minutes did you do it?
        builder.setMessage("What did you do?");

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
