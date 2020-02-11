package com.example.project1kpate262;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Button display;
    protected Button addContact;
    protected Intent contact;
    protected int resultingCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = null;

        setContentView(R.layout.activity_main);

        display = (Button) findViewById(R.id.displayContacts);
        addContact = (Button) findViewById(R.id.addContacts);

        display.setOnClickListener(displayListener);
        addContact.setOnClickListener(addContactListener);
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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text;
        Toast toast;

        if(contact != null && this.resultingCode == RESULT_OK){
            startActivity(contact);
        }
        else if(contact != null && this.resultingCode == RESULT_CANCELED){
            text = String.format("%s is not a valid legal name", String.format("%s", contact.getStringExtra(ContactsContract.Intents.Insert.NAME)));
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(contact == null){
            text = "Please add contact first";
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }


    protected void addContactButton(View view){
        Intent addingContact = new Intent(MainActivity.this, addContactsActivity.class);
        startActivityForResult(addingContact, 1);
    }


    protected void onActivityResult(int code, int result_code, Intent i) {
        super.onActivityResult(code, result_code, i);
        Log.i("MainActivity: onActivityResult ", "Returned result is: " + result_code);

        if(code == 1){
            if(result_code == RESULT_OK){
                this.resultingCode = RESULT_OK;
                contact = i;
                Log.d("MainActivity: onActivityResult ", String.format("%s", contact.getStringExtra(ContactsContract.Intents.Insert.NAME)));
            }
            else{
                this.resultingCode = RESULT_CANCELED;
                contact = i;
            }
        }

    }


    public void onResume(){
        super.onResume();
    }
}
