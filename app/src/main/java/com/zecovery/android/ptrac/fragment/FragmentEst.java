package com.zecovery.android.ptrac.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zecovery.android.ptrac.R;

import org.json.JSONObject;

import static com.zecovery.android.ptrac.activity.BaseActivity.LOG_TAG;

public class FragmentEst extends Fragment {

    private OnFragmentInteractionListener listener;
    private WebView wvCalendar;

    public FragmentEst() {
        // Required empty public constructor
    }

    public static FragmentEst newInstance() {
        return new FragmentEst();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_est, container, false);


        createCalendario();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
    }


    private void createCalendario(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (int i = 0; i <dataSnapshot.getChildrenCount() ; i++) {

                    Log.d(LOG_TAG, "comuna_key: " + dataSnapshot.child("region").child(""+i+"").child("comuna").child("0").child("comuna_nombre").getValue().toString());

                }

                //String comuna_key = dataSnapshot.child("region").child("4").child("comuna").child("5").child("comuna_nombre").getValue().toString();

                //Log.d(LOG_TAG, "comuna_key: " + comuna_key);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                FirebaseCrash.log("ERROR:: " +databaseError);
            }
        });

    }



}
