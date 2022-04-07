package com.example.teezzim.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Scorebord {
    //날짜 / 시간 / 장소 / 메모 / 취소 여부 / 사진 목록 / 스코어 사진.

    @PrimaryKey(autoGenerate = true)
    private int seq;

    private String Date;
    private String Time;
    private String Location;
    private String Memo;
    private boolean Cansel;
    private String CanselDay;
    private String scorePhoto;
    private String Json_album;

    public Scorebord (String _date, String _time, String _location, String _memo, Boolean _cansel
            , String _album, String _scorePhoto){
        this.Date = _date;
        this.Time = _time;
        this.Location = _location;
        this.Memo = _memo;
        this.Cansel = _cansel;
        this.Json_album = _album;
        this.scorePhoto = _scorePhoto;
    }

    public Scorebord (String _date, String _time, String _location, String _memo, Boolean _cansel ){
        this.Date = _date;
        this.Time = _time;
        this.Location = _location;
        this.Memo = _memo;
        this.Cansel = _cansel;
    }

    public Scorebord(){

    }

    @Override
    public String toString() {
        return "Scorebord{" +
                "seq=" + seq +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Location='" + Location + '\'' +
                ", Memo='" + Memo + '\'' +
                ", Cansel=" + Cansel +
                ", album=" + Json_album +
                ", scorePhoto='" + scorePhoto + '\'' +
                '}';
    }



    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public boolean isCansel() {
        return Cansel;
    }

    public void setCansel(boolean cansel) {
        Cansel = cansel;
    }

    public String getCanselDay() {
        return CanselDay;
    }

    public void setCanselDay(String canselDay) {
        CanselDay = canselDay;
    }

    public String getScorePhoto() {
        return scorePhoto;
    }

    public void setScorePhoto(String scorePhoto) {
        this.scorePhoto = scorePhoto;
    }

    public String getJson_album() {
        return Json_album;
    }

    public void setJson_album(String json_album) {
        Json_album = json_album;
    }
}
