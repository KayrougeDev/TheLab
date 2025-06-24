package fr.kayrouge.thelab.mixin;

import fr.kayrouge.thelab.block.interdimensional.swe.InterdimensionalBlock;
import fr.kayrouge.thelab.item.TLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(BlockBehaviour.class)
public class BlockMixin {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    public void collision(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if(this instanceof InterdimensionalBlock inter) {
            Optional<Item> op = TLItems.CAMERA.asOptional();
            if(op.isEmpty() || !(context instanceof EntityCollisionContext entityCollisionContext) || !(entityCollisionContext.getEntity() instanceof Player player)) return;
            boolean holdCam = player.isHolding(op.get());
            if((holdCam && inter.visibleByDefault()) || (!holdCam && !inter.visibleByDefault())) {
                cir.setReturnValue(Shapes.empty());
                cir.cancel();
            }
        }
    }

}
