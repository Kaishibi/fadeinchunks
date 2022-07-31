package me.wurgo.fadeinchunks.extensions;

import me.jellysquid.mods.sodium.client.render.chunk.ChunkGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderContainer;

public interface ChunkGraphicsStateExtension {
    ChunkRenderContainer<?> getContainer();

    float getLoadTime();
    void setLoadTime(float time);

    default float getFadeInProgress(float currentTime) {
        return (currentTime - getLoadTime()) * 5;
    }

    static ChunkGraphicsStateExtension extend(ChunkGraphicsState obj) {
        return (ChunkGraphicsStateExtension) obj;
    }
}
