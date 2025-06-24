package fr.kayrouge.thelab.client.utils;

import fr.kayrouge.thelab.item.TLItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class PlayerUtil {

    public static boolean useCamera() {
        LocalPlayer player = Minecraft.getInstance().player;
        if(player == null) return false;
        return player.isHolding(TLItems.CAMERA.asItem());
    }

}
