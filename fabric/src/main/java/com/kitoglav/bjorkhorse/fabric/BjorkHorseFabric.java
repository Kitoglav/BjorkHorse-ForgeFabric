package com.kitoglav.bjorkhorse.fabric;

import com.kitoglav.bjorkhorse.BjorkHorseMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

import static com.kitoglav.bjorkhorse.BjorkHorseMod.AGREE_SOUND_ID;

public final class BjorkHorseFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BjorkHorseMod.init();
        Registry.register(Registries.SOUND_EVENT, AGREE_SOUND_ID, BjorkHorseMod.AGREE_SOUND_EVENT = SoundEvent.of(AGREE_SOUND_ID));
    }
}
