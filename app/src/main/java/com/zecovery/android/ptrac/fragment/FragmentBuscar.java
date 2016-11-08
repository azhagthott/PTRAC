package com.zecovery.android.ptrac.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zecovery.android.ptrac.R;
import com.zecovery.android.ptrac.animation.LinearLayoutCustomAnimation;

import static com.zecovery.android.ptrac.activity.BaseActivity.LOG_TAG;

public class FragmentBuscar extends Fragment{

    private OnFragmentInteractionListener listener;

    private LinearLayout linearLayoutRut;
    private LinearLayout linearLayoutChip;

    private TextView textViewTitleRut;
    private TextView textViewTitleChip;

    private TextView textViewSubRut;
    private TextView textViewSubChip;

    private EditText editTextBusquedaRut;
    private EditText editTextBusquedaChip;

    private Button buttonBuscarRut;
    private Button buttonBuscarChip;

    private ImageView iconRut;
    private ImageView iconChip;

    public FragmentBuscar() {
    }

    public static FragmentBuscar newInstance() {
        return new FragmentBuscar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_buscar, container, false);

        linearLayoutRut = (LinearLayout) rootView.findViewById(R.id.linearLayoutRut);
        linearLayoutChip = (LinearLayout) rootView.findViewById(R.id.linearLayoutChip);

        textViewTitleRut = (TextView) rootView.findViewById(R.id.textViewTitleRut);
        textViewTitleChip = (TextView) rootView.findViewById(R.id.textViewTitleChip);

        textViewSubRut = (TextView) rootView.findViewById(R.id.textViewSubRut);
        textViewSubChip = (TextView) rootView.findViewById(R.id.textViewSubChip);

        editTextBusquedaRut = (EditText) rootView.findViewById(R.id.editTextBusquedaRut);
        editTextBusquedaChip = (EditText) rootView.findViewById(R.id.editTextBusquedaChip);

        buttonBuscarRut = (Button) rootView.findViewById(R.id.buttonBuscarRut);
        buttonBuscarChip = (Button) rootView.findViewById(R.id.buttonBuscarChip);

        iconRut = (ImageView) rootView.findViewById(R.id.iconRut);
        iconChip = (ImageView) rootView.findViewById(R.id.iconChip);

        updateUIRut(false);

        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();

        final float TEXT_VIEW_HEIGHT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
        final float TEXT_VIEW_HEIGHT_EXPANDED = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());



        linearLayoutRut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isExpanded;
                LinearLayoutCustomAnimation anim = new LinearLayoutCustomAnimation();

                if(!isExpanded){
                    anim.expand(linearLayoutRut,300, (int) TEXT_VIEW_HEIGHT_EXPANDED);
                    anim.rotateImage(iconRut,300);
                    isExpanded=true;
                    Log.d(LOG_TAG, "isExpanded: " + isExpanded);
                }else {
                    isExpanded = false;
                    anim.rotateImage(iconRut, 300);
                    anim.collapse(linearLayoutRut, 300, (int) TEXT_VIEW_HEIGHT);
                    Log.d(LOG_TAG, "isExpanded: " + isExpanded);
                }
            }
        });


    }

    private void updateUIRut(boolean visibility){

        if(visibility){
            textViewSubRut.setVisibility(View.VISIBLE);
            editTextBusquedaRut.setVisibility(View.VISIBLE);
            buttonBuscarRut.setVisibility(View.VISIBLE);

            buttonBuscarChip.setVisibility(View.GONE);
            editTextBusquedaChip.setVisibility(View.GONE);
            textViewSubChip.setVisibility(View.GONE);

        }else {
            textViewSubRut.setVisibility(View.GONE);
            textViewSubChip.setVisibility(View.GONE);
            editTextBusquedaRut.setVisibility(View.GONE);
            editTextBusquedaChip.setVisibility(View.GONE);
            buttonBuscarRut.setVisibility(View.GONE);
            buttonBuscarChip.setVisibility(View.GONE);
        }
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

}
