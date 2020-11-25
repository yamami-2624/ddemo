package to.msn.wings.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class FriendsListFragment extends Fragment {

    private int cnt = 0;

    static FriendsListFragment newInstance(int count) {
        // FriendsListFragment インスタンス生成
        FriendsListFragment FriendsListFragment = new FriendsListFragment();

        // Bundle にパラメータを設定
        Bundle args = new Bundle();
        args.putInt("Counter", count);
        FriendsListFragment.setArguments(args);

        return FriendsListFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_friendlist,
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
            FragmentManager fragmentManager = getSupportFragmentManager();
//            if (fragmentManager != null) {
            // FragmentTransactionのインスタンスを取得
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // BackStackを設定 // インスタンスに対して張り付け方を指定する
            fragmentTransaction.addToBackStack(null);
            // インスタンスに対して張り付け方を指定する
            fragmentTransaction.replace(R.id.container,
                    AddFriendFragment.newInstance(cnt));

//           貼り付けを実行
            fragmentTransaction.commit();

        });

        // BackStackで１つ戻す
        Button pop01 = view.findViewById(R.id.pop_01);
        pop01.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.popBackStack();
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

