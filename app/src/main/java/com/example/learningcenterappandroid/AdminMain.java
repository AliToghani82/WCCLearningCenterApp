package com.example.learningcenterappandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminMain extends AppCompatActivity {
  //  private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminmain);
        BottomNavigationView navView = findViewById(R.id.nav_views);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.admin_actives, R.id.admin_announcments, R.id.admin_home,R.id.admin_students,R.id.admin_tutor)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragments);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
       // mDatabase = FirebaseDatabase.getInstance().getReference();
      //  mDatabase.child("adminContent").child("blue").setValue("red");
//
    }

}
