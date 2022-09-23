package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;

public class BasePower extends Power {
    public BasePower(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
        setTicking(false);
    }
}
