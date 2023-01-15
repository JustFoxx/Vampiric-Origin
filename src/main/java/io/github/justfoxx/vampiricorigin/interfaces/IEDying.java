package io.github.justfoxx.vampiricorigin.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface IEDying {
    void onDeath(LivingEntity livingEntity, DamageSource source);
}
