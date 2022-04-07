package com.example.teezzim.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Scorebord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScorebordDao scorebordDao();
}
