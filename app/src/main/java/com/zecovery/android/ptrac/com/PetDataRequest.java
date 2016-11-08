package com.zecovery.android.ptrac.com;

import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.ptrac.activity.BaseActivity;
import com.zecovery.android.ptrac.app.Mascota;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 24-08-16.
 */

public class PetDataRequest extends BaseActivity {

    private JSONObject jsonObject;

    public PetDataRequest(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public List<Mascota> getData() {

        List<Mascota> list = new ArrayList<>();

        try {
            String status = jsonObject.getString(RESPONSE_STATUS);

            if (status.equals(RESPONSE_STATUS_OK)) {
                JSONArray response = jsonObject.getJSONArray("response");


                for (int i = 0; i <response.length() ; i++) {

                    Log.d(LOG_TAG, "response.length: "  + response.length());

                    Mascota mascota = new Mascota();

                    JSONObject object = response.getJSONObject(i);

                    int numeroFolio = object.getInt("N_FOLIO");

                    mascota.setNumeroFolio(numeroFolio);
                    mascota.setNumeroChip(object.getString("CHIP"));
                    mascota.setNombre(object.getString("NOMBRE"));
                    mascota.setEspecie(object.getString("ESPECIE"));
                    mascota.setSexo(object.getString("SEXO"));
                    mascota.setColor(object.getString("COLOR"));
                    mascota.setRaza(object.getString("RAZA"));
                    mascota.setFechaNacimiento(object.getString("FECHA"));
                    mascota.setTipoTenencia(object.getString("TIPO_TENENCIA"));
                    mascota.setResponsable(object.getString("RESPONSABLE"));
                    mascota.setRut(object.getString("RUT"));
                    mascota.setDireccion(object.getString("COLOR"));
                    mascota.setComuna(object.getString("COMUNA"));
                    mascota.setTelefonoFijo(object.getString("TELEFONO"));
                    mascota.setTelefonoCelular(object.getString("MOVIL"));
                    mascota.setCorreoElectronico(object.getString("EMAIL"));

                    list.add(mascota);
                }

            } else {
                Log.d(LOG_TAG, "NOT FOUND - 404");
                FirebaseCrash.log("NOT FOUND - 404");
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, "getDataFromRut: " + e);
            FirebaseCrash.log("getDataFromRut" + e);
            FirebaseCrash.report(e);
        }
        return list;
    }

    private String validaResultado(JSONObject jsonObject, String key){

        String resultado="";

        try{
            if(jsonObject.isNull(key)){
                resultado = null;
            }else {
                resultado = jsonObject.getString(key);
            }

        }catch(Exception e){
            Log.d(LOG_TAG, "Exception: " + e);
        }
        return resultado;
    }
}

