package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkProgram;
import me.wurgo.fadeinchunks.extensions.ChunkProgramExtension;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;

import java.nio.FloatBuffer;

@Mixin(ChunkProgram.class)
public class ChunkProgramMixin implements ChunkProgramExtension {
    private final FloatBuffer uModelOffsetBuffer = MemoryUtil.memAllocFloat(4);

    @Override
    public void setFadeIn(float progress) {
        this.uModelOffsetBuffer.put(3, progress);
    }
}
