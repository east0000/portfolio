package com.example.mymemo.command;

import com.example.mymemo.MemoModel;
import com.example.mymemo.MemoView;

public class ComSearch  implements MemoCommand{
    private MemoView mView;
    private MemoModel mModel;

    public ComSearch(MemoView view, MemoModel model){//コンストラクタ
        mView = view;
        mModel = model;
    }

    public void execute() {
        mModel.search(mView.getDate(),mView.getTitle(), mView.getText());
        mView.setDate(mModel.getFocusedDate());
        mView.setTitle(mModel.getFocusedTitle());
        mView.setText(mModel.getFocusedText());
    }
}
