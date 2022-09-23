package io.github.justfoxx.vampiricorigin.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.justfoxx.vampiricorigin.Main;
import io.github.justfoxx.vampiricorigin.powers.NoMobAttack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import javax.annotation.Nullable;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin extends TrackTargetGoal {
    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "net/minecraft/entity/mob/MobEntity.setTarget (Lnet/minecraft/entity/LivingEntity;)V"))
    public LivingEntity canTarget(LivingEntity target) {
        Main.LOGGER.info("canTarget");
        if(PowerHolderComponent.hasPower(target, NoMobAttack.class)) {
            return null;
        } else {
            return target;
        }
    }
}
