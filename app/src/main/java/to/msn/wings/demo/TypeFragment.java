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


public class TypeFragment extends Fragment{
    static TypeFragment newInstance(int count){
        // Fragemnt02 インスタンス生成
        TypeFragment TypeFragment = new TypeFragment();

        // Bundleにパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("Counter", count);
        TypeFragment.setArguments(barg);

        return TypeFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_type,
                container, false);
    }

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

//               どのようにフラグメントを張り替えるかを指定している
                fragmentTransaction.replace(R.id.container, SelectFragment.newInstance(cnt));
                fragmentTransaction.commit();
            }
        });
    }
}
