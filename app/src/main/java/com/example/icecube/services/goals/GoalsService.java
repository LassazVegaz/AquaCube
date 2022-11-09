package com.example.icecube.services.goals;

import androidx.annotation.NonNull;

import com.example.icecube.models.Goal;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GoalsService {
    final AuthService authService;
    final FirebaseFirestore fs = FirebaseFirestore.getInstance();

    public GoalsService(AuthService authService) {
        this.authService = authService;
    }

    public void createGoal(Goal goal, OnCompleteListener<Void> onCompleteListener) {
        fs.collection(getGoalsPath())
                .add(goal)
                .addOnSuccessListener(reference ->
                        reference.update("uid", reference.getId())
                                .addOnSuccessListener(unused ->
                                        onCompleteListener.onComplete(Tasks.forResult(null))));
    }

    public Query getGoalsQuery() {
        return fs.collection(getGoalsPath());
    }

    public void getGoalData(String id, OnSuccessListener<Goal> onSuccessListener) {
        fs.collection(getGoalsPath())
                .document(id)
                .get()
                .addOnSuccessListener(d -> onSuccessListener.onSuccess(d.toObject(Goal.class)));
    }

    // private
    String getGoalsPath() {
        return "users/" + authService.getLoggedUserId() + "/goals";
    }
}
