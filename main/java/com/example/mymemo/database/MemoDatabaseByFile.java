package com.example.mymemo.database;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;//テキストファイルを読み込む
import java.io.BufferedWriter;//テキストファイルの書き込み
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoDatabaseByFile implements MemoDatabase{
    private Context mContext;
    private String mDir;
    private String mFilename;
    private List<String> mBuffer;
    private boolean mModified;

    public MemoDatabaseByFile(Context context){//コンストラクタ
        mContext = context;
        mDir = mContext.getFilesDir().getPath();//アプリ用のディレクトリ
        mFilename = mDir + "/my_memo_file.txt";//ファイルの場所＋ファイルの名前
        mBuffer = new ArrayList<>();
        mModified = true;
    }


    public void submitOne(DtMemo memo) {//テキストファイルの書き出し
        try {
            FileOutputStream fos = new FileOutputStream(new File(mFilename), true);//ファイルにバイト単位のデータを書き込む。追加書き込みの場合、true
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//バイトを読み込み、文字に変換
            BufferedWriter bw = new BufferedWriter(osw);//テキストファイルに書き込む

            bw.write(memo.getDate());//日付
            bw.write("\n");//改行
            bw.write(memo.getTitle());
            bw.write("\n");//改行
            bw.write(memo.getText());//テキスト
            bw.write("\n");//改行
            bw.write("<EOT>\n");//<EOT>、つまりEnd Of Text

            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {//例外発生時の処理
            System.out.println("ファイルに登録できませんでした");
        }

        mModified = true;//ここで処理打ち切り
    }

    //public void FileDelete(){
        //File a = new File(mFilename);
       // a.delete();
   // }

    public List<DtMemo> searchSome(DtMemo query){//テキストファイルの検索
        ArrayList<DtMemo> ans = new ArrayList<>();
        if(mModified)
            mBuffer = load();
        int i = 0;
        int sz = mBuffer.size();
        while(i<sz){
            String d = mBuffer.get(i++);//日付を加えていく
            String s = mBuffer.get(i++);//タイトルを加えていく
            String t = mBuffer.get(i++);//テキストを加えていく
            ans.add(new DtMemo(d, s, t));//DtMemoの型に適応させ、ansに加える
        }
        return ans;
    }

    private List<String> load(){//テキストファイルの読み込み
        ArrayList<String> ans = new ArrayList<>();
        String line;
        try{
            FileInputStream fis = new FileInputStream(new File(mFilename));//システム内のファイルから入力バイトを取得
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");//バイトを読み込み、文字に変換
            BufferedReader br = new  BufferedReader(isr);//テキストファイルを読み込む
            //ここまでの流れ。システム内のファイルから入力バイトを取得→バイトを読み込み、文字に変換→テキストファイルを読み込む

            while(null != (line = br.readLine())){//順番的にまず読み込まれるのは日付。readLine()はすべて読み込んだらnullを返す
                ans.add(line);//日付をArrayListのansに追加
                String text = "";//テキスト初期化
                String sp = "";
                line = br.readLine();//lineにタイトルを代入
                ans.add(line);//ansにタイトルを加える
                while (null != (line = br.readLine())) {//メモ→break(EOTによる)の順番
                    if ("<EOT>".equals(line))//EOTまで来たら終了。この操作が複数のメモがある一つのファイルから一つのメモをロードすることを可能にしている
                        break;
                    text += sp + line;//改行+テキスト
                    sp = "\n";
                }
                ans.add(text);
            }

            br.close();
            isr.close();
            fis.close();
        }catch(Exception e){
            System.out.println("ファイルが見つかりませんでした");
        }
        Log.d("TEST", "LOADED");
        mModified = false;
        return ans;
    }
}
