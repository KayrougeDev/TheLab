package fr.kayrouge.thelab.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.kayrouge.thelab.block.entity.GhostBlockEntity;
import fr.kayrouge.thelab.client.TheLabClient;
import fr.kayrouge.thelab.client.utils.PlayerUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record GhostBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<GhostBlockEntity> {

    @Override
    public void render(@NotNull GhostBlockEntity ghostBlockEntity, float v, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        BlockState state = ghostBlockEntity.getDisplayBlock();
        if (state == null || state.getBlock() == Blocks.AIR) {
            state = Blocks.STONE.defaultBlockState();
        }

        context().getBlockRenderDispatcher().renderSingleBlock(
                state,
                poseStack,
                multiBufferSource,
                (int)(packedLight*0.9),
                OverlayTexture.NO_OVERLAY,
                ghostBlockEntity.getModelData(),
                RenderType.cutout()
        );
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(@NotNull GhostBlockEntity blockEntity, @NotNull Vec3 cameraPos) {
        return !TheLabClient.useCamera;
    }
}
