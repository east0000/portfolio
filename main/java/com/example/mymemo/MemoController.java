package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mymemo.command.ComClear;
//import com.example.mymemo.command.ComDelete; 実装途中
import com.example.mymemo.command.ComSearch;
import com.example.mymemo.command.ComSearchReset;
import com.example.mymemo.command.ComShowCurrent;
import com.example.mymemo.command.ComShowNext;
import com.example.mymemo.command.ComShowPrevious;
import com.example.mymemo.command.ComSubmit;
import com.example.mymemo.command.ComTime;
import com.example.mymemo.command.MemoCommand;

import java.util.HashMap;

public class MemoController extends AppCompatActivity {//ユーザの入力を受けて発生するイベントを担当。モデルやビューと連携をとる。
    private MemoView mView;
    private MemoModel mModel;
    private HashMap<Integer, MemoCommand> mComList;
    private Context mContext;

    @Override//レイアウトファイルを読み込んだりするため、オーバーライドする
    protected void onCreate(Bundle savedInstanceState) {//使用するオブジェクトの起動や初期値の設定。引数は実行状態。activityが最初に生成されたとき呼び出される
        super.onCreate(savedInstanceState);//状態の復帰処理
        mModel = new MemoModel(this);//contextはリソース全体を管理。データベース(SQLiteを用いるときに必要)
        mView = new MemoView(this, mModel);//activityは画面部分のリソースを管理。contextの下位クラス
        mComList = new HashMap<>();
        mComList.put(R.id.submitButton, new ComSubmit(mView, mModel));//ComSubmitはexecute()よりMemoViewから日時とテキストを取得し、MemoModelに登録を依頼。
        mComList.put(R.id.clearButton, new ComClear(mView, mModel));
        mComList.put(R.id.searchButton, new ComSearch(mView, mModel));
        mComList.put(R.id.previousButton, new ComShowPrevious(mView, mModel));
        mComList.put(R.id.currentButton, new ComShowCurrent(mView, mModel));
        mComList.put(R.id.nextButton, new ComShowNext(mView, mModel));
        mComList.put(R.id.ResetButton, new ComSearchReset(mView, mModel));
        //mComList.put(R.id.DeleteButton, new ComDelete(mView, mModel));//実装途中
        mComList.put(R.id.TimeButton, new ComTime(mView, mModel));


        //R.id.submitButtonは登録ボタンのidを示している。HashMapの性質より、登録ボタンが実行されると、右側が実行される連想配列が出来上がる。
    }

    public void onStart(){//画面の生成。activityが開始状態になると呼び出され、アクティビティがユーザーに表示される。
        super.onStart();
        mView.start();
    }

    public void onStop(){
        super.onStop();

    }

    public boolean onClick(View v) {//vはidでそれがボタンに対応。ちなみにViewクラスのコンストラクタは[Context context]である。
        mComList.get(v.getId()).execute();
        //v.getId())でボタンのidを取り出し、idに対応したexecuteを実行する
        return true;
    }
}