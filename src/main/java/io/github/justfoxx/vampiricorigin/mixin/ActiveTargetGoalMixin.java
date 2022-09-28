package io.github.justfoxx.vampiricorigin.mixin;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.justfoxx.vampiricorigin.powers.Sucker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin<T extends LivingEntity> extends TrackTargetGoal {
    @Shadow @Final protected Class<T> targetClass;

    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "net/minecraft/entity/mob/MobEntity.setTarget (Lnet/minecraft/entity/LivingEntity;)V"))
    public LivingEntity canTarget(LivingEntity target) {
        if(
                PowerHolderComponent.hasPower(target, Sucker.class)
                && this.mob.getCustomName() != null
                && this.mob.getCustomName().equals(Text.literal("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))
        ) return null;
        return target;
    }
}
