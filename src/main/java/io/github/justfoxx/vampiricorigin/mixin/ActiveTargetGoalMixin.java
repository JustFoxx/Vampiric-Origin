package io.github.justfoxx.vampiricorigin.mixin;

import io.github.justfoxx.vampiricorigin.Main;
import io.github.justfoxx.vampiricorigin.RegistryTypes;
import io.github.justfoxx.vampiricorigin.powers.PowerWrapper;
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
    public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "net/minecraft/entity/mob/MobEntity.setTarget (Lnet/minecraft/entity/LivingEntity;)V"))
    public LivingEntity canTarget(LivingEntity target) {
        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if(
                power.isActive(target)
                && this.mob.getCustomName() != null
                && this.mob.getCustomName().equals(Text.literal("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))
        ) return null;
        return target;
    }
}
