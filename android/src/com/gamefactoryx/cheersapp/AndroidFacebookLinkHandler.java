package com.gamefactoryx.cheersapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class AndroidFacebookLinkHandler implements com.gamefactoryx.cheersapp.LinkHandler {
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

    @Override
    public void openInstagramPage(String instagramWebURL) {
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( instagramWebURL ) );
        mActivity.startActivity( intent );
    }
}
