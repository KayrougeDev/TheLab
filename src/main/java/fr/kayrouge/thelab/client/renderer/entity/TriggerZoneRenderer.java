package fr.kayrouge.thelab.client.renderer.entity;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.entity.triggerzone.TriggerZone;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TriggerZoneRenderer extends EntityRenderer<TriggerZone> {

    public TriggerZoneRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(TriggerZone triggerZoneEntity) {
        return TheLab.path("");
    }

}
