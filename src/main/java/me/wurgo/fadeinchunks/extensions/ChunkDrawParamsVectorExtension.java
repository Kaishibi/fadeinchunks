package me.wurgo.fadeinchunks.extensions;

import me.jellysquid.mods.sodium.client.render.chunk.backends.multidraw.ChunkDrawParamsVector;

public interface ChunkDrawParamsVectorExtension {
    void pushChunkDrawParamFadeIn(float progress);

    static ChunkDrawParamsVectorExtension extend(ChunkDrawParamsVector obj) {
        return (ChunkDrawParamsVectorExtension) obj;
    }
}
