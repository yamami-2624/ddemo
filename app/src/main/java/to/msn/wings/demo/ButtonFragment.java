package to.msn.wings.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ButtonFragment extends Fragment{
    private int cnt = 0;

    @Override
//  onCreateViewで画面を作る
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_button,
                container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button friendsbtn = view.findViewById(R.id.friendsbtn);
        friendsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button sentbtn = view.findViewById(R.id.sentbtn);
        sentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SentActivity.class);
                startActivity(intent);
            }
        });

        Button recivebtn = view.findViewById(R.id.recivebtn);
        recivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReceiveActivity.class);
                startActivity(intent);
            }
        });

        Button mypagebtn = view.findViewById(R.id.mypagebtn);
        mypagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}
