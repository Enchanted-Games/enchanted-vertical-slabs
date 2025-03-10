package games.enchanted.verticalslabs.util;

import net.minecraft.core.Direction;

public class DirectionUtil {
    public static int getBlockStateYRotationForDirection(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return 180;
            }
            case EAST -> {
                return 270;
            }
            case WEST -> {
                return 90;
            }
            default -> {
                return 0;
            }
        }
    }
}
