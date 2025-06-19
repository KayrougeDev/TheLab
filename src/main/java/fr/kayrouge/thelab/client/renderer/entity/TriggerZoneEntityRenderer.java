package fr.kayrouge.thelab.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.kayrouge.thelab.entity.triggerzone.TriggerZoneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.jetbrains.annotations.NotNull;

public class TriggerZoneEntityRenderer extends EntityRenderer<TriggerZoneEntity, EntityRenderState> {

    public TriggerZoneEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void render(EntityRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(renderState, poseStack, bufferSource, packedLight);
    }
}
