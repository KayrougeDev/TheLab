package fr.kayrouge.thelab.item;

import fr.kayrouge.thelab.client.TheLabClient;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Camera extends Item {
    public Camera(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide()) return;
        if(!(entity instanceof Player player)) return;
        TheLabClient.useCamera = player.isHolding(TLItems.CAMERA.get());
    }
}
