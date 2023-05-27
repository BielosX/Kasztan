package org.level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameObject {
    protected float positionX;
    protected float positionY;
    protected int width;
    protected int height;

    public boolean collide(GameObject other) {
        return positionX <= other.positionX + other.width &&
                positionX + width >= other.positionX &&
                positionY <= other.positionY + other.height &&
                positionY + height >= other.positionY;
    }
}
