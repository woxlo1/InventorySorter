package com.woxloi.inventorysorter;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(InventorySorterMod.MOD_ID)
public class InventorySorterMod {
    public static final String MOD_ID = "inventorysorter";
    public static final Logger LOGGER = LogManager.getLogger();

    public InventorySorterMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        LOGGER.info("Inventory Sorter Mod initialized!");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup complete!");
    }
}
