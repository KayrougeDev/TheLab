package fr.kayrouge.thelab.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SWEBlockEntity extends BlockEntity {
    public SWEBlockEntity(BlockPos pos, BlockState blockState) {
        super(TLBlockEntityTypes.SWE_BLOCK_ENTITY.get(), pos, blockState);
    }
}
