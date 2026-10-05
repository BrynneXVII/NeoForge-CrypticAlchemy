package com.brynne.crypticalchemy.block;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.block.custom.orientableHayBlock;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class CrypticAlchemyBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CrypticAlchemy.MOD_ID);

    public static final DeferredBlock<Block> MIST_GRASS_BLOCK = registerBlock("mist_grass_block", properties -> new orientableHayBlock(properties
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_BLUE)
            .lightLevel((state) -> 4)
    ));

    //public static final DeferredBlock<FireflyBushBlock> MIST_GRASS_BUSH = BLOCKS.registerBlock("mist_grass_bush", Blocks.FIREFLY_BUSH.properties());


    //Helper (register) Methods
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name,function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        CrypticAlchemyItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
