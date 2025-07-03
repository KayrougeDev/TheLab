package fr.kayrouge.thelab.client.rendertype;

import foundry.veil.api.client.render.rendertype.VeilRenderType;
import fr.kayrouge.thelab.TheLab;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class TLRenderTypes {

    private static final ResourceLocation TEST = TheLab.path("test");

    public static @Nullable RenderType test() {
        return VeilRenderType.get(TEST);
    }
}
