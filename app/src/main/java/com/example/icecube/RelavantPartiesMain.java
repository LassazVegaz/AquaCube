package com.example.icecube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;



import com.example.icecube.databinding.ActivityRelavantPartiesMainBinding;

import java.util.ArrayList;

public class RelavantPartiesMain extends AppCompatActivity {

    ActivityRelavantPartiesMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRelavantPartiesMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] ImageId = {R.drawable.hotel1,R.drawable.hotel2,R.drawable.hotel3,R.drawable.hotel4,R.drawable.hotel5,
                R.drawable.hotel6,R.drawable.hotel7,R.drawable.hotel8};
        String[] Name = {"1","2","3","4","5","6","7","8"};
        String[] City = {"Negombo","Kandy","Kurunegala","Nugegoda","Rathnapura","Kelaniya","Colombo 10","Colombo 2"};
        String[] Time = {"Negombo","Kandy","Kurunegala","Nugegoda","Rathnapura","Kelaniya","Colombo 10","Colombo 2"};
        String[] PhoneNo = {"+947656610000","+949999043232","+947834354323","+949876543211","+945434432343",
                "+949439043232","+947534354323","+946545543211"};
        String[] NameA = {"Hiruni mudannayake","Kamal Perera","Nimal Oshada","Amal Perera","Vinash Mohomad",
                "Buwini Tharaka","Nisha Shanelle","Nisal Kannangara"};
        String[] Dis = {"Lavanya Hotel, Colombo offers some of the finest accommodation in the country and wedding event space catering to up to 2,000 guests.",
                "If you’re looking for a luxury hotel in rathnapura, look no further than Heritance Negombo.For those interested in checking out popular landmarks while visiting Negombo, Heritance Negombo is located a short distance from Negombo Public Cemetery (0.3 mi) and Dutch Clock Tower (0.8 mi).",
                "Jetwing Lagoon, an architectural masterpiece designed by the world-renowned Sri Lankan Architect, Geoffrey Bawa and wedding event space catering to up to 2,000 guests",
                "Situated in the heart of City of Kandy, The Grand Kandyan five -star luxury hotel is the perfect heaven for those with time to explore Kandyan warm hospitality and diverse cultures. Just 10 minutes from ‘ Sri Dalada Maligawa’, The Temple of the Sacred Tooth Relic of Buddha which is the most popular attraction in UNESCO World Heritage City,Kandy",
                "Since its beginnings in 1864 The Galle Face Hotel has been part of Colombo heritage, catering to world travellers, the property stands as one of the most storied hotels in Asia. Oozing history from every pore, timeless traditions and character abounds in each of our 156 guestrooms, restored to reflect the Galle Face Hotel’s rich past.",
                "An unspoiled personal tropical paradise on the southern coast of Sri Lanka. Our spacious rooms create a sense of privacy and relaxation, Famous for wedding photo shoots",
                "An unspoiled personal tropical paradise on the southern coast of Sri Lanka. Our spacious wedding halls create a sense of relaxation",
                "With the beach at your doorstep, Jetwing Sea offers an idyllic coastal retreat along the soft golden sands of Negombo and makes for an enchanting holiday home where one can work, play and fall in love."};

        ArrayList<RelavantParties> relavantPartiesArrayList = new ArrayList<>();

        for(int i = 0;i< ImageId.length;i++){

            RelavantParties relavantParties = new RelavantParties(Name[i],City[i],Time[i],PhoneNo[i],NameA[i],Dis[i],ImageId[i]);
            relavantPartiesArrayList.add(relavantParties);

        }


        RelavantPartiesAdapter relavantPartiesAdapterAdapter = new RelavantPartiesAdapter(RelavantPartiesMain.this,relavantPartiesArrayList);

        binding.listview.setAdapter(relavantPartiesAdapterAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(RelavantPartiesMain.this,RelavantPartiesActivity.class);
                i.putExtra("Name",Name[position]);
                i.putExtra("PhoneNo",PhoneNo[position]);
                i.putExtra("Dis",Dis[position]);
                i.putExtra("NameA",NameA[position]);
                i.putExtra("ImageId",ImageId[position]);
                startActivity(i);

            }
        });

    }
}