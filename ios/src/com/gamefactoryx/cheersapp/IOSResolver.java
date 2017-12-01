package com.gamefactoryx.cheersapp;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.pay.PurchaseManagerConfig;


public class IOSResolver extends PlatformResolver {

    private final static String GOOGLEKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhnyn6FsoeJcdqVaBlNfeUuxYfavrbctsFeskI8btCRPLNmf9rEBpyP2xSFRHVMraaEslYryoELsx0WbrTDwTUeEtHk8Q2mJ8+ZMkwY904t46KKTImKYB6qF6PK6NNI0rksv0r/foTxS16UZkykmBs7u6tXz7S9RGk+jeiAD96FdjeF94bAkAskg6w5Uz4z5rSi6wgMBxVXPq71SmQno+9Np5K1gIJ/dFmShoptoDsv3SFOGiuHViJJwDE89D2XHKGDJC8HTyOpjqmS94WNa1+Z3yFZGr1cUfGcI6q+EqH2Z1OGqedkXlzfEfJ8SSvH7s4jSYpJ5N+sa33kaHVn7crwIDAQAB";
    static final int RC_REQUEST = 10001;	// (arbitrary) request code for the purchase flow

    public IOSApplication iosApplication;
    public CheersGdxGame cheers;

    public IOSResolver(CheersGdxGame cheers, IOSApplication iosApplication) {
        //super(cheers);
        this.cheers = cheers;
        this.iosApplication = iosApplication;

        PurchaseManagerConfig config = cheers.purchaseManagerConfig;

        //TODO
        //config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLEKEY);

        initializeIAP(null, cheers.purchaseObserver, config);
    }
}