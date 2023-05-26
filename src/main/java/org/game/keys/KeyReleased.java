package org.game.keys;

public record KeyReleased(int keyCode) implements GameKeyEvent {
    @Override
    public void accept(GameKeyVisitor visitor) {
        visitor.visit(this);
    }
}
