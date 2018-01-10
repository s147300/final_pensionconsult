package com.example.jonathanlarsen.pensionconsultmainpage.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jonathanlarsen.pensionconsultmainpage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact extends Fragment implements View.OnClickListener {

    private Button sendButton;
    private String name, mail, subject, comment;

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

//        startLayout();


        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == sendButton) {

            setVariables(view);
            if (!name.equals("")) {
                if (!mail.equals("")) {
                    //tjek mail
                    try {
                        String[] mailPart = mail.split("@");
                        if (mailPart[1].equalsIgnoreCase("hotmail.com") || mailPart[1].equalsIgnoreCase("gmail.com") ||
                                mailPart[1].equalsIgnoreCase("live.dk") || mailPart[1].equalsIgnoreCase("yahoo.com")) {

                            sendMessage(view);
                        }
                    } catch (Exception e) {
                        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                                .create();
                        dialog.setCancelable(false);
                        dialog.setTitle("Invalid Mail!" + name);
                        dialog.setMessage("You entered a false e-mail! \nPlease enter your e-mail");
                        dialog.setButton(view.getContext().getString(R.string.Ok_text), new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                            .create();
                    dialog.setCancelable(false);
                    dialog.setTitle("Missing Mail!");
                    dialog.setMessage("You didn't enter a mail");
                    dialog.setButton(view.getContext().getString(R.string.Ok_text), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            } else {
                AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                        .create();
                dialog.setCancelable(false);
                dialog.setTitle("Missing Name!");
                dialog.setMessage("You didn't enter a name");
                dialog.setButton(view.getContext().getString(R.string.Ok_text), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }
    }


    public void startLayout () {
        sendButton.setOnClickListener(this);
    }

    // verify that String only contain letters
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public void setVariables (View view) {
        // Get variables from editable text
        // Name
        EditText etName = (EditText) view.findViewById(R.id.etName);
        name = etName.getText().toString();
        // Sender email
        EditText etSubject = (EditText) view.findViewById(R.id.etMail);
        mail = etSubject.getText().toString();
        // Spinner
        subject = mySpinner.getSelectedItem().toString();
        // Comment
        EditText etComment = (EditText) view.findViewById(R.id.etComment);
        comment = etComment.getText().toString();
    }

    public void sendMessage(View view) {
        /* Create the Intent */
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"dannyjoensson@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject:" + subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Comment: " + comment + "From: " + name + " Mail: " + mail);

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
