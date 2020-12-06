package to.msn.wings.demo;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            fragmentTransaction.replace(R.id.container, FriendsListFragment.newInstance(count));

            fragmentTransaction.commit();
        }
    }

    public void sent_onClick(View v){
        //      明示インテントを作成するにはこれが必要
        //      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.SentActivity.class);
        startActivity(i);
    }

    public void recive_onClick (View v){
        //      明示インテントを作成するにはこれが必要
        //      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.ReceiveActivity.class);
        startActivity(i);
    }

    public void mypage_onClick (View v){
        //      明示インテントを作成するにはこれが必要
        //      新しいインテンツを作ってそこにSubActivitを呼び出す
        Intent i = new Intent(this, to.msn.wings.demo.MypageActivity.class);
        startActivity(i);
    }
}
