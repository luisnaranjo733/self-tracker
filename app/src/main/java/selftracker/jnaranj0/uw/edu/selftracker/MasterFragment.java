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
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    public static final String TAG = "SelfTracker.MasterFrag";
    private ArrayAdapter<Workout> adapter;

    private onWorkoutSelectedListener callback;

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

        ArrayList<Workout> list = new ArrayList<Workout>();
        for (int i=0; i < 10; i++) {
            Workout workout = new Workout("" + i + " bottles of beer on the wall", i + 60);
            list.add(workout);
        }

        adapter = new ArrayAdapter<Workout>(
                getActivity(), R.layout.list_item, R.id.txtItem, list);

        AdapterView listView = (AdapterView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

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

}
