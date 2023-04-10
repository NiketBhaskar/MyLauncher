package com.revia.mylauncher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.Process;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppLauncherUtils {
    static void launchApp(Context context, Intent intent) {
        context.startActivity(intent);
    }

    static class LauncherAppsInfo {
        private final Map<ComponentName, AppMetaData> mLaunchables;

        LauncherAppsInfo(Map<ComponentName, AppMetaData> mLaunchables) {
            this.mLaunchables = mLaunchables;
        }

        List<AppMetaData> getLaunchableAppList() {
            return new ArrayList<>(mLaunchables.values());
        }
    }

    private static final LauncherAppsInfo EMPTY_APPS = new LauncherAppsInfo(Collections.emptyMap());

    static LauncherAppsInfo getLauncherApps(LauncherApps launcherApps, PackageManager packageManager) {
        if (launcherApps == null || packageManager == null) {
            return EMPTY_APPS;
        }
        List<LauncherActivityInfo> availableActivities = launcherApps.getActivityList(null, Process.myUserHandle());
        Map<ComponentName, AppMetaData> launchableMap = new HashMap<>(availableActivities.size());
        for (LauncherActivityInfo info : availableActivities) {
            ComponentName componentName = info.getComponentName();
            Intent intent = new Intent(Intent.ACTION_MAIN)
                    .setComponent(componentName)
                    .addCategory(Intent.CATEGORY_LAUNCHER)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            AppMetaData appMetaData = new AppMetaData(
                    info.getLabel().toString(),
                    componentName,
                    info.getBadgedIcon(0),
                    context -> AppLauncherUtils.launchApp(context, intent));
            launchableMap.put(componentName, appMetaData);
        }
        return new LauncherAppsInfo(launchableMap);
    }
}
