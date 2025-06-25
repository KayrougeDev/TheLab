package fr.kayrouge.thelab.block.interdimensional.hereonly;

import com.mojang.serialization.MapCodec;
import fr.kayrouge.thelab.block.TLBlocks;
import fr.kayrouge.thelab.block.entity.HereOnlyBlockEntity;
import fr.kayrouge.thelab.block.interdimensional.swe.InterdimensionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HereOnlyBlock extends DirectionalBlock implements InterdimensionalBlock {

    public static final MapCodec<HereOnlyBlock> CODEC = simpleCodec(HereOnlyBlock::new);

    public HereOnlyBlock(BlockBehaviour.Properties properties) {
        super(InterdimensionalBlock.applyProperty(properties));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            Direction direction = state.getValue(FACING);
            BlockPos originalPos = pos.relative(direction);
            BlockState originalBlock = level.getBlockState(originalPos);
            if(originalBlock.getBlock() == Blocks.AIR) {
                originalBlock = Blocks.STONE.defaultBlockState();
            }
            else if(originalBlock.getBlock() == TLBlocks.HERE_ONLY_BLOCK.get()
                    && level.getBlockEntity(originalPos) instanceof HereOnlyBlockEntity originalEntity) {
                originalBlock = originalEntity.getDisplayBlock();
            }


            if(level.getBlockEntity(pos) instanceof HereOnlyBlockEntity entity) {
                entity.setDisplayBlock(originalBlock);
                entity.setChanged();
            }
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected @NotNull MapCodec<HereOnlyBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new HereOnlyBlockEntity(blockPos, blockState);
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
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return InterdimensionalBlock.getCollisionShape(this, context, super.getCollisionShape(state, level, pos, context));
    }

    @Override
    public boolean visibleByDefault() {
        return true;
    }
}
