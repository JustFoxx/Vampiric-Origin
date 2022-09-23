package io.github.justfoxx.vampiricorigin;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.justfoxx.vampiricorigin.powers.*;
import net.minecraft.util.Identifier;

import static io.github.justfoxx.vampiricorigin.Main.ID;

public class Powers {
    public static void register() {
        Utils.register(Utils.createPower("size", data -> Size::new));
        Utils.register(Utils.createPower("no_eat", data -> NoEat::new));
        Utils.register(Utils.createPower("undead", data -> UndeadEffect::new));
        Utils.register(Utils.createPower("no_mob_attack", data -> NoMobAttack::new));
        Utils.register(new PowerFactory<>(ID("sucker"),
                new SerializableData().add("resource", ApoliDataTypes.POWER_TYPE),
                data -> (type, player) -> new Sucker(type, player, data)
        ));
    }
}
