package com.gamefactoryx.cheersapp.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamefactoryx.cheersapp.tool.Configuration;
import com.gamefactoryx.cheersapp.tool.Resolution;

public class CheckedButton extends Sprite {

    private boolean checked;
    private static Texture premiumTexture = new Texture("common/premium.png");
    private Sprite premiumSprite;

    public CheckedButton(Texture txt){
        super(txt);
        setPremiumSprite();
    }

    public CheckedButton(Texture txt, boolean checked){
        super(txt);
        this.checked = checked;
        setPremiumSprite();
    }


    public boolean isAllowed() {
        return checked ? Configuration.isPremium(): true;
    }


    public void draw(Batch batch) {
        float oldAlpha = getColor().a;
        float newAlpha = isAllowed() ? 1.0f: 0.5f;
        setAlpha(oldAlpha * newAlpha);
        super.draw(batch);
        setAlpha(oldAlpha);
    }


    public void draw(Batch batch, float alphaModulation) {
        /*if(alphaModulation == 1.0f)
            super.draw(batch, isAllowed()? 1.0f: 0.5f);
        else*/
        super.draw(batch, alphaModulation);
        if(!isAllowed()){
            premiumSprite.setPosition(getX() + getWidth() - premiumSprite.getWidth() * 0.5f , getY() + getHeight() - premiumSprite.getHeight() * 0.5f);
            premiumSprite.draw(batch, 1.0f);
        }
    }



    private void setPremiumSprite(){
        premiumSprite = new Sprite(premiumTexture);
        premiumSprite.setSize(Resolution.getGameWorldWidthPortrait() * 0.1f, Resolution.getGameWorldWidthPortrait() * 0.1f);
    }

}
