package com.example.conor.softwareapp.mainActivties;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.conor.softwareapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import javax.mail.Session;
import com.example.conor.softwareapp.players.sendMail;

public class feedback extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private EditText email, subject, message;
    private Button sendMail;
    private String em, sub, msg;
    private FirebaseUser firebaseUser;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        email = (EditText) findViewById(R.id.emailFeedback);
        subject = (EditText) findViewById(R.id.subjectFeed);
        message = (EditText) findViewById(R.id.messageFeed);
        sendMail = (Button) findViewById(R.id.sendMail);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolAddf);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feedback");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (message.getText().length() > 500 || message.getText().length() < 0) {
                        message.setError("Content to Long Maximum 500 Characters");
                    }
                }
            }
        });

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em = email.getText().toString().trim();
                sub = subject.getText().toString().trim();
                msg = message.getText().toString().trim();

                if (em.isEmpty()) {
                    email.setError("Email Required");
                    return;
                }
                if (!em.equals(firebaseUser.getEmail())) {
                    email.setError("Email invalid, Must provide currently Logged in email");
                    return;
                }

                if (sub.isEmpty()) {
                    subject.setError("Subject Required");
                    return;
                }

                if (msg.isEmpty()) {
                    message.setError("Please provide a message");
                    return;
                } else {
                    boolean sent = sendFeedback();
                    email.setText("");
                    subject.setText("");
                    message.setText("");
                    if (!sent) {
                        Toast.makeText(feedback.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private Boolean sendFeedback() {
        em = email.getText().toString().trim();
        sub = subject.getText().toString().trim();
        msg = message.getText().toString().trim();
        sendMail sm = new sendMail(this, em, sub, msg);
        sm.execute();
        return true;
    }
}
