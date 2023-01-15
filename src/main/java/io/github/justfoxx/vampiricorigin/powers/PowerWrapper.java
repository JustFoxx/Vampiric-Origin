package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class PowerWrapper {
    private final Identifier id;
    protected PowerTypeReference<Power> powerTypeReference;
    protected PowerType<?> powerType;
    protected PowerHolderComponent powerHolderComponent;

    public PowerWrapper(Identifier identifier) {
        this.id = identifier;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    public boolean isActive(LivingEntity livingEntity) {
        return powerTypeReference.isActive(livingEntity);
    }

    public PowerHolderComponent getPowerHolder(LivingEntity entity) {
        if (powerHolderComponent == null) {
            powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return powerHolderComponent;
    }

    public Power getPower(LivingEntity entity) {
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }
}

