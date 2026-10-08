package com.brynne.crypticalchemy.creativemodetab;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.block.CrypticAlchemyBlocks;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CrypticAlchemyCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CrypticAlchemy.MOD_ID);

    public static final Supplier<CreativeModeTab> CRYPTIC_ALCHEMY_ITEMS_TAB = CREATIVE_MODE_TABS.register("cryptic_alchemy_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(CrypticAlchemyItems.MIST_GRASS_SEED.get()))
                    .title(Component.translatable("creativetab.crypticalchemy.cryptic_alchemy_items"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(CrypticAlchemy.MOD_ID, "cryptic_alchemy_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //ITEMS IN CRYPTIC ALCHEMY ITEMS TAB
                        output.accept(CrypticAlchemyItems.MIST_GRASS);
                        output.accept(CrypticAlchemyItems.MIST_GRASS_SEED);
                    })
            .build());

    public static final Supplier<CreativeModeTab> CRYPTIC_ALCHEMY_BLOCKS_TAB = CREATIVE_MODE_TABS.register("cryptic_alchemy_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(CrypticAlchemyBlocks.MIST_GRASS_BLOCK.get()))
                    .title(Component.translatable("creativetab.crypticalchemy.cryptic_alchemy_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //ITEMS IN CRYPTIC ALCHEMY BLOCKS TAB
                        output.accept(CrypticAlchemyBlocks.MIST_GRASS_BLOCK);
                        output.accept(CrypticAlchemyBlocks.MIST_GRASS_BUSH);
                    })
                    .build());

    public static final Supplier<CreativeModeTab> ALCHEMY_COMPONENTS_TAB = CREATIVE_MODE_TABS.register("alchemy_components_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(CrypticAlchemyItems.MIST_GRASS.get()))
                    .title(Component.translatable("creativetab.crypticalchemy.alchemy_components"))
                    .withTabsAfter(Identifier.fromNamespaceAndPath(CrypticAlchemy.MOD_ID, "cryptic_alchemy_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //ITEMS IN ALCHEMY COMPONENTS TAB
                        output.accept(CrypticAlchemyItems.MIST_GRASS);
                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
