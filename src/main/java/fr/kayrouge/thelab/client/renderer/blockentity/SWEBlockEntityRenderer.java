package fr.kayrouge.thelab.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.kayrouge.thelab.block.entity.SWEBlockEntity;
import fr.kayrouge.thelab.client.TheLabClient;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

public record SWEBlockEntityRenderer(BlockEntityRendererProvider.Context context) implements BlockEntityRenderer<SWEBlockEntity> {

    @Override
    public void render(@NotNull SWEBlockEntity sweBlockEntity, float v, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int packedLight, int i1) {

        if(sweBlockEntity.getBlockState().getBlock() instanceof StairBlock) {
            BlockState state = Blocks.OAK_STAIRS.defaultBlockState();

            for(Property property : sweBlockEntity.getBlockState().getProperties()) {
                state = state.trySetValue(property, sweBlockEntity.getBlockState().getValue(property));
            }
            context.getBlockRenderDispatcher().renderSingleBlock(state, poseStack, multiBufferSource, packedLight, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());
            return;
        }

        context.getBlockRenderDispatcher().renderSingleBlock(Blocks.STONE.defaultBlockState(), poseStack, multiBufferSource, packedLight, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());


    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(@NotNull SWEBlockEntity blockEntity, @NotNull Vec3 cameraPos) {
        return TheLabClient.useCamera;
    }
}
