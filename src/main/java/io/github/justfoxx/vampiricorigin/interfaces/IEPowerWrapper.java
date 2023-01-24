package io.github.justfoxx.vampiricorigin.interfaces;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public interface IEPowerWrapper {

    boolean isActive(LivingEntity livingEntity);
    PowerHolderComponent getPowerHolder(LivingEntity entity);
    Power getPower(LivingEntity entity);
    Identifier getId();
}
