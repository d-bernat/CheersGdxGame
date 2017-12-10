package com.gamefactoryx.cheersapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.pay.*;
import com.gamefactoryx.cheersapp.controller.StageEnum;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkStageManager;
import com.gamefactoryx.cheersapp.model.INeverDoModel;
import com.gamefactoryx.cheersapp.tool.Configuration;

public class CheersGdxGame extends Game {

	// ----- app stores -------------------------
	public static final int APPSTORE_UNDEFINED	= 0;
	public static final int APPSTORE_GOOGLE 	= 1;
	public static final int APPSTORE_OUYA 		= 2;
	public static final int APPSTORE_AMAZON 	= 3;
	public static final int APPSTORE_DESKTOP 	= 4;


	private int isAppStore = APPSTORE_UNDEFINED;

	public final static String productID_fullVersion = "com.gamefactoryx.cheersapp.premium";
	private static com.gamefactoryx.cheersapp.ScreenLock screenLock;
	private static com.gamefactoryx.cheersapp.LinkHandler linkHandler;
	private boolean adMobActivated;
	private boolean adMobVisible;
	static com.gamefactoryx.cheersapp.PlatformResolver m_platformResolver;
	public PurchaseManagerConfig purchaseManagerConfig;
	private boolean playMusicFlag;
	private ActivityRequestHandler adMobRequestHandler;
	private InterstitialResolver interstitialResolver;
	private boolean disposed;
	private int adMobHeight;


	public PurchaseObserver purchaseObserver = new PurchaseObserver() {
		@Override
		public void handleRestore (Transaction[] transactions) {
			for (int i = 0; i < transactions.length; i++) {
				if (checkTransaction(transactions[i].getIdentifier(), true) == true) break;
			}
		}
		@Override
		public void handleRestoreError (Throwable e) {
			//throw new GdxRuntimeException(e);
		}
		@Override
		public void handleInstall () {	}

		@Override
		public void handleInstallError (Throwable e) {
			//throw new GdxRuntimeException(e);
		}
		@Override
		public void handlePurchase (Transaction transaction) {
			checkTransaction(transaction.getIdentifier(), false);
		}
		@Override
		public void handlePurchaseError (Throwable e) {	//--- Amazon IAP: this will be called for cancelled
			//throw new GdxRuntimeException(e);
		}
		@Override
		public void handlePurchaseCanceled () {	//--- will not be called by amazonIAP
		}
	};

	private boolean checkTransaction (String ID, boolean isRestore) {
		boolean ret = productID_fullVersion.equals(ID);
		if (ret) {
			Configuration.setPremium(true);
			persistPurchase();
			INeverDoModel.getNewInstance();
		}
		return ret;
	}

	public CheersGdxGame(com.gamefactoryx.cheersapp.ScreenLock screenLock, com.gamefactoryx.cheersapp.LinkHandler linkHandler,
						ActivityRequestHandler adMobRequestHandler, InterstitialResolver interstitialResolver ){
		this.screenLock = screenLock;
		this.linkHandler = linkHandler;
		this.adMobRequestHandler = adMobRequestHandler;
		this.interstitialResolver = interstitialResolver;

		setAppStore(APPSTORE_GOOGLE);	// change this if you deploy to another platform

		// ---- IAP: define products ---------------------
		purchaseManagerConfig = new PurchaseManagerConfig();
		purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(productID_fullVersion));
		//adMobActivated = true;
	}
	@Override
	public void create () {
		//screenLock.setOrientationPortrait();
		try{
			checkPurchase();
			Configuration.setPremium(true);
		}catch(Exception e) {
			getPlatformResolver().requestPurchaseRestore();    // check for purchases in the past
		}

		StageManager.getInstance().initialize(this);
		KongosDrinkStageManager.getInstance().initialize(this);
		com.gamefactoryx.cheersapp.tool.Resolution.setResolution();
		StageManager.getInstance().showStage(StageEnum.SPLASH_STAGE);
        new Thread(new Runnable(){
            long timeout = 600_000L;
            @Override
            public void run() {
                while(!disposed) {
                    /*long now = System.currentTimeMillis();
                    while (System.currentTimeMillis() - now < 600_000L) {
                    }*/
                    try {
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {

                    }
                    adMobActivated = true;
                }
            }
        }).start();

	}

	@Override
	public void pause(){
		super.pause();
		if(Configuration.isPlayMusic()){
			playMusicFlag = true;
			Configuration.setPlayMusic(false);
			Configuration.playMusic();
		}
	}

	@Override
	public void resume(){
		super.resume();
		if(playMusicFlag){
			Configuration.setPlayMusic(true);
			Configuration.playMusic();
			playMusicFlag = false;
		}
	}

    @Override
    public void dispose() {
        super.dispose();
        disposed = true;
    }

    public static com.gamefactoryx.cheersapp.ScreenLock getScreenLock(){
		return screenLock;
	}
	public static com.gamefactoryx.cheersapp.LinkHandler getLinkHandler(){
		return linkHandler;
	}

	public com.gamefactoryx.cheersapp.PlatformResolver getPlatformResolver() {
		return m_platformResolver;
	}
	public static void setPlatformResolver (com.gamefactoryx.cheersapp.PlatformResolver platformResolver) {
		m_platformResolver = platformResolver;
	}


	public int getAppStore () {
		return isAppStore;
	}
	public void setAppStore (int isAppStore) {
		this.isAppStore = isAppStore;
	}

    public ActivityRequestHandler getAdMobRequestHandler() {
        return adMobRequestHandler;
    }

	public InterstitialResolver getInterstitialResolver() {
		return interstitialResolver;
	}

	private void checkPurchase() throws Exception{
		FileHandle fHandle = Gdx.files.local("premium.txt");
		if(fHandle== null) throw new Exception();
		String[] tokens = fHandle.readString().split(":");
		if(tokens.length != 2) throw new Exception();
		if(!"true".equals(tokens[1])) throw new Exception();
	}

	private void persistPurchase(){
		FileHandle fHandle = Gdx.files.local("premium.txt");
		fHandle.writeString("purchase:true", false);
	}

	public boolean isAdMobActivated() {
		return adMobActivated;
	}

	public void setAdMobActivated(boolean adMobActivated) {
		this.adMobActivated = adMobActivated;
	}

	public int getAdMobHeight() {
		return adMobHeight;
	}

	public void setAdMobHeight(int adMobHeight) {
		this.adMobHeight = adMobHeight;
	}

	public boolean isAdMobVisible() {
		return adMobVisible;
	}

	public void setAdMobVisible(boolean adMobVisible) {
		this.adMobVisible = adMobVisible;
	}
}

