package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.*;
import io.github.apace100.calio.data.SerializableData;
import io.github.justfoxx.vampiricorigin.Main;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Sucker extends BasePower {
    private enum MODIFIER {
        ADD,
        REMOVE,
        SET
    }
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

    private void modifyResource(int value, MODIFIER modifier) {
        PowerHolderComponent component = PowerHolderComponent.KEY.get(entity);
        Power power = component.getPower(data.get("resource"));
        if(!(power instanceof VariableIntPower variableIntPower)) return;
        switch (modifier) {
            case ADD -> variableIntPower.setValue(variableIntPower.getValue() + value);
            case REMOVE -> variableIntPower.setValue(variableIntPower.getValue() - value);
            case SET -> variableIntPower.setValue(value);
        }

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
            modifyResource(2, MODIFIER.ADD);
        } else if(livingEntity.isPlayer() || livingEntity instanceof VillagerEntity) {
            modifyResource(3, MODIFIER.ADD);
        } else {
            if (livingEntity instanceof MobEntity mobEntity) {
                mobEntity.setTarget(entity);
            }
            modifyResource(1, MODIFIER.ADD);
        }
    }

    public void tick() {
        if(!isActive()) return;
        if(entity.getVehicle()==null) return;
        if(!(entity.getVehicle() instanceof LivingEntity livingEntity)) return;

        tick++;
        if(tick < 40) return;
        tick = 0;

        livingEntity.damage(DamageSource.MAGIC, 3);

        if(!livingEntity.getActiveStatusEffects().isEmpty())
            copyEffect(livingEntity);

        addingResource(livingEntity);
        if(!(entity.getWorld() instanceof ServerWorld serverWorld)) return;
        serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()), entity.getX(), entity.getY()+0.5, entity.getZ(), 3, 0.2, 0.2, 0.2, 0.1);
    }

    public void onDeath() {
        modifyResource(10, MODIFIER.SET);
    }
}
