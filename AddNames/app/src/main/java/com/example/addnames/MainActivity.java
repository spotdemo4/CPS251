package com.example.addnames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Fragment listViewFragment;

    public void addToList(View view) {
        EditText editText = findViewById(R.id.editText);
        TextView errorText = findViewById(R.id.errorText);

        String name = editText.getText().toString();

        if (name.equals("")){
            errorText.setVisibility(View.VISIBLE);
        } else {
            errorText.setVisibility(View.INVISIBLE);
            ((ListViewFragment) listViewFragment).addListItem(name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewFragment = getSupportFragmentManager().findFragmentById(R.id.listViewFrag);

        if (savedInstanceState != null){
            listViewFragment = getSupportFragmentManager().getFragment(savedInstanceState, "listFragment");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "listFragment", listViewFragment);
    }
}