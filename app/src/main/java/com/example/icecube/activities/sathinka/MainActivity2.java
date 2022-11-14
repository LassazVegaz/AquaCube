package com.example.icecube.activities.sathinka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.icecube.R;

public class MainActivity2 extends AppCompatActivity {
    String fruitlist[]={"Apple","Banana","Apricot","Orange","Water melon"};
    int fruitImages[]={R.drawable.as,R.drawable.banana,R.drawable.apricot,R.drawable.orange,R.drawable.water_melon };
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=(ListView)findViewById(R.id.listView) ;
        CustomBaseAdapter customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),fruitlist,fruitImages);

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>
                ( this,R.layout.activity_listview,R.id.text,fruitlist);
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)

            {
                Log.i("LIST_VIEW","Item is clicked @ position" +position);

            }
        });
    }
}