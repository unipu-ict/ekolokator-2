package com.example.adrian.ekolokator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    TextView AddressText;
    TextView MailText;
    TextView NumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        AddressText = (TextView) findViewById(R.id.AdresaContacts);
        MailText = (TextView) findViewById(R.id.EMailContacts);
        NumberText = (TextView) findViewById(R.id.TelefonContacts);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerContact);
        List<String> list = new ArrayList<String>();
        for (GarbageType GType:GarbageType.values())
        {
            list.add(GType.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        spinner.setSelection(GarbageType.Noise.ordinal());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (GarbageType type : GarbageType.values()) {
                    if (type.ordinal() == position) {
                        UpdateSpinner(type);
                        return;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        UpdateSpinner(GarbageType.Noise);


    }

    void UpdateSpinner(GarbageType newType)
    {
        ContactData data = ContactInfo.GetContactInfo(newType);
        AddressText.setText(data.Address);
        MailText.setText(data.Mail);
        NumberText.setText(data.PhoneNumber);
    }

}
