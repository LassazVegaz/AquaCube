package com.example.icecube.services.goals;

import com.example.icecube.models.Goal;
import com.example.icecube.models.Plan;
import com.example.icecube.models.Reminder;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.BinaryOperator;

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

    public void updatePlan(String id, Plan plan, OnSuccessListener<Plan> onSuccessListener) {
        fs.collection(getPlanPath())
                .document(id)
                .update(new HashMap<String, Object>() {{
                    put("days", plan.days);
                }})
                .addOnSuccessListener(unused -> onSuccessListener.onSuccess(plan));
    }

    public Query getPlansQuery() {
        return fs.collection(getPlanPath());
    }

    public void getPlan(String id, OnSuccessListener<Plan> onSuccessListener) {
        fs.collection(getPlanPath())
                .document(id)
                .get()
                .addOnSuccessListener(ref -> onSuccessListener.onSuccess(ref.toObject(Plan.class)));
    }

    public void getDisabledDays(OnSuccessListener<List<Boolean>> onSuccessListener) {
        fs.collection(getPlanPath())
                .get()
                .addOnSuccessListener(snapshots -> {
                    Boolean[] disabledDays = new Boolean[7];
                    Arrays.fill(disabledDays, false);
                    List<Plan> plans = snapshots.toObjects(Plan.class);

                    for (Plan p : plans) {
                        List<Boolean> days = p.days;

                        for (int i = 0; i < 7; i++)
                            disabledDays[i] = disabledDays[i] || days.get(i);
                    }

                    onSuccessListener.onSuccess(Arrays.asList(disabledDays));
                });
    }

    public Query getRemindersQuery(String planId) {
        return fs.collection(getPlanPath() + "/" + planId + "/reminders");
    }

    public void getRemainingCups(String planId, OnSuccessListener<Integer> onSuccessListener) {
        new Thread(() -> {
            try {

                QuerySnapshot snaps = Tasks.await(getRemindersQuery(planId).get());
                List<Reminder> rems = snaps.toObjects(Reminder.class);
                int totalCups = getCupsSum(rems);

                Goal g = Tasks.await(fs.collection("users/" + as.getLoggedUserId() + "/goals")
                        .document(goalId).get()).toObject(Goal.class);
                int goalCups = (int) Math.ceil(g.waterAmount * 1000 / g.potionSize);

                onSuccessListener.onSuccess(goalCups - totalCups);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                onSuccessListener.onSuccess(0);
            }
        }).start();
    }


    int getCupsSum(List<Reminder> rems) {
        int sum = 0;
        for (int i = 0; i < rems.size(); i++)
            sum += rems.get(i).noOfCups;
        return sum;
    }

    String getPlanPath() {
        return String.format("users/%s/goals/%s/plans", as.getLoggedUserId(), goalId);
    }
}
