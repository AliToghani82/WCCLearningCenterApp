package com.example.learningcenterappandroid.admin.active_admin;

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

import com.example.learningcenterappandroid.AdminMain;
import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.students.home_student.HomeStudentViewModel;
import com.google.firebase.database.DatabaseReference;

public class ActiveAdminFragment extends Fragment {

    private ActiveAdminViewModel dashboardViewModel;
    private DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(ActiveAdminViewModel.class);
        View  rootView  = inflater.inflate(R.layout.admin_actives, container, false);



        return rootView;
    }


}