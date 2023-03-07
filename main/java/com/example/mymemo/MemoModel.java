package com.example.mymemo;

import android.content.Context;

import com.example.mymemo.database.DtMemo;
import com.example.mymemo.database.MemoDatabase;
import com.example.mymemo.database.MemoDatabaseByArray;
import com.example.mymemo.database.MemoDatabaseByFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MemoModel {//処理の本質的な部分。データベースなど
    private Context mContext;
    private MemoDatabase mDatabase;
    private List<String> mDateResults;
    private List<String> mTextResults;
    private List<String> mTitleResults;
    private int mIndex;//格納された要素に番号をつける。０から順番に格納

    //アプリ起動時に用いるindex(データベースに登録されている数-1)と検索時のindex


    public MemoModel(Context context){//コンストラクト時にデータベース準備
        mContext = context;
        //mDatabase = new MemoDatabaseByArray();//配列のデータベース
        mDatabase = new MemoDatabaseByFile(context);
    }

    //ComSubmitのexecute();より
    public void submit(String date, String title, String text){//dateとtextは画面上の日付とメモである
        mDatabase.submitOne(new DtMemo(date, title, text));

    }//dateとtextは画面にある日付とメモのことである。



    public void searchMemo(String date, String title, String text){
        clearSearchResults();//検索結果廃棄

        List<DtMemo> results = mDatabase.searchSome(new DtMemo(date, title, text));//resultsにデータベースにあるメモをを格納する
        //resultsにはリスト一つで日付、タイトル、テキストがセットで格納されている

         for(DtMemo one : results){//リストresultsから検索結果(日付とテキスト)を取り出し、oneに格納する。ただし、一つずつ取り出し、前の結果を無視することを忘れない。
        //DtMemoを見ればわかるが、日付→文字の順番であるため以下のような順番でコードを書く。
             mDateResults.add(one.getDate());//oneから日付を取り出し、リストmDateResultsに格納
             mTitleResults.add(one.getTitle());
             mTextResults.add(one.getText());//oneからテキストを取り出し、リスト mTextResultsに格納
             mIndex++;
         }
      }

      /*public void delete(String date, String title,String text){//削除操作.実装途中
           int Index = mIndex;//現在閲覧している場所を記憶
           clearSearchResults();//検索結果廃棄
           List<DtMemo> results = mDatabase.searchSome(new DtMemo(date, title, text));

               for(DtMemo one : results){
                   if(Index != mIndex-1){//削除したい場所以外の時、検索結果に加える
                   mDateResults.add(one.getDate());
                   mTitleResults.add(one.getTitle());
                   mTextResults.add(one.getText());
                   mIndex++;
                   }
                   mIndex++;
               }
           mDatabase.FileDelete();

           for(int c = 0 ;c < mIndex ; c++){
               mDatabase.submitOne(new DtMemo(mDateResults.get(c), mTitleResults.get(c), mTextResults.get(c)));
           }
      }

       */

    public void search(String date, String title, String text){//dateは日時の検索条件、textはメモの検索条件
        clearSearchResults();//検索結果廃棄

        List<DtMemo> results = mDatabase.searchSome(new DtMemo(date, title, text));//resultsにデータベースにあるメモをを格納する
        //mDatabaseは0番目が日付、１番目がメモ２番目が日付、３番目がメモの順番

            if(date == "" && title == ""){//テキストのみ入力されているとき
                for(DtMemo one : results){
                    if (one.getText().contains(text)) {//テキストの判定
                        mDateResults.add(one.getDate());
                        mTitleResults.add(one.getTitle());
                        mTextResults.add(one.getText());
                        mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                    }
                }
                mIndex = 0;//こうすることで、前からメモが表示されるようになる
            }else if(text == "" && title == ""){//日付のみ入力されているとき
                for(DtMemo one : results){
                    if (one.getDate().contains(date)) {//日付の判定
                        mDateResults.add(one.getDate());
                        mTitleResults.add(one.getTitle());
                        mTextResults.add(one.getText());
                        mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                    }
                }
                mIndex = 0;
            }else if(date =="" && text == ""){
                for(DtMemo one : results){
                    if (one.getTitle().contains(title)) {//日付の判定
                        mDateResults.add(one.getDate());
                        mTitleResults.add(one.getTitle());
                        mTextResults.add(one.getText());
                        mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                    }
                }
                mIndex = 0;
            }else if(title == "") {//日付とテキストがある場合
                for(DtMemo one : results) {
                    if (one.getDate().contains(date)) {//日付の判定
                        if (one.getText().contains(text)) {//テキストの判定
                            mDateResults.add(one.getDate());
                            mTitleResults.add(one.getTitle());
                            mTextResults.add(one.getText());
                            mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                        }
                    }
                }
                mIndex = 0;
            }else if(date == ""){//タイトルとテキストがある場合
                for(DtMemo one : results) {
                    if (one.getTitle().contains(title)) {//タイトルの判定
                        if (one.getText().contains(text)) {//テキスト」の判定
                            mDateResults.add(one.getDate());
                            mTitleResults.add(one.getTitle());
                            mTextResults.add(one.getText());
                            mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                        }
                    }
                }
                mIndex = 0;
            }else if(text == ""){//日付とタイトルがある場合
                for(DtMemo one : results) {
                    if (one.getTitle().contains(title)) {//タイトルの判定
                        if (one.getDate().contains(date)) {//日付の判定
                            mDateResults.add(one.getDate());
                            mTitleResults.add(one.getTitle());
                            mTextResults.add(one.getText());
                            mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                        }
                    }
                }
                mIndex = 0;
            }else{
                for(DtMemo one : results) {//日付、タイトル、メモのすべてがある場合
                    if (one.getTitle().contains(title)) {//タイトルの判定
                        if (one.getDate().contains(date)) {//日付の判定
                            if(one.getText().contains(text)){
                            mDateResults.add(one.getDate());
                            mTitleResults.add(one.getTitle());
                            mTextResults.add(one.getText());
                            mIndex++;//当てはまるメモだけにmIndexを新たに当てはめる
                            }
                        }
                    }
                }
                mIndex = 0;
            }
       }



    public String getNow() {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String s = f.format(date);

        return s;
    }


    public void clearSearchResults(){//検索結果廃棄
        mIndex = -1;
        mDateResults = new ArrayList<>();
        mTitleResults = new ArrayList<>();
        mTextResults = new ArrayList<>();
    }

    public String getFocusedDate(){//mIndexが示すメモの日時を返す
        if(mIndex < mDateResults.size()){
            return mDateResults.get(mIndex);
        }
        return "No date";
    }

    public String getFocusedTitle(){//mIndexが示すメモのテキストを返す
        if(mIndex < mTitleResults.size()){
            return mTitleResults.get(mIndex);
        }
        return "No title";
    }

    public String getFocusedText(){//mIndexが示すメモのテキストを返す
        if(mIndex < mTextResults.size()){
            return mTextResults.get(mIndex);
        }
        return "No text";
    }

    public boolean movePrevious(){
        if(mIndex-1 < mTextResults.size() && mIndex-1 >= 0) {
            mIndex--;
            return true;
        }
        return false;
    }

    public boolean movecurrent(){
        for(int i=mIndex; i<mTextResults.size()-1;i++ ) {
            mIndex++;
        }
        return true;
    }

    public boolean moveNext(){
        if(mIndex+1 < mTextResults.size() && mIndex+1 >= 0) {
            mIndex++;
            return true;
        }
        return false;
    }
}

