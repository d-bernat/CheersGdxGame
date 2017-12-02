package com.gamefactoryx.cheersapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
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
	static com.gamefactoryx.cheersapp.PlatformResolver m_platformResolver;
	public PurchaseManagerConfig purchaseManagerConfig;
	private boolean playMusicFlag;
	public PurchaseObserver purchaseObserver = new PurchaseObserver() {
		@Override
		public void handleRestore (Transaction[] transactions) {
			Gdx.app.log("**************", "handle restore callback invoked");
			for (int i = 0; i < transactions.length; i++) {
				if (checkTransaction(transactions[i].getIdentifier(), true) == true) break;
			}
		}
		@Override
		public void handleRestoreError (Throwable e) {
			throw new GdxRuntimeException(e);
		}
		@Override
		public void handleInstall () {	}

		@Override
		public void handleInstallError (Throwable e) {
			Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
			throw new GdxRuntimeException(e);
		}
		@Override
		public void handlePurchase (Transaction transaction) {
			checkTransaction(transaction.getIdentifier(), false);
		}
		@Override
		public void handlePurchaseError (Throwable e) {	//--- Amazon IAP: this will be called for cancelled
			throw new GdxRuntimeException(e);
		}
		@Override
		public void handlePurchaseCanceled () {	//--- will not be called by amazonIAP
			Gdx.app.log("**************", "Transaction cancelled");
		}
	};

	private boolean checkTransaction (String ID, boolean isRestore) {
		boolean ret = productID_fullVersion.equals(ID);
		if (ret) {
			Gdx.app.log("checkTransaction", "full version found!");
			Configuration.setPremium(true);
			INeverDoModel.getNewInstance();
		}
		else
			Gdx.app.log("checkTransaction", "full version not found!");

		if(isRestore)
			Gdx.app.log("checkTransaction", "after restore");
		else
			Gdx.app.log("checkTransaction", "after purchase");
			//----- put your logic for full version here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		return ret;
	}

	public CheersGdxGame(com.gamefactoryx.cheersapp.ScreenLock screenLock, com.gamefactoryx.cheersapp.LinkHandler linkHandler){
		this.screenLock = screenLock;
		this.linkHandler = linkHandler;
		setAppStore(APPSTORE_GOOGLE);	// change this if you deploy to another platform

		// ---- IAP: define products ---------------------
		purchaseManagerConfig = new PurchaseManagerConfig();
		purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(productID_fullVersion));
	}
	@Override
	public void create () {
		//screenLock.setOrientationPortrait();
		getPlatformResolver().requestPurchaseRestore();	// check for purchases in the past
		StageManager.getInstance().initialize(this);
		KongosDrinkStageManager.getInstance().initialize(this);
		com.gamefactoryx.cheersapp.tool.Resolution.setResolution();
		StageManager.getInstance().showStage(StageEnum.SPLASH_STAGE);

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

}
