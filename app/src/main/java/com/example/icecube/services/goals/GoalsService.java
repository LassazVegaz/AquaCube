package com.example.icecube.services.goals;

import com.example.icecube.models.Goal;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

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
                    put("active", goal.active);
                }})
                .addOnSuccessListener(unused -> onSuccessListener.onSuccess(goal));
    }

    public Query getGoalsQuery() {
        return fs.collection(getGoalsPath());
    }

    public void getGoalData(String id, OnSuccessListener<Goal> onSuccessListener) {
        getGoalData(id).addOnSuccessListener(d -> onSuccessListener.onSuccess(d.toObject(Goal.class)));
    }

    public Task<DocumentSnapshot> getGoalData(String id) {
        return fs.collection(getGoalsPath())
                .document(id)
                .get();
    }

    public void areGoalsEmpty(OnSuccessListener<Boolean> onSuccessListener) {
        fs.collection(getGoalsPath())
                .get()
                .addOnSuccessListener(snaps -> onSuccessListener.onSuccess(snaps.isEmpty()));
    }

    public void deleteGoal(String id, OnSuccessListener<Void> onSuccessListener) {
        fs.collection(getGoalsPath())
                .document(id)
                .delete()
                .addOnSuccessListener(onSuccessListener);
    }

    // private
    String getGoalsPath() {
        return "users/" + authService.getLoggedUserId() + "/goals";
    }
}
