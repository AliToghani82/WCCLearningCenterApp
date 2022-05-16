package com.example.learningcenterappandroid.tutor.requests_tutor;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.StudentMain;
import com.example.learningcenterappandroid.TutorMain;

public class RequestsTutor extends Fragment {

    private com.example.learningcenterapp.tutor.requests_tutor.RequestTutorViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel =
                new ViewModelProvider(this).get(com.example.learningcenterapp.tutor.requests_tutor.RequestTutorViewModel.class);
        View rootView = inflater.inflate(R.layout.tutor_payroll, container, false);
        final TextView textView = rootView.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntentT = new Intent(getActivity(), TutorMain.class);
                startActivity(loginIntentT);

            }
        });
        return rootView;
    }

}