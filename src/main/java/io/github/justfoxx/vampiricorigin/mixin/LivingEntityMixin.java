package io.github.justfoxx.vampiricorigin.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.justfoxx.vampiricorigin.Main;
import io.github.justfoxx.vampiricorigin.RegistryTypes;
import io.github.justfoxx.vampiricorigin.interfaces.IEDamaging;
import io.github.justfoxx.vampiricorigin.interfaces.IEDying;
import io.github.justfoxx.vampiricorigin.interfaces.IETicking;
import io.github.justfoxx.vampiricorigin.powers.PowerWrapper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "getGroup", at = @At("RETURN"))
    private EntityGroup modifyGroup(EntityGroup group) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("undead"));

        if (power.isActive((LivingEntity) (Object) this)) return EntityGroup.UNDEAD;
        return group;
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if (!power.isActive((LivingEntity) (Object) this)) return;
        if (!(power instanceof IEDying dyingPower)) return;

        dyingPower.onDeath((LivingEntity) (Object) this, source);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if (!power.isActive((LivingEntity) (Object) this)) return;
        if (!(power instanceof IETicking tickingPower)) return;

        tickingPower.tick((LivingEntity) (Object) this);
    }


    @Inject(method = "damage", at = @At("RETURN"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        boolean bl = cir.getReturnValue();

        if(!bl) return;

        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if(!power.isActive((LivingEntity) (Object) this)) return;
        if (!(power instanceof IEDamaging damagingPower)) return;

        damagingPower.onDamage((LivingEntity) (Object) this, source, amount);
    }
}
