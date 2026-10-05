package com.brynne.crypticalchemy.datagen;

import com.brynne.crypticalchemy.CrypticAlchemy;
import com.brynne.crypticalchemy.block.CrypticAlchemyBlocks;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;


import static net.minecraft.client.data.models.BlockModelGenerators.ROTATIONS_COLUMN_WITH_FACING;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class CrypticAlchemyModelProvider extends ModelProvider {
    public CrypticAlchemyModelProvider(PackOutput output) {
        super(output, CrypticAlchemy.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        /* ITEMS */
        itemModels.generateFlatItem(CrypticAlchemyItems.MIST_GRASS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(CrypticAlchemyItems.MIST_GRASS_SEED.get(), ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        MultiVariant mist_grass_block_model = plainVariant(TexturedModel.CUBE_BOTTOM_TOP.create(CrypticAlchemyBlocks.MIST_GRASS_BLOCK.get(), blockModels.modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(CrypticAlchemyBlocks.MIST_GRASS_BLOCK.get(),mist_grass_block_model).with(ROTATIONS_COLUMN_WITH_FACING));


    }


}
