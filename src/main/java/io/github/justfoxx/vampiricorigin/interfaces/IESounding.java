package io.github.justfoxx.vampiricorigin.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;

public interface IESounding {
    SoundEvent deathSound();

    LivingEntity.FallSounds fallSound();

    SoundEvent hurtSound() ;

    SoundEvent eatSound();

    SoundEvent dropSound();

    SoundEvent sleepSound();
}
