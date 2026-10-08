package com.brynne.crypticalchemy.datagen;

import com.brynne.crypticalchemy.CrypticAlchemy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class CrypticAlchemyBlockTagsProvider extends BlockTagsProvider {
    public CrypticAlchemyBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, CrypticAlchemy.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /* Add to Vanilla Tags */
        //tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CrypticAlchemyBlocks.getResourceKey(CrypticAlchemyBlocks.????.get()));

        /* Add to NeoForge Tags */
        //tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(CrypticAlchemyBlocks.getResourceKey(CrypticAlchemyBlocks.????.get()));

        /* Add to Custom Tags */
    }
}
