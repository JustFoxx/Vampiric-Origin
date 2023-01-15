package io.github.justfoxx.vampiricorigin.helpers;

import io.github.apace100.apoli.power.VariableIntPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class PowerHelper {
    public static void copyEffect(Random generator, LivingEntity livingEntity, LivingEntity entity) {
        Map<StatusEffect, StatusEffectInstance> effects = livingEntity.getActiveStatusEffects();
        ArrayList<StatusEffectInstance> effectsList = new ArrayList<>(effects.values());
        if (effects.size() <= 0) return;
        int i = generator.nextInt(effects.size());
        StatusEffectInstance effect = effectsList.get(i);

        entity.addStatusEffect(effect);
        livingEntity.removeStatusEffect(effect.getEffectType());
    }
    public static void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity) {
        switch (mathEnum) {
            case ADD ->power.setValue(power.getValue() + value);
            case REMOVE ->power.setValue(power.getValue() - value);
            case SET ->power.setValue(value);
        }
    }
}
