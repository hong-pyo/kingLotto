package hong2.kinglotto;

import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import hong2.kinglotto.menu.Fragment1;
import hong2.kinglotto.menu.Fragment2;
import hong2.kinglotto.menu.Fragment3;
import hong2.kinglotto.menu.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "첫번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "두번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        return;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "세번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                }
            }
        });
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }
}