package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Sucker extends BasePower {
    private final SerializableData.Instance data;
    private final Random generator = new Random();
    private int tick = 30;

    public Sucker(PowerType<?> type, LivingEntity entity, SerializableData.Instance data) {
        super(type, entity);
        this.data = data;
    }

    public void onDamage() {
        if(!(entity.getVehicle() instanceof LivingEntity))return;

        entity.dismountVehicle();
    }

    private void addToResource(int add) {
        PowerHolderComponent component = PowerHolderComponent.KEY.get(entity);
        Power power = component.getPower(data.get("resource"));
        if(!(power instanceof VariableIntPower variableIntPower)) return;

        variableIntPower.setValue(variableIntPower.getValue() + add);
        component.sync();
    }

    private void copyEffect(LivingEntity livingEntity) {
        Map<StatusEffect, StatusEffectInstance> effects = livingEntity.getActiveStatusEffects();
        ArrayList<StatusEffectInstance> effectsList = new ArrayList<>(effects.values());

        int i = generator.nextInt(effects.size());
        StatusEffectInstance effect = effectsList.get(i);

        entity.addStatusEffect(effect);
        livingEntity.removeStatusEffect(effect.getEffectType());
    }

    private void addingResource(LivingEntity livingEntity) {
        if(livingEntity instanceof AnimalEntity) {
            addToResource(2);
        } else if(livingEntity.isPlayer() || livingEntity instanceof VillagerEntity) {
            addToResource(3);
        } else {
            if (livingEntity instanceof MobEntity mobEntity) {
                mobEntity.setTarget(entity);
            }
            addToResource(1);
        }
    }

    public void tick() {
        if(!isActive()) return;
        if(entity.getVehicle()==null) return;
        if(!(entity.getVehicle() instanceof LivingEntity livingEntity)) return;
        tick++;
        if(tick >= 40) return;


        livingEntity.damage(DamageSource.MAGIC, 3);

        if(!livingEntity.getActiveStatusEffects().isEmpty())
            copyEffect(livingEntity);

        addingResource(livingEntity);
        tick = 0;
    }
}
