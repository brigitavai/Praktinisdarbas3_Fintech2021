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

    Spinner spSelectionForDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        spSelectionForDelete = findViewById(R.id.spSelectionForDelete);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String>notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectionForDelete.setAdapter(listAdapter);
    }

    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> savedNotesList = sp.getStringSet("notes", new HashSet<String>());

        String selectedNote = spSelectionForDelete.getSelectedItem().toString();

        for (String savedNote : savedNotesList){
            if(savedNote.equalsIgnoreCase(selectedNote)){
                Toast.makeText(this, "Found it! =>" + savedNote, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, savedNote, Toast.LENGTH_SHORT).show();
            }
        }

        Set<String> newStrSet = new HashSet<String>();
        newStrSet.add(txtNote.getText().toString());
        newStrSet.addAll(savedNotesList);

        spEd.putStringSet("notes",newStrSet);
        spEd.apply();

        finish();
    }
}