package fr.kayrouge.thelab.block.entity;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
@Getter
public class GhostBlockEntity extends BlockEntity {

    private BlockState displayBlock = Blocks.STONE.defaultBlockState();

    public GhostBlockEntity(BlockPos pos, BlockState blockState) {
        super(TLBlockEntityTypes.GHOST_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        CompoundTag tag = new CompoundTag();

        tag.put("displayBlock", NbtUtils.writeBlockState(displayBlock));

        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.@NotNull Provider lookupProvider) {
        this.displayBlock = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), (CompoundTag) tag.getCompound("displayBlock"));
        super.handleUpdateTag(tag, lookupProvider);
    }

}
