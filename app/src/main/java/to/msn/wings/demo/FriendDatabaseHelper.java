package to.msn.wings.demo;

import android.content.Context;
//import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteStatement;


public class FriendDatabaseHelper extends SQLiteOpenHelper {
    static final private String DBNAME = "friend.sqlite";
    static final private int VERSION = 1;

    //  コンストラクターで初期値を生成する。
    FriendDatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //  開くメソッド
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //  テーブルを作成する
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friend (" + "name TEXT)");
        db.execSQL("INSERT INTO friend(name)" + " VALUES('芥川力也')");
        db.execSQL("INSERT INTO friend(name)" + " VALUES('青木将人')");
    }

    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books (" +
                "isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)");
        String[] isbns = {"978-4-7980-4512-2",
                "978-4-7980-4179-7", "978-4-7741-8030-4",
                "978-4-7741-9617-6", "978-4-7981-3547-2"};
        String[] titles = {"はじめてのASP.NET Webフォーム",
                "ASP.NET MVC 5実践プログラミング", "Javaポケットリファレンス",
                "Swiftポケットリファレンス", "独習PHP 第3版"};
        int[] prices = {3000, 3500, 2680, 2780, 3200};
        db.beginTransaction();
        try {
            SQLiteStatement sql = db.compileStatement(
                    "INSERT INTO books(isbn, title, price) VALUES(?, ?, ?)");
            for (int i = 0; i < isbns.length; i++) {
                sql.bindString(1, isbns[i]);
                sql.bindString(2, titles[i]);
                sql.bindLong(3, prices[i]);
                sql.executeInsert();
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
//      削除した後onCreateメソッドでテーブルを再生成してる。
        onCreate(db);
    }
}