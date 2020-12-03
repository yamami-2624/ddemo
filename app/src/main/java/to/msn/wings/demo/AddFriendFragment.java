package to.msn.wings.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class AddFriendFragment extends Fragment {
    private int cnt = 0;
    EditText addfriend;
    private FriendDatabaseHelper helper = null;
    private EditText txtName = null;
    private TextView view;

    static AddFriendFragment newInstance(int count){
        // Fragemnt02 インスタンス生成
        AddFriendFragment AddFriendFragment = new AddFriendFragment();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        AddFriendFragment.setArguments(barg);

        return AddFriendFragment;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {

        helper = new FriendDatabaseHelper(getActivity());

        return inflater.inflate(R.layout.fragment_addfriend,
                container2, false);
//
//        view.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_MOVE){
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button return_button = view.findViewById(R.id.return_button);
        return_button.setOnClickListener( v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            if(fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // BackStackを設定
                fragmentTransaction.addToBackStack(null);
//              バックスタックを押した時に出てくるページのコンテイナーとフラグメントを指定
                fragmentTransaction.replace(R.id.container, FriendsListFragment.newInstance(cnt));
                fragmentTransaction.commit();
            }
        });

        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener( v -> {
            try (SQLiteDatabase db = helper.getWritableDatabase()) {
                ContentValues cv  = new ContentValues();
                cv.put("name", txtName.getText().toString());
                db.insert("friend", null, cv);
//          重複した時の処理
                db.insertWithOnConflict("books", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                Toast.makeText(getActivity(), "データの登録に成功しました。", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    //  保存ボタンを押した時に呼び出されるメソッド
//    public void save_onClick(View view) {
//        try (SQLiteDatabase db = helper.getWritableDatabase()) {
//            ContentValues cv  = new ContentValues();
//            cv.put("name", txtName.getText().toString());
//            db.insert("friend", null, cv);
////          重複した時の処理
//            db.insertWithOnConflict("books", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
//            Toast.makeText(getActivity(), "データの登録に成功しました。", Toast.LENGTH_SHORT).show();
//        }
//    }
}


//変更
