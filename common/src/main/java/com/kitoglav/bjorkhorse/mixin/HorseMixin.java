package com.kitoglav.bjorkhorse.mixin;

import com.kitoglav.bjorkhorse.HorseAgreeGoal;
import com.kitoglav.bjorkhorse.api.IHorseAgree;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Copyright © 2025 Kitoglav Licensed under the Apache License, Version 2.0
 **/
@Mixin(AbstractHorseEntity.class)
public abstract class HorseMixin extends AnimalEntity implements InventoryChangedListener, RideableInventory, Tameable, JumpingMount, Saddleable, IHorseAgree {
    @Unique
    private static final int AGREE_FLAG = 128;
    @Unique
    private float bjorkhorse$agreeingAnimationProgress;
    @Unique
    private float bjorkhorse$lastAgreeingAnimationProgress;

    protected HorseMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean getHorseFlag(int bitmask);

    @Shadow
    public abstract void setHorseFlag(int bitmask, boolean flag);

    @Inject(method = "initCustomGoals", at = @At("TAIL"))
    private void addAgreeGoal(CallbackInfo ci) {
        this.goalSelector.add(5, new HorseAgreeGoal((AbstractHorseEntity) (Object) this));
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        this.bjorkhorse$lastAgreeingAnimationProgress = this.bjorkhorse$agreeingAnimationProgress;
        if (bjorkhorse$isAgreeing()) {
            this.bjorkhorse$agreeingAnimationProgress += 1 / (float) HorseAgreeGoal.AGREE_DURATION;
            if (this.bjorkhorse$agreeingAnimationProgress > 1.0F) {
                this.bjorkhorse$agreeingAnimationProgress = 1.0F;
            }
        } else {
            this.bjorkhorse$agreeingAnimationProgress = 0F;
        }
    }

    @Inject(method = "eatsGrass", at = @At("RETURN"), cancellable = true)
    private void eatsGrass(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() && !bjorkhorse$isAgreeing());
    }

    @Override
    public float bjorkhorse$getAgreeProgress(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.bjorkhorse$lastAgreeingAnimationProgress, this.bjorkhorse$agreeingAnimationProgress);
    }

    @Override
    public boolean bjorkhorse$isAgreeing() {
        return this.getHorseFlag(AGREE_FLAG);
    }

    @Override
    public void bjorkhorse$setAgreeing(boolean b) {
        this.setHorseFlag(AGREE_FLAG, b);
    }
}