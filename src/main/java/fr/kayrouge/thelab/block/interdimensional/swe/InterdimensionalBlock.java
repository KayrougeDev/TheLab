package fr.kayrouge.thelab.block.interdimensional.swe;

import fr.kayrouge.thelab.item.TLItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public interface InterdimensionalBlock extends EntityBlock {

    boolean visibleByDefault();

    static BlockBehaviour.Properties applyProperty(BlockBehaviour.Properties properties) {
        return properties.noOcclusion().isSuffocating((blockState, blockGetter, blockPos) -> false);
    }

    static VoxelShape getCollisionShape(InterdimensionalBlock inter, CollisionContext context, VoxelShape defaultShape) {
        if(!(context instanceof EntityCollisionContext entityCollisionContext) || !(entityCollisionContext.getEntity() instanceof Player player)) return defaultShape;
        boolean holdCam = player.isHolding(TLItems.CAMERA.get());
        if((holdCam && inter.visibleByDefault()) || (!holdCam && !inter.visibleByDefault())) {
            return Shapes.empty();
        }
        return defaultShape;
    }
}
