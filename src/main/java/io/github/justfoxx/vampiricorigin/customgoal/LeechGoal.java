package io.github.justfoxx.vampiricorigin.customgoal;

import io.github.justfoxx.vampiricorigin.Main;
import io.github.justfoxx.vampiricorigin.RegistryTypes;
import io.github.justfoxx.vampiricorigin.powers.PowerWrapper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class LeechGoal extends ActiveTargetGoal<LivingEntity> {
    public LeechGoal(MobEntity mob) {
        super(mob, LivingEntity.class,true);
    }

    @Override
    public void start() {

        if(this.targetEntity == null) return;

        PowerWrapper power = Main.registry.get(RegistryTypes.POWERS, Main.g.id("sucker"));

        if(power.isActive(this.targetEntity)) return;
        if(Objects.equals(this.targetEntity.getCustomName(), new LiteralText("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))) return;
        if(this.mob.getCustomName() == null) return;
        if(!this.mob.getCustomName().equals(new LiteralText("Undead Leech").formatted(Formatting.BOLD, Formatting.DARK_RED))) return;

        super.start();
    }
}
