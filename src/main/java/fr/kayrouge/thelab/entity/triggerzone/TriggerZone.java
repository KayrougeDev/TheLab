package fr.kayrouge.thelab.entity.triggerzone;

import fr.kayrouge.thelab.entity.triggerzone.scripts.IScript;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class TriggerZone extends Entity {

    private double zoneXSize = 2;
    private double zoneYSize = 2;
    private double zoneZSize = 1;
    private String script = "";
    private boolean isScriptCommand = true;
    private int updateDelay = 5;

    private int tickCounter = 0;

    public TriggerZone(EntityType<?> entityType, Level level) {
        this(entityType, level, 2, 2, 1);
    }

    public TriggerZone(EntityType<?> entityType, Level level, double xSize, double ySize, double zSize) {
        super(entityType, level);
        this.zoneXSize = xSize;
        this.zoneYSize = ySize;
        this.zoneZSize = zSize;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }


    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.zoneXSize = tag.getDouble("xSize");
        this.zoneYSize = tag.getDouble("ySize");
        this.zoneXSize = tag.getDouble("zSize");

        this.script = tag.getString("script");
        this.isScriptCommand = tag.getBoolean("isCommand");

        this.updateDelay = tag.getInt("updateDelay");
    }
    @Override
    protected AABB makeBoundingBox() {
        Vec3 position = position();
        return new AABB(position.x, position.y, position.z, position.x+this.zoneXSize, position.y+this.zoneYSize, position.z+this.zoneZSize);
    }

    public Set<Player> getCollidingPlayers() {
        Set<Player> players = new HashSet<>();
        for(Player player : level().players()) {
            if(player.getBoundingBox().intersects(this.getBoundingBox())) {
                players.add(player);
            }
        }
        return players;
    }

    @Override
    public void tick() {
        super.tick();
        if (++tickCounter > 5) {
            tickCounter = 0;
            if(!level().isClientSide()) {
                getCollidingPlayers().forEach(this::trigger);
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putDouble("xSize", this.zoneXSize);
        tag.putDouble("ySize", this.zoneYSize);
        tag.putDouble("zSize", this.zoneZSize);

        tag.putString("script", this.script);
        tag.putBoolean("isCommand", this.isScriptCommand);

        tag.putInt("updateDelay", this.updateDelay);
    }

    public void trigger(Player player) {
        if(isScriptCommand) {
            // TODO command
        }
        else {
            IScript iScript = IScript.getScript(this.script);
            if(iScript != null) {
                iScript.trigger(this, player);
            }
        }
    }
}
