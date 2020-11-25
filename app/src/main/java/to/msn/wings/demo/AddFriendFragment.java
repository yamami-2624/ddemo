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
import android.widget.TextView;

public class AddFriendFragment extends Fragment {
    private int cnt = 0;

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
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addfriend,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null ){
            int count = args.getInt("Counter");
            String str = "Fragment02: " + String.valueOf(count);
            cnt = count +1;

            TextView textView = view.findViewById(R.id.add_textView);
            textView.setText(str);
        }

        Button return_button = view.findViewById(R.id.return_button);
        return_button.setOnClickListener( v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            if(fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container, FriendsListFragment.newInstance(cnt));
                fragmentTransaction.commit();
            }
        });

        // BackStackで１つ戻す
        Button pop02 = view.findViewById(R.id.pop_02);
        pop02.setOnClickListener( v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            if(fragmentManager != null) {
                fragmentManager.popBackStack();
            }
        });
    }
}
