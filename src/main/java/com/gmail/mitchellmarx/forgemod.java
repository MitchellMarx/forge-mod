/**
 * forgemod Documentation
 * <p>
 * User Story:
 * As a player, I want to craft a special item that opens a user interface (UI). In this UI, I can insert a limited amount of placeable blocks.
 * These blocks are placed in an alternate Minecraft dimension and have blocks similar to the entangled mod placed on all six sides.
 * I can configure each inserted block by selecting which other blocks should be on each side. I can also open the UI of any inserted block.
 * <p>
 * User Requirements:
 * 1. The player should be able to craft a special item. The crafting recipe for this item should be configurable.
 * 2. The special item should open a UI when used. The player should be able to insert a limited amount of placeable blocks into this UI. The number of blocks that can be inserted should be configurable.
 * 3. The blocks inserted into the UI should be placed in an alternate Minecraft dimension. This dimension should be a void, except for the blocks placed by the mod.
 * 4. The placed blocks should have blocks similar to the entangled mod placed on all six sides. These blocks should behave as if they are the block that is linked on the appropriate side as configured in the UI. They should be able to transfer items, energy, fluids, redstone signals, etc.
 * 5. The player should be able to configure each inserted block by selecting which other blocks should be on each side. There should be a UI for configuring each block, allowing the player to link any side of one block to any side of another block.
 * 6. The player should be able to open the UI of any inserted block. The UI of the inserted block is determined by the block itself.
 * <p>
 * Tasks:
 * 1. Crafting the Item: Create a new item class and a JSON file for its crafting recipe. The recipe should be configurable through this JSON file.
 * 2. Creating the UI: Create a GUI that opens when the item is used. This GUI should have slots for the blocks that can be inserted.
 * 3. Inserting Blocks into the UI: Create a system for storing and displaying blocks in the GUI. The number of blocks that can be inserted should be configurable.
 * 4. Creating the Alternate Dimension: Create a new dimension that is a void, except for the blocks placed by the mod.
 * 5. Creating the Entangled Blocks: Create a new block class that behaves similarly to the entangled blocks. These blocks should be able to transfer items, energy, fluids, redstone signals, etc. based on their configuration.
 * 6. Configuring the Blocks: Create a UI for configuring each block. This UI should allow players to link any side of one block to any side of another block.
 * 7. Opening the Block UI: Create a system for opening the UI of a block when it is clicked on. The UI should be determined by the block itself.
 */
package com.gmail.mitchellmarx;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml filec
@Mod(forgemod.MODID)
public class forgemod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "forgemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "forgemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "forgemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MOD_ITEM = ITEMS.register("mod_item", ModItem::new);

    // Creates a new Block with the id "forgemod:example_block", combining the namespace and path
    // public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "forgemod:example_block", combining the namespace and path
    // public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));


    public forgemod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
