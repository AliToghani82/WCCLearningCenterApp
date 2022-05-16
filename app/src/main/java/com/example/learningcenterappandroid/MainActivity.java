package com.example.learningcenterappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public  class MainActivity extends AppCompatActivity {

        private FirebaseAuth mAuth;
        private FirebaseFirestore fStore;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            mAuth = FirebaseAuth.getInstance();
            Member member = new Member();
            String test = member.getFirstName();
            String tests = member.getLastName();
            member.setFirstName("hardev");

            setContentView(R.layout.activity_main);
            Button login, create;

            login = findViewById(R.id.login1);
            create = findViewById(R.id.create1);

            mAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();

            if (mAuth.getCurrentUser() == null) {
                // grabs the current user that is logged in
                FirebaseUser user = mAuth.getCurrentUser();
                // from knowing the current user, it will send them to the apporriate location such as tutor or admin
                DocumentReference df = fStore.collection("users").document(user.getUid());
                df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Toast.makeText(getApplicationContext(), documentSnapshot.getString("Status"),
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





            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent loginIntent = new Intent(getApplicationContext(), login.class);
                    startActivity(loginIntent);
                }
            });

            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent createIntent = new Intent(getApplicationContext(), createanaccount.class);
                    startActivity(createIntent);
                }
            });

        }
    }
