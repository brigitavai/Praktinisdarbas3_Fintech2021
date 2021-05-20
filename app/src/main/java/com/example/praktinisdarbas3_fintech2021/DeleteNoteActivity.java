package com.example.praktinisdarbas3_fintech2021;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

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

    public void onDeleteNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String selectedNote = spSelectionForDelete.getSelectedItem().toString();

        Set<String> deletedNotesList = sp.getStringSet("deleted notes", new HashSet<String>());
        deletedNotesList.add(txtNote.getText().toString());


        for (String deletedNote : deletedNotesList){
            if(deletedNote.equalsIgnoreCase(selectedNote)){
                Toast.makeText(this, deletedNote, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "left notes", Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }
}