package com.revia.mylauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class AppGridActivity extends AppCompatActivity {

    private int mColumnNumber = 3;
    private AppGridAdapter mGridAdapter;
    private PackageManager mPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_grid);
        mPackageManager = getPackageManager();
        mGridAdapter = new AppGridAdapter(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            RecyclerView gridView = requireViewById(R.id.app_grid);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, mColumnNumber);
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(mGridAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAppsLists();
    }

    private void updateAppsLists(){
        AppLauncherUtils.LauncherAppsInfo appsInfo = AppLauncherUtils.getLauncherApps(
                getSystemService(LauncherApps.class),mPackageManager
        );
        mGridAdapter.setAllApps(appsInfo.getLaunchableAppList());
    }
}