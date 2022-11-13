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

public class A extends RecyclerView.Adapter<A.ViewHolder>{

    ArrayList<ComplaintsModel> list;
    Context context;



    public A(ArrayList<ComplaintsModel> list, Context context){
        this.list= list;
        this.context=context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull A.ViewHolder holder, int position) {

        ComplaintsModel model= list.get(position);

        holder.Area.setText(model.getArea());
        holder.Date.setText(model.getDate());
        holder.Issue.setText(model.getIssue());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView Area,Date,Issue;

        public ViewHolder(@NonNull View itemView){
            super(itemView);


            Area = (TextView) itemView.findViewById(R.id.area);
            Date =(TextView) itemView.findViewById(R.id.date);
            Issue=(TextView)itemView.findViewById(R.id.issue);



        }
    }

/*
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ComplaintsModel model) {
        holder.Area.setText(model.getArea());
        holder.Date.setText(model.getDate());
        holder.Issue.setText(model.getIssue());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, SingleComplaint.class);
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

/*
             Glide.with(holder.img.getContext())
                     .load(model.getSurl())
                     .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                     circleCrop()
                             .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                             .into(holder.img);

 }
 */

/*
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.complaints_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull A.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView Area,Date,Issue;

        public myViewHolder(@NonNull View itemView){
            super(itemView);


            Area = (TextView) itemView.findViewById(R.id.area);
            Date =(TextView) itemView.findViewById(R.id.date);
            Issue=(TextView)itemView.findViewById(R.id.issue);







        }


    }

 */

}

