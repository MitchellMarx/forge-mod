/**
 * ModItem Class Documentation
 *
 * Description:
 * This class represents the special item that players can craft and use to interact with the mod.
 * When used, it opens a user interface (UI) where players can insert a limited amount of placeable blocks.
 *
 * User Requirements:
 * 1. The player should be able to craft this special item. The crafting recipe for this item should be configurable.
 * 2. The special item should open a UI when used. The player should be able to insert a limited amount of placeable blocks into this UI. The number of blocks that can be inserted should be configurable.
 *
 * Tasks:
 * 1. Crafting the Item: Create a new item class and a JSON file for its crafting recipe. The recipe should be configurable through this JSON file.
 * 2. Creating the UI: Create a GUI that opens when the item is used. This GUI should have slots for the blocks that can be inserted.
 */
package com.gmail.mitchellmarx;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ModItem extends Item {
    public ModItem() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD));
    }
}
