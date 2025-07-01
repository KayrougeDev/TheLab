package fr.kayrouge.thelab.client;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.entity.TLBlockEntityTypes;
import fr.kayrouge.thelab.client.renderer.blockentity.HereOnlyBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.blockentity.OnGroundItemHolderBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.blockentity.SWEBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.entity.TriggerZoneRenderer;
import fr.kayrouge.thelab.client.utils.PlayerUtil;
import fr.kayrouge.thelab.entity.TLEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = TheLab.MODID, dist = Dist.CLIENT)
public class TheLabClient {

    public static boolean useCamera = false;

    public static final boolean DEBUG = true;

    public TheLabClient(IEventBus bus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        bus.addListener(this::registerEntityRenderers);
        NeoForge.EVENT_BUS.addListener(this::shadersActivator);

        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Entity
        event.registerEntityRenderer(TLEntities.TRIGGER_ZONE.get(), TriggerZoneRenderer::new);

        // Block entity
        event.registerBlockEntityRenderer(TLBlockEntityTypes.HERE_ONLY_BLOCK_ENTITY.get(), HereOnlyBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityTypes.SWE_BLOCK_ENTITY.get(), SWEBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityTypes.ON_GROUND_ITEM_HOLDER.get(), OnGroundItemHolderBlockEntityRenderer::new);
    }

    public void shadersActivator(RenderLevelStageEvent event) {
        if(useCamera) {
            cameraShader();
            useCamera = PlayerUtil.useCamera();
        }
    }

    public void cameraShader() {
        if(ClientConfig.DISABLE_SHADERS.get()) return;
        PostProcessingManager manager = VeilRenderSystem.renderer().getPostProcessingManager();
        PostPipeline pipeline = manager.getPipeline(TheLab.path("cube"));
        if(pipeline == null) return;
        //pipeline.getOrCreateUniform("iResolution").setVector(Minecraft.getInstance().getWindow().getWidth(),
        //        Minecraft.getInstance().getWindow().getHeight());
        manager.runPipeline(pipeline);
    }
}