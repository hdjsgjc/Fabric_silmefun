package com.menghuan.cn.item.ToolItems;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class PortableCraftingtabble extends Item {

    public PortableCraftingtabble(Settings settings) {
        super(settings);
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient()) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerEntity) -> new CraftingScreenHandler(syncId, playerInventory ) {
                @Override
                public void onClosed(PlayerEntity player) {
                    super.onClosed(player);
                    for (int i = 1; i < 10; i++) {
                        Slot slot = ((CraftingScreenHandler) player.currentScreenHandler).getSlot(i);
                        ItemStack itemStack = slot.getStack();
                        if (!itemStack.isEmpty() && itemStack.getItem() != Items.AIR) {
                            if (!player.getInventory().insertStack(itemStack)) {
                                player.dropItem(itemStack, false);
                            }

                        }
                    }
                }

                @Override
                public void onContentChanged(Inventory inventory) {
                    if (player.currentScreenHandler instanceof CraftingScreenHandler) {
                        CraftingScreenHandler craftingTable = (CraftingScreenHandler) player.currentScreenHandler;

                        // 获取合成台中的输入格子
                        DefaultedList<ItemStack> inputItems = DefaultedList.ofSize(9, ItemStack.EMPTY);
                        for (int i = 1; i < 10; i++) { // 输入格子的索引从 1 到 9
                            Slot slot = craftingTable.getSlot(i);
                            if (slot.hasStack()) {
                                inputItems.set(i-1, slot.getStack());
                            }
                        }
                        CrafManager(world,inputItems,player);
                    }
                }

            }, Text.of("便携式工作台")));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }


    public static void CrafManager(World world, DefaultedList<ItemStack> inputItems, PlayerEntity player) {
        RecipeManager recipeManager = world.getRecipeManager();
        CraftingInventory craftingInventory = new CraftingInventory(player.playerScreenHandler, 3, 3);

        // 将物品放入合成表格的输入槽位中
        for (int i = 0; i < 9; i++) {
            if (!inputItems.get(i).isEmpty()) {
                craftingInventory.setStack(i, inputItems.get(i));
            }
        }

        // 查找配方
        RecipeEntry<CraftingRecipe> recipe = recipeManager.getFirstMatch(RecipeType.CRAFTING, craftingInventory, world).orElse(null);
        if (recipe != null && inputItems != null) {
            ItemStack result = recipe.value().craft(craftingInventory, world.getRegistryManager());
            CraftingScreenHandler craftingTable = (CraftingScreenHandler) player.currentScreenHandler;
            Slot outputSlot = craftingTable.getSlot(0);
            outputSlot.setStack(result);
        } else {
            CraftingScreenHandler craftingTable = (CraftingScreenHandler) player.currentScreenHandler;
            Slot outputSlot = craftingTable.getSlot(0);
            outputSlot.setStack(ItemStack.EMPTY);
        }
    }

}

