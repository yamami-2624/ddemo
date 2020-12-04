package to.msn.wings.demo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import 	android.widget.ListView;
import android.widget.ArrayAdapter;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;

import java.util.Objects;
import java.util.ArrayList;


public class FriendsListFragment extends Fragment {
    private FriendDatabaseHelper helper = null;
    protected SQLiteDatabase db;
    private int cnt = 0;
    private FriendDatabaseAdapter friendDatabaseAdapter;                // DBAdapter
    private ArrayAdapter<String> adapter;       // ArrayAdapter
    private ArrayList<String> items;            // ArrayList
    private ListView friends_list;        // ListView


    static FriendsListFragment newInstance(int count) {
        // FriendsListFragment インスタンス生成
        FriendsListFragment FriendsListFragment = new FriendsListFragment();

        // Bundle にパラメータを設定　フラグメントの画面遷移にはBundleを使う。
        Bundle args = new Bundle();
        args.putInt("Counter", count);
        FriendsListFragment.setArguments(args);

        return FriendsListFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        friendDatabaseAdapter = new FriendDatabaseAdapter(getActivity());
        friendDatabaseAdapter.openDB();     // DBの読み込み(読み書きの方)

//        findViews();            // 各部品の結び付け

        // ArrayListを生成
        items = new ArrayList<>();

        // DBのデータを取得
        String[] columns = {friendDatabaseAdapter.COL_NAME};     // DBのカラム：品名
        Cursor c = friendDatabaseAdapter.getDB(columns);

        if (c.moveToFirst()) {
            do {
                items.add(c.getString(0));
                Log.d("取得したCursor:", c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        friendDatabaseAdapter.closeDB();    // DBを閉じる

        // ArrayAdapterのコンストラクタ
        // 第1引数：Context
        // 第2引数：リソースとして登録されたTextViewに対するリソースID 今回は元々用意されている定義済みのレイアウトファイルのID
        // 第3引数：一覧させたいデータの配列
        adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1, items);

//        friends_list.setAdapter(adapter);     //ListViewにアダプターをセット(=表示)
//
//        // ArrayAdapterに対してListViewのリスト(items)の更新
//        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendlist, container, false);

        friends_list  = (ListView) view.findViewById(R.id.friends_list);
        friends_list.setAdapter(adapter);     //ListViewにアダプターをセット(=表示)

        // ArrayAdapterに対してListViewのリスト(items)の更新
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        Button add_button = view.findViewById(R.id.add_button);
        add_button.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            if (fragmentManager != null) {
                // FragmentTransactionのインスタンスを取得
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // BackStackを設定 // インスタンスに対して張り付け方を指定する
                fragmentTransaction.addToBackStack(null);
                // インスタンスに対して張り付け方を指定する
                fragmentTransaction.replace(R.id.container, AddFriendFragment.newInstance(cnt));

//           貼り付けを実行
                fragmentTransaction.commit();
            }
        });
    }


//        FriendsList = view.findViewById(R.id.friends_list);
//        db = helper.getReadableDatabase();
//
//        cursor = db.query("friend",
//                null, null, null, null, null, null);
//        cursor.moveToFirst();

//        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);


////      リストビュー取得
//        ListView listView = view.findViewById(R.id.friends_list);
//
////      db
//        helper = new FriendDatabaseHelper(getActivity());
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        Cursor cursor = db.query(
//            ListView itemIds = new ArrayList<>();
//            while(cursor.moveToNext()) {
//                long itemId = cursor.getLong(
//                        cursor.getColumnIndexOrThrow(FeedEntry._ID));
//                itemIds.add(itemId);
//            }
//            cursor.close();
    }



