package us.devs.ingrosware.mixin.impl;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tcb.bces.event.EventType;
import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.event.impl.entity.UpdateEvent;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends MixinEntityPlayer {
    private UpdateEvent eventUpdate;

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    private void onUpdateWalkingPlayerHead(CallbackInfo ci) {
        eventUpdate = new UpdateEvent(EventType.PRE, (EntityPlayerSP) (Object) this, getRotationYaw(), getRotationPitch(), getPositionX(), getPositionY(), getPositionZ(), getPositionOnGround());
        IngrosWare.INSTANCE.getBus().post(eventUpdate);
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;posX:D"))
    private double onUpdateWalkingPlayerPosX(EntityPlayerSP player) {
        return eventUpdate.getX();
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/AxisAlignedBB;minY:D"))
    private double onUpdateWalkingPlayerMinY(AxisAlignedBB boundingBox) {
        return eventUpdate.getY();
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;posZ:D"))
    private double onUpdateWalkingPlayerPosZ(EntityPlayerSP player) {
        return eventUpdate.getZ();
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;onGround:Z"))
    private boolean onUpdateWalkingPlayerOnGround(EntityPlayerSP player) {
        return eventUpdate.isOnGround();
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;rotationYaw:F"))
    private float onUpdateWalkingPlayerRotationYaw(EntityPlayerSP player) {
        return eventUpdate.getYaw();
    }

    @Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;rotationPitch:F"))
    private float onUpdateWalkingPlayerRotationPitch(EntityPlayerSP player) {
        return eventUpdate.getPitch();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    private void onUpdateWalkingPlayerReturn(CallbackInfo ci) {
        IngrosWare.INSTANCE.getBus().post(new UpdateEvent());
    }


}
