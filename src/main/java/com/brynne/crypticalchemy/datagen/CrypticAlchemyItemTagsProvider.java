package com.brynne.crypticalchemy.datagen;

import com.brynne.crypticalchemy.CrypticAlchemy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class CrypticAlchemyItemTagsProvider extends ItemTagsProvider {
    public CrypticAlchemyItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, CrypticAlchemy.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /* Add to Vanilla Tags */

        /* Add to NeoForge Tags */

        /* Add to Custom Tags */
        //tag(CrypticAlchemyTags.Items.????).add(ItemIds.IRON_INGOT).add(BlockItemIds.REDSTONE_DUST.item()).add(CrypticAlchemyItems.getResourceKey(CrypticAlchemyItems.???.get()))

    }
}
