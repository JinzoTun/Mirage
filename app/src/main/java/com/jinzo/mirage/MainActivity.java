package com.jinzo.mirage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    // Arrays to store user login and password information
    private String[] passwords = new String[100];
    private String[] logins = new String[100];

    // Counter to keep track of the current position in the arrays
    private int userCount = 1;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // Initialize the first user as admin
        passwords[0] = "admin";
        logins[0] = "admin";

        // Initialize UI components
        Button loginButton = findViewById(R.id.bt_signup);
        TextView authTextView = findViewById(R.id.authTxt);
        EditText mailEditText = findViewById(R.id.mail);
        EditText pswEditText = findViewById(R.id.psw);

        // Get signup and login text views
        TextView headTextView = findViewById(R.id.headTxt);
        TextView signupTextView = findViewById(R.id.signupTxt);

        // Set click listener for the signup text view
        signupTextView.setOnClickListener(view -> {
            loginButton.setText(getString(R.string.signup));
            headTextView.setText(getString(R.string.signup));
            authTextView.setText(""); // Clear the authentication text after signup
        });

        // Set click listener for the login button
        loginButton.setOnClickListener(view -> {
            String email = mailEditText.getText().toString();
            String password = pswEditText.getText().toString();

            // Check the header text
            if (headTextView.getText().toString().equals(getString(R.string.signup))) {
                // User is signing up
                passwords[userCount] = password;
                logins[userCount] = email;
                loginButton.setText(getString(R.string.login));
                headTextView.setText(getString(R.string.login));
                mailEditText.setText("");
                pswEditText.setText("");

                // Change authTextView after signup
                authTextView.setText(getString(R.string.signup_done));
            } else {
                // User is trying to log in
                if (isValidLogin(email, password)) {
                    authTextView.setText(getString(R.string.auth_success) + getString(R.string.hello) + logins[i]);
                    System.out.println("Login successful");
                } else {
                    authTextView.setText(getString(R.string.auth_failed));
                    System.out.println("Login failed");
                }
            }

            // Increment user count for the next signup
            userCount++;
        });
    }

    // Helper method to check if the entered login and password match
    private boolean isValidLogin(String enteredLogin, String enteredPassword) {
        for (i = 0; i < userCount; i++) {
            if (enteredLogin.equals(logins[i]) && enteredPassword.equals(passwords[i])) {
                return true;
            }
        }
        return false;
    }
}
