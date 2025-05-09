package com.kitoglav.bjorkhorse;

import com.kitoglav.bjorkhorse.api.IHorseAgree;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
/**
 * Copyright © 2025 Kitoglav Licensed under the Apache License, Version 2.0
 **/
public class HorseAgreeGoal extends Goal {
    private static final double AGREE_CHANCE = 0.01D;
    private static final double AGREE_DISTANCE = 15D;
    public static final int AGREE_DURATION = 80;

    private final AbstractHorseEntity horse;
    @Nullable
    private PlayerEntity player;
    private int agreeTimer;

    public HorseAgreeGoal(AbstractHorseEntity horse) {
        this.horse = horse;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (horse.getRandom().nextDouble() >= AGREE_CHANCE) return false;
        if (!horse.getPassengerList().isEmpty()) return false;
        if (horse.isEatingGrass()) return false;
        return getClosestPlayer() != null;
    }

    @Override
    public boolean shouldContinue() {
        return agreeTimer > 0;
    }

    private PlayerEntity getClosestPlayer() {
        return horse.getWorld().getClosestPlayer(horse, AGREE_DISTANCE);
    }

    @Override
    public void start() {
        this.agreeTimer = AGREE_DURATION;
        ((IHorseAgree) horse).bjorkhorse$setAgreeing(true);
        player = getClosestPlayer();
        if (BjorkHorseMod.AGREE_SOUND_EVENT != null) {
            horse.getWorld().playSoundFromEntity(null, horse, BjorkHorseMod.AGREE_SOUND_EVENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    @Override
    public void tick() {
        if (agreeTimer > 0) {
            agreeTimer--;
            if (player != null && player.distanceTo(horse) < AGREE_DISTANCE) {
                horse.getLookControl().lookAt(player, 30F, 30F);
            }
        }
    }

    @Override
    public boolean canStop() {
        return !shouldContinue();
    }

    @Override
    public void stop() {
        ((IHorseAgree) horse).bjorkhorse$setAgreeing(false);
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

}