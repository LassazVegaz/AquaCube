package com.example.icecube.adapters.goals;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;
import com.example.icecube.models.Plan;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PlansListAdapter extends FirestoreRecyclerAdapter<Plan, PlansListAdapter.PlanItemViewHolder> {
    private OnPlanClickListener onClickListener;

    public PlansListAdapter(@NonNull FirestoreRecyclerOptions<Plan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PlanItemViewHolder holder, int position, @NonNull Plan model) {
        holder.view.setOnClickListener(view -> {
            if (onClickListener != null) onClickListener.onClick(view, model.id);
        });

        for (int i = 0; i < 7; i++) {

            if (model.days.get(i))
                holder.makeBold(i);
        }
    }

    @NonNull
    @Override
    public PlanItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_plan_list_item, parent, false);
        return new PlanItemViewHolder(v);
    }

    public void setOnClickListener(OnPlanClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    static class PlanItemViewHolder extends RecyclerView.ViewHolder {
        private final Typeface boldFont;
        final View view;

        TextView[] tvs = new TextView[7];

        public PlanItemViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            boldFont = ResourcesCompat.getFont(itemView.getContext(), R.font.inter_bold);

            tvs[0] = itemView.findViewById(R.id.plan_li_mo);
            tvs[1] = itemView.findViewById(R.id.plan_li_tu);
            tvs[2] = itemView.findViewById(R.id.plan_li_we);
            tvs[3] = itemView.findViewById(R.id.plan_li_th);
            tvs[4] = itemView.findViewById(R.id.plan_li_fr);
            tvs[5] = itemView.findViewById(R.id.plan_li_sa);
            tvs[6] = itemView.findViewById(R.id.plan_li_su);
        }

        public void makeBold(int index) {
            tvs[index].setTypeface(boldFont);
        }

    }

}

