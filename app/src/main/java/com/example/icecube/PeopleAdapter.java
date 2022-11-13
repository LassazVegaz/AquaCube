package com.example.icecube;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder>{


    ArrayList<PeopleModel> list;
    Context context;


    public PeopleAdapter(ArrayList<PeopleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.people_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PeopleModel model=list.get(position);

        holder.area1.setText(model.getArea());
        holder.date1.setText(model.getDate());
        holder.issue1.setText(model.getIssue());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, SinglePeople.class);
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

