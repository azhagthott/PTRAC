package com.zecovery.android.ptrac.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.ptrac.R;
import com.zecovery.android.ptrac.animation.LinearLayoutCustomAnimation;
import com.zecovery.android.ptrac.app.Mascota;
import com.zecovery.android.ptrac.com.CustomJsonRequest;
import com.zecovery.android.ptrac.com.PetDataRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.ptrac.activity.BaseActivity.LOG_TAG;
import static com.zecovery.android.ptrac.activity.BaseActivity.URL_REQUEST_CHIP;
import static com.zecovery.android.ptrac.activity.BaseActivity.URL_REQUEST_RUT;

public class FragmentBuscar extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener listener;

    private LinearLayout linearLayoutRut;
    private LinearLayout linearLayoutChip;
    private LinearLayout linearLayoutRutResult;
    private LinearLayout linearLayoutChipResult;

    private TextView textViewDescriptionRut;
    private TextView textViewDescriptionChip;
    private TextView textViewRutResult;
    private TextView textViewChipResult;

    private TextInputEditText editTextRut;
    private TextInputEditText editTextChip;

    private ImageView iconRut;
    private ImageView iconChip;

    private ToggleButton toggleButtonRut;
    private ToggleButton toggleButtonChip;

    private TextInputLayout textInputLayoutRut;
    private TextInputLayout textInputLayoutChip;

    private Button buttonBuscarRut;
    private Button buttonBuscarChip;

    private List<Mascota> list;

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

        View rootView = inflater.inflate(R.layout.fragment_buscar, container, false);

        final float TEXT_VIEW_HEIGHT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        final float TEXT_VIEW_HEIGHT_EXPANDED = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

        linearLayoutRut = (LinearLayout) rootView.findViewById(R.id.linearLayoutRut);
        linearLayoutChip = (LinearLayout) rootView.findViewById(R.id.linearLayoutChip);

        linearLayoutRut.setMinimumHeight((int)TEXT_VIEW_HEIGHT);
        linearLayoutChip.setMinimumHeight((int)TEXT_VIEW_HEIGHT);

        linearLayoutRutResult = (LinearLayout) rootView.findViewById(R.id.linearLayoutRutResult);
        linearLayoutChipResult = (LinearLayout) rootView.findViewById(R.id.linearLayoutChipResult);

        textViewDescriptionRut = (TextView) rootView.findViewById(R.id.textViewDescriptionRut);
        textViewDescriptionChip = (TextView) rootView.findViewById(R.id.textViewDescriptionChip);

        textViewRutResult = (TextView) rootView.findViewById(R.id.textViewRutResult);
        textViewChipResult = (TextView) rootView.findViewById(R.id.textViewChipResult);

        editTextRut = (TextInputEditText) rootView.findViewById(R.id.editTextRut);
        editTextChip = (TextInputEditText) rootView.findViewById(R.id.editTextChip);

        textInputLayoutRut = (TextInputLayout) rootView.findViewById(R.id.textInputLayoutRut);
        textInputLayoutChip = (TextInputLayout) rootView.findViewById(R.id.textInputLayoutChip);

        iconRut = (ImageView) rootView.findViewById(R.id.rotatingIconRut);
        iconChip = (ImageView) rootView.findViewById(R.id.rotatingIconChip);

        buttonBuscarRut = (Button) rootView.findViewById(R.id.buttonBuscarRut);
        buttonBuscarChip = (Button) rootView.findViewById(R.id.buttonBuscarChip);

        buttonBuscarRut.setOnClickListener(this);
        buttonBuscarChip.setOnClickListener(this);

        toggleButtonRut = (ToggleButton) rootView.findViewById(R.id.toggleButtonRut);
        toggleButtonChip = (ToggleButton) rootView.findViewById(R.id.toggleButtonChip);

        setChipUIVisible(false);
        setRutUIVisible(false);

        toggleButtonRut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                LinearLayoutCustomAnimation anim = new LinearLayoutCustomAnimation();

                if (isChecked) {
                    // animacion del linear layout y del icono (+)
                    anim.expand(linearLayoutRut, 300, (int) TEXT_VIEW_HEIGHT_EXPANDED);
                    anim.rotateImage(iconRut, 300);
                    setRutUIVisible(true);
                    setChipUIVisible(false);
                    toggleButtonChip.setChecked(false);

                } else {
                    anim.collapse(linearLayoutRut, 300, (int) TEXT_VIEW_HEIGHT);
                    anim.rotateImage(iconRut, 300);
                    setRutUIVisible(false);
                }
            }
        });

        toggleButtonChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                LinearLayoutCustomAnimation anim = new LinearLayoutCustomAnimation();

                if (isChecked) {
                    // animacion del linear layout y del icono (+)
                    anim.expand(linearLayoutChip, 300, (int) TEXT_VIEW_HEIGHT_EXPANDED);
                    anim.rotateImage(iconChip, 300);
                    setChipUIVisible(true);
                    setRutUIVisible(false);
                    toggleButtonRut.setChecked(false);

                } else {
                    anim.collapse(linearLayoutChip, 300, (int) TEXT_VIEW_HEIGHT);
                    anim.rotateImage(iconChip, 300);
                    setChipUIVisible(false);
                }
            }
        });

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

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonBuscarRut:
                Log.d(LOG_TAG, "onClick:  buscar por RUT");
                buscarPorRut();
                break;
            case R.id.buttonBuscarChip:
                Log.d(LOG_TAG, "onClick:  buscar por Chip");
                buscarPorChip();
                break;
        }

    }

    public interface OnFragmentInteractionListener {
    }

    private void buscarPorRut() {

        linearLayoutRutResult.setVisibility(View.GONE);
        final Context context = getActivity().getApplicationContext();

        try {

            if (editTextRut != null) {

                final String rutCompleto = editTextRut.getText().toString();

                String[] splitRut = rutCompleto.split("-");
                String rut = splitRut[0];

                CustomJsonRequest request = new CustomJsonRequest(
                        Request.Method.GET,
                        URL_REQUEST_RUT + rut,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                list = new ArrayList<>();

                                PetDataRequest dataRequest = new PetDataRequest(response);
                                list = dataRequest.getData();

                                if (!list.isEmpty()) {
                                    if (!list.get(0).getTelefonoCelular().equals("")) {
                                        linearLayoutRutResult.setVisibility(View.VISIBLE);
                                        textViewRutResult.setText(list.get(0).getTelefonoCelular());
                                    } else {
                                        Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(LOG_TAG, "onErrorResponse: " + error);
                                Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                            }
                        });
                request.setPriority(Request.Priority.HIGH);

                Volley.newRequestQueue(context).add(request);
            }
        } catch (Exception e) {
            FirebaseCrash.log("ERROR: " + e);
        }

    }

    private void buscarPorChip() {

        linearLayoutChipResult.setVisibility(View.GONE);
        final Context context = getActivity().getApplicationContext();

        try {
            if (editTextChip != null) {

                final String chip = editTextChip.getText().toString();

                CustomJsonRequest request = new CustomJsonRequest(
                        Request.Method.GET,
                        URL_REQUEST_CHIP + chip,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                list = new ArrayList<>();

                                PetDataRequest dataRequest = new PetDataRequest(response);
                                list = dataRequest.getData();

                                if (!list.isEmpty()) {
                                    if (!list.get(0).getTelefonoFijo().equals("")) {
                                        linearLayoutChipResult.setVisibility(View.VISIBLE);
                                        textViewChipResult.setText(list.get(0).getTelefonoFijo());
                                    } else {
                                        Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(LOG_TAG, "onErrorResponse: " + error);
                                Toast.makeText(context, R.string.no_data, Toast.LENGTH_SHORT).show();
                            }
                        });
                request.setPriority(Request.Priority.HIGH);

                Volley.newRequestQueue(context).add(request);
            }
        } catch (Exception e) {
            FirebaseCrash.log("error: " + e);
        }
    }

    private void setChipUIVisible(boolean visibility){

        if(visibility){
            textViewDescriptionChip.setVisibility(View.VISIBLE);
            textInputLayoutChip.setVisibility(View.VISIBLE);
            editTextChip.setVisibility(View.VISIBLE);
            buttonBuscarChip.setVisibility(View.VISIBLE);
        }else {
            textViewDescriptionChip.setVisibility(View.GONE);
            textInputLayoutChip.setVisibility(View.GONE);
            editTextChip.setVisibility(View.GONE);
            buttonBuscarChip.setVisibility(View.GONE);
            linearLayoutRutResult.setVisibility(View.GONE);
        }
    }

    private void setRutUIVisible(boolean visibility){

        if(visibility){
            textViewDescriptionRut.setVisibility(View.VISIBLE);
            textInputLayoutRut.setVisibility(View.VISIBLE);
            editTextRut.setVisibility(View.VISIBLE);
            buttonBuscarRut.setVisibility(View.VISIBLE);
        }else {
            textViewDescriptionRut.setVisibility(View.GONE);
            textInputLayoutRut.setVisibility(View.GONE);
            editTextRut.setVisibility(View.GONE);
            buttonBuscarRut.setVisibility(View.GONE);
            linearLayoutChipResult.setVisibility(View.GONE);
        }
    }
}
