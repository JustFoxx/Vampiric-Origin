package io.github.justfoxx.vampiricorigin.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public interface IEItemUsing {
    boolean canUseItem(Item item, LivingEntity entity);
}
