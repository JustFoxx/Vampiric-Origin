package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.justfoxx.vampiricorigin.interfaces.IEPowerWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class PowerWrapperImpl implements IEPowerWrapper {
    private final Identifier id;
    protected final PowerTypeReference<Power> powerTypeReference;
    protected PowerType<?> powerType;
    protected PowerHolderComponent powerHolderComponent;

    public PowerWrapperImpl(Identifier identifier) {
        this.id = identifier;
        this.powerTypeReference = new PowerTypeReference<>(this.id);
    }

    @Override
    public boolean isActive(LivingEntity livingEntity) {
        return powerTypeReference.isActive(livingEntity);
    }

    @Override
    public PowerHolderComponent getPowerHolder(LivingEntity entity) {
        if (powerHolderComponent == null) {
            powerHolderComponent = PowerHolderComponent.KEY.get(entity);
        }
        return powerHolderComponent;
    }

    @Override
    public Power getPower(LivingEntity entity) {
        if (this.powerType == null) {
            this.powerType = PowerTypeRegistry.get(id);
        }
        return getPowerHolder(entity).getPower(this.powerType);
    }


    public Identifier getId() {
        return id;
    }
}
