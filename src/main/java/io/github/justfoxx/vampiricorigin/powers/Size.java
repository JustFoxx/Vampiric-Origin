package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class Size extends BasePower {
    public Size(PowerType<?> type, LivingEntity entity) {
        super(type, entity);
    }
    public final float baseScale = 0.7F;
    public final float reachScale = 1.5F;


    @Override
    public void tick() {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(entity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(entity);

        if(!isActive()) {
            if(baseData.getScale() != 1)
                baseData.setTargetScale(1);

            if(reachData.getScale() != 1)
                reachData.setTargetScale(1);

            return;
        }

        if(baseData.getScale() != baseScale)
            baseData.setTargetScale(baseScale);

        if(reachData.getScale() != reachScale)
            reachData.setTargetScale(reachScale);
    }

    @Override
    public void onLost() {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(entity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(entity);
        baseData.setTargetScale(1);
        reachData.setTargetScale(1);
    }
}
