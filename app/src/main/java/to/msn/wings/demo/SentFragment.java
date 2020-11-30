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


public class SentFragment extends Fragment{
    private int cnt = 0;
    //
    static SentFragment newInstance(int count){
        // Fragemnt02 インスタンス生成
        SentFragment SentFragment = new SentFragment();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        SentFragment.setArguments(barg);

        return SentFragment;
    }

//    作成したインスタンスをViewに渡すことで画面を表示
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_send,
                container, false);
    }


//    ここからしたの部分でスタックを戻したりしてる
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

//
//        if (args != null) {
//            int count = args.getInt("Counter");
//            String str = "Fragment02: " + String.valueOf(count);
//            cnt = count + 1;
//
//            TextView textView = view.findViewById(R.id.add_textView);
//            textView.setText(str);
//        }

        Button return_button = view.findViewById(R.id.return_button);
        return_button.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container, TypeFragment.newInstance(cnt));
                fragmentTransaction.commit();
            }
        });
    }
}
