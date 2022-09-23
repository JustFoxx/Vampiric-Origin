package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.apace100.calio.data.SerializableData;
import io.github.justfoxx.vampiricorigin.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;

public class Sucker extends BasePower {
    private final SerializableData.Instance data;

    public Sucker(PowerType<?> type, LivingEntity entity, SerializableData.Instance data) {
        super(type, entity);
        this.data = data;
    }

    public void onDamage() {
        Main.LOGGER.info("test damage");
        if(entity.getVehicle() instanceof LivingEntity) {
            entity.stopRiding();
        }
    }

    private int tick = 30;

    private void modifyResource(int add) {
        Main.LOGGER.info("test resource");
        PowerHolderComponent component = PowerHolderComponent.KEY.get(entity);
        Power power = component.getPower(data.get("resource"));
        if(power instanceof VariableIntPower variableIntPower) {
            variableIntPower.setValue(variableIntPower.getValue() + add);
        }
        component.sync();
    }

    public void tick() {
        if(isActive()) {
            entity.damage(new EntityDamageSource("vampiresuck", entity), 5);
            if(entity.getVehicle()!=null&&entity.getVehicle() instanceof LivingEntity livingEntity) {
                tick++;
                if(tick >= 30) {
                    livingEntity.damage(DamageSource.MAGIC, 5);
                    if(livingEntity instanceof AnimalEntity) {
                        modifyResource(2);
                    } else if(livingEntity.isPlayer() || livingEntity instanceof VillagerEntity) {
                        modifyResource(3);
                    } else {
                        modifyResource(1);
                    }
                    tick = 0;
                }
            }
        }
    }
}
