package com.brynne.crypticalchemy.particle;

import com.brynne.crypticalchemy.CrypticAlchemy;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CrypticAlchemyParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CrypticAlchemy.MOD_ID);

    public static final Supplier<SimpleParticleType> MIST_GRASS_PARTICLE = PARTICLE_TYPES.register("mist_grass_particle", () -> new SimpleParticleType(false)); //true means always shows particles?? ie if too many they are still there

    /* Helper Methods */
    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
