package fr.kayrouge.thelab.client.renderer.blockentity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import fr.kayrouge.thelab.block.entity.TestBlockEntity;
import fr.kayrouge.thelab.client.rendertype.TLRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public record TestBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<TestBlockEntity> {

    @Override
    public void render(@NotNull TestBlockEntity testBlockEntity, float v,
                       @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource,
                       int i, int i1) {

        RenderType renderType = TLRenderTypes.test();
        if(renderType == null) {
            return;
        }

        poseStack.pushPose();

        Matrix4f matrix4f = poseStack.last().pose();

        // Utilisez le bon format de vertex (POSITION_TEX)
        BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);

        this.renderCube(testBlockEntity, matrix4f, builder, i);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        renderType.draw(builder.buildOrThrow());
        poseStack.popPose();
    }

    private void renderCube(TestBlockEntity blockEntity, Matrix4f pose, VertexConsumer consumer, int light) {
        float f = 0;
        float f1 = 1;
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH, light);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH, light);
        this.renderFace(blockEntity, pose, consumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST, light);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST, light);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, f, f, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN, light);
        this.renderFace(blockEntity, pose, consumer, 0.0F, 1.0F, f1, f1, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP, light);
    }

    private void renderFace(TestBlockEntity blockEntity, Matrix4f pose, VertexConsumer consumer,
                            float x0, float x1, float y0, float y1, float z0, float z1, float z2, float z3,
                            Direction direction, int light) {
        consumer.addVertex(pose, x0, y0, z0).setColor(1.0F,1.0F,1.0F,1.0F).setUv(1, 1).setLight(light);
        consumer.addVertex(pose, x1, y0, z1).setColor(1.0F,1.0F,1.0F,1.0F).setUv(1, 0).setLight(light);
        consumer.addVertex(pose, x1, y1, z2).setColor(1.0F,1.0F,1.0F,1.0F).setUv(0, 0).setLight(light);
        consumer.addVertex(pose, x0, y1, z3).setColor(1.0F,1.0F,1.0F,1.0F).setUv(0, 1).setLight(light);

    }
}
