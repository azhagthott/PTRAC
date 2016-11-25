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
    private LinearLayout linearLayoutContainer;

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

        linearLayoutContainer = (LinearLayout) rootView.findViewById(R.id.linearLayoutContainer);

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


    private void createCalendario() {

        final Context context = getActivity().getApplicationContext();

        final float LINEAR_LAYOUT_PADDING_LEFT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_RIGHT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_TOP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float LINEAR_LAYOUT_PADDING_DOWN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        final float TEXT_VIEW_PADDING_LEFT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        final float TEXT_VIEW_PADDING_RIGHT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        final float TEXT_VIEW_PADDING_TOP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
        final float TEXT_VIEW_PADDING_DOWN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        final float TEXT_SIZE_REGION = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        final float TEXT_SIZE_DATA = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());

        try {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference reference = db.getReference();

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String regionActual = "";

                    for (int i = 0; i < dataSnapshot.child("calendario").getChildrenCount(); i++) {

                        String region = "sin datos";
                        String comuna = "sin datos";
                        String direccion = "sin datos";
                        String telefono = "sin datos";
                        String estado = "sin datos";


                        if (dataSnapshot.child("calendario").child("" + i + "").child("region").getValue() != null) {
                            region = dataSnapshot.child("calendario").child("" + i + "").child("region").getValue().toString();
                        }

                        if (dataSnapshot.child("calendario").child("" + i + "").child("comuna").getValue() != null) {
                            comuna = dataSnapshot.child("calendario").child("" + i + "").child("comuna").getValue().toString();
                        }

                        if (dataSnapshot.child("calendario").child("" + i + "").child("direccion").getValue() != null) {
                            direccion = dataSnapshot.child("calendario").child("" + i + "").child("direccion").getValue().toString();
                        }

                        if (dataSnapshot.child("calendario").child("" + i + "").child("telefono").getValue() != null) {
                            telefono = dataSnapshot.child("calendario").child("" + i + "").child("telefono").getValue().toString();
                        }

                        if (dataSnapshot.child("calendario").child("" + i + "").child("estado").getValue() != null) {
                            estado = dataSnapshot.child("calendario").child("" + i + "").child("estado").getValue().toString();
                        }

                        //calendario = new Calendario(region, comuna, direccion, telefono, estado);

                        final LinearLayout linearLayout = new LinearLayout(context);
                        final LinearLayout linearLayoutComuna = new LinearLayout(context);
                        final LinearLayout linearLayoutDireccion = new LinearLayout(context);
                        final LinearLayout linearLayoutTelefono = new LinearLayout(context);
                        final LinearLayout linearLayoutEstado = new LinearLayout(context);

                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.setPadding((int) LINEAR_LAYOUT_PADDING_LEFT, (int) LINEAR_LAYOUT_PADDING_TOP, (int) LINEAR_LAYOUT_PADDING_RIGHT, (int) LINEAR_LAYOUT_PADDING_DOWN);

                        linearLayoutComuna.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutComuna.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutComuna.setPadding((int) LINEAR_LAYOUT_PADDING_LEFT, (int) LINEAR_LAYOUT_PADDING_TOP, (int) LINEAR_LAYOUT_PADDING_RIGHT, (int) LINEAR_LAYOUT_PADDING_DOWN);

                        linearLayoutDireccion.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutDireccion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutDireccion.setPadding((int) LINEAR_LAYOUT_PADDING_LEFT, (int) LINEAR_LAYOUT_PADDING_TOP, (int) LINEAR_LAYOUT_PADDING_RIGHT, (int) LINEAR_LAYOUT_PADDING_DOWN);

                        linearLayoutTelefono.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutTelefono.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutTelefono.setPadding((int) LINEAR_LAYOUT_PADDING_LEFT, (int) LINEAR_LAYOUT_PADDING_TOP, (int) LINEAR_LAYOUT_PADDING_RIGHT, (int) LINEAR_LAYOUT_PADDING_DOWN);

                        linearLayoutEstado.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayoutEstado.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayoutEstado.setPadding((int) LINEAR_LAYOUT_PADDING_LEFT, (int) LINEAR_LAYOUT_PADDING_TOP, (int) LINEAR_LAYOUT_PADDING_RIGHT, (int) LINEAR_LAYOUT_PADDING_DOWN);

                        TextView textViewRegion = new TextView(context);

                        TextView textViewLblComuan = new TextView(context);
                        TextView textViewLblDireccion = new TextView(context);
                        TextView textViewLblTelefono = new TextView(context);
                        TextView textViewLblEstado = new TextView(context);

                        TextView textViewComuan = new TextView(context);
                        TextView textViewDireccion = new TextView(context);
                        TextView textViewTelefono = new TextView(context);
                        TextView textViewEstado = new TextView(context);

                        //Estilo - colores
                        linearLayoutComuna.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        linearLayoutDireccion.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        linearLayoutTelefono.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        linearLayoutEstado.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                        textViewLblComuan.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewLblDireccion.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewLblTelefono.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewLblEstado.setTextColor(getResources().getColor(R.color.colorAccent));

                        textViewComuan.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewDireccion.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewTelefono.setTextColor(getResources().getColor(R.color.colorAccent));
                        textViewEstado.setTextColor(getResources().getColor(R.color.colorAccent));

                        //Estilo - padding
                        textViewLblComuan.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewLblDireccion.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewLblTelefono.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewLblEstado.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);

                        textViewComuan.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewDireccion.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewTelefono.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                        textViewEstado.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);

                        textViewLblComuan.setText(R.string.esteriliza_comuna);
                        textViewLblDireccion.setText(R.string.esteriliza_direccion);
                        textViewLblTelefono.setText(R.string.esteriliza_telefono);
                        textViewLblEstado.setText(R.string.esteriliza_estado);

                        textViewComuan.setText(comuna);
                        textViewDireccion.setText(direccion);
                        textViewTelefono.setText(telefono);
                        textViewEstado.setText(estado);

                        if (!regionActual.equals(region)) {
                            regionActual = region;
                            textViewRegion.setTextColor(getResources().getColor(R.color.colorAccent));
                            textViewRegion.setTextSize(TEXT_SIZE_REGION);
                            textViewRegion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            textViewRegion.setPadding((int) TEXT_VIEW_PADDING_LEFT, (int) TEXT_VIEW_PADDING_TOP, (int) TEXT_VIEW_PADDING_RIGHT, (int) TEXT_VIEW_PADDING_DOWN);
                            textViewRegion.setText(regionActual);
                        } else {
                            textViewRegion.setVisibility(View.GONE);
                        }

                        textViewComuan.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewDireccion.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewTelefono.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textViewEstado.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        linearLayout.addView(textViewRegion);

                        linearLayoutComuna.addView(textViewLblComuan);
                        linearLayoutComuna.addView(textViewComuan);
                        linearLayout.addView(linearLayoutComuna);

                        linearLayoutDireccion.addView(textViewLblDireccion);
                        linearLayoutDireccion.addView(textViewDireccion);
                        linearLayout.addView(linearLayoutDireccion);

                        linearLayoutTelefono.addView(textViewLblTelefono);
                        linearLayoutTelefono.addView(textViewTelefono);
                        linearLayout.addView(linearLayoutTelefono);

                        linearLayoutEstado.addView(textViewLblEstado);
                        linearLayoutEstado.addView(textViewEstado);
                        linearLayout.addView(linearLayoutEstado);

                        linearLayoutContainer.addView(linearLayout);

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
