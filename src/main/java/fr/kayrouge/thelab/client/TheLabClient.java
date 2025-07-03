package fr.kayrouge.thelab.client;

import ca.weblite.objc.Client;
import foundry.veil.Veil;
import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.VeilRenderer;
import foundry.veil.api.client.render.post.PostPipeline;
import foundry.veil.api.client.render.post.PostProcessingManager;
import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.entity.TLBlockEntityTypes;
import fr.kayrouge.thelab.client.renderer.blockentity.HereOnlyBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.blockentity.OnGroundItemHolderBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.blockentity.SWEBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.blockentity.TestBlockEntityRenderer;
import fr.kayrouge.thelab.client.renderer.entity.TriggerZoneRenderer;
import fr.kayrouge.thelab.client.shaders.WaveShader;
import fr.kayrouge.thelab.client.utils.PlayerUtil;
import fr.kayrouge.thelab.entity.TLEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.TheEndGatewayRenderer;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(value = TheLab.MODID, dist = Dist.CLIENT)
public class TheLabClient {

    public static boolean useCamera = false;
    public static final boolean DEBUG = true;

    public static final WaveShader WAVE_SHADER = new WaveShader();


    public TheLabClient(IEventBus bus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        bus.addListener(this::registerEntityRenderers);
        NeoForge.EVENT_BUS.addListener(this::shadersActivator);
        NeoForge.EVENT_BUS.addListener(WAVE_SHADER::onTick);

        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);

        bus.register(TLKeyMappings.class);
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Entity
        event.registerEntityRenderer(TLEntities.TRIGGER_ZONE.get(), TriggerZoneRenderer::new);

        // Block entity
        event.registerBlockEntityRenderer(TLBlockEntityTypes.HERE_ONLY_BLOCK_ENTITY.get(), HereOnlyBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityTypes.SWE_BLOCK_ENTITY.get(), SWEBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityTypes.ON_GROUND_ITEM_HOLDER.get(), OnGroundItemHolderBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityTypes.TEST.get(), TestBlockEntityRenderer::new);
    }

    public void shadersActivator(RenderLevelStageEvent event) {
        PostProcessingManager manager = VeilRenderSystem.renderer().getPostProcessingManager();
        if(ClientConfig.ENABLE_TEST_SHADER.get()) {
            PostPipeline testPipeline = manager.getPipeline(TheLab.path("test"));
            if(testPipeline == null) return;
            manager.runPipeline(testPipeline);
        }

        if(useCamera) {
            cameraShader();
            useCamera = PlayerUtil.useCamera();
        }
        WAVE_SHADER.runPipeline();
    }

    public void cameraShader() {
        if(ClientConfig.DISABLE_SHADERS.get()) return;
        PostProcessingManager manager = VeilRenderSystem.renderer().getPostProcessingManager();
        PostPipeline pipeline = manager.getPipeline(TheLab.path("cube"));
        if(pipeline == null) return;
        manager.runPipeline(pipeline);
    }


}