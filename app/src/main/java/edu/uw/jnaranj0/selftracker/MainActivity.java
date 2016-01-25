package edu.uw.jnaranj0.selftracker;
// TODO: fix package name

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements MasterFragment.onWorkoutSelectedListener, RecorderFragment.onEventRecordedListener{
    public static final String TAG = "SelfTracker";
    public static final String MASTER_FRAG_TAG = "MasterFrag";
    public static final String SUMMARY_FRAG_TAG =  "SummaryFrag";
    public static final String DETAIL_FRAG_TAG = "DetailFrag";

    public static final String FIREBASE_ROOT = "https://workout-self-tracker.firebaseio.com/";
    public static final String FIREBASE_WORKOUTS_ARRAY = "workouts";
    public Firebase ref;
    public Firebase workoutsRef;

    public FrameLayout leftPane;
    public FrameLayout rightPane;

    public MasterFragment masterFragment;

    public boolean eventJustRecorded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        ref = new Firebase(FIREBASE_ROOT);
        workoutsRef = ref.child(FIREBASE_WORKOUTS_ARRAY);
        setContentView(R.layout.activity_main);

        leftPane = (FrameLayout) findViewById(R.id.leftPane);
        rightPane = (FrameLayout) findViewById(R.id.rightPane);

        if (leftPane != null) {
            Log.v(TAG, "Landscape mode");
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.leftPane, new SummaryFragment(), SUMMARY_FRAG_TAG);
            //masterFragment = new MasterFragment();
            ft.add(R.id.rightPane, new MasterFragment(), MASTER_FRAG_TAG);
            ft.commit();

        } else {
            Log.v(TAG, "Portrait mode");
        }

    }


    @Override
    public void onWorkoutSelected(Workout workout) {
        //Toast.makeText(getApplicationContext(), workout.toString(), Toast.LENGTH_SHORT).show();

        DetailFragment detailFrag = (DetailFragment) getFragmentManager()
                .findFragmentByTag(DETAIL_FRAG_TAG);

        Bundle bundle = new Bundle();
        bundle.putString("title", workout.toString());


        // if current view is (summary/master)
        // switch to (master/detail)
        if  (detailFrag == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            // TODO: should not create a new "list" fragment when you do this;
            // just move the existing one around.
            ft.replace(R.id.leftPane, new MasterFragment(), MASTER_FRAG_TAG);

            detailFrag = new DetailFragment();
            detailFrag.setArguments(bundle);

            ft.replace(R.id.rightPane, detailFrag, DETAIL_FRAG_TAG);
            ft.addToBackStack("Switching frags");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else if (detailFrag.isVisible()) {
            // if current view is (master/detail),
            // update the detail view with the newly selected event
            detailFrag.update(workout);
        }

    }

    @Override
    public void onShowDialog(RecorderFragment fragment) {
        Log.v(TAG, "Callback active");
        FragmentManager manager = getFragmentManager();
        fragment.show(manager, "dialog");
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean eventJustRecorded() {
        return eventJustRecorded;
    }
}
