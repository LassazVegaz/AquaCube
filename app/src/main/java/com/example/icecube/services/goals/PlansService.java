package com.example.icecube.services.goals;

import com.example.icecube.models.Plan;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PlansService {
    final String goalId;
    final FirebaseFirestore fs = FirebaseFirestore.getInstance();
    final AuthService as;

    public PlansService(String goalId, AuthService as) {
        this.goalId = goalId;
        this.as = as;
    }

    public void createPlan(Plan plan, OnSuccessListener<Plan> onSuccessListener) {
        fs.collection(getPlanPath())
                .add(plan)
                .addOnSuccessListener(ref -> {
                    plan.id = ref.getId();
                    ref.update("id", ref.getId())
                            .addOnSuccessListener(unused -> onSuccessListener.onSuccess(plan));
                });
    }

    public Query getPlansQuery() {
        return fs.collection(getPlanPath());
    }

    private String getPlanPath() {
        return String.format("users/%s/goals/%s/plans", as.getLoggedUserId(), goalId);
    }
}
