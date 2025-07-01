package fr.kayrouge.thelab.block.entity;

import fr.kayrouge.thelab.item.TLItems;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class OnGroundItemHolderBlockEntity extends SimpleBlockEntity {

    private ItemStack stack = new ItemStack(TLItems.ON_GROUND_ITEM_DEFAULT.get());

    public OnGroundItemHolderBlockEntity(BlockPos pos, BlockState blockState) {
        super(TLBlockEntityTypes.ON_GROUND_ITEM_HOLDER.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        Tag stackTag = tag.get("stack");
        if(stackTag != null) {
            ItemStack.parse(registries, stackTag).ifPresent(itemStack -> this.stack = itemStack);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("stack", stack.save(registries, tag));
    }

}
