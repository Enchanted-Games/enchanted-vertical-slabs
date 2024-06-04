package games.enchanted.verticalslabs.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockAndItem {
    private final Block block;
    private final BlockItem blockItem;

    /**
     * Holds a Block and a BlockItem in one object
     *
     * @param block     the Block
     * @param blockItem the corresponding BlockItem
     */
    public BlockAndItem(Block block, BlockItem blockItem) {
        this.block = block;
        this.blockItem = blockItem;
    }

    public Block getBlock() {
        return this.block;
    }

    public BlockItem getBlockItem() {
        return this.blockItem;
    }
}
