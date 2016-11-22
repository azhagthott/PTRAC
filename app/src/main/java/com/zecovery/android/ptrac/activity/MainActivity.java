package com.zecovery.android.ptrac.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.zecovery.android.ptrac.R;
import com.zecovery.android.ptrac.fragment.FragmentBuscar;
import com.zecovery.android.ptrac.fragment.FragmentConsejos;
import com.zecovery.android.ptrac.fragment.FragmentEst;
import com.zecovery.android.ptrac.fragment.FragmentInfo;
import com.zecovery.android.ptrac.fragment.FragmentInicio;

public class MainActivity extends BaseActivity implements
        FragmentInicio.OnFragmentInteractionListener,
        FragmentBuscar.OnFragmentInteractionListener,
        FragmentConsejos.OnFragmentInteractionListener,
        FragmentEst.OnFragmentInteractionListener,
        FragmentInfo.OnFragmentInteractionListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, FragmentInicio.newInstance());
        fragmentTransaction.commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                Bundle bundle = new Bundle();
                int id = item.getItemId();

                switch (id) {
                    case R.id.action_inicio:
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.bottom_menu_inicio));
                        fragment = FragmentInicio.newInstance();
                        break;
                    case R.id.action_buscar:
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.bottom_menu_buscar));
                        fragment = FragmentBuscar.newInstance();
                        break;
                    case R.id.action_consejos:
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.bottom_menu_consejos));
                        fragment = FragmentConsejos.newInstance();
                        break;
                    case R.id.action_esteriliza:
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.bottom_menu_est));
                        fragment = FragmentEst.newInstance();
                        break;
                    case R.id.action_info:
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.bottom_menu_mas_info));
                        fragment = FragmentInfo.newInstance();
                        break;
                }
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

    }
}
