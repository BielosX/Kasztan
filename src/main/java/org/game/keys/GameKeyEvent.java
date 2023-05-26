package org.game.keys;

public interface GameKeyEvent {
    int keyCode();
    void accept(GameKeyVisitor visitor);
}
