package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkProgram;
import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkRenderShaderBackend;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChunkRenderShaderBackend.class, remap = false)
public class ChunkRenderShaderBackendMixin {
    @Shadow protected ChunkProgram activeProgram;
    protected float currentTime;

    @Inject(method = "begin", at = @At("HEAD"))
    private void fadeinchunks_begin(CallbackInfo ci) {
        this.currentTime = Util.getMeasuringTimeMs() / 1000.0F;
    }
}
