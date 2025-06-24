package fr.kayrouge.thelab.block.interdimensional.swe;

import fr.kayrouge.thelab.block.entity.SWEBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SWEBlock extends Block implements InterdimensionalBlock {

    public SWEBlock(Properties properties) {
        super(InterdimensionalBlock.applyProperty(properties));
    }

    @Override
    public boolean visibleByDefault() {
        return false;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SWEBlockEntity(blockPos, blockState);
    }
}
