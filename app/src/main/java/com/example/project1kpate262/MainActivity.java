package com.example.project1kpate262;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
            displayButton() ;
        }
    } ;

    public View.OnClickListener addContactListener = new View.OnClickListener() {

        // Called when down Button is selected
        @Override
        public void onClick(View v) {
            displayButton() ;
        }
    } ;

    protected void displayButton(){

    }
}
