package org.level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public abstract class GameObject {
    protected float positionX;
    protected float positionY;
    protected int width;
    protected int height;

    private boolean betweenOnY(GameObject other) {
        return (positionY >= other.positionY && positionY <= other.positionY + other.height) ||
                (positionY + height >= other.positionY && positionY + height <= other.positionY + height);
    }

    private boolean betweenOnX(GameObject other) {
        return (positionX + width >= other.positionX && positionX + width <= other.positionX + other.width) ||
                (positionX <= other.positionX + other.width && positionX >= other.positionX);
    }
    public Set<CollisionType> collide(GameObject other) {
        Set<CollisionType> result = new HashSet<>();
        if (positionX + width >= other.positionX && betweenOnY(other)) {
            result.add(CollisionType.RIGHT);
        }
        if (other.positionX + other.width >= positionX && betweenOnY(other)) {
            result.add(CollisionType.LEFT);
        }
        if (positionY <= other.positionY + other.height && betweenOnY(other)) {
            result.add(CollisionType.TOP);
        }
        if (positionY + height >= other.positionY && betweenOnX(other)) {
            result.add(CollisionType.BOTTOM);
        }
        return result;
    }
}
