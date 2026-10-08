package com.brynne.crypticalchemy.item;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.block.CrypticAlchemyBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CrypticAlchemyItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CrypticAlchemy.MOD_ID);

    // ITEMS.registerSimpleItem("mist_grass") - basic
    public static final DeferredItem<Item> MIST_GRASS = ITEMS.registerItem("mist_grass", properties -> new Item(properties
            .compostable(ContextIntProviders.COMPOSTABLE_MEDIUM)));
    public static final DeferredItem<Item> MIST_GRASS_SEED = ITEMS.registerItem("mist_grass_seed", properties -> new BlockItem(CrypticAlchemyBlocks.MIST_GRASS_BUSH.get(), properties
            .compostable(ContextIntProviders.COMPOSTABLE_LOW)));




    public static ResourceKey<Item> getResourceKey(Item item){
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
