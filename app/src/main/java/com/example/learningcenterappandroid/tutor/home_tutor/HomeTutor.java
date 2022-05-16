package com.example.learningcenterappandroid.tutor.home_tutor;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningcenterappandroid.AdminMain;
import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.StudentMain;
import com.example.learningcenterappandroid.TutorMain;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeTutor extends Fragment {

    private com.example.learningcenterappandroid.tutor.home_tutor.HomeTutorViewModel dashboardViewModel;

    private Button btn;
    private TextView textView;
    private TextView textViewName, sample;
    private FirebaseAuth mAuth;
    private String username;
    private Date date;
    private CountDownTimer waitTimer;
    private Map<String, Map<String, Boolean>> subjectMap;
    private List<String> mathlist;



    private FirebaseFirestore fStore;
    private DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                new ViewModelProvider(this).get(com.example.learningcenterappandroid.tutor.home_tutor.HomeTutorViewModel.class);
        View  rootView  = inflater.inflate(R.layout.tutor_home, container, false);

        List<String> mathlist = new ArrayList<String>();
        mathlist.add("calculus one");
        mathlist.add("pre calculus");
        mathlist.add("calculus two");
        this.mathlist = mathlist;

        // button to start the shift
        btn = (Button) rootView.findViewById(R.id.start);
       // btn.setVisibility(View.INVISIBLE);
        textViewName = (TextView) rootView.findViewById(R.id.name);
        sample = (TextView) rootView.findViewById(R.id.sample);

        // access the fireStore.
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        //Firebase user of logged in
        DocumentReference df = fStore.collection("users").document(user.getUid());



        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = documentSnapshot.getString("name");
                textViewName.setText("Welcome, " + name + " !");
                username = documentSnapshot.getString("Username");
                setDate();


            }
        });


         return rootView;
    }

    private void setDate() {
        DocumentReference tutors = fStore.collection("Tutors").document(username);
        tutors.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                
                Timestamp timestamp = (Timestamp) documentSnapshot.getData().get("shift");
                Timestamp timestamp2 = (Timestamp) documentSnapshot.getData().get("shiftend");
                /*Date date = timestamp.toDate();
                Date date2 = timestamp2.toDate();
                subjectMap = new HashMap<String, Map<String, Boolean>>();
                subjectMap = (Map<String, Map<String, Boolean>>) documentSnapshot.getData().get("Subject");
                Map<String, Boolean> Math = subjectMap.get("Math");
                for(String y : mathlist) {
                    if(!Math.get(y)) {
                        mathlist.remove(y);
                    }
                }

                for(String y : mathlist) {
                    
                }




                long x = date2.getTime()-date.getTime();
                if(checkdate(date)){
                    btn.setVisibility(View.VISIBLE);
                }


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase = FirebaseDatabase.getInstance().getReference();


                        //mDatabase.child("Tutors").child();

                        waitTimer = new CountDownTimer(x,100) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                sample.setText("DONE");
                            }
                        }.start();
                        if(waitTimer != null) {
                            waitTimer.cancel();
                            waitTimer = null;
                        }
                    }
                });

                 */
            }
        });


    }

    private boolean checkdate(Date d2) {
        Date d = new Date();
        long diffInMillies = d2.getTime() - d.getTime();
        if(diffInMillies <= 300000 && diffInMillies >= -300000) {
            return true;
        } else {
            return false;
        }

    }
}