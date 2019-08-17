package com.julio.camaraapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.julio.camaraapp.vistas.CameraFragment;
import com.julio.camaraapp.vistas.DatosFragment;
import com.julio.camaraapp.vistas.FotosFragment;

public class MainActivity extends AppCompatActivity implements FotosFragment.OnFragmentInteractionListener, DatosFragment.OnFragmentInteractionListener {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_camera:
                    setFragment(new CameraFragment());
                    return true;
                case R.id.navigation_fotos:
                    setFragment(new FotosFragment());
                    return true;
                case R.id.navigation_perfil:
                    setFragment(new DatosFragment());
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       setFragment(new CameraFragment());
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public  void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}
