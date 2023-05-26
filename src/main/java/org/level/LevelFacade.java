package org.level;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Graphics2D;

@Service
@RequiredArgsConstructor
public class LevelFacade implements Drawable {
    private final Player player;


    @Override
    public void draw(Graphics2D graphics2D) {
        player.draw(graphics2D);
    }
}
