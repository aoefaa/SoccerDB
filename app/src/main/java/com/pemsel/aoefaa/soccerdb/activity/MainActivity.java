package com.pemsel.aoefaa.soccerdb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pemsel.aoefaa.soccerdb.R;
import com.pemsel.aoefaa.soccerdb.adapter.TeamAdapter;
import com.pemsel.aoefaa.soccerdb.data.Teams;
import com.pemsel.aoefaa.soccerdb.network.ApiClient;
import com.pemsel.aoefaa.soccerdb.network.ApiInterface;
import com.pemsel.aoefaa.soccerdb.ui.FavoritesFragment;
import com.pemsel.aoefaa.soccerdb.ui.MatchesFragment;
import com.pemsel.aoefaa.soccerdb.ui.TeamsFragment;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new TeamsFragment());
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment){

        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_favorites:
                fragment = new FavoritesFragment();
                break;

            case R.id.nav_matches:
                fragment = new MatchesFragment();
                break;

            case R.id.nav_teams:
                fragment = new TeamsFragment();
                break;
        }
        return loadFragment(fragment);
    }
}