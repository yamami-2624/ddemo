package to.msn.wings.demo;
import android.content.ContentValues;
import android.content.Context;
//import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
//import android.database.sqlite.SQLiteStatement;

public class SiriaiDatabaseAdapter {
    static final private String DBNAME = "siriai.sqlite";
    private final static String DB_TABLE = "siriai";       // DBのテーブル名
    static final private int VERSION = 1;
    /**
     * DBのカラム名
     */
    public final static String COL_ID = "_id";             // id
    public final static String COL_NAME = "name";

    private SQLiteDatabase db = null;           // SQLiteDatabase
    private SiriaiDatabaseHelper helper = null;           // DBHepler
    protected Context context;                  // Context

    // コンストラクタ
    public SiriaiDatabaseAdapter(Context context) {
        this.context = context;
        helper = new SiriaiDatabaseHelper(this.context);
    }

    /**
     * DBの読み書き
     * openDB()
     *
     * @return this 自身のオブジェクト
     */
    public SiriaiDatabaseAdapter openDB() {
        db = helper.getWritableDatabase();        // DBの読み書き
        return this;
    }

    /**
     * DBの読み込み 今回は未使用
     * readDB()
     *
     * @return this 自身のオブジェクト
     */
    public SiriaiDatabaseAdapter readDB() {
        db = helper.getReadableDatabase();        // DBの読み込み
        return this;
    }

    /**
     * DBを閉じる
     * closeDB()
     */
    public void closeDB() {
        db.close();     // DBを閉じる
        db = null;
    }


    public void saveDB(String name) {

        db.beginTransaction();          // トランザクション開始

        try {
            ContentValues values = new ContentValues();     // ContentValuesでデータを設定していく
            values.put(COL_NAME, name);
            // insertメソッド データ登録
            // 第1引数：DBのテーブル名
            // 第2引数：更新する条件式
            // 第3引数：ContentValues
            db.insert(DB_TABLE, null, values);      // レコードへ登録

            db.setTransactionSuccessful();      // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                // トランザクションの終了
        }
    }

    /**
     * DBのデータを取得
     * getDB()
     *
     * @param columns String[] 取得するカラム名 nullの場合は全カラムを取得
     * @return DBのデータ
     */
    public Cursor getDB(String[] columns) {

        // queryメソッド DBのデータを取得
        // 第1引数：DBのテーブル名
        // 第2引数：取得するカラム名
        // 第3引数：選択条件(WHERE句)
        // 第4引数：第3引数のWHERE句において?を使用した場合に使用
        // 第5引数：集計条件(GROUP BY句)
        // 第6引数：選択条件(HAVING句)
        // 第7引数：ソート条件(ODERBY句)
        return db.query(DB_TABLE, columns, null, null, null, null, null);
    }

    public void selectDelete(String position) {

        db.beginTransaction();                      // トランザクション開始
        try {
            db.delete(DB_TABLE, COL_ID + "=?", new String[]{position});
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

}


class SiriaiDatabaseHelper extends SQLiteOpenHelper {

    // コンストラクタ
    public SiriaiDatabaseHelper(Context context) {
        //第1引数：コンテキスト
        //第2引数：DB名
        //第3引数：factory nullでよい
        //第4引数：DBのバージョン
        super(context, "friend.sqlite", null, 1);
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
        db.execSQL("DROP TABLE IF EXISTS friend");
//      削除した後onCreateメソッドでテーブルを再生成してる。
        onCreate(db);
    }
}
