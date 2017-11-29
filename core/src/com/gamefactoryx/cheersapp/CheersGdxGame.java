package com.gamefactoryx.cheersapp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.gamefactoryx.cheersapp.controller.StageEnum;
import com.gamefactoryx.cheersapp.controller.StageManager;
import com.gamefactoryx.cheersapp.controller.kongosdrink.KongosDrinkStageManager;

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
	public PurchaseObserver purchaseObserver = new PurchaseObserver() {
		@Override
		public void handleRestore (Transaction[] transactions) {
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
		}
	};

	protected boolean checkTransaction (String ID, boolean isRestore) {
		boolean returnbool = false;

		if (productID_fullVersion.equals(ID)) {
			Gdx.app.log("checkTransaction", "full version found!");

			//----- put your logic for full version here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			returnbool = true;
		}
		return returnbool;
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
