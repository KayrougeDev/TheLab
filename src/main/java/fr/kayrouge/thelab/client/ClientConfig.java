package fr.kayrouge.thelab.client;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DISABLE_SHADERS = BUILDER
            .comment("Disable all shaders used by the mod")
            .define("disableShaders", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
