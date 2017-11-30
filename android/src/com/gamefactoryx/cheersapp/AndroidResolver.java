package com.gamefactoryx.cheersapp;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.pay.PurchaseManagerConfig;


public class AndroidResolver extends com.gamefactoryx.cheersapp.PlatformResolver {

    private final static String GOOGLEKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgRJRNybzv16lVqDzoioJ1QcpB2gCIjy0gvMd3fFAKjec4LkivoGiqOiOBiPcX6mkpk/4fchyKAcl1Bmmf+c/YUSDgTp5BIumXtMFqgODVnAUATThLyIwdjld/jpD0EQDGkjs8ZgQfHOeoHJycczBHP37bYaSSg6HF/8HiahDYjXazFmwDLt06sCa8ueuT7Z0Psnj0h5mcHkuEychDvcreLaVv0o2bR8Egebv+HyRn1TPPhfS2rB3m38A6uvKWqH125TPHYeNFoVXMTsCMryUKAyL0Cys1G/NRZE7V1yawHkqGW7iJkcAJhtb9NAX4dyJXjNBubeWCTSbBDmVKXlrpQIDAQAB";
    static final int RC_REQUEST = 10001;	// (arbitrary) request code for the purchase flow

    public AndroidApplication androidApplication;
    public com.gamefactoryx.cheersapp.CheersGdxGame cheers;

    public AndroidResolver(com.gamefactoryx.cheersapp.CheersGdxGame cheers, AndroidApplication androidApplication) {
        //super(cheers);
        this.cheers = cheers;
        this.androidApplication = androidApplication;

        PurchaseManagerConfig config = cheers.purchaseManagerConfig;
        config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);

        initializeIAP(null, cheers.purchaseObserver, config);
    }
}