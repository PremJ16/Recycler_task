package com.example.recycler_task.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_task.R;
import com.example.recycler_task.helpers.FirebaseHelper;
import com.example.recycler_task.models.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {
    //constants
    public String user_URL;
    public static final String LOG_TAG="logtag";

    //ArrayLists
    public ArrayList<UserModel> userArrayList;

    //Views
    public RecyclerView recyclerView;

    //Adapters
    RecyclerView.Adapter adapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserData();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MainAdapter(userArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void getUserData() {

        FirebaseHelper firebaseHelper = new FirebaseHelper();

        user_URL = "/Users";
        firebaseHelper.readCollectionFromFirestore(user_URL,
                new FirebaseHelper.CollectionReadListener() {
                    @Override
                    public void handleDocuments(Task<QuerySnapshot> task) {
                        task.addOnSuccessListener(queryDocumentSnapshots -> {
                            if (task.isSuccessful()) {
                                userArrayList=new ArrayList<>();
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Map<String,Object> userData=documentSnapshot.getData();

                                    String name=userData.get(FirebaseHelper.DOC_NAME).toString();
                                    String gender=userData.get(FirebaseHelper.DOC_GENDER).toString();
                                    UserModel user=new UserModel(name,gender);
                                    userArrayList.add(user);
                                }

                            }
                        });
                    }

                    @Override
                    public void onDocumentsReadError(Exception error) {

                    }
                });

    }


}





