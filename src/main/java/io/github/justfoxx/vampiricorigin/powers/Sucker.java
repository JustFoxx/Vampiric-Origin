package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.power.*;
import io.github.justfoxx.vampiricorigin.helpers.MathEnum;
import io.github.justfoxx.vampiricorigin.helpers.PowerHelper;
import io.github.justfoxx.vampiricorigin.interfaces.IEDamaging;
import io.github.justfoxx.vampiricorigin.interfaces.IEDying;
import io.github.justfoxx.vampiricorigin.interfaces.IETicking;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Random;

public class Sucker extends PowerWrapper implements IEDying, IETicking, IEDamaging {
    private final Random generator = new Random();
    private int tick = 30;

    public Sucker(Identifier identifier) {
        super(identifier);
    }


    @Override
    public void onDamage(LivingEntity livingEntity, DamageSource source, float amount) {
        if(!(livingEntity.getVehicle() instanceof LivingEntity))return;

        livingEntity.dismountVehicle();
    }

    @Override
    public void onDeath(LivingEntity livingEntity, DamageSource source) {
        PowerHelper.modifyResource((VariableIntPower) this.getPower(livingEntity), 1, MathEnum.ADD, livingEntity);
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        if(!isActive(livingEntity)) return;
        if(!(livingEntity.getVehicle() instanceof LivingEntity entity)) return;

        tick++;
        if(tick < 40) return;
        tick = 0;

        entity.damage(DamageSource.OUT_OF_WORLD, 3);

        if(!entity.getActiveStatusEffects().isEmpty())
            PowerHelper.copyEffect(generator, livingEntity, entity);

        addingResource(livingEntity, entity);
        if(!(livingEntity.getWorld() instanceof ServerWorld serverWorld)) return;
        serverWorld.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()), livingEntity.getX(), livingEntity.getY()+0.5, livingEntity.getZ(), 3, 0.2, 0.2, 0.2, 0.1);
    }


    private void addingResource(LivingEntity livingEntity, LivingEntity entity) {
        if(livingEntity instanceof AnimalEntity) {
            PowerHelper.modifyResource((VariableIntPower) this.getPower(livingEntity), 2, MathEnum.ADD, livingEntity);
        } else if(livingEntity.isPlayer() || entity instanceof VillagerEntity) {
            PowerHelper.modifyResource((VariableIntPower) this.getPower(livingEntity), 3, MathEnum.ADD, livingEntity);
        } else {
            if (livingEntity instanceof MobEntity mobEntity) {
                mobEntity.setTarget(livingEntity);
            }
            PowerHelper.modifyResource((VariableIntPower) this.getPower(livingEntity), 1, MathEnum.ADD, livingEntity);
        }
    }
}
