package org.game.keys;

public interface GameKeyVisitor {
    void visit(KeyPressed key);
    void visit(KeyReleased key);
}
