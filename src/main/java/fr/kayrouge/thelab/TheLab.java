package fr.kayrouge.thelab;

import fr.kayrouge.thelab.block.OnGroundItemHolder;
import fr.kayrouge.thelab.block.TLBlocks;
import fr.kayrouge.thelab.block.entity.TLBlockEntityTypes;
import fr.kayrouge.thelab.entity.TLEntities;
import fr.kayrouge.thelab.item.TLItems;
import fr.kayrouge.thelab.tabs.TLTabs;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(TheLab.MODID)
public class TheLab {

    public static final String MODID = "thelab";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TheLab(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        TLBlocks.BLOCKS.register(modEventBus);
        TLBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        TLItems.ITEMS.register(modEventBus);
        TLTabs.TABS.register(modEventBus);
        TLEntities.ENTITIES.register(modEventBus);

        NeoForge.EVENT_BUS.register(new OnGroundItemHolder.Event());
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }



    public static ResourceLocation path(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
