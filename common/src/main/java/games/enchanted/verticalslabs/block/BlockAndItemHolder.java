package games.enchanted.verticalslabs.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class BlockAndItemHolder {
    private final Block block;
    private final BlockItem blockItem;

    /**
     * Holds a Block and a BlockItem in one object
     *
     * @param block     the Block
     * @param blockItem the corresponding BlockItem
     */
    public BlockAndItemHolder(Block block, BlockItem blockItem) {
        this.block = block;
        this.blockItem = blockItem;
    }

    public Optional<Block> getBlock() {
        return Optional.of(this.block);
    }

    public Optional<BlockItem> getBlockItem() {
        return Optional.of(this.blockItem);
    }
}
