package com.example.recycler_task.helpers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseHelper {
    //constants
    public static final String ROOT_COLLECTION_USERS="Users";
    public static final String DOC_NAME="name";
    public static final String DOC_GENDER="gender";
    public static final String SEPARATOR = "/";

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface CollectionReadListener {
        void handleDocuments(Task<QuerySnapshot> task);

        void onDocumentsReadError(Exception error);
    }

    public void readCollectionFromFirestore(String url, final CollectionReadListener readListener) {
        CollectionReference colRef = db.collection(url);
        colRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                readListener.handleDocuments(task);
            } else {
                readListener.onDocumentsReadError(task.getException());
            }
        });
    }

}
