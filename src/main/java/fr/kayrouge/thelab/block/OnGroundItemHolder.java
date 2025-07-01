package fr.kayrouge.thelab.block;

import com.mojang.serialization.MapCodec;
import fr.kayrouge.thelab.block.entity.OnGroundItemHolderBlockEntity;
import fr.kayrouge.thelab.item.TLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OnGroundItemHolder extends BaseEntityBlock {

    public static final MapCodec<OnGroundItemHolder> CODEC = simpleCodec(OnGroundItemHolder::new);

    protected OnGroundItemHolder(Properties properties) {
        super(properties.noCollission().offsetType(OffsetType.XZ).noOcclusion().isValidSpawn(Blocks::never)
                .isRedstoneConductor(TLBlocks::never).isSuffocating(TLBlocks::never).isViewBlocking(TLBlocks::never));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new OnGroundItemHolderBlockEntity(blockPos, blockState);
    }

    @Override
    protected boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return true;
    }

    @Override
    protected float getShadeBrightness(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
        return 1f;
    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.empty();
    }

    public static class Event {
        @SubscribeEvent
        public void onRightClick(PlayerInteractEvent.RightClickBlock e) {
            BlockState blockState = e.getLevel().getBlockState(e.getPos());
            if(blockState.getBlock() != TLBlocks.ON_GROUND_ITEM_HOLDER.get()) return;
            Player player = e.getEntity();
            Level level = e.getLevel();
            BlockPos pos = e.getPos();
            ItemStack stack = e.getItemStack();
            if(!level.isClientSide()) {
                if(player.isCreative() && player.isCrouching()) {
                    if(level.getBlockEntity(pos) instanceof OnGroundItemHolderBlockEntity entity) {
                        if(stack.getItem() != Items.AIR) {
                            entity.setStack(stack);
                            entity.setChanged();
                            player.getInventory().removeItem(stack);
                            level.sendBlockUpdated(pos, blockState, blockState, 0);
                        }
                    }
                }
                else {
                    if(level.getBlockEntity(pos) instanceof OnGroundItemHolderBlockEntity entity) dropItem(level, entity);
                }
            }
        }

        private void dropItem(Level level, OnGroundItemHolderBlockEntity entity) {
            if(entity.getStack().getItem() != TLItems.ON_GROUND_ITEM_DEFAULT.get()
                    && entity.getStack().getItem() != Items.AIR) {
                BlockPos pos = entity.getBlockPos();
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), entity.getStack());
                level.addFreshEntity(item);
                level.setBlock(entity.getBlockPos(), Blocks.AIR.defaultBlockState(), 0);
            }

        }
    }
}
