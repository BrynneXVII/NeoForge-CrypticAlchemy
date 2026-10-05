package com.brynne.crypticalchemy.item;

import com.brynne.crypticalchemy.CrypticAlchemy;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CrypticAlchemyItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CrypticAlchemy.MOD_ID);

    public static final DeferredItem<Item> MIST_GRASS = ITEMS.registerSimpleItem("mist_grass");
    public static final DeferredItem<Item> MIST_GRASS_SEED = ITEMS.registerSimpleItem("mist_grass_seed");

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
