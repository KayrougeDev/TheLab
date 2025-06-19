package fr.kayrouge.thelab.client;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.client.renderer.entity.TriggerZoneEntityRenderer;
import fr.kayrouge.thelab.entity.TLEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TheLab.MODID, dist = Dist.CLIENT)
public class TheLabClient {
    public TheLabClient(IEventBus bus, ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        bus.addListener(this::registerEntityRenderers);
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TLEntities.TRIGGER_ZONE.get(), TriggerZoneEntityRenderer::new);
    }
}