package com.example.learningcenterappandroid.tutor.settings_tutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.StudentMain;
import com.example.learningcenterappandroid.TutorMain;
import com.example.learningcenterappandroid.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SettingsTutor extends Fragment {

    private com.example.learningcenterapp.tutor.settings_tutor.SettingsTutorViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        dashboardViewModel =
                new ViewModelProvider(this).get(com.example.learningcenterapp.tutor.settings_tutor.SettingsTutorViewModel.class);
        View  rootView  = inflater.inflate(R.layout.tutor_settings, container, false);
        final TextView textView =  rootView .findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Button button = (Button)  rootView .findViewById(R.id.button);
        TextView test = (TextView) rootView.findViewById((R.id.textView3));
        button.setText("Sign out");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser red  = mAuth.getCurrentUser();
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);



            }
        });
        return rootView;
    }


}