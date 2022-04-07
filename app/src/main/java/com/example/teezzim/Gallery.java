package com.example.teezzim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.teezzim.db.AppDatabase;
import com.example.teezzim.db.Scorebord;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    private AppDatabase db;
    private ArrayList<Scorebord> scorebordList;
    private RecyclerView recyclerView;
    private myRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Recycle_init();

        dbInit();


    }
    private void Recycle_init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        myRecyclerAdapter = new myRecyclerAdapter();
        recyclerView.setAdapter(myRecyclerAdapter);
    }
    private void dbInit(){
        db = Room.databaseBuilder(this,AppDatabase.class, "Scorebord-db").build();

        db.scorebordDao().getAll();

        db.scorebordDao().getAll().observe(this, scorebords ->{
            Log.d("Scorebord ", " dbInit-getAll Size = "+scorebords.size() +" Data :"+scorebords.toString());

            myRecyclerAdapter.ClearAll();

            for(int i=0; i< scorebords.size(); i++){
                Scorebord data = new Scorebord();
                data.setJson_album(scorebords.get(i).getJson_album());
                data.setDate(scorebords.get(i).getDate());
                data.setTime(scorebords.get(i).getTime());
                data.setCansel(scorebords.get(i).isCansel());
                data.setCanselDay(scorebords.get(i).getCanselDay());
                data.setLocation(scorebords.get(i).getLocation());
                data.setDate(scorebords.get(i).getDate());
                data.setMemo(scorebords.get(i).getMemo());
                data.setScorePhoto(scorebords.get(i).getScorePhoto());
                myRecyclerAdapter.addItem(data);
            }
            myRecyclerAdapter.notifyDataSetChanged();

        } );

    }

}