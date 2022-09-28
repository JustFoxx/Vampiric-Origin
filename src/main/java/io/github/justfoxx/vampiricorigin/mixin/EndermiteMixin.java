package io.github.justfoxx.vampiricorigin.mixin;

import io.github.justfoxx.vampiricorigin.customgoal.LeechGoal;
import io.github.justfoxx.vampiricorigin.customgoal.LeechLookGoal;
import io.netty.channel.unix.IovArray;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermiteEntity.class)
public abstract class EndermiteMixin extends HostileEntity {

    protected EndermiteMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    private void initGoals(CallbackInfo ci) {
        this.goalSelector.add(7, new LeechLookGoal(this, 8.0F));
        this.targetSelector.add(3, new LeechGoal(this));
    }

}
