package com.gamefactoryx.cheers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.gamefactoryx.cheers.controller.StageEnum;
import com.gamefactoryx.cheers.controller.StageManager;
import com.gamefactoryx.cheers.controller.kongosdrink.KongosDrinkStageManager;
import com.gamefactoryx.cheers.tool.Resolution;

public class CheersGdxGame extends Game {

	private static ScreenLock screenLock;
	private static LinkHandler linkHandler;
	public PurchaseManagerConfig purchaseManagerConfig;
	public PurchaseObserver purchaseObserver = new PurchaseObserver(){
		@Override
		public void handleRestore (Transaction[] transactions) {
			for (int i = 0; i < transactions.length; i++) {
				if (checkTransaction(transactions[i].getIdentifier()) == true) break;
			}
		}

		@Override
		public void handleRestoreError (Throwable e) {
			// getPlatformResolver().showToast("PurchaseObserver: handleRestoreError!");
			Gdx.app.log("ERROR", "PurchaseObserver: handleRestoreError!: " + e.getMessage());
			throw new GdxRuntimeException(e);
		}

		@Override
		public void handleInstall () {
			// getPlatformResolver().showToast("PurchaseObserver: installed successfully...");
			Gdx.app.log("handleInstall: ", "successfully..");
		}

		@Override
		public void handleInstallError (Throwable e) {
			// getPlatformResolver().showToast("PurchaseObserver: handleInstallError!");
			Gdx.app.log("ERROR", "PurchaseObserver: handleInstallError!: " + e.getMessage());
			throw new GdxRuntimeException(e);
		}

		@Override
		public void handlePurchase (Transaction transaction) {
			checkTransaction(transaction.getIdentifier());
		}

		@Override
		public void handlePurchaseError (Throwable e) {
			if (e.getMessage().equals("There has been a Problem with your Internet connection. Please try again later")) {

				// this check is needed because user-cancel is a handlePurchaseError too)
				// getPlatformResolver().showToast("handlePurchaseError: " + e.getMessage());
			}
			throw new GdxRuntimeException(e);
		}

		@Override
		public void handlePurchaseCanceled () {
		}

	};

	protected boolean checkTransaction (String ID) {
		boolean returnbool = false;
		/*if (productID_TANKWAR_MULTIPLAYER.equals(ID)) {
			PeDialogEvent purchasedmultiplayerEvent = new PeDialogEvent();
			purchasedmultiplayerEvent.setType(PeDialogEvent.Type.purchasedDualPlayer);
			notify(purchasedmultiplayerEvent);
			returnbool = true;
		}*/
		return returnbool;
	}

	public CheersGdxGame(ScreenLock screenLock, LinkHandler linkHandler){
		this.screenLock = screenLock;
		this.linkHandler = linkHandler;
	}
	@Override
	public void create () {
		//screenLock.setOrientationPortrait();
		StageManager.getInstance().initialize(this);
		KongosDrinkStageManager.getInstance().initialize(this);
		Resolution.setResolution();
		StageManager.getInstance().showStage(StageEnum.SPLASH_STAGE);

	}

	public static ScreenLock getScreenLock(){
		return screenLock;
	}
	public static LinkHandler getLinkHandler(){
		return linkHandler;
	}

}
