package io.github.justfoxx.vampiricorigin.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface IEDamaging {
    void onDamage(LivingEntity livingEntity, DamageSource source, float amount);
}
