package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathanlarsen.pensionconsultmainpage.Logic.MailSender;
import com.example.jonathanlarsen.pensionconsultmainpage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact extends Fragment implements View.OnClickListener {


    private Button sendButton;
    private String name, mail, subject, comment;

    private EditText etName;
    private EditText etSenderMail;
    private EditText etComment;

    private ProgressDialog pdialog;

    // Initiate spinner variables
    Spinner mySpinner;
    ArrayAdapter<String> myAdapter;

    public Contact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);


        sendButton = (Button) view.findViewById(R.id.button2);

        mySpinner = (Spinner) view.findViewById(R.id.spinner);
        myAdapter = new ArrayAdapter<String>(com.example.jonathanlarsen.pensionconsultmainpage.fragments.Contact.this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.contact_subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        etName = view.findViewById(R.id.etName);
        etSenderMail = view.findViewById(R.id.etSenderMail);
        etComment = view.findViewById(R.id.etComment);


        startLayout();


        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == sendButton) {
            setVariables(view);

            if (TextUtils.isEmpty(name)) {
                etName.setError("Angiv venligst navn.");
                return;
            }
            if (TextUtils.isEmpty(mail)) {
                etSenderMail.setError("Angiv venligst din mail.");
                return;
            }

            if (!isValidEmail(mail)) {
                //Verify mail
                // Mail does not match the criteria
                etSenderMail.setError("Invalid e-mail adresse.");
                return;
            } else {
                // Mail is validated
                sendMessage(view);
            }
        }
    }


    public void startLayout() {
        sendButton.setOnClickListener(this);
    }

    // verify that String only contain letters
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private void setVariables(View view) {
        // Get variables from editable text
        // Name
        name = etName.getText().toString();
        // Sender email
        mail = etSenderMail.getText().toString();
        // Spinner
        subject = mySpinner.getSelectedItem().toString();
        // Comment
        comment = etComment.getText().toString();
    }

    private boolean isValidEmail(String mail) {

        if (TextUtils.isEmpty(mail)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
        }
    }

    public void sendMessage(View view) {
        MailSender mailsender = new MailSender(view.getContext(), name, mail, subject, comment);
//        pdialog = ProgressDialog.show(getContext(), "", "Sender henvendelsen...", true);

            etName.setText("");
            etSenderMail.setText("");
            etComment.setText("");
    }
}

