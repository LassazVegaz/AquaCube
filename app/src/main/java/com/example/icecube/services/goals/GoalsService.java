package com.example.icecube.services.goals;

import androidx.annotation.NonNull;

import com.example.icecube.models.Goal;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class GoalsService {
    final AuthService authService;
    final FirebaseFirestore fs = FirebaseFirestore.getInstance();

    public GoalsService(AuthService authService) {
        this.authService = authService;
    }


    public void createGoal(Goal goal, OnSuccessListener<Goal> onSuccessListener) {
        fs.collection(getGoalsPath())
                .add(goal)
                .addOnSuccessListener(reference ->
                        reference.update("id", reference.getId())
                                .addOnSuccessListener(unused -> {
                                    goal.id = reference.getId();
                                    onSuccessListener.onSuccess(goal);
                                }));
    }

    public void updateGoal(String id, Goal goal, OnSuccessListener<Goal> onSuccessListener) {
        goal.id = id;
        fs.collection(getGoalsPath())
                .document(id)
                .update(new HashMap<String, Object>() {{
                    put("name", goal.name);
                    put("waterAmount", goal.waterAmount);
                    put("potionSize", goal.potionSize);
                }})
                .addOnSuccessListener(unused -> onSuccessListener.onSuccess(goal));
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

    public void areGoalsEmpty(OnSuccessListener<Boolean> onSuccessListener) {
        fs.collection(getGoalsPath())
                .get()
                .addOnSuccessListener(snaps -> onSuccessListener.onSuccess(snaps.isEmpty()));
    }

    // private
    String getGoalsPath() {
        return "users/" + authService.getLoggedUserId() + "/goals";
    }
}
