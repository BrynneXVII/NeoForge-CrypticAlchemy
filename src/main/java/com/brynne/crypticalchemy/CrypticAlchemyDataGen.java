package com.brynne.crypticalchemy;

import com.brynne.crypticalchemy.datagen.*;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;


@EventBusSubscriber(modid = CrypticAlchemy.MOD_ID)
public class CrypticAlchemyDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var lookupProvider = event.getReloadableLookupProvider(); //???

        generator.addProvider(true, new CrypticAlchemyModelProvider(packOutput));
        generator.addProvider(true, new CrypticAlchemyBlockTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new CrypticAlchemyItemTagsProvider(packOutput, lookupProvider));

        generator.addProvider(true, new CrypticAlchemyDataMapProvider(packOutput, lookupProvider));

        event.createReloadableRegistryObjects(new RegistrySetBuilder().add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(new LootTableProvider.SubProviderEntry(CrypticAlchemyBlockLootTableProvider::new, LootContextParamSets.BLOCK)))));
        event.createProvider(CrypticAlchemyParticleDescriptionProvider::new);
    }
}
