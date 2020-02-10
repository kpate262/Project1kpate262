package com.example.project1kpate262;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected Button display;
    protected Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (Button) findViewById(R.id.displayContacts);
        addContact = (Button) findViewById(R.id.addContacts);

        display.setOnClickListener(displayListener) ;
        addContact.setOnClickListener(addContactListener) ;
    }



    public View.OnClickListener displayListener = new View.OnClickListener() {

        // Called when down Button is selected
        @Override
        public void onClick(View v) {
            displayButton(v);
            Log.d("DisplayButton", "Display button was clicked");
        }
    } ;

    public View.OnClickListener addContactListener = new View.OnClickListener() {

        // Called when down Button is selected
        @Override
        public void onClick(View v) {
            addContactButton(v);
            Log.d("addContactButton", "Add contact button was clicked");
        }
    } ;

    protected void displayButton(View view){

    }

    protected void addContactButton(View view){
        Intent addingContact = new Intent(MainActivity.this, addContactsActivity.class);
        startActivityForResult(addingContact, 1);
    }

    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);
        Log.i("MainActivity ", "Returned result is: " + result_code) ;
    }
}
