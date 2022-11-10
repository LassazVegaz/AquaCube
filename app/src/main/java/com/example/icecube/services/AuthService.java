package com.example.icecube.services;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    final FirebaseAuth fa = FirebaseAuth.getInstance();

    public String getLoggedUserId() {
        return fa.getCurrentUser().getUid();
    }

    public boolean isLoggedIn() {
        return getLoggedUserId() != null;
    }

    public void login(String email, String password, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        fa.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> onSuccessListener.onSuccess(null))
                .addOnFailureListener(onFailureListener);
    }

    public void register(String email, String password, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        fa.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(res -> onSuccessListener.onSuccess(null))
                .addOnFailureListener(onFailureListener);
    }
}
