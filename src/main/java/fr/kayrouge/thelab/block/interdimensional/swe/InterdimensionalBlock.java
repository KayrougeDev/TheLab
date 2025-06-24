package fr.kayrouge.thelab.block.interdimensional.swe;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public interface InterdimensionalBlock extends EntityBlock {

    boolean visibleByDefault();

    static BlockBehaviour.Properties applyProperty(BlockBehaviour.Properties properties) {
        return properties.noOcclusion().isSuffocating((blockState, blockGetter, blockPos) -> false);
    }
}
