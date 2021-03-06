package com.example.thanasistratigakis.servicelog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

/**
 * Created by ThanasiStratigakis on 5/18/15.
 */
public class SignUpActivity extends Activity {

    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordReView;
    private EditText nameFirstView;
    private EditText nameLastView;
    private EditText yearOfGraduation;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);

        Parse.initialize(this, "bFlfAKQNEWDRvaNX7ikcJ0ZaSKbcKc6W98InH9Ie", "Idlc9KjBgLFvLakk2jUMBpUEfvUNZemux6tT10p9");

        setContentView(R.layout.activity_signup);

        // Set up the signUp form.
        usernameView = (EditText) findViewById(R.id.txtUsername);
        passwordView = (EditText) findViewById(R.id.txtPassword);
        passwordReView = (EditText) findViewById(R.id.txtPasswordRe);
        nameFirstView = (EditText) findViewById(R.id.txtNameFirst);
        nameLastView = (EditText) findViewById(R.id.txtNameLast);
        yearOfGraduation = (EditText) findViewById(R.id.txtYearOfGraduation);


        // Set up the submit button click handler
        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validate the Sign up data
                boolean validationError = false;
                StringBuilder validationErrorMessage = new StringBuilder("Please "); //Begin the validation error message
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append("enter a username");
                }
                if (isEmpty(passwordView)) {
                    if (validationError){
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter a password");
                }
                if (!isMatching(passwordView, passwordReView)) {
                    if (validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter the same password twice");
                }
                if (isEmpty(nameFirstView)){
                    if (validationError){
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter your First Name");
                }
                if (isEmpty(nameLastView)){
                    if (validationError){
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter your Last Name");
                }
                if (isEmpty(yearOfGraduation)){
                    if (validationError){
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter your Year Of Graduation");
                }

                validationErrorMessage.append(".");

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }


                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing up. Please wait.");
                dlg.show();

                // Set up a new Parse user
                ParseUser user = new ParseUser();
                user.setUsername(usernameView.getText().toString());
                user.setPassword(passwordView.getText().toString());
                user.put("FirstName", nameFirstView.getText().toString());
                user.put("LastName", nameLastView.getText().toString());
                user.put("GraduationYear", Integer.parseInt(yearOfGraduation.getText().toString()));
                ArrayList<ParseObject> organizations = new ArrayList<ParseObject>();
                user.put("CurrentOrganizations", organizations);
                user.put("UserType", "Student");

                        //ParseUser.getCurrentUser()

                // Call the Parse signup method
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            // Start an intent for the dispatch activity
                            Intent intent = new Intent(SignUpActivity.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    // checks if the text is > 0
    private boolean isEmpty (EditText etText) {
        // gets text, makes it a string, takes out spaces, gets length
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    // checks if two strings match (for password and re-password)
    private boolean isMatching (EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())){
            return true;
        } else {
            return false;
        }
    }














}
