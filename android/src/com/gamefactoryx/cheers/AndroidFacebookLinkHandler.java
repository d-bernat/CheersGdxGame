package com.gamefactoryx.cheers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class AndroidFacebookLinkHandler implements FacebookLinkHandler {
    Activity mActivity;

    public AndroidFacebookLinkHandler(Activity activity){
        mActivity = activity;
    }
    @Override
    public void openFacebookPage(String facebookAppURI, String facebookWebURL) {
        Intent intent = null;
        try {
            mActivity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookAppURI));
        } catch (PackageManager.NameNotFoundException e) {
            intent = new Intent (Intent.ACTION_VIEW, Uri.parse(facebookWebURL));
        }
        mActivity.startActivity(intent);
    }
}
