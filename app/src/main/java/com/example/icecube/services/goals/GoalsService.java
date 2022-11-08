package com.example.icecube.services.goals;

import com.example.icecube.DTOs.in.GoalIn;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class GoalsService {
    final AuthService authService;
    final FirebaseFirestore fs = FirebaseFirestore.getInstance();

    public GoalsService(AuthService authService) {
        this.authService = authService;
    }

    public void createGoal(GoalIn goal, OnCompleteListener<Void> onCompleteListener) {
        fs.collection(getGoalsPath())
                .add(goal)
                .addOnSuccessListener(reference -> onCompleteListener.onComplete(Tasks.forResult(null)));
    }

    String getGoalsPath() {
        return "users/" + authService.getLoggedUserId() + "/goals";
    }
}
