package com.example.mymemo.database;

import com.example.mymemo.database.MemoDatabase;

import java.util.ArrayList;
import java.util.List;

public class MemoDatabaseByArray implements MemoDatabase {
    private ArrayList<String> mBuffer;//リスト宣言

    public MemoDatabaseByArray(){//インスタンス生成
        mBuffer = new ArrayList<>();
    }

    public void submitOne(DtMemo memo){//memoは画面にある日時とテキストのこと。日時、テキストをテキストから取得し、mBufferに追加(１セット)
        mBuffer.add(memo.getDate());//日付を格納
        mBuffer.add(memo.getTitle());
        mBuffer.add(memo.getText());//メモを格納
        //0 日付　1 メモ 2日付 3メモ　の順番
    }

    public void FileDelete(){};//インターフェースの都合上用意したメソッドであり、本内容とは関係なし


    public List<DtMemo> searchSome(DtMemo query){//queryは検索条件(探したい日付、メモ)であり、合致するメモを探し出す
        ArrayList<DtMemo> ans = new ArrayList<>();
        int i = 0;
        int sz = mBuffer.size();//格納されているメモの数。
        while(i < sz){//メモはmBufferの０番目から格納されているのでこれでOK
            String d = mBuffer.get(i++);//バッファーに格納されている日付を取り出す
            String s = mBuffer.get(i++);
            String t = mBuffer.get(i++);//バッファーに格納されているメモを取り出す
            ans.add(new DtMemo(d,s,t));
        }
        return ans;

    }
}
