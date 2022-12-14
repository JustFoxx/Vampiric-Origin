package io.github.justfoxx.vampiricorigin.customgoal;

import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.justfoxx.vampiricorigin.powers.Sucker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class LeechLookGoal extends LookAtEntityGoal {
    public LeechLookGoal(MobEntity mob, float range) {
        super(mob, LivingEntity.class, range);
    }

    @Override
    public boolean canStart() {
        boolean bl = super.canStart();
        if(this.target == null) return false;
        if(PowerHolderComponent.hasPower(this.target, Sucker.class)) bl = false;

        //if(this.target.getCustomName() == null) return false;
        if(Objects.equals(this.target.getCustomName(), Text.literal("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))) bl = false;

        if(this.mob.getCustomName() == null) return false;
        if(!this.mob.getCustomName().equals(Text.literal("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))) bl = false;
        return bl;
    }
}
