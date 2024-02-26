package com.menghuan.cn.datagen;

import com.menghuan.cn.item.item;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.book.RecipeCategory;

public class ModRrecipes extends FabricRecipeProvider {
    public ModRrecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, item.RAG,RecipeCategory.MISC,item.VITAMINS);
    }
}
