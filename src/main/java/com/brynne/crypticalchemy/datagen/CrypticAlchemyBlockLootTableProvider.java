package com.brynne.crypticalchemy.datagen;

import com.brynne.crypticalchemy.block.CrypticAlchemyBlocks;
import com.brynne.crypticalchemy.block.custom.MistGrassBush;
import com.brynne.crypticalchemy.item.CrypticAlchemyItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Set;


public class CrypticAlchemyBlockLootTableProvider extends BlockLootSubProvider {

    public CrypticAlchemyBlockLootTableProvider(Context output) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), output);
    }

    @Override
    protected void generate() {
        /* Custom Block Loot Tables */
        dropSelf(CrypticAlchemyBlocks.MIST_GRASS_BLOCK.get()); //simple version - get the block back

        //add(block, builder); - more complex, builder can be premade functions like createOreDrop

        add(
                CrypticAlchemyBlocks.MIST_GRASS_BUSH.get(),
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .add(
                                                        AlternativesEntry.alternatives(
                                                                MistGrassBush.AGE.getPossibleValues(),
                                                                age -> {
                                                                    LootItemCondition.Builder isAge = MatchBlock.blockMatches(
                                                                            this.blocks,
                                                                            CrypticAlchemyBlocks.MIST_GRASS_BUSH.get(),
                                                                            StatePropertiesPredicate.Builder.properties().hasProperty(MistGrassBush.AGE, age.intValue())
                                                                    );
                                                                    if(age == 2){
                                                                        return LootItem.lootTableItem(CrypticAlchemyItems.MIST_GRASS.get())
                                                                                .when(isAge)
                                                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                                                                .apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)));
                                                                    } else if (age == 1) {
                                                                        return LootItem.lootTableItem(CrypticAlchemyItems.MIST_GRASS.get())
                                                                                .when(isAge)
                                                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)));
                                                                    } else {
                                                                        return LootItem.lootTableItem(CrypticAlchemyItems.MIST_GRASS.get())
                                                                                .when(isAge)
                                                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(0)));
                                                                    }
                                                                }
                                                        )
                                                )

                                )
                                .withPool(LootPool.lootPool()
                                        .add(
                                                LootItem.lootTableItem(CrypticAlchemyItems.MIST_GRASS_SEED.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0,1)))
                                                        .apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                                        )

                                )
                )
        );



    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CrypticAlchemyBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
