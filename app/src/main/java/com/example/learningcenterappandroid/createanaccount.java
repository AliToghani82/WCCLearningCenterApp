package com.example.learningcenterappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class createanaccount extends AppCompatActivity {
    private String option;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        TextView confrimtext;
        EditText email, password, name, SID,passwordcheck, confrimc; // The user input
        Button create; // the create button
        Switch switchc;
        Member member; // the member class file

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        confrimtext = findViewById(R.id.confrimtext);
        switchc = findViewById(R.id.switch1);
        email = findViewById(R.id.email);
        confrimc = findViewById(R.id.confirmc);
        password = findViewById(R.id.passwordc);
        passwordcheck = findViewById(R.id.passwordcheckc);
        name = findViewById(R.id.name);
        SID = findViewById(R.id.sid);
        create = findViewById(R.id.createc);
        confrimtext.setVisibility(View.INVISIBLE);
        confrimc.setVisibility(View.INVISIBLE);
        //atoghani1623@student.whatcom.edu
        member = new Member();
        boolean test = false;

        switchc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchc.isChecked()){
                    confrimtext.setVisibility(View.VISIBLE);
                    confrimc.setVisibility(View.VISIBLE);
                } else {
                    confrimtext.setVisibility(View.INVISIBLE);
                    confrimc.setVisibility(View.INVISIBLE);
                }
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String Stat = "";
                      boolean acc= false;
                      if(confrimc.getText().toString().trim().equals("")) {
                          acc = true;
                          Stat = "Students";
                      } else if (confrimc.getText().toString().trim().equals("test")) {
                          acc = true;
                          Stat = "Tutor";
                      }

                      Toast.makeText(createanaccount.this, Stat, Toast.LENGTH_SHORT).show();


                      if(acc) {
                          String fStat = Stat + "";
                          DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("member");
                          String fname, sid, passwords, emails;
                          fname = name.getText().toString().trim();
                          passwords = password.getText().toString().trim();
                          sid = SID.getText().toString().trim();
                          emails = email.getText().toString().trim();
                          Boolean check = true;
                          // connects to auth of firebase and creates a new user based on the given email and password.
                          mAuth.createUserWithEmailAndPassword(emails, passwords)
                                  .addOnCompleteListener(createanaccount.this, new OnCompleteListener<AuthResult>() {
                                      @Override
                                      public void onComplete(@NonNull Task<AuthResult> task) {
                                          if (task.isSuccessful()) {
                                              FirebaseUser user = mAuth.getCurrentUser();
                                              // Sign in success, update UI with the signed-in user's information
                                              Log.d(TAG, "createUserWithEmail:success");
                                              FirebaseUser users = mAuth.getCurrentUser();
                                              DocumentReference df = fStore.collection("users").document(users.getUid());
                                              FirebaseAuth.getInstance();
                                              Map<String, Object> userinfo = new HashMap<>();
                                              userinfo.put("name", fname);
                                              userinfo.put("SID", sid);
                                              userinfo.put("Status", fStat);
                                              df.set(userinfo);

                                              Map<String, Object> claims = new HashMap<>();
                                              claims.put("admin", true);


                                              Toast.makeText(createanaccount.this, "Authentication Worked!!.",
                                                      Toast.LENGTH_SHORT).show();
                                              if(fStat.equals("Tutor")) {
                                                  String email = emails.substring(emails.indexOf("@"));
                                                  int sizeemail = email.length();
                                                  String username = email.substring(sizeemail-3);

                                                  DocumentReference dfs = fStore.collection("Tutors").document("username");
                                                  FirebaseAuth.getInstance();
                                                  FirebaseAuth.getInstance();
                                                  Map<String, Object> userinfos = new HashMap<>();
                                                  userinfo.put("name", fname);
                                                  userinfo.put("SID", sid);
                                                  userinfo.put("Status", fStat);
                                                  df.set(userinfos);
                                              }

                                              if (mAuth.getCurrentUser() != null) {
                                                  // grabs the current user that is logged in
                                                  FirebaseUser logged = mAuth.getCurrentUser();
                                                  // from knowing the current user, it will send them to the apporriate location such as tutor or admin
                                                  DocumentReference loggeddf = fStore.collection("users").document(user.getUid());
                                                  df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                      @Override
                                                      public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                          Toast.makeText(getApplicationContext(), documentSnapshot.getString("Status"),
                                                                  Toast.LENGTH_SHORT).show();
                                                          if (documentSnapshot.getString("Status").equals("Admin")) {
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
                                          } else {
                                              // If sign in fails, display a message to the user.
                                              Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                              Toast.makeText(createanaccount.this, "Authentication failed.",
                                                      Toast.LENGTH_SHORT).show();
                                          }

                                      }
                                  });
                      }
                  }
         });
    }

}
                /*
                if(emails.contains("@")) {
                   int testuseri = emails.indexOf("@");
                   testuser = emails.substring(0, testuseri);
                }

                if(!usernames.equals(testuser)) {
                   check = false;
                   Toast.makeText(createanaccount.this, "Make sure you have entered a valid username or WCC Email", Toast.LENGTH_SHORT).show();
                } else if(!emails.contains("whatcom.edu")) {
                    check = false;
                    Toast.makeText(createanaccount.this, "Make sure you have entered a valid WCC Email", Toast.LENGTH_SHORT).show();
                } else if (passwords.length() < 6 ){
                    check = false;
                    Toast.makeText(createanaccount.this, "Please enter a password greater then 6", Toast.LENGTH_SHORT).show();
                } else {
                    member.setUsername(usernames);
                    member.setPassword(passwords);
                    member.setFirstName(firstnames);
                    member.setLastName(lastnames);
                    member.setEmail(emails);
                    member.setType(option);
                    reff.child(usernames).setValue(member);
                    Toast.makeText(createanaccount.this, option, Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(getApplicationContext(), Created.class);
                    startActivity(loginIntent);
                }

            }
        });

//      spinner object here for the student, admin tutor drop down.
//        Spinner mySpinner = findViewById(R.id.action_bar_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.createdrop, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(adapter);
//        mySpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        option = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
     */