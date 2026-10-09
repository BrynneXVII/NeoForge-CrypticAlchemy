package com.brynne.crypticalchemy.block;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.block.custom.MistGrassBush;
import com.brynne.crypticalchemy.block.custom.OrientableHayBlock;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Function;

public class CrypticAlchemyBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CrypticAlchemy.MOD_ID);
    /* Notes */
    //use .requiresCorrectToolForDrops() to need an appropriate tool as dictated by tags
    //use .noLootTable() if you do not want the block to have a loot table

    /* All Blocks */
    public static final DeferredBlock<Block> MIST_GRASS_BLOCK = registerBlock("mist_grass_block", properties -> new OrientableHayBlock(properties
            .strength(0.5F)
            .sound(SoundType.GRASS)
            .mapColor(MapColor.COLOR_BLUE)
            .lightLevel((state) -> 4)
    )); // new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH)

    public static final DeferredBlock<Block> MIST_GRASS_BUSH = BLOCKS.registerBlock("mist_grass_bush", properties -> new MistGrassBush(properties
            .randomTicks()
            .sound(SoundType.SWEET_BERRY_BUSH)
            .mapColor(MapColor.PLANT)
            .pushReaction(PushReaction.POPPED)
            .lightLevel((state) -> state.getValue(MistGrassBush.AGE) == 0 ? 4 : (state.getValue(MistGrassBush.AGE) == 1 ? 6 : 8))
            .noCollision())); //***Add properties!


    /* Helper Methods */
    public static ResourceKey<Block> getResourceKey(Block block){
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    //Register Block when the BlockItem has no properties
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name,function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //Register BlockItem with no properties
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        CrypticAlchemyItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
