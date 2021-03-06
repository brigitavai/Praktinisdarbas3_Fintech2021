package com.example.praktinisdarbas3_fintech2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> savedNotesList = sp.getStringSet("notes", new HashSet<String>());

        Set<String> newStrSet = new HashSet<String>();
        newStrSet.add(txtNote.getText().toString());
        newStrSet.addAll(savedNotesList);

        spEd.putStringSet("notes",newStrSet);
        spEd.apply();

        finish();
    }
}