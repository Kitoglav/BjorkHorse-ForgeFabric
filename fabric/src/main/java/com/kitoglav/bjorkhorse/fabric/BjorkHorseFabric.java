package com.kitoglav.bjorkhorse.fabric;

import com.kitoglav.bjorkhorse.BjorkHorseMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

import static com.kitoglav.bjorkhorse.BjorkHorseMod.AGREE_SOUND_ID;
/**
 * Copyright © 2025 Kitoglav Licensed under the Apache License, Version 2.0
 **/
public final class BjorkHorseFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BjorkHorseMod.init();
        Registry.register(Registry.SOUND_EVENT, AGREE_SOUND_ID, BjorkHorseMod.AGREE_SOUND_EVENT = new SoundEvent(AGREE_SOUND_ID));
    }
}
