package com.gamefactoryx.cheers.controller.kongosdrink;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.gamefactoryx.cheers.model.kongosdrink.Card;
import com.gamefactoryx.cheers.model.kongosdrink.KongosDrinkMainModel;
import com.gamefactoryx.cheers.tool.kongosdrink.CardTextParser;
import com.gamefactoryx.cheers.tool.kongosdrink.Configuration;
import com.gamefactoryx.cheers.tool.kongosdrink.CoorTransformator;
import com.gamefactoryx.cheers.view.kongosdrink.KongosDrinkMainScreen;
import com.gamefactoryx.cheers.view.kongosdrink.CtrlSprite;

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

    public KongosDrinkMainController(final KongosDrinkMainScreen screen) {
        super(screen);
        setScreenLock(0);
        KongosDrinkMainModel.getNewInstance();
        lastSelectedCard.put(1, 0);
        lastSelectedCard.put(2, 0);
        lastSelectedCard.put(3, 0);
        lastSelectedCard.put(5, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("common/horn.mp3"));
                Map<Integer, String> backup = new HashMap<>();
                backup.putAll(KongosDrinkMainModel.getInstance().getModusTypeTextMap());

                long accInterval = 0L;
                List<Integer> rnds = new ArrayList<>();
                for (int i = 0; i < 11; i++)
                    rnds.add(i);
                while (!KongosDrinkMainModel.getInstance().isFinished()) {
                    long time = System.currentTimeMillis();

                    while (System.currentTimeMillis() < time + Configuration.getModusTypeInterval() &&
                            !KongosDrinkMainModel.getInstance().isFinished()) {
                    }
                    accInterval += Configuration.getModusTypeInterval();
                    if (!KongosDrinkMainModel.getInstance().isFinished()) {
                        Collections.shuffle(rnds);
                        if (accInterval / Configuration.getModusTypeInterval() < 4)
                            KongosDrinkMainModel.getInstance().setModus((int) (Math.pow(2.0d, rnds.get(0))));
                        else if (accInterval / Configuration.getModusTypeInterval() < 8) {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1)));
                            KongosDrinkMainModel.getInstance().setModus(modus);
                        } else {
                            int modus = (int) (Math.pow(2.0d, rnds.get(0))) + (int) (Math.pow(2.0d, rnds.get(1))) + (int) (Math.pow(2.0d, rnds.get(2)));
                            KongosDrinkMainModel.getInstance().setModus(modus);
                        }
                    }
                    for (Integer i : backup.keySet()) {
                        KongosDrinkMainModel.getInstance().getModusTypeTextMap().put(i,
                                CardTextParser.replacePlayerNames(backup.get(i)));
                    }
                    if (KongosDrinkMainModel.getInstance().getModus() != 0)
                        mp3Music.play();

                }
                KongosDrinkMainModel.getInstance().setModus(0);
            }
        }).start();

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (CtrlSprite ms : getScreen().getModusSprite()) {
            if (ms.isActive()) {
                int x = CoorTransformator.getX(960, screenX);
                int y = CoorTransformator.getY(540, screenY);
                if (x >= ms.getX() &&
                        x <= ms.getX() + ms.getWidth() &&
                        y >= ms.getY() &&
                        y <= ms.getY() + ms.getHeight()) {
                    ms.setClicked(true);
                    clickedOnModusHelp = true;
                    KongosDrinkMainModel.getInstance().setTextBoxDisplayed(true);
                }
            }
        }

        if (KongosDrinkMainModel.getInstance().isAnimationRunning())
            return true;


        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        KongosDrinkMainModel.getInstance().setTextBoxDisplayed(false);

        if (clickedOnModusHelp) {
            clickedOnModusHelp = false;
            for (CtrlSprite ms : getScreen().getModusSprite()) {
                ms.setClicked(false);
            }

            return true;
        }

        if (KongosDrinkMainModel.getInstance().isAnimationRunning())
            return true;

        if (!super.touchUp(screenX, screenY, pointer, button)) {
            KongosDrinkMainModel.getInstance().setXxcoor(0);
            KongosDrinkMainModel.getInstance().setXcoor(0);
            KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            KongosDrinkMainModel.getInstance().setPosition(1);
            KongosDrinkMainModel.getInstance().setStep(KongosDrinkMainModel.Step.LEVEL);
            suspend = true;
            KongosDrinkMainModel.getInstance().setFinished(true);
            return true;
        }

        if (KongosDrinkMainModel.getInstance().isFinished()) {
            return true;
        }

        int x = CoorTransformator.getX(960, screenX);
        int y = CoorTransformator.getY(540, screenY);

        if (getScreen().getMainButtonsSprite()[0].isActive()) {
            /*if (x >= getScreen().getMainButtonsSprite()[0].getX() &&
                    x <= getScreen().getMainButtonsSprite()[0].getX() + getScreen().getMainButtonsSprite()[0].getWidth() * 3.0f &&
                    y >= getScreen().getMainButtonsSprite()[0].getY() &&
                    y <= getScreen().getMainButtonsSprite()[0].getY() + getScreen().getMainButtonsSprite()[0].getHeight() * 3.0f) {*/

            gotoPlayer(KongosDrinkMainModel.getInstance().getPlayerIndex());
            getScreen().getMainButtonsSprite()[0].setActive(false);
            getScreen().getMainButtonsSprite()[1].setActive(true);
            getScreen().getMainButtonsSprite()[2].setActive(false);
            getScreen().getMainButtonsSprite()[3].setActive(false);

        } else if (getScreen().getMainButtonsSprite()[1].isActive()) {

            //if click on some level
            //if no move
            //determinate level
            int level = 0;

            float xa =  getScreen().getMainButtonsSprite()[1].getX();
            float ya =  getScreen().getMainButtonsSprite()[1].getY();
            float xb =  getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
            float yb =  getScreen().getMainButtonsSprite()[1].getY();
            float xc =  getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth()/2;
            float yc =  getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight()/2;
            if(pointInTriangle( (float)x, (float)y, xa, ya, xb, yb, xc, yc)) level = 5;

            if(level == 0) {
                xb = getScreen().getMainButtonsSprite()[1].getX();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 2;
            }

            if(level == 0) {
                xa =  getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                ya =  getScreen().getMainButtonsSprite()[1].getY();
                xb = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 3;
            }

            if(level == 0) {
                xa =  getScreen().getMainButtonsSprite()[1].getX();
                ya =  getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                xb = getScreen().getMainButtonsSprite()[1].getX() + getScreen().getMainButtonsSprite()[1].getWidth();
                yb = getScreen().getMainButtonsSprite()[1].getY() + getScreen().getMainButtonsSprite()[1].getHeight();
                if (pointInTriangle((float) x, (float) y, xa, ya, xb, yb, xc, yc)) level = 1;
            }


//            List<Integer> level = Arrays.asList(1, 2, 3, 5);
//            Collections.shuffle(level);
//            KongosDrinkMainModel.getInstance().setLevel(level.get(0));

            if(level > 0) {
                KongosDrinkMainModel.getInstance().setLevel(level);
                //select active card
                int cardsSize = KongosDrinkMainModel.getInstance().getCards().get(KongosDrinkMainModel.getInstance().getLevel()).size();
                int lastSelectedCardIndex = lastSelectedCard.get(KongosDrinkMainModel.getInstance().getLevel());
                if (lastSelectedCardIndex < cardsSize - 1)
                    ++lastSelectedCardIndex;
                else
                    lastSelectedCardIndex = 0;
                Card c = KongosDrinkMainModel.getInstance().getCards().get(KongosDrinkMainModel.getInstance().getLevel()).get(lastSelectedCardIndex);
                c.setText(CardTextParser.replacePlayerNames(c.getOriginText()));
                KongosDrinkMainModel.getInstance().setActiveCard(c);
                lastSelectedCard.put(KongosDrinkMainModel.getInstance().getLevel(), lastSelectedCardIndex);
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

            KongosDrinkMainModel.getInstance().getActiveCard().setText(KongosDrinkMainModel.getInstance().getActiveCard().getOriginText());
            KongosDrinkMainModel.getInstance().setActiveCard(null);
            setNextPlayerActive();


        } else if (x >= getScreen().getMainButtonsSprite()[3].getX() &&
                x <= getScreen().getMainButtonsSprite()[3].getX() + getScreen().getMainButtonsSprite()[3].getWidth() &&
                y >= getScreen().getMainButtonsSprite()[3].getY() &&
                y <= getScreen().getMainButtonsSprite()[3].getY() + getScreen().getMainButtonsSprite()[3].getHeight()) {

            getScreen().getMainButtonsSprite()[0].setActive(true);
            getScreen().getMainButtonsSprite()[1].setActive(false);
            getScreen().getMainButtonsSprite()[2].setActive(false);
            getScreen().getMainButtonsSprite()[3].setActive(false);

            int point = KongosDrinkMainModel.getInstance().getActiveCard().getPoint();
            KongosDrinkMainModel.getInstance().getActiveCard().setText(KongosDrinkMainModel.getInstance().getActiveCard().getOriginText());
            KongosDrinkMainModel.getInstance().setActiveCard(null);
            if (point != 0) {
                int nextPoint = Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex()).getPosition() +
                        point;
                if(nextPoint >= Configuration.getGameSize().getValue()) nextPoint = Configuration.getGameSize().getValue() + 3;
                movePlayer(KongosDrinkMainModel.getInstance().getPlayerIndex(), nextPoint);
            }
            else {
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
        movePlayground(Configuration.getPlayers().get(playerIndex).getPosition());
    }

    private void movePlayer(int playerIndex, int position) {
        Configuration.getPlayers().get(playerIndex).setActive(true);
        movePlayground(position);
    }


    private void movePlayground(final int position) {
        if (KongosDrinkMainModel.getInstance().getPosition() == position) {
            return;
        }


        suspend = false;


        if (!KongosDrinkMainModel.getInstance().isAnimationRunning()) {
            KongosDrinkMainModel.getInstance().setAnimationRunning(true);
            forward = KongosDrinkMainModel.getInstance().getPosition() < position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!suspend) {
                        for (int i = 1; i < KongosDrinkMainModel.getInstance().getCountOfTextures(); i++) {
                            for (int j = 0; j < KongosDrinkMainModel.getInstance().getEnablePlayersSize(); j++)
                                if (Configuration.getPlayers().get(j).isActive())
                                    if (forward)
                                        Configuration.getPlayers().get(j).setRotate(-10.0f);
                                    else
                                        Configuration.getPlayers().get(j).setRotate(10.0f);

                            if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && forward) {
                                KongosDrinkMainModel.getInstance().setIndex(i);
                                KongosDrinkMainModel.getInstance().setXcoor(0);
                            }

                            if (KongosDrinkMainModel.getInstance().getXxcoor() == 960 * i && !forward) {
                                KongosDrinkMainModel.getInstance().setIndex(i - 1);
                                KongosDrinkMainModel.getInstance().setXcoor(960);

                            }
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() >= 960 * KongosDrinkMainModel.getInstance().getCountOfTextures() &&
                                forward
                                && position == -1) {
                        }

                        if (KongosDrinkMainModel.getInstance().getPosition() != -1 &&
                                KongosDrinkMainModel.getInstance().getXxcoor() == (position - 1) * Configuration.KongosDrink.DISTANCE_BETWEEN_TWO_FIELDS) {
                            KongosDrinkMainModel.getInstance().setAnimationRunning(false);
                            break;
                        }

                        if (KongosDrinkMainModel.getInstance().getXxcoor() <= 0 && !forward) {
                            KongosDrinkMainModel.getInstance().setXxcoor(0);
                            KongosDrinkMainModel.getInstance().setXcoor(0);
                            KongosDrinkMainModel.getInstance().setIndex(0);
                            KongosDrinkMainModel.getInstance().setAnimationRunning(false);
                            break;
                        }


                        if (forward) {
                            KongosDrinkMainModel.getInstance().setXxcoor(KongosDrinkMainModel.getInstance().getXxcoor() + 1);
                            KongosDrinkMainModel.getInstance().setXcoor(KongosDrinkMainModel.getInstance().getXcoor() + 1);
                        } else {
                            KongosDrinkMainModel.getInstance().setXxcoor(KongosDrinkMainModel.getInstance().getXxcoor() - 1);
                            KongosDrinkMainModel.getInstance().setXcoor(KongosDrinkMainModel.getInstance().getXcoor() - 1);
                        }

                        long time = System.currentTimeMillis();
                        while (System.currentTimeMillis() < time + delay) {
                        }
                    }
                    KongosDrinkMainModel.getInstance().setPosition(position);
                    forward = false;
                    for (int i = 0; i < KongosDrinkMainModel.getInstance().getEnablePlayersSize(); i++) {
                        if (Configuration.getPlayers().get(i).isActive()) {
                            Configuration.getPlayers().get(i).setRotate(0.0f);
                            Configuration.getPlayers().get(i).setActive(false);
                            Configuration.getPlayers().get(i).setPosition(position);
                            if (Configuration.getPlayers().get(i).getPosition() >= Configuration.getGameSize().getValue())
                                Configuration.getPlayers().get(i).setFinished(true);
                            boolean finished = true;
                            for (int j = 0; j < KongosDrinkMainModel.getInstance().getEnablePlayersSize(); j++) {
                                if (!Configuration.getPlayers().get(j).isFinished()) {
                                    finished = false;
                                    break;
                                }
                            }

                            KongosDrinkMainModel.getInstance().setFinished(finished);

                            if (!finished) {
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
        if (KongosDrinkMainModel.getInstance().isFinished())
            return;


        do {
            if (KongosDrinkMainModel.getInstance().getPlayerIndex() >= KongosDrinkMainModel.getInstance().getEnablePlayersSize() - 1)
                KongosDrinkMainModel.getInstance().setPlayerIndex(0);
            else
                KongosDrinkMainModel.getInstance().setPlayerIndex(KongosDrinkMainModel.getInstance().getPlayerIndex() + 1);
        } while (Configuration.getPlayers().get(KongosDrinkMainModel.getInstance().getPlayerIndex()).isFinished());

    }

    private boolean pointInTriangle(float xp, float yp, float xa, float ya, float xb, float yb, float xc, float yc)
    {
        float s = ya * xc - xa * yc + (yc - ya) * xp + (xa - xc) * yp;
        float t = xa * yb - ya * xb + (ya - yb) * xp + (xb - xa) * yp;

        if ((s < 0) != (t < 0))
            return false;

        float A = -yb * xc + ya * (xc - xb) + xa * (yb - yc) + xb * yc;
        if (A < 0.0)
        {
            s = -s;
            t = -t;
            A = -A;
        }
        return s > 0 && t > 0 && (s + t) <= A;
    }
}
