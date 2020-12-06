package to.msn.wings.demo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WhoFragment extends Fragment {
    private FriendDatabaseHelper helper = null;
    protected SQLiteDatabase db;
    private int cnt = 0;
    private FriendDatabaseAdapter friendDatabaseAdapter;                // DBAdapter
    private ArrayAdapter<String> adapter;       // ArrayAdapter
    private ArrayList<String> items;            // ArrayList
    private ListView friends_list;        // ListView

    static WhoFragment newInstance(int count) {
        // FriendsListFragment インスタンス生成
        WhoFragment WhoFragment = new WhoFragment();

        // Bundle にパラメータを設定　フラグメントの画面遷移にはBundleを使う。
        Bundle args = new Bundle();
        args.putInt("Counter", count);
        WhoFragment.setArguments(args);

        return WhoFragment;
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
        View view = inflater.inflate(R.layout.fragment_who, container, false);

        friends_list  = (ListView) view.findViewById(R.id.friends_list);
        friends_list.setAdapter(adapter);     //ListViewにアダプターをセット(=表示)

        // ArrayAdapterに対してListViewのリスト(items)の更新
        adapter.notifyDataSetChanged();
        return view;
    }
}


//ここに友達一覧で出てきたやつを全員追加する。