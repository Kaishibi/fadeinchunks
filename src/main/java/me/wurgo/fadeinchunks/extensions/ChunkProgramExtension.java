package me.wurgo.fadeinchunks.extensions;

import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkProgram;

public interface ChunkProgramExtension {
    void setFadeIn(float progress);

    static ChunkProgramExtension extend(ChunkProgram obj) {
        return (ChunkProgramExtension) obj;
    }
}
