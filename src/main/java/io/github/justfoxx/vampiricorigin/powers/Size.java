package io.github.justfoxx.vampiricorigin.powers;

import io.github.apace100.apoli.power.PowerType;
import io.github.justfoxx.vampiricorigin.interfaces.IETicking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class Size extends PowerWrapper implements IETicking {
    private final float baseScale = 0.8F;
    private final float reachScale = 1.3F;

    public Size(Identifier identifier) {
        super(identifier);
    }

    //public final float speedScale = 1.2F;


    @Override
    public void tick(LivingEntity livingEntity) {
        final ScaleData baseData = ScaleTypes.BASE.getScaleData(livingEntity);
        final ScaleData reachData = ScaleTypes.REACH.getScaleData(livingEntity);
        //final ScaleData speedData = ScaleTypes.MOTION.getScaleData(entity);

        if(!isActive(livingEntity)) {
            if(baseData.getScale() != 1) baseData.setTargetScale(1);
            if(reachData.getScale() != 1) reachData.setTargetScale(1);
            //if(speedData.getScale() != 1) speedData.setTargetScale(1);

            return;
        }

        if(baseData.getScale() != baseScale) baseData.setTargetScale(baseScale);
        if(reachData.getScale() != reachScale) reachData.setTargetScale(reachScale);
        //if(speedData.getScale() != speedScale) speedData.setTargetScale(speedScale);
    }
}
