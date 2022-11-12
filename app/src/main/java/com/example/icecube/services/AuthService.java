package com.example.icecube.services;

import com.example.icecube.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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

    public void register(User user, String password, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        fa.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener(res -> {
                    user.id = res.getUser().getUid();
                    createAccountsInDB(user, onSuccessListener);
                })
                .addOnFailureListener(onFailureListener);
    }

    public void logout() {
        fa.signOut();
    }

    public boolean isLoggedIn() {
        return fa.getCurrentUser() != null;
    }


    private void createAccountsInDB(User user, OnSuccessListener<Void> onSuccessListener) {
        Map<String, Object> map = userToMap(user);
        ff.collection("users")
                .document(user.id)
                .set(map)
                .addOnSuccessListener(d -> {
                    fd.getReference()
                            .child("users")
                            .child(user.id)
                            .setValue(map)
                            .addOnSuccessListener(onSuccessListener);
                });
    }

    Map<String, Object> userToMap(User u) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("email", u.email);
            put("id", u.id);
            put("name", u.name);
        }};
        return map;
    }
}
