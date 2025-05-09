package com.kitoglav.bjorkhorse.forge;

import com.kitoglav.bjorkhorse.BjorkHorseMod;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.sound.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.kitoglav.bjorkhorse.BjorkHorseMod.AGREE_SOUND_ID;
/**
 * Copyright © 2025 Kitoglav Licensed under the Apache License, Version 2.0
 **/
@Mod(BjorkHorseMod.MOD_ID)
public final class BjorkHorseForge {

    public BjorkHorseForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(BjorkHorseMod.MOD_ID, bus);
        bus.addListener(this::registerSounds);
        BjorkHorseMod.init();
    }

    private void registerSounds(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.SOUND_EVENTS, helper -> helper.register(AGREE_SOUND_ID, BjorkHorseMod.AGREE_SOUND_EVENT = SoundEvent.of(AGREE_SOUND_ID)));
    }
}
