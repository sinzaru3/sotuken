package com.g_r.healthcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Blood extends AppCompatActivity {

    // CustomOpenHelperクラスを定義
    CustomOpenHelper helper = null;

    //IDがNAMETextのテキストを取得
    public EditText editText;
    //IDがTestViweのテキストを取得
    public TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        // idがsearchButtonのボタンを取得
        Button button = (Button) findViewById(R.id.searchButton);
        // clickイベント追加
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                selectBLOODTable();
            }
        });


        // idがdeleteButtonのボタンを取得
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        // clickイベント追加
        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteBLOODTable();
            }
        });
        // idがinButtonのボタンを取得
        Button inButton = (Button) findViewById(R.id.inButton);
        // clickイベント追加
        inButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inBLOODTable();
            }
        });

        //IDがNAMETextのテキストを取得
        editText = (EditText) findViewById(R.id.NameText);
        //IDがTestViweのテキストを取得
        testView = (TextView) findViewById(R.id.TestView);

    }

    // Blood_TABLEの全行を取得してTextViewに表示
    private void selectBLOODTable(){
        // CustomOpenHelperクラスがまだインスタンス化されていなかったらインスタンス化する
        if(helper == null){
            helper = new CustomOpenHelper(Blood.this);
        }
        // データベースを取得する
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c = db.rawQuery("select id, hblood, lblood from BLOOD_TABLE", null);
            // Cursorの先頭行があるかどうか確認
            boolean next = c.moveToFirst();

            // 最終的に表示する文字列
            String dispStr = "";
            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                String rowdata = String.valueOf(c.getInt(0))+" , ";// idを取得
                rowdata += c.getString(1)+" , ";// nameを取得
                rowdata += String.valueOf(c.getInt(2));// priceを取得
                dispStr += rowdata + "\n";// \nは改行を表し、複数行取れた場合に一行ごとに改行するため
                // 次の行が存在するか確認
                next = c.moveToNext();
            }
            dispStr += "取得完了";

            // TextViewに取得したデータを表示
            ((TextView) findViewById(R.id.searchText)).setText(dispStr);
        } finally {
            // finallyは、tryの中で例外が発生した時でも必ず実行される
            // dbを開いたら確実にclose
            db.close();
        }
    }


    // BlOOD_TABLEの行を削除
    private void deleteBLOODTable(){
        // CustomOpenHelperクラスがまだインスタンス化されていなかったらインスタンス化する
        if(helper == null){
            helper = new CustomOpenHelper(Blood.this);
        }
        // データベースを取得する
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            // 値段が一番低いデータを削除
            db.execSQL("delete from BLOOD_TABLE where lblood = (select MIN(lblood) from BLOOD_TABLE)");

        } finally {
            // finallyは、tryの中で例外が発生した時でも必ず実行される
            // dbを開いたら確実にclose
            db.close();
        }
    }
    private void inBLOODTable(){
        // CustomOpenHelperクラスがまだインスタンス化されていなかったらインスタンス化する
        if(helper == null){
            helper = new CustomOpenHelper(Blood.this);
        }
        // データベースを取得する
        SQLiteDatabase db = helper.getWritableDatabase();

        //editをtextに入れる
        String text = editText.getText().toString();
        //textをtestで表示
        testView.setText(text);

        //データを挿入
        db.execSQL("INSERT INTO BLOOD_TABLE(hblood, lblood) VALUES('"+text+"', 700)");
        //dbを開いたら必ず閉じる
        db.close();
    }

}
