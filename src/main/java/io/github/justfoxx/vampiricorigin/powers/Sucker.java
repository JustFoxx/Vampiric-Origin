package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.apace100.calio.data.SerializableData;
import io.github.justfoxx.vampiricorigin.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Sucker extends BasePower {
    private final SerializableData.Instance data;

    public Sucker(PowerType<?> type, LivingEntity entity, SerializableData.Instance data) {
        super(type, entity);
        this.data = data;
    }

    public void onDamage() {
        if(entity.getVehicle() instanceof LivingEntity) {
            entity.dismountVehicle();
        }
    }

    private int tick = 30;

    private void modifyResource(int add) {
        PowerHolderComponent component = PowerHolderComponent.KEY.get(entity);
        Power power = component.getPower(data.get("resource"));
        if(power instanceof VariableIntPower variableIntPower) {
            variableIntPower.setValue(variableIntPower.getValue() + add);
        }
        component.sync();
    }

    private final Random generator = new Random();

    public void tick() {
        if(isActive()) {
            if(entity.getVehicle()!=null&&entity.getVehicle() instanceof LivingEntity livingEntity) {
                tick++;
                if(tick >= 40) {
                    livingEntity.damage(DamageSource.MAGIC, 3);
                    if(!livingEntity.getActiveStatusEffects().isEmpty()) {
                        Map<StatusEffect, StatusEffectInstance> effects = livingEntity.getActiveStatusEffects();
                        ArrayList<StatusEffectInstance> effectsList = new ArrayList<>(effects.values());

                        int i = generator.nextInt(effects.size());
                        StatusEffectInstance effect = effectsList.get(i);

                        entity.addStatusEffect(effect);
                        livingEntity.removeStatusEffect(effect.getEffectType());
                    }

                    if(livingEntity instanceof AnimalEntity) {
                        modifyResource(2);
                    } else if(livingEntity.isPlayer() || livingEntity instanceof VillagerEntity) {
                        modifyResource(3);
                    } else {
                        if(livingEntity instanceof MobEntity mobEntity) {
                            mobEntity.setTarget(entity);
                        }
                        modifyResource(1);
                    }
                    tick = 0;
                }
            }
        }
    }
}
