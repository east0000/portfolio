package com.example.mymemo.command;

import com.example.mymemo.MemoModel;
import com.example.mymemo.MemoView;

public class ComSearchReset implements MemoCommand{
    private MemoView mView;
    private MemoModel mModel;

    public ComSearchReset(MemoView view, MemoModel model){//コンストラクタ
        mView = view;
        mModel = model;
    }

    public void execute() {
        mModel.searchMemo(mView.getDate(),mView.getTitle(), mView.getText());//mIndex,mDateResults,mTextResultsへの登録
        mView.settime();
    }
}
