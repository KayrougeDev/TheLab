package fr.kayrouge.thelab.block.interdimensional.hereonly;

import com.mojang.serialization.MapCodec;
import fr.kayrouge.thelab.block.entity.GhostBlockEntity;
import fr.kayrouge.thelab.block.interdimensional.swe.InterdimensionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GhostBlock extends Block implements InterdimensionalBlock {

    public static final MapCodec<GhostBlock> CODEC = simpleCodec(GhostBlock::new);

    public GhostBlock(BlockBehaviour.Properties properties) {
        super(InterdimensionalBlock.applyProperty(properties));
    }

    @Override
    protected @NotNull MapCodec<GhostBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new GhostBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return false;
    }

    @Override
    public boolean visibleByDefault() {
        return true;
    }
}
