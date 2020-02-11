package com.example.project1kpate262;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addContactsActivity extends MainActivity {
    private String legalName;
    protected Button doneButton;
    protected EditText legalNameText;
    private String firstName;
    private String lastName;
    private boolean isNameLegal;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);

        doneButton = (Button) findViewById(R.id.returnButton);
        legalNameText = (EditText) findViewById(R.id.legalName);


        doneButton.setOnClickListener(doneListener);

        legalNameText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

                legalName = legalNameText.getText().toString();

                Log.d("Editable text", String.format("Legal name entered %s", legalName));

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        legalNameText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(keyCode == event.KEYCODE_ENTER){
                    Log.d("DoneButton", "Done button was clicked");
                    doneButton(v);
                    return true;
                }
                return false;
            }
        });

    }

    protected void doneButton(View v){
        String[] name = this.legalName.split(" ");
        int counter = 0;

        for(String k: name){
            if(k.length() > 0 && counter == 0){
                counter++;
                this.firstName = k;
            }
            else if (k.length() > 0 && counter == 1){
                counter++;
                this.lastName = k;
            }
            else if(k.length() > 0 && counter == 2){
                counter++;
                isNameLegal = false;
            }
        }

        if(counter == 2){
            isNameLegal = true;
        }

        Intent contact = new Intent(ContactsContract.Intents.Insert.ACTION, ContactsContract.Contacts.CONTENT_LOOKUP_URI);
        contact.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            contact.putExtras(extras);
        }

        if(isNameLegal){
            contact.putExtra(ContactsContract.Intents.Insert.NAME, String.format("%s %s", this.firstName, this.lastName));
            setResult(RESULT_OK, contact);
            Log.d("onPause()", String.format("%s returned", RESULT_OK));
        }
        else{
            contact.putExtra(ContactsContract.Intents.Insert.NAME, String.format("%s", this.legalName));
            setResult(RESULT_CANCELED, contact);
            Log.d("onPause()", String.format("%s returned", RESULT_CANCELED));
        }
        finish();
    }

    public View.OnClickListener doneListener = new View.OnClickListener() {
        // Called when down Button is selected
        @Override
        public void onClick(View v) {
            Log.d("DoneButton", "Done button was clicked");
            doneButton(v);
        }
    } ;

    protected void onPause() {
        super.onPause();
    }
}
