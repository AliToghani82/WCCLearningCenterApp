package com.example.learningcenterappandroid.admin.announcments_admin;

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

public class AnnouncmentsAdminFragment extends Fragment {

    private AnnouncmentsAdminViewModel dashboardViewModel;
    private DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(AnnouncmentsAdminViewModel.class);
        View  rootView  = inflater.inflate(R.layout.admin_announcments, container, false);



        return rootView;
    }


}