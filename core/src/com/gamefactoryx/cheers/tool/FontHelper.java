package com.gamefactoryx.cheers.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by bernat on 18.05.2017.
 */
public class FontHelper {
    private final static FileHandle fontFile = Gdx.files.internal("font/TIMESS.ttf");
    private final static FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private static GlyphLayout glyphLayout = new GlyphLayout();

    public static FileHandle getFontFile(){
        return fontFile;
    }

    public static FreeTypeFontGenerator.FreeTypeFontParameter getParameter(){
        return parameter;
    }

    public static GlyphLayout getGlyphLayout(){
        return glyphLayout;
    }
    private FontHelper(){

    }
}
