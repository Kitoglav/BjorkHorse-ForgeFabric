package com.kitoglav.bjorkhorse.forge;

import com.kitoglav.bjorkhorse.BjorkHorseMod;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import static com.kitoglav.bjorkhorse.BjorkHorseMod.AGREE_SOUND_ID;
/**
 * Copyright © 2025 Kitoglav Licensed under the Apache License, Version 2.0
 **/
@Mod(BjorkHorseMod.MOD_ID)
public final class BjorkHorseNeoForge {

    public BjorkHorseNeoForge(IEventBus modBus, ModContainer container) {
        modBus.addListener(this::registerSounds);
        BjorkHorseMod.init();
    }

    private void registerSounds(RegisterEvent event) {
        event.register(RegistryKeys.SOUND_EVENT, helper -> helper.register(AGREE_SOUND_ID, BjorkHorseMod.AGREE_SOUND_EVENT = SoundEvent.of(AGREE_SOUND_ID)));
    }
}
