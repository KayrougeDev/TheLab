package fr.kayrouge.thelab.entity.triggerzone.scripts;

import fr.kayrouge.thelab.entity.triggerzone.TriggerZoneEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Test implements IScript {

    @Override
    public void trigger(TriggerZoneEntity zone, Player player) {
        player.displayClientMessage(Component.literal("Triggered zone at "+zone.blockPosition().toShortString()), false);
    }
}
