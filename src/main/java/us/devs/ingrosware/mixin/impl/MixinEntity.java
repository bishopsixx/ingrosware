package us.devs.ingrosware.mixin.impl;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(value = Entity.class, priority = 1001)
public class MixinEntity {
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;
    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;
    @Shadow
    public boolean onGround;

    public float getRotationYaw() {
        return rotationYaw;
    }

    public float getRotationPitch() {
        return rotationPitch;
    }

    public double getPositionX() {
        return posX;
    }

    public double getPositionY() {
        return posY;
    }

    public double getPositionZ() {
        return posZ;
    }

    public boolean getPositionOnGround() {
        return onGround;
    }


}
