package io.github.justfoxx.vampiricorigin.powers;

import io.github.justfoxx.vampiricorigin.interfaces.IEItemUsing;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;

public class NoEat extends PowerWrapper implements IEItemUsing {

    public NoEat(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean canUseItem(Item item, LivingEntity entity) {
        return item.getUseAction(entity.getStackInHand(entity.getActiveHand())) != UseAction.EAT && item.getUseAction(entity.getStackInHand(entity.getActiveHand())) != UseAction.EAT;
    }
}
