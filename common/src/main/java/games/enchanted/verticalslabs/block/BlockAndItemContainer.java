package games.enchanted.verticalslabs.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public record BlockAndItemContainer(Block block, BlockItem blockItem) {
    /**
     * Holds a Block and a BlockItem in one object
     *
     * @param block     the Block
     * @param blockItem the corresponding BlockItem
     */
    public BlockAndItemContainer {
    }
}
