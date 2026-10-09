package com.brynne.crypticalchemy.datagen;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.particle.CrypticAlchemyParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

public class CrypticAlchemyParticleDescriptionProvider extends ParticleDescriptionProvider {
    public CrypticAlchemyParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(CrypticAlchemyParticles.MIST_GRASS_PARTICLE.get(),
                Identifier.fromNamespaceAndPath(CrypticAlchemy.MOD_ID, "mist_grass_0"),
                Identifier.fromNamespaceAndPath(CrypticAlchemy.MOD_ID, "mist_grass_1"),
                Identifier.fromNamespaceAndPath(CrypticAlchemy.MOD_ID, "mist_grass_2"));
    }
}
