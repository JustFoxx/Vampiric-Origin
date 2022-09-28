package io.github.justfoxx.vampiricorigin;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "vampiricorigin";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Identifier ID(String name) {
        return new Identifier(MOD_ID, name);
    }


    @Override
    public void onInitialize() {
        MixinExtrasBootstrap.init();
        Powers.register();
        LOGGER.info("Vampires have been loaded");
    }
}
