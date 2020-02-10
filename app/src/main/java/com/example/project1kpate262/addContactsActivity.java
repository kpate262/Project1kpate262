package com.example.project1kpate262;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    }

    protected void doneButton(View v){
        String[] name = legalName.split(" ");
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
        }

        if(counter == 2){
            isNameLegal = true;
        }

        finish();
    }

    public View.OnClickListener doneListener = new View.OnClickListener() {
        // Called when down Button is selected
        @Override
        public void onClick(View v) {
            doneButton(v);
            Log.d("DoneButton", "Done button was clicked");
        }
    } ;

    protected void onPause() {
        super.onPause();
        if(isNameLegal){
            setResult(RESULT_OK);
            Log.d("onPause()", String.format("%s returned", RESULT_OK));
        }
    }
}
