package com.example.contactproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.contactproject.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    MainFragment fragment = MainFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case R.id.show_all_contacts:
                fragment.getAllProducts();
                return true;
            case R.id.add_contact:
                fragment.addContact();
                return true;
            case R.id.find_contact:
                fragment.findContact();
                return true;
            case R.id.sort_a_z:
                fragment.sortAZ();
                return true;
            case R.id.sort_z_a:
                fragment.sortZA();
                return true;
            default:
                return true;
        }
    }
}
