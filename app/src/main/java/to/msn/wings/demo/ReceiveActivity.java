package to.msn.wings.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
    }

    public void sent_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.SentActivity.class);
        startActivity(i);
    }

    public void friendsbtn_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.MainActivity.class);
        startActivity(i);
    }

    public void mypage_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.MypageActivity.class);
        startActivity(i);
    }
}
