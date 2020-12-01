package to.msn.wings.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddFriendFragment extends Fragment {
    private int cnt = 0;
    EditText addfriend;

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addfriend,
                container2, false);
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
//        // BackStackで１つ戻す
//        Button pop02 = view.findViewById(R.id.pop_02);
//        pop02.setOnClickListener( v -> {
//            FragmentManager fragmentManager = getParentFragmentManager();
//            if(fragmentManager != null) {
//                fragmentManager.popBackStack();
//            }
//        });
    }


//  読み込んだ記述をs寿徳するための器
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        //        EditTextに書き込む記述
        StringBuilder str = new StringBuilder();
//        文字入力ストリームクラス
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                openFileInput("memo.dat")))){
//            行単位で読み込んで、その内容をStringBufferに保存
            String line;
//          readLineはテキストファイルの現在の読み取り位置を表すBufferedReader内部の目印になる。
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        テキストメモを取得
        addfriend = findViewById(R.id.addfriend);
        addfriend.setText(str.toString());
    }


//      保存ボタンをクリックされた時に呼び出されるコード
    public void btnSave_onClick(View view) {
    //        memo.datへの書き込みを準備
    //        BufferedWriterは文字入力ストリームクラス
            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
    //                openFileOutputで専用フォルダーにアクセス
            openFileOutput("memo.dat", Context.MODE_PRIVATE)))){
            writer.write(txtMemo.getText().toString());
            } catch (IOException e) {
            e.printStackTrace();
            }
    }
}


//変更
