package io.github.justfoxx.vampiricorigin.interfaces;

import io.github.apace100.apoli.power.VariableIntPower;
import io.github.justfoxx.vampiricorigin.helpers.MathEnum;
import net.minecraft.entity.LivingEntity;

public interface IEResource {
    void modifyResource(VariableIntPower power, int value, MathEnum mathEnum, LivingEntity livingEntity);

}
