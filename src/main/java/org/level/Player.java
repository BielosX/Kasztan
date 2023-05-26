package org.level;

import lombok.RequiredArgsConstructor;

import java.awt.Graphics2D;

@RequiredArgsConstructor
public class Player implements Drawable {
    private final float positionX;
    private final float positionY;

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(Math.round(positionX), Math.round(positionY), 30, 30);
    }
}
