package games.enchanted.verticalslabs.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockAndItem {
    private final Block block;
    private final BlockItem blockItem;

    public BlockAndItem(Block block, BlockItem blockItem) {
        this.block = block;
        this.blockItem = blockItem;
    }

    public Block asBlock() {
        return this.block;
    }

    public BlockItem asBlockItem() {
        return this.blockItem;
    }
}
