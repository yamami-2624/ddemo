package to.msn.wings.demo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;


public class SelectFragment extends Fragment {
    private int cnt = 0;

    static SelectFragment newInstance(int count) {
        // FriendsListFragment インスタンス生成
        SelectFragment SelectFragment = new SelectFragment();

        // Bundle にパラメータを設定　フラグメントの画面遷移にはBundleを使う。
        Bundle args = new Bundle();
        args.putInt("Counter", count);
        SelectFragment.setArguments(args);

        return SelectFragment;
    }


    @Override
//  onCreateViewで画面を作る
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_select,
                container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            int count = args.getInt("Counter");
            String str = "Fragment01: " + String.valueOf(count);
            cnt = count + 1;

            TextView textView = view.findViewById(R.id.textView4);
            textView.setText(str);
        }

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


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_friendlist, container, false);
//        Bundle bundle;
//        return view;
//    }
}
