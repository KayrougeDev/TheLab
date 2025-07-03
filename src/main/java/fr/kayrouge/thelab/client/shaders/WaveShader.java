package fr.kayrouge.thelab.client.shaders;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.post.PostPipeline;
import fr.kayrouge.thelab.TheLab;
import fr.kayrouge.thelab.client.ClientConfig;
import fr.kayrouge.thelab.client.TLKeyMappings;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import javax.annotation.Nullable;

public class WaveShader {

    private float waveTime = -1;
    private static final ModConfigSpec.DoubleValue SPEED = ClientConfig.WAVE_SHADER_SPEED;
    private static final ModConfigSpec.DoubleValue MAX_RADIUS = ClientConfig.WAVE_SHADER_MAX_RADIUS;
    private static final ModConfigSpec.DoubleValue WIDTH = ClientConfig.WAVE_SHADER_WIDTH;

    public void onTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (waveTime < 0 && TLKeyMappings.EXAMPLE_MAPPING.get().consumeClick()) {
            waveTime = 0f;
        }

        if (waveTime >= 0) {
            waveTime += mc.getTimer().getGameTimeDeltaTicks();
            updateUniforms(mc, waveTime);
            if (waveTime * SPEED.get() > MAX_RADIUS.get()) waveTime = -1f;
        }
    }

    private void updateUniforms(Minecraft mc, float time) {
        float px = (float) mc.player.getX(), py = (float)mc.player.getY(), pz = (float) mc.player.getZ();
        PostPipeline pipeline = getPipeline();
        if(pipeline != null) {
            pipeline.getOrCreateUniform("time").setFloat(time);
            pipeline.getOrCreateUniform("playerPos").setVector(px, py, pz);
            pipeline.getOrCreateUniform("speed").setFloat(SPEED.get().floatValue());
            pipeline.getOrCreateUniform("width").setFloat(WIDTH.get().floatValue());
        }
    }

    @Nullable
    public PostPipeline getPipeline() {
        return VeilRenderSystem.renderer().getPostProcessingManager().getPipeline(TheLab.path("wave"));
    }

    public void runPipeline() {
        PostPipeline pipeline = getPipeline();
        if(pipeline != null) VeilRenderSystem.renderer().getPostProcessingManager().runPipeline(pipeline);
    }

}
