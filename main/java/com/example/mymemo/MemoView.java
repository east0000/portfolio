package com.example.mymemo;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoView {//表示の部分の実装を担当
    private Activity mActivity;
    private MemoModel mModel;
    private EditText mDate;
    private EditText mText;
    private EditText mTitle;

    public MemoView(Activity activity, MemoModel model){//コンストラクタ
        mActivity = activity;
        mModel = model;
    }

    //ボタンとの対応付け
    public void start(){
        mActivity.setContentView(R.layout.activity_main);
        mDate = (EditText)mActivity.findViewById(R.id.dateFrame);//mDateは日時部分と対応された
        mTitle = (EditText)mActivity.findViewById(R.id.MemoTitle);//mTitleはタイトル部分と対応された
        mText = (EditText)mActivity.findViewById(R.id.textFrame);//mTextはメモ部分と対応された
        mModel.searchMemo(getDate(),getTitle(),getText());//ファイル読み込み
        mDate.setText(mModel.getNow());

    }

    public void textclear(){//セッターのsetTextとは異なるので注意
        mTitle.setText("");
        mText.setText("");
    }

    public void settime(){
        mDate.setText(mModel.getNow());
    }



    public String getDate(){
        return mDate.getText().toString();
    }//日時を文字列で取得

    public void setDate(String date){
        mDate.setText(date);
    }//日時部分をセット

    public String getTitle(){return  mTitle.getText().toString();}//タイトルを文字列で取得

    public void setTitle(String title){mTitle.setText(title);}//タイトルをセット

    public String getText(){return mText.getText().toString();}//メモを文字列で取得

    public void setText(String text){
        mText.setText(text);
    }//メモをセット


}

