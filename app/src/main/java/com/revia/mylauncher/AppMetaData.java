package com.revia.mylauncher;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.function.Consumer;

final class AppMetaData {
    private final String mDisplayName;
    private final ComponentName mComponentName;
    private final Drawable mIcon;
    private final Consumer<Context> mLaunchCallback;

    AppMetaData(String mDisplayName, ComponentName mComponentName, Drawable mIcon, Consumer<Context> mLaunchCallback) {
        this.mDisplayName = mDisplayName;
        this.mComponentName = mComponentName;
        this.mIcon = mIcon;
        this.mLaunchCallback = mLaunchCallback;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public ComponentName getComponentName() {
        return mComponentName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public Consumer<Context> getLaunchCallback() {
        return mLaunchCallback;
    }
}
