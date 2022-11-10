package com.example.icecube.services.goals;

import com.example.icecube.models.Reminder;
import com.example.icecube.services.AuthService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

public class RemindersService {
    final FirebaseFirestore fs = FirebaseFirestore.getInstance();
    final AuthService as;
    final String goalId, planId;

    public RemindersService(AuthService as, String goalId, String planId) {
        this.as = as;
        this.goalId = goalId;
        this.planId = planId;
    }

    public String getRemindersPath() {
        return String.format("users/%s/goals/%s/plans/%s/reminders", as.getLoggedUserId(), goalId, planId);
    }

    public void createReminder(Reminder r, OnSuccessListener<Reminder> onSuccessListener) {
        fs.collection(getRemindersPath())
                .add(r)
                .addOnSuccessListener(ref -> {
                    r.id = ref.getId();
                    ref.update("id", ref.getId())
                            .addOnSuccessListener(v -> onSuccessListener.onSuccess(r));
                });
    }

    public void updateReminder(String id, Reminder r, OnSuccessListener<Reminder> onSuccessListener) {
        fs.collection(getRemindersPath())
                .document(id)
                .update(new HashMap<String, Object>() {{
                    put("enableSound", r.enableSound);
                    put("enableVibration", r.enableVibration);
                    put("noOfCups", r.noOfCups);
                    put("time", r.time);
                }})
                .addOnSuccessListener(v -> onSuccessListener.onSuccess(r));
    }

    public void getReminder(String id, OnSuccessListener<Reminder> onSuccessListener) {
        fs.collection(getRemindersPath())
                .document(id)
                .get()
                .addOnSuccessListener(d ->
                        onSuccessListener.onSuccess(d.toObject(Reminder.class)));
    }

    public Query getRemindersQuery() {
        return fs.collection(getRemindersPath());
    }
}
