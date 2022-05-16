package com.example.learningcenterappandroid.admin.tutors_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningcenterappandroid.Member;
import com.example.learningcenterappandroid.R;
import com.example.learningcenterappandroid.Admin_TutorPop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TutorsAdminFragment extends Fragment {

    private TutorsAdminViewModel dashboardViewModel;
    private DatabaseReference mDatabase;
    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    PopupWindow popUp;
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(TutorsAdminViewModel.class);
        View  rootView  = inflater.inflate(R.layout.admin_tutors, container, false);


        popUp = new PopupWindow(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());
        LinearLayout mainLayout = new LinearLayout(getActivity());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        DocumentReference df = fStore.collection("users").document(user.getUid());
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> red = document.getData();
                        String status = (String) red.get("Status");
                        if(status.equals("Admin")){
                            Member member = new Member();
                            member.setFirstName("Ali");
                            fStore.collection("Tutors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<String> list1 = new ArrayList<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            list1.add(document.getId());
                                        }
                                        ListView listView = (ListView) rootView.findViewById(R.id.list);
                                        ArrayAdapter<String> listViewAdaptor = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list1);
                                        listView.setAdapter(listViewAdaptor);
                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent i = new Intent(getActivity(), Admin_TutorPop.class);
                                                i.putExtra("Name", list1.get(position));
                                                startActivity(i);
                                            }
                                        });

                                    }
                                }
                            });

                        }
                    }
                }

            }
        });
        return rootView;
    }
}