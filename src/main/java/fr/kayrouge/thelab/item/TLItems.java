package fr.kayrouge.thelab.item;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.TLBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheLab.MODID);

    public static final DeferredItem<BlockItem> HERE_ONLY_BLOCK = ITEMS.registerSimpleBlockItem(TLBlocks.HERE_ONLY_BLOCK);
    public static final DeferredItem<BlockItem> SWE_BLOCK = ITEMS.registerSimpleBlockItem(TLBlocks.SWE_BLOCK);
    public static final DeferredItem<BlockItem> SWE_STAIR = ITEMS.registerSimpleBlockItem(TLBlocks.SWE_STAIR);
    public static final DeferredItem<BlockItem> ON_GROUND_ITEM_HOLDER = ITEMS.registerSimpleBlockItem(TLBlocks.ON_GROUND_ITEM_HOLDER);

    public static final DeferredItem<Item> CAMERA = ITEMS.registerItem("camera", Camera::new);
    public static final DeferredItem<Item> ON_GROUND_ITEM_DEFAULT = ITEMS.registerSimpleItem("on_ground_item_default");

}
