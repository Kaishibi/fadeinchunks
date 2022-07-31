package me.wurgo.fadeinchunks.mixin;

import me.jellysquid.mods.sodium.client.render.chunk.ChunkCameraContext;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.backends.oneshot.ChunkOneshotGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.backends.oneshot.ChunkRenderBackendOneshot;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPass;
import me.wurgo.fadeinchunks.extensions.ChunkGraphicsStateExtension;
import me.wurgo.fadeinchunks.extensions.ChunkProgramExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChunkRenderBackendOneshot.class, remap = false)
public abstract class ChunkRenderBackendOneshotMixin<T extends ChunkOneshotGraphicsState> extends ChunkRenderShaderBackendMixin {
    @Inject(method = "prepareDrawBatch", at = @At("HEAD"))
    private void fadeinchunks_prepareDrawBatch(ChunkCameraContext camera, T state, CallbackInfo ci) {
        float progress = ChunkGraphicsStateExtension.extend(state).getFadeInProgress(this.currentTime);
        ChunkProgramExtension.extend(this.activeProgram).setFadeIn(progress);
    }

    @ModifyArg(method = "upload", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/render/chunk/ChunkRenderContainer;setGraphicsState(Lme/jellysquid/mods/sodium/client/render/chunk/passes/BlockRenderPass;Lme/jellysquid/mods/sodium/client/render/chunk/ChunkGraphicsState;)V"))
    private BlockRenderPass fadeinchunks_upload(BlockRenderPass pass, ChunkGraphicsState newState) {
        if (newState != null) {
            ChunkGraphicsState oldState = ChunkGraphicsStateExtension.extend(newState).getContainer().getGraphicsState(pass);
            ChunkGraphicsStateExtension.extend(newState).setLoadTime(oldState == null ? this.currentTime : ChunkGraphicsStateExtension.extend(oldState).getLoadTime());
        }
        return pass;
    }
}
