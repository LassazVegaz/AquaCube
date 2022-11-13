package com.example.icecube.activities.mithun;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecube.R;

import java.util.ArrayList;

public class SocietiesAdapter extends RecyclerView.Adapter<SocietiesAdapter.ViewHolder>{


    ArrayList<SocietiesModel> list;
    Context context;


    public SocietiesAdapter(ArrayList<SocietiesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.societies_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SocietiesModel model=list.get(position);

        holder.area1.setText(model.getArea());
        holder.date1.setText(model.getDate());
        holder.issue1.setText(model.getIssue());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, SingleSociety.class);
                intent.putExtra("singlename",model.getName());
                intent.putExtra("singlearea",model.getArea());
                intent.putExtra("singleissue",model.getIssue());
                intent.putExtra("singledis",model.getDis());
                intent.putExtra("singlephone",model.getPhone());
                intent.putExtra("singledate",model.getDate());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView area1,date1,issue1;

        public ViewHolder(@NonNull View itemView){
            super(itemView);


            area1 = (TextView) itemView.findViewById(R.id.area1);
            date1 =(TextView) itemView.findViewById(R.id.date1);
            issue1=(TextView)itemView.findViewById(R.id.issue1);







        }


    }

}

