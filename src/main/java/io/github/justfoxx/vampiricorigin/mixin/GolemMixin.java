package io.github.justfoxx.vampiricorigin.mixin;

import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({IronGolemEntity.class, SnowGolemEntity.class})
public class GolemMixin { }
