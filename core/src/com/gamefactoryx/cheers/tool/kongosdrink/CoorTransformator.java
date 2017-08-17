package com.gamefactoryx.cheers.tool.kongosdrink;

import com.gamefactoryx.cheers.tool.Resolution;

public class CoorTransformator {
    public static int getX(int viewportX, int screenX){
        return (int) (screenX/(Resolution.getGameWorldWidthLandscape()/viewportX) - viewportX/2);
    }

    public static int getY(int viewportY, int screenY){
        return (int) (viewportY/2 - screenY/(Resolution.getGameWorldHeightLandscape()/viewportY));
    }
}
