package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.ChunkGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderContainer;
import me.wurgo.fadeinchunks.extensions.ChunkGraphicsStateExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChunkGraphicsState.class, remap = false)
public class ChunkGraphicsStateMixin implements ChunkGraphicsStateExtension {
    private ChunkRenderContainer<?> container;
    private float loadTime;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void fadeinchunks_init(ChunkRenderContainer container, CallbackInfo ci) {
        this.container = container;
    }

    @Override
    public ChunkRenderContainer<?> getContainer() {
        return this.container;
    }

    @Override
    public float getLoadTime() {
        return this.loadTime;
    }

    @Override
    public void setLoadTime(float time) {
        this.loadTime = time;
    }
}
