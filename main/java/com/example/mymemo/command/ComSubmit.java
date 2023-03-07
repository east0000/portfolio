package com.example.mymemo.command;

import com.example.mymemo.MemoModel;
import com.example.mymemo.MemoView;

public class ComSubmit implements MemoCommand{ //void execute();を継承している
    private MemoView mView;
    private MemoModel mModel;
    
    public ComSubmit(MemoView view, MemoModel model){//コンストラクタ
        mView = view;
        mModel = model;
    }
    
    public void execute(){
        mModel.submit(mView.getDate(), mView.getTitle(), mView.getText());//データベースに日付とテキストを登録
        mModel.searchMemo(mView.getDate(),mView.getTitle(), mView.getText());//mIndex,mDateResults,mTextResultsへの登録
        mView.settime();
    }
}
