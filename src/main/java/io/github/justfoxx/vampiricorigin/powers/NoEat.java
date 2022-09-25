package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.UseAction;

public class NoEat extends BasePower {
    public NoEat(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }

    public boolean canUse(Item item) {
        return item.getUseAction(entity.getStackInHand(entity.getActiveHand())) != UseAction.EAT && item.getUseAction(entity.getStackInHand(entity.getActiveHand())) != UseAction.EAT;
    }
}
