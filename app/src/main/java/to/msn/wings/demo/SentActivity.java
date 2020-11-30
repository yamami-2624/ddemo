package to.msn.wings.demo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // BackStackを設定
            fragmentTransaction.addToBackStack(null);

            // counterをパラメータとして設定
            int count = 0;
            fragmentTransaction.replace(R.id.container, TypeFragment.newInstance(count));

            fragmentTransaction.commit();
        }
    }

    public void friendsbtn_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.MainActivity.class);
        startActivity(i);
    }

    public void recive_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.ReceiveActivity.class);
        startActivity(i);
    }

    public void mypage_onClick(View v) {
//      明示インテントを作成するにはこれが必要
//      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.MypageActivity.class);
        startActivity(i);
    }
}
