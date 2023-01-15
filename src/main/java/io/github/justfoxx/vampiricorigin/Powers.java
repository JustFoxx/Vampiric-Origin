package io.github.justfoxx.vampiricorigin;

import io.github.ivymc.ivycore.registry.RegistryBuilder;
import io.github.justfoxx.vampiricorigin.powers.*;

public class Powers {
    public Powers() {
    }

    public void init() {
        Main.registry.add(RegistryTypes.POWERS, Main.g.id("size"),new Size(Main.g.id("nobody_size")));
        Main.registry.add(RegistryTypes.POWERS, Main.g.id("no_eat"),new NoEat(Main.g.id("nobody_eating")));
        Main.registry.add(RegistryTypes.POWERS, Main.g.id("undead"),new UndeadEffect(Main.g.id("undead")));
        Main.registry.add(RegistryTypes.POWERS, Main.g.id("sucker"),new Sucker(Main.g.id("bloodsource")));
    }
}
