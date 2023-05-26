package org.game.keys;

public record KeyPressed(int keyCode) implements GameKeyEvent {
    @Override
    public void accept(GameKeyVisitor visitor) {
        visitor.visit(this);
    }
}
