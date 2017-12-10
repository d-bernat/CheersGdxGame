package com.gamefactoryx.cheersapp.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheersapp.CheersGdxGame;
import com.gamefactoryx.cheersapp.controller.StageManager;

import java.util.*;


/**
 * Created by bernat on 28.04.2017.
 */
@SuppressWarnings("DefaultFileTemplate")
final public class KongosDrinkMainController extends KongosDrinkAbstractController {

    private boolean forward = true;
    //    private boolean isRunning = false;
    private boolean suspend = false;
    private int delay = 1;
    private boolean clickedOnModusHelp;
    private Map<Integer, Integer> lastSelectedCard = new HashMap<>();

    public KongosDrinkMainController(final com.gamefactoryx.cheersapp.view.kongosdrink.KongosDrinkMainScreen screen) {
        super(screen);
        setScreenLock(0);
        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getNewInstance();
        lastSelectedCard.put(1, 0);
        lastSelectedCard.put(2, 0);
        lastSelectedCard.put(3, 0);
        lastSelectedCard.put(5, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("common/horn.mp3"));
                Map<Integer, String> backup = new HashMap<>();
                backup.putAll(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModusTypeTextMap());

                long accInterval = 0L;
                List<Integer> rnds = new ArrayList<>();
                for (int i = 0; i < 11; i++)
                    rnds.add(i);
                while (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()) {
                    long time = System.currentTimeMillis();

                    while (System.currentTimeMillis() < time +  com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getModusTypeInterval() &&
                            !com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()) {
                    }

                    accInterval += com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getModusTypeInterval();
                    if (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()) {
                        Collections.shuffle(rnds);
                        if (accInterval / com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getModusTypeInterval() < 4)
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setModus((int) (Math.pow(2.0d, rnds.get(0))));
                        else if (accInterval / com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getModusTypeInterval() < 8) {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1)));
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setModus(modus);
                        } else {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1))) + (int) (Math.pow(2.0d, rnds.get(2)));
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setModus(modus);
                        }
                    }
                    for (Integer i : backup.keySet()) {
                        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModusTypeTextMap().put(i,
                                com.gamefactoryx.cheersapp.tool.kongosdrink.CardTextParser.replacePlayerNames(backup.get(i)));
                    }
                    if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getModus() != 0)
                        mp3Music.play();

                }
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setModus(0);
            }
        }).start();

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //StageManager.getInstance().getGame().getPlatformResolver().requestPurchase(CheersGdxGame.productID_fullVersion);
        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isAnimationRunning())
            return true;

        for (com.gamefactoryx.cheersapp.view.kongosdrink.CtrlSprite ms : getScreen().getModusSprite()) {
            if (ms.isActive()) {
                int x = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getX(960, screenX);
                int y = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getY(540, screenY);
                if (x >= ms.getX() &&
                        x <= ms.getX() + ms.getWidth() &&
                        y >= ms.getY() &&
                        y <= ms.getY() + ms.getHeight()) {
                    ms.setClicked(true);
                    clickedOnModusHelp = true;
                    com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setTextBoxDisplayed(true);
                }
            }
        }

        if (getScreen().getMainButtonsSprite()[1].isActive()) {
            int x = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getX(960, screenX);
            int y = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getY(540, screenY);
            if (x >= getScreen().getMainButtonsSprite()[0].getX() &&
                    x <= getScreen().getMainButtonsSprite()[0].getX() + getScreen().getMainButtonsSprite()[0].getWidth() &&
                    y >= getScreen().getMainButtonsSprite()[0].getY() &&
                    y <= getScreen().getMainButtonsSprite()[0].getY() + getScreen().getMainButtonsSprite()[0].getHeight()) {
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setWhoIsWho(true);
            }
        }



        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setTextBoxDisplayed(false);

        if (clickedOnModusHelp) {
            clickedOnModusHelp = false;
            for (com.gamefactoryx.cheersapp.view.kongosdrink.CtrlSprite ms : getScreen().getModusSprite()) {
                ms.setClicked(false);
            }

            return true;
        }

        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isAnimationRunning())
            return true;

        if (!super.touchUp(screenX, screenY, pointer, button)) {
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXxcoor(0);
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(0);
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setPosition(1);
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setStep(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.Step.LEVEL);
            suspend = true;
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setFinished(true);
            return true;
        }

        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()) {
            return true;
        }

        int x = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getX(960, screenX);
        int y = com.gamefactoryx.cheersapp.tool.kongosdrink.CoorTransformator.getY(540, screenY);

        if (getScreen().getMainButtonsSprite()[0].isActive()) {
            gotoPlayer(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex());
            getScreen().getMainButtonsSprite()[0].setActive(false);
            getScreen().getMainButtonsSprite()[1].setActive(true);
            getScreen().getMainButtonsSprite()[2].setActive(false);
            getScreen().getMainButtonsSprite()[3].setActive(false);

        } else if (getScreen().getMainButtonsSprite()[1].isActive()) {

            /*if (x >= getScreen().getMainButtonsSprite()[0].getX() &&
                    x <= getScreen().getMainButtonsSprite()[0].getX() + getScreen().getMainButtonsSprite()[0].getWidth() &&
                    y >= getScreen().getMainButtonsSprite()[0].getY() &&
                    y <= getScreen().getMainButtonsSprite()[0].getY() + getScreen().getMainButtonsSprite()[0].getHeight()) {*/
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setWhoIsWho(false);
            //}

            //if click on some level
            //if no move
            //determinate level
            int level = 0;

            float xa = getScreen().getMainButtonsSprite()[1].getX();
            float ya = getScreen().getMainButtonsSprite()[1].getY();
            float xb = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
            float yb = getScreen().getMainButtonsSprite()[1].getY();
            float xc = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth() / 2;
            float yc = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight() / 2;
            if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 5;

            if (level == 0) {
                xb = getScreen().getMainButtonsSprite()[1].getX();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 2;
            }

            if (level == 0) {
                xa = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                ya = getScreen().getMainButtonsSprite()[1].getY();
                xb = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 3;
            }

            if (level == 0) {
                xa = getScreen().getMainButtonsSprite()[1].getX();
                ya = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                xb = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 1;
            }


//            List<Integer> level = Arrays.asList(1, 2, 3, 5);
//            Collections.shuffle(level);
//            KongosDrinkMainModel.getInstance().setLevel(level.get(0));

            if (level > 0) {
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setLevel(level);
                //select active card
                int cardsSize = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getCards().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getLevel()).size();
                int lastSelectedCardIndex = lastSelectedCard.get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getLevel());
                if (lastSelectedCardIndex < cardsSize - 1)
                    ++lastSelectedCardIndex;
                else
                    lastSelectedCardIndex = 0;
                com.gamefactoryx.cheersapp.model.kongosdrink.Card c = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getCards().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getLevel()).get(lastSelectedCardIndex);
                c.setText(com.gamefactoryx.cheersapp.tool.kongosdrink.CardTextParser.replacePlayerNames(c.getOriginText()));
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setActiveCard(c);
                lastSelectedCard.put(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getLevel(), lastSelectedCardIndex);
                //card selected
                getScreen().getMainButtonsSprite()[0].setActive(false);
                getScreen().getMainButtonsSprite()[1].setActive(false);
                getScreen().getMainButtonsSprite()[2].setActive(true);
                getScreen().getMainButtonsSprite()[3].setActive(c.getPoint() > 0);
            }
            //}
        } else if (x >= getScreen().getMainButtonsSprite()[2].getX() &&
                x <= getScreen().getMainButtonsSprite()[2].getX() + getScreen().getMainButtonsSprite()[2].getWidth() &&
                y >= getScreen().getMainButtonsSprite()[2].getY() &&
                y <= getScreen().getMainButtonsSprite()[2].getY() + getScreen().getMainButtonsSprite()[2].getHeight()) {

            getScreen().getMainButtonsSprite()[0].setActive(true);
            getScreen().getMainButtonsSprite()[1].setActive(false);
            getScreen().getMainButtonsSprite()[2].setActive(false);
            getScreen().getMainButtonsSprite()[3].setActive(false);

            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard().setText(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard().getOriginText());
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setActiveCard(null);
            setNextPlayerActive();


        } else if (x >= getScreen().getMainButtonsSprite()[3].getX() &&
                x <= getScreen().getMainButtonsSprite()[3].getX() + getScreen().getMainButtonsSprite()[3].getWidth() &&
                y >= getScreen().getMainButtonsSprite()[3].getY() &&
                y <= getScreen().getMainButtonsSprite()[3].getY() + getScreen().getMainButtonsSprite()[3].getHeight()) {

            getScreen().getMainButtonsSprite()[0].setActive(true);
            getScreen().getMainButtonsSprite()[1].setActive(false);
            getScreen().getMainButtonsSprite()[2].setActive(false);
            getScreen().getMainButtonsSprite()[3].setActive(false);

            int point = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard().getPoint();
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard().setText(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getActiveCard().getOriginText());
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setActiveCard(null);
            if (point != 0) {
                int nextPoint = com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).getPosition() +
                        point;
                if (nextPoint >= com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getGameSize().getValue())
                    nextPoint = com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getGameSize().getValue() + 1;
                movePlayer(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex(), nextPoint);
            } else {
                setNextPlayerActive();
            }

        }


        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return true;
    }

    private void gotoPlayer(int playerIndex) {
        movePlayground(com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(playerIndex).getPosition());
    }

    private void movePlayer(int playerIndex, int position) {
        com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(playerIndex).setActive(true);
        movePlayground(position);
    }


    private void movePlayground(final int position) {
        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPosition() == position) {
            return;
        }


        suspend = false;


        if (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isAnimationRunning()) {
            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setAnimationRunning(true);
            forward = com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPosition() < position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!suspend) {
                        for (int i = 1; i < com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getCountOfTextures(); i++) {
                            for (int j = 0; j < com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getEnablePlayersSize(); j++)
                                if (com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(j).isActive())
                                    if (forward)
                                        com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(j).setRotate(-10.0f);
                                    else
                                        com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(j).setRotate(10.0f);

                            if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && forward) {
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setIndex(i);
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(0);
                            }

                            if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && !forward) {
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setIndex(i - 1);
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(960);

                            }
                        }

                        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() >= 960 * com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getCountOfTextures() &&
                                forward
                                && position == -1) {
                        }

                        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPosition() != -1 &&
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() == (position - 1) * com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS) {
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setAnimationRunning(false);
                            break;
                        }

                        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXxcoor(0);
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(0);
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setIndex(0);
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setAnimationRunning(false);
                            break;
                        }


                        if (forward) {
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXxcoor(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() + 1);
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXcoor() + 1);
                        } else {
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXxcoor(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXxcoor() - 1);
                            com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setXcoor(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getXcoor() - 1);
                        }

                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis() < time + delay) {
                        }
                    }
                    com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setPosition(position);
                    forward = false;
                    for (int i = 0; i < com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getEnablePlayersSize(); i++) {
                        if (com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).isActive()) {
                            com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).setRotate(0.0f);
                            com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).setActive(false);
                            com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).setPosition(position);
                            if (com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).getPosition() >= com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getGameSize().getValue()) {
                                com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(i).setFinished(true);
                                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setFinished(true);
                            }
                            //boolean finished = true;
                            /*for (int j = 0; j < KongosDrinkMainModel.getInstance().getEnablePlayersSize(); j++) {
                                if (!Configuration.getInstance().getPlayers().get(j).isFinished()) {
                                    finished = false;
                                    break;
                                }
                            }*/



                            if (!com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished()) {
                                setNextPlayerActive();
                            } else {
                                getScreen().getMainButtonsSprite()[0].setActive(false);
                                getScreen().getMainButtonsSprite()[1].setActive(false);
                                getScreen().getMainButtonsSprite()[2].setActive(false);
                                getScreen().getMainButtonsSprite()[3].setActive(false);
                            }
                        }
                    }
                }
            }).start();
        }
    }

    private void setNextPlayerActive() {
        if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().isFinished())
            return;


        do {
            if (com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex() >= com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getEnablePlayersSize() - 1)
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            else
                com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().setPlayerIndex(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex() + 1);
        }
        while (com.gamefactoryx.cheersapp.tool.kongosdrink.Configuration.getInstance().getPlayers().get(com.gamefactoryx.cheersapp.model.kongosdrink.KongosDrinkMainModel.getInstance().getPlayerIndex()).isFinished());

    }

    private boolean pointInTriangle(float xp, float yp, float xa, float ya, float xb, float yb, float xc, float yc) {
        float s = ya * xc - xa * yc + (yc - ya) * xp + (xa - xc) * yp;
        float t = xa * yb - ya * xb + (ya - yb) * xp + (xb - xa) * yp;

        if ((s < 0) != (t < 0))
            return false;

        float A = -yb * xc + ya * (xc - xb) + xa * (yb - yc) + xb * yc;
        if (A < 0.0) {
            s = -s;
            t = -t;
            A = -A;
        }
        return s > 0 && t > 0 && (s + t) <= A;
    }
}
