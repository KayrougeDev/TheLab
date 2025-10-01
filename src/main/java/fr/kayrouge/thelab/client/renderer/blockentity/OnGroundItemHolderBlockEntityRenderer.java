package fr.kayrouge.thelab.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.kayrouge.thelab.block.entity.OnGroundItemHolderBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public record OnGroundItemHolderBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<OnGroundItemHolderBlockEntity> {

    @Override
    public void render(@NotNull OnGroundItemHolderBlockEntity entity, float v,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource,
                       int light, int i1) {

        boolean isFullBlock = entity.getStack().getItem() instanceof BlockItem bi && bi.getBlock().defaultBlockState().canOcclude();

        int numberOfItem = Math.min(4, entity.getStack().getCount());

        poseStack.pushPose();

        if(entity.getLevel() == null) return;
        Vec3 vec3 = entity.getBlockState().getOffset(entity.getLevel(), entity.getBlockPos());

        double translation = 0.5d;
        float rotation = (float)((Math.abs(vec3.x)*234) * Math.abs(vec3.z)*123);

        // translate item depending on block offset
        float itemHeight = isFullBlock ? 0.1f : 0f;
        poseStack.translate(translation+vec3.x, itemHeight, translation+vec3.z);

        // put item on floor
        poseStack.mulPose(Axis.XN.rotationDegrees(90));

        // TODO stack item

        // translate item depending on block offset
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));

        // change item size
        float size = isFullBlock ? 0.2f : 0.7f;
        poseStack.scale(size, size, size);

        if(isFullBlock) {
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }


        context().getItemRenderer().renderStatic(entity.getStack(),
                ItemDisplayContext.NONE, light, i1, poseStack, multiBufferSource,
                entity.getLevel(), new Random().nextInt(0, 10));

        if(!isFullBlock && numberOfItem > 1) {
            for(int i = 1; i < numberOfItem; i++) {
                poseStack.pushPose();

                // TODO use blockstate offset for a better displacement
                poseStack.translate(0.2, 0.1, 0.05*i);
                poseStack.mulPose(Axis.ZN.rotationDegrees(60*((float) i * 1.25f)));
                poseStack.mulPose(Axis.XP.rotationDegrees(5*(i+1)));

                context().getItemRenderer().renderStatic(entity.getStack(),
                        ItemDisplayContext.NONE, light, i1, poseStack, multiBufferSource,
                        entity.getLevel(), new Random().nextInt(0, 10));
                poseStack.popPose();
            }
        }

        poseStack.popPose();

    }

}
