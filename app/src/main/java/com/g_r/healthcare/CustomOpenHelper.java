package com.g_r.healthcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 171y035 on 2018/06/22.
 */

public class CustomOpenHelper extends SQLiteOpenHelper {

    // データベース自体の名前(テーブル名ではない)
    static final private String DBName = "TATSU_DB";
    // データベースのバージョン(2,3と挙げていくとonUpgradeメソッドが実行される)
    static final private int VERSION = 1;

    // コンストラクタ、以下のように呼ぶこと
    public CustomOpenHelper(Context context){
        super(context, DBName, null, VERSION);
    }
    //データベースが作成された時の処理
    @Override
    public void onCreate(SQLiteDatabase db) {
        //処理を記述
                /*     テーブルを作成する
          execSQLメソッドにCREATET TABLE命令を文字列として渡すことで実行される
          引数で指定されているものの意味は以下の通り
          引数1 ・・・ id：列名 , INTEGER：数値型 , PRIMATY KEY：テーブル内の行で重複無し , AUTOINCREMENT：1から順番に振っていく
          引数2 ・・・ name：列名 , TEXT：文字列型
          引数3 ・・・ price：列名 , INTEGER：数値型
         */
        db.execSQL("CREATE TABLE BLOOD_TABLE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hblood INTEGER, " +
                "lblood INTEGER)");
        /**
         * BLOOD_TABLEに行を追加する
         * VALUESの前と後に()があるが、前の()に列名、後の()に実際入れる値を指定する
         * idは自動で振られるため指定しない
         */
        db.execSQL("INSERT INTO BLOOD_TABLE(hblood, lblood) VALUES(200, 700)");
        db.execSQL("INSERT INTO BLOOD_TABLE(hblood, lblood) VALUES(100, 300)");
        db.execSQL("INSERT INTO BLOOD_TABLE(hblood, lblood) VALUES(300, 600)");
    }
    //データベースがバージョンアップした時の処理
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //処理を記述
        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS BLOOD_TABLE");

        // 新しくテーブルを作成する
        onCreate(db);

    }
    //データベースが開かれたときの処理
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
