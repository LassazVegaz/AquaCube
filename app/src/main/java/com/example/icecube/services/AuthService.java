package com.example.icecube.services;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AuthService {
    final FirebaseAuth fa = FirebaseAuth.getInstance();
    final FirebaseFirestore ff = FirebaseFirestore.getInstance();
    final FirebaseDatabase fd = FirebaseDatabase.getInstance();

    public String getLoggedUserId() {
        return fa.getCurrentUser().getUid();
    }

    public void login(String email, String password, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        fa.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> onSuccessListener.onSuccess(null))
                .addOnFailureListener(onFailureListener);
    }

    public void register(String email, String password, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        fa.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(res -> {
                    ff.collection("users")
                            .document(res.getUser().getUid())
                            .set(new HashMap<String, Object>() {{
                                put("email", res.getUser().getEmail());
                            }})
                            .addOnSuccessListener(d -> {
                                fd.getReference()
                                        .child("users")
                                        .child(res.getUser().getUid())
                                        .child("email")
                                        .setValue(res.getUser().getEmail())
                                        .addOnSuccessListener(unused -> onSuccessListener.onSuccess(null));
                            });
                })
                .addOnFailureListener(onFailureListener);
    }
}
