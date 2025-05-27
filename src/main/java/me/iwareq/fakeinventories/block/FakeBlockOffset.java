package me.iwareq.fakeinventories.block;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;

public enum FakeBlockOffset {
    STANDARD {
        @Override
        public Vector3 getOffset(Player player) {
            Vector3 offset = player.getDirectionVector();
            offset.x *= -(1 + player.getWidth());
            offset.y *= -(1 + player.getHeight());
            offset.z *= -(1 + player.getWidth());
            return offset;
        }
    },
    HIDDEN {
        @Override
        public Vector3 getOffset(Player player) {
            Level level = player.getLevel();
            Vector3 playerPos = player.getPosition();
            int minY = level.getMinBlockY();
            int maxY = level.getMaxBlockY();

            Vector3 downOffset = new Vector3(0, -3, 0);
            Vector3 downPos = playerPos.add(downOffset);

            if (downPos.y >= minY) {
                Vector3 aboveDownPos = downPos.add(0, 1, 0);
                if (aboveDownPos.y <= maxY && !level.getBlock(aboveDownPos).isAir()) {
                    return downOffset;
                }
            }

            double pitch = player.getPitch();
            boolean lookingDown = pitch > 45;
            boolean lookingUp = pitch < -45;

            Vector3 offset;
            if (lookingUp) {
                offset = new Vector3(0, -3, 0);
            } else if (lookingDown) {
                offset = new Vector3(0, 3, 0);
            } else {
                offset = new Vector3(0, -3, 0);
            }

            Vector3 testPos = playerPos.add(offset);
            if (testPos.y >= minY && testPos.y <= maxY) {
                return offset;
            }

            Vector3 invertedOffset = offset.multiply(-1);
            Vector3 invertedPos = playerPos.add(invertedOffset);
            if (invertedPos.y >= minY && invertedPos.y <= maxY) {
                return invertedOffset;
            }

            if (testPos.y < minY) {
                Vector3 up4Offset = new Vector3(0, 4, 0);
                Vector3 up4Pos = playerPos.add(up4Offset);
                if (up4Pos.y <= maxY) {
                    return up4Offset;
                }
            }

            if (testPos.y > maxY) {
                return null;
            }

            return offset;
        }
    };

    public abstract Vector3 getOffset(Player player);
}
