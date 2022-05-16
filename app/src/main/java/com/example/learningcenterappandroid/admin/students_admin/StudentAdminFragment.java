package com.example.learningcenterappandroid.admin.students_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.admin.active_admin.ActiveAdminViewModel;
import com.google.firebase.database.DatabaseReference;

public class StudentAdminFragment extends Fragment {

    private StudentAdminViewModel dashboardViewModel;
    private DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(StudentAdminViewModel.class);
        View  rootView  = inflater.inflate(R.layout.admin_students, container, false);



        return rootView;
    }


}