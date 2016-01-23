package selftracker.jnaranj0.uw.edu.selftracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  MasterFragment.onWorkoutSelectedListener {
    public static final String TAG = "SelfTracker";
    public static final String MASTER_FRAG_TAG = "MasterFrag";
    public static final String SUMMARY_FRAG_TAG =  "SummaryFrag";
    public static final String DETAIL_FRAG_TAG = "DetailFrag";

    public FrameLayout leftPane;
    public FrameLayout rightPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftPane = (FrameLayout) findViewById(R.id.leftPane);
        rightPane = (FrameLayout) findViewById(R.id.rightPane);

        if (leftPane != null) {
            Log.v(TAG, "Landscape mode");
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.leftPane, new SummaryFragment(), SUMMARY_FRAG_TAG);
            ft.add(R.id.rightPane, new MasterFragment(), MASTER_FRAG_TAG);
            //ft.addToBackStack("initializing frags");
            ft.commit();
            Toast.makeText(getApplicationContext(), "initializing frags", Toast.LENGTH_SHORT).show();

        } else {
            Log.v(TAG, "Portrait mode");
        }

    }


    @Override
    public void onWorkoutSelected(Workout workout) {
        //Toast.makeText(getApplicationContext(), workout.toString(), Toast.LENGTH_SHORT).show();

        DetailFragment detailFrag = (DetailFragment) getFragmentManager()
                .findFragmentByTag(DETAIL_FRAG_TAG);

        if  (detailFrag == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.leftPane, new MasterFragment(), MASTER_FRAG_TAG);
            ft.replace(R.id.rightPane, new DetailFragment(), DETAIL_FRAG_TAG);
            ft.addToBackStack("Switching frags");
            ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            ft.commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
