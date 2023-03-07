package com.example.mymemo.command;

import com.example.mymemo.MemoModel;
import com.example.mymemo.MemoView;

public class ComShowNext  implements MemoCommand{
    private MemoView mView;
    private MemoModel mModel;

    public ComShowNext(MemoView view, MemoModel model){//コンストラクタ
        mView = view;
        mModel = model;
    }

    public void execute(){
        mModel.moveNext();
        mView.setDate(mModel.getFocusedDate());
        mView.setTitle(mModel.getFocusedTitle());
        mView.setText(mModel.getFocusedText());
    }
}
