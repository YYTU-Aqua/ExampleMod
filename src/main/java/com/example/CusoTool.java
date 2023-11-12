package com.example;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CusoTool implements ToolMaterial {

    public static final CusoTool INSTANCE = new CusoTool();

    @Override
    public int getDurability() {
    return 500;
    }
    public float getMiningSpeedMultiplier() {
        return 99.0F;
    }
    public float getAttackDamage() {
        return 3.0F;
    }
    public int getMiningLevel() {
        return 2;
    }
    public int getEnchantability() {
        return 15;
    }
    public Ingredient getRepairIngredient() {
    return Ingredient.ofItems(ExampleMod.CUSTOM_ITEM);
    }
}
