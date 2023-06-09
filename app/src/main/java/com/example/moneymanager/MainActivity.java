package com.example.moneymanager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.moneymanager.dialog.ChiDialog;
import com.example.moneymanager.dialog.LoaiChiDialog;
import com.example.moneymanager.dialog.LoaiThuDialog;
import com.example.moneymanager.dialog.ThuDialog;
import com.example.moneymanager.entity.Thu;
import com.example.moneymanager.ui.chi.ChiFragment;
import com.example.moneymanager.ui.chi.KhoanChiFragment;
import com.example.moneymanager.ui.chi.LoaiChiFragment;
import com.example.moneymanager.ui.home.HomeFragment;
import com.example.moneymanager.ui.thu.KhoanThuFragment;
import com.example.moneymanager.ui.thu.LoaiThuFragment;
import com.example.moneymanager.ui.thu.ThuFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moneymanager.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final MainActivity currentContext = this;
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size() - 1);
                if(fragment instanceof LoaiThuFragment){
                    LoaiThuDialog dialog = new LoaiThuDialog(currentContext, (LoaiThuFragment) fragment);
                    dialog.show();
                };
                if (fragment instanceof KhoanThuFragment) {
                    ThuDialog dialog = new ThuDialog(currentContext, (KhoanThuFragment) fragment);
                    dialog.show();
                };
                if(fragment instanceof LoaiChiFragment){
                    LoaiChiDialog dialog = new LoaiChiDialog(currentContext, (LoaiChiFragment) fragment);
                    dialog.show();
                };
                if (fragment instanceof KhoanChiFragment) {
                    ChiDialog dialog = new ChiDialog(currentContext, (KhoanChiFragment) fragment);
                    dialog.show();
                };
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.nav_thoat){
                    finish();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}