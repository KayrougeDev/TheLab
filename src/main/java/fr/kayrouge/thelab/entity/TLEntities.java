package fr.kayrouge.thelab.entity;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.entity.triggerzone.TriggerZone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TLEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, TheLab.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<TriggerZone>> TRIGGER_ZONE =
            ENTITIES.register("trigger_zone",
                    () -> EntityType.Builder.<TriggerZone>of(TriggerZone::new, MobCategory.MISC)
                            .sized(0.9F, 1.4F)
                            .clientTrackingRange(10)
                            .build("trigger_zone"));

}
