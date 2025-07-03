package fr.kayrouge.thelab.client;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DISABLE_SHADERS = BUILDER
            .comment("Disable all shaders used by the mod")
            .define("disableShaders", false);

    public static final ModConfigSpec.BooleanValue ENABLE_TEST_SHADER = BUILDER
            .comment("Enable test shader")
            .define("enableTestShader", false);

    public static final ModConfigSpec.DoubleValue WAVE_SHADER_SPEED = BUILDER
            .comment("Enable test shader").defineInRange("waveShader.speed", 2.5d, 0.25d, 100d);

    public static final ModConfigSpec.DoubleValue WAVE_SHADER_WIDTH = BUILDER
            .comment("Enable test shader").defineInRange("waveShader.width", 1d, 0d, 10d);


    public static final ModConfigSpec.DoubleValue WAVE_SHADER_MAX_RADIUS = BUILDER
            .comment("Enable test shader").defineInRange("waveShader.maxRadius", 200d, 1, 1000d);

    static final ModConfigSpec SPEC = BUILDER.build();
}
