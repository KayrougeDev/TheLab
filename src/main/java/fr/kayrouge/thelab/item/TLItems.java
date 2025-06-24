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

    public static final DeferredItem<BlockItem> GHOST_BLOCK = ITEMS.registerSimpleBlockItem(TLBlocks.GHOST_BLOCK);
    public static final DeferredItem<BlockItem> SWE_BLOCK = ITEMS.registerSimpleBlockItem(TLBlocks.SWE_BLOCK);
    public static final DeferredItem<BlockItem> SWE_STAIR = ITEMS.registerSimpleBlockItem(TLBlocks.SWE_STAIR);

    public static final DeferredItem<Item> CAMERA = ITEMS.registerItem("camera", Camera::new);

}
