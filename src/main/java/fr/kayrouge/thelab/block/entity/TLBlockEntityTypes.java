package fr.kayrouge.thelab.block.entity;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.block.TLBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TheLab.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HereOnlyBlockEntity>> HERE_ONLY_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "here_only_block", () -> BlockEntityType.Builder.of(HereOnlyBlockEntity::new, TLBlocks.HERE_ONLY_BLOCK.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SWEBlockEntity>> SWE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "swe_block", () -> BlockEntityType.Builder.of(SWEBlockEntity::new,
                    TLBlocks.SWE_STAIR.get(), TLBlocks.SWE_BLOCK.get()
            ).build(null)
    );
}
