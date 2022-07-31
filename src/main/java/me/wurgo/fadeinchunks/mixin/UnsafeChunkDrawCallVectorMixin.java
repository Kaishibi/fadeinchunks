package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.backends.multidraw.ChunkDrawParamsVector;
import me.wurgo.fadeinchunks.extensions.ChunkDrawParamsVectorExtension;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import sun.misc.Unsafe;

@Mixin(value = ChunkDrawParamsVector.UnsafeChunkDrawCallVector.class, remap = false)
public abstract class UnsafeChunkDrawCallVectorMixin extends ChunkDrawParamsVector implements ChunkDrawParamsVectorExtension {
    @Shadow @Final private static Unsafe UNSAFE;
    @Shadow private long writePointer;

    protected UnsafeChunkDrawCallVectorMixin(int capacity) {
        super(capacity);
    }

    @Override
    public void pushChunkDrawParamFadeIn(float progress) {
        UNSAFE.putFloat(this.writePointer - 4, progress);
    }
}
