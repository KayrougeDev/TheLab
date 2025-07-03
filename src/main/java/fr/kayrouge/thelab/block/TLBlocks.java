package fr.kayrouge.thelab.block;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.interdimensional.hereonly.HereOnlyBlock;
import fr.kayrouge.thelab.block.interdimensional.swe.SWEBlock;
import fr.kayrouge.thelab.block.interdimensional.swe.SWEStair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TheLab.MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<HereOnlyBlock> HERE_ONLY_BLOCK = BLOCKS.registerBlock("here_only_block", HereOnlyBlock::new);
    public static final DeferredBlock<SWEBlock> SWE_BLOCK = BLOCKS.registerBlock("swe_block", SWEBlock::new);
    public static final DeferredBlock<SWEStair> SWE_STAIR = BLOCKS.registerBlock("swe_stair", SWEStair::new);
    public static final DeferredBlock<OnGroundItemHolder> ON_GROUND_ITEM_HOLDER = BLOCKS.registerBlock("on_ground_item_holder", OnGroundItemHolder::new);
    public static final DeferredBlock<TestBlock> TEST = BLOCKS.registerBlock("test", TestBlock::new);

    public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }
}
