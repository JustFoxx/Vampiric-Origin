package io.github.justfoxx.vampiricorigin;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.registry.Registry;

import java.util.function.BiFunction;
import java.util.function.Function;

import static io.github.justfoxx.vampiricorigin.Main.ID;

public class Utils {
    public static PowerFactory<Power> createPower(String name, Function<SerializableData.Instance, BiFunction<PowerType<Power>, LivingEntity, Power>> factoryConstructor) {
        return new PowerFactory<>(ID(name),
                new SerializableData(),
                factoryConstructor
        );
    }
    public static void register(PowerFactory<Power> serializer) {
        Registry.register(ApoliRegistries.POWER_FACTORY, serializer.getSerializerId(), serializer);
    }
}
