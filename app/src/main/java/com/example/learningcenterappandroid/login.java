package com.example.learningcenterappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class login extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText passwordc, usernamec;
        Button login, create;

        mAuth = FirebaseAuth.getInstance();
       fStore = FirebaseFirestore.getInstance();

        passwordc = findViewById(R.id.password);
        usernamec = findViewById(R.id.username);

        login = findViewById(R.id.login);
        create = findViewById(R.id.create);

        FirebaseOptions options = null;

       if (mAuth.getCurrentUser() == null) {
            // grabs the current user that is logged in
            FirebaseUser user = mAuth.getCurrentUser();
            // from knowing the current user, it will send them to the apporriate location such as tutor or admin
            DocumentReference df = fStore.collection("users").document(user.getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Toast.makeText(login.this, documentSnapshot.getString("Status"),
                            Toast.LENGTH_SHORT).show();
                    if(documentSnapshot.getString("Status").equals("Admin")){
                        Intent loginIntentT = new Intent(getApplicationContext(), AdminMain.class);
                        loginIntentT.putExtra("username", user.getUid());
                        startActivity(loginIntentT);
                        finish();
                    } else if (documentSnapshot.getString("Status").equals("Tutor")) {
                        Intent loginIntentT = new Intent(getApplicationContext(), TutorMain.class);
                        startActivity(loginIntentT);
                    }
                }
            });
        }



      //  UserRecord.CreateRequest request = new UserRecord.CreateRequest()
       //         .setUid("some-uid")
       //         .setEmail("user@example.com")
       //         .setPhoneNumber("+11234567890");




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernamec.getText().toString();
                String password = passwordc.getText().toString();
                FirebaseApp.initializeApp(login.this);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");`
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    DocumentReference df = fStore.collection("users").document(user.getUid());
                                    df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            Toast.makeText(login.this, documentSnapshot.getString("Status"),
                                                    Toast.LENGTH_SHORT).show();
                                            if(documentSnapshot.getString("Status").equals("Admin")){
                                                Intent loginIntentT = new Intent(getApplicationContext(), AdminMain.class);
                                                loginIntentT.putExtra("username", user.getUid());
                                                startActivity(loginIntentT);
                                                finish();
                                            } else if (documentSnapshot.getString("Status").equals("Tutor")) {
                                                Intent loginIntentT = new Intent(getApplicationContext(), TutorMain.class);
                                                startActivity(loginIntentT);
                                            }
                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }


                        });
            }
        });


    }
}
