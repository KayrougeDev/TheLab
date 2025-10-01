package fr.kayrouge.thelab.tabs;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.TLBlocks;
import fr.kayrouge.thelab.item.TLItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheLab.MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> THE_LAB_ITEMS = TABS.register("items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thelab.items")) //The language key for the title of your CreativeModeTab
            .icon(() -> TLItems.CAMERA.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TLItems.CAMERA.get());
            }).build());


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> THE_LAB_BLOCKS = TABS.register("blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thelab.blocks"))
            .withTabsBefore(THE_LAB_ITEMS.getId())
            .icon(() -> TLBlocks.SWE_BLOCK.asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TLItems.HERE_ONLY_BLOCK.get());
                output.accept(TLItems.SWE_BLOCK.get());
                output.accept(TLItems.SWE_STAIR.get());
                output.accept(TLItems.ON_GROUND_ITEM_HOLDER.get());
            }).build());
}
