package com.example.mymemo.command;

import com.example.mymemo.MemoModel;
import com.example.mymemo.MemoView;

public class ComTime implements MemoCommand{
    private MemoView mView;
    private MemoModel mModel;

    public ComTime(MemoView view, MemoModel model){//コンストラクタ
        mView = view;
        mModel = model;
    }

    public void execute(){
        mView.settime();
    }
}
