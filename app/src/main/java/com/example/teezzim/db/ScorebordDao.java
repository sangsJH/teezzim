package com.example.teezzim.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ScorebordDao {

    @Query("SELECT * FROM Scorebord")
    LiveData<List<Scorebord>> getAll();
    //List<BookNumber> getAll();

    @Insert
    void inset(Scorebord scorebord);

    @Update
    void update(Scorebord scorebord);

    @Delete
    void delete(Scorebord scorebord);

    @Query("DELETE FROM Scorebord")
    void deleteall();

    @Delete
    void reset(List<Scorebord> scorebord);


    // 골프장 업데이트 기능. 날짜 / 시간 / 장소.를  키 값으로 없데이트 함수.
}
