package io.github.justfoxx.vampiricorigin.mixin;

import io.github.justfoxx.vampiricorigin.Main;
import io.github.justfoxx.vampiricorigin.RegistryTypes;
import io.github.justfoxx.vampiricorigin.interfaces.IEItemUsing;
import io.github.justfoxx.vampiricorigin.powers.PowerWrapper;
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
public abstract class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if (!power.isActive(user)) return;
        if (!(power instanceof IEItemUsing itemUsePower)) return;
        if (!itemUsePower.canUseItem((Item) (Object) this, user)) return;

        cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
    }
}
