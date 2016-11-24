package com.zecovery.android.ptrac.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zecovery.android.ptrac.R;
import com.zecovery.android.ptrac.app.Calendario;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.ptrac.activity.BaseActivity.LOG_TAG;

public class FragmentEst extends Fragment {

    private OnFragmentInteractionListener listener;
    private Calendario calendario;
    private LinearLayout container;

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
        View rootView = inflater.inflate(R.layout.fragment_est, container, false);

        container = (LinearLayout) rootView.findViewById(R.id.container);

        createCalendario(getActivity().getApplicationContext());
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


    private void createCalendario(final Context context) {

        final float LINEAR_LAYOUT_PADDING_LEFT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_RIGHT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_TOP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_DOWN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        try {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference reference = db.getReference();

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //for (int i = 0; i <dataSnapshot.child("calendario").getChildrenCount(); i++) {
                    for (int i = 0; i <10; i++) {

                        String region = "sin datos";
                        String comuna = "sin datos";
                        String direccion = "sin datos";
                        String telefono = "sin datos";
                        String estado = "sin datos";

                        if (equals(dataSnapshot.child("calendario").child("" + i + "").child("region").getValue())) {
                            region = dataSnapshot.child("calendario").child("" + i + "").child("region").getValue().toString();
                        }

                        if (equals(dataSnapshot.child("calendario").child("" + i + "").child("comuna").getValue())) {
                            comuna = dataSnapshot.child("calendario").child("" + i + "").child("comuna").getValue().toString();
                        }

                        if (equals(dataSnapshot.child("calendario").child("" + i + "").child("direccion").getValue())) {
                            direccion = dataSnapshot.child("calendario").child("" + i + "").child("direccion").getValue().toString();
                        }

                        if (equals(dataSnapshot.child("calendario").child("" + i + "").child("telefono").getValue())) {
                            telefono = dataSnapshot.child("calendario").child("" + i + "").child("telefono").getValue().toString();
                        }

                        if (equals(dataSnapshot.child("calendario").child("" + i + "").child("estado").getValue())) {
                            estado = dataSnapshot.child("calendario").child("" + i + "").child("estado").getValue().toString();
                        }

                        calendario = new Calendario(region, comuna, direccion, telefono, estado);

                        final LinearLayout linearLayout = new LinearLayout(context);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.setPadding(
                                (int) LINEAR_LAYOUT_PADDING_LEFT,
                                (int) LINEAR_LAYOUT_PADDING_TOP,
                                (int) LINEAR_LAYOUT_PADDING_RIGHT,
                                (int) LINEAR_LAYOUT_PADDING_DOWN
                        );



                        TextView textViewRegion = new TextView(context);
                        TextView textViewComuan = new TextView(context);
                        TextView textViewDireccion = new TextView(context);
                        TextView textViewTelefono = new TextView(context);
                        TextView textViewEstado = new TextView(context);

                        textViewRegion.setText(region);
                        textViewComuan.setText(comuna);
                        textViewDireccion.setText(direccion);
                        textViewTelefono.setText(telefono);
                        textViewEstado.setText(estado);

                        textViewRegion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewComuan.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewDireccion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewTelefono.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewEstado.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        linearLayout.addView(textViewRegion);
                        linearLayout.addView(textViewComuan);
                        linearLayout.addView(textViewDireccion);
                        linearLayout.addView(textViewTelefono);
                        linearLayout.addView(textViewEstado);

                        container.addView(linearLayout);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    FirebaseCrash.log("ERROR:: " + databaseError);
                }
            });
        } catch (Exception e) {
            Log.d(LOG_TAG, "Exception: " + e);
        }
    }
}
