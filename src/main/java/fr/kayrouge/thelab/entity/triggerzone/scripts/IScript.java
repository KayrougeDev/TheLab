package fr.kayrouge.thelab.entity.triggerzone.scripts;

import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.entity.triggerzone.TriggerZoneEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public interface IScript {

    void trigger(TriggerZoneEntity zone, Player player);

    static IScript getScript(String script) {
        Class<? extends IScript> clazz = getClass(script);
        if(clazz == null) return null;
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
            TheLab.LOGGER.warn("Can't instantiate script '{}', script class should NOT have a constructor with parameters or be private", script);
            return null;
        }
    }

    @Nullable
    private static Class<? extends IScript> getClass(String script) {
        try {
            Class<?> clazz = Class.forName("fr.kayrouge.thelab.entity.triggerzone.scripts."+script);
            if (!IScript.class.isAssignableFrom(clazz)) return null;
            @SuppressWarnings("unchecked")
            Class<? extends IScript> scriptClass = (Class<IScript>) clazz;
            return scriptClass;
        }
        catch (ClassNotFoundException e) {
            TheLab.LOGGER.warn("Can't find script '{}'", script);
            return null;
        }
    }

}
