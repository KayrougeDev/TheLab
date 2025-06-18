package fr.kayrouge.thelab.entity;

import fr.kayrouge.thelab.TheLab;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TLEntities {

    public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(TheLab.MODID);

    public static final Supplier<EntityType<TriggerZoneEntity>> TRIGGER_ZONE = ENTITIES.registerEntityType("trigger_zone", TriggerZoneEntity::new, MobCategory.MISC,
            builder -> builder.updateInterval(5));

}
