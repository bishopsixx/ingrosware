package us.devs.ingrosware.mixin.impl;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer extends MixinEntity {


    @ModifyConstant(method = "attackTargetEntityWithCurrentItem", constant = { @Constant(doubleValue = 0.6) })
    private double decelerate(final double original) {
        return 1.0;
    }
}
