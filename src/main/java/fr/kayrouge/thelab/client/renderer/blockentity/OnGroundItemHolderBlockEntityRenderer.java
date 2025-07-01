package fr.kayrouge.thelab.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.kayrouge.thelab.block.entity.OnGroundItemHolderBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public record OnGroundItemHolderBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<OnGroundItemHolderBlockEntity> {

    @Override
    public void render(@NotNull OnGroundItemHolderBlockEntity entity, float v,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource,
                       int i, int i1) {

        poseStack.pushPose();

        if(entity.getLevel() == null) return;
        Vec3 vec3 = entity.getBlockState().getOffset(entity.getLevel(), entity.getBlockPos());

        double translation = 0.5d;
        float rotation = (float)((Math.abs(vec3.x)*234) * Math.abs(vec3.z)*123);

        poseStack.translate(translation+vec3.x, 0, translation+vec3.z);

        // put item on floor
        poseStack.mulPose(Axis.XN.rotationDegrees(90));

        // TODO stack item

        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        float size = 0.7f;
        poseStack.scale(size, size, size);

        context().getItemRenderer().renderStatic(entity.getStack(),
                ItemDisplayContext.NONE, i, i1, poseStack, multiBufferSource,
                entity.getLevel(), new Random().nextInt(0, 10));

        poseStack.popPose();

    }

}
