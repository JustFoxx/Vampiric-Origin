package io.github.justfoxx.vampiricorigin.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.justfoxx.vampiricorigin.powers.NoEat;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if(PowerHolderComponent.hasPower(user, NoEat.class)) {
            for (NoEat power : PowerHolderComponent.getPowers(user, NoEat.class)) {
                if (!power.canUse((Item)(Object)this)) {
                    cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
                    return;
                }
            }
        }
    }
}
