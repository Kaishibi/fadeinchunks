package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.backends.multidraw.ChunkDrawParamsVector;
import me.wurgo.fadeinchunks.extensions.ChunkDrawParamsVectorExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ChunkDrawParamsVector.NioChunkDrawCallVector.class, remap = false)
public abstract class NioChunkDrawCallVectorMixin extends ChunkDrawParamsVector implements ChunkDrawParamsVectorExtension {
    @Shadow private int writeOffset;

    protected NioChunkDrawCallVectorMixin(int capacity) {
        super(capacity);
    }

    @Override
    public void pushChunkDrawParamFadeIn(float progress) {
        this.buffer.putFloat(this.writeOffset - 4, progress);
    }
}
