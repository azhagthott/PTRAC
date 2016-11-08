package com.zecovery.android.ptrac.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.zecovery.android.ptrac.R;
import com.zecovery.android.ptrac.fragment.FragmentBuscar;
import com.zecovery.android.ptrac.fragment.FragmentConsejos;
import com.zecovery.android.ptrac.fragment.FragmentEst;
import com.zecovery.android.ptrac.fragment.FragmentInfo;
import com.zecovery.android.ptrac.fragment.FragmentInicio;

public class MainActivity extends AppCompatActivity implements
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

                switch (item.getItemId()) {
                    case R.id.action_inicio:
                        fragment = FragmentInicio.newInstance();
                        break;
                    case R.id.action_buscar:
                        fragment = FragmentBuscar.newInstance();
                        break;
                    case R.id.action_consejos:
                        fragment = FragmentConsejos.newInstance();
                        break;
                    case R.id.action_esteriliza:
                        fragment = FragmentEst.newInstance();
                        break;
                    case R.id.action_info:
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
