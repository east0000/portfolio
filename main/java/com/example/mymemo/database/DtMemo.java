package com.example.mymemo.database;


public class DtMemo {//メモの情報をまとめる役割を持つ
    private String mDate;
    private String mText;
    private String mTitle;

    public DtMemo(){;//デフォルトコンストラクタ
        mDate = "";
        mTitle = "";
        mText = "";

    }

    public DtMemo(String date, String title, String text){//文字列格納
        mDate = date;
        mTitle = title;
        mText = text;
    }


    public String getDate(){
        return mDate;
    }

    public void setDate(String date){
        mDate = date;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public String getText(){
        return mText;
    }

    public void setText(String text){
        mText = text;
    }
}
