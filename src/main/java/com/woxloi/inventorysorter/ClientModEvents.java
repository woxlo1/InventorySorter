package com.woxloi.inventorysorter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InventorySorterMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    
    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyBindings.register();
        event.register(KeyBindings.sortKey);
        InventorySorterMod.LOGGER.info("Key mappings registered!");
    }
}

@Mod.EventBusSubscriber(modid = InventorySorterMod.MOD_ID, value = Dist.CLIENT)
public class ClientForgeEvents {
    
    @SubscribeEvent
    public static void onKeyInput(ScreenEvent.KeyPressed.Post event) {
        Minecraft mc = Minecraft.getInstance();
        
        if (KeyBindings.sortKey.isDown() && mc.screen instanceof AbstractContainerScreen<?> containerScreen) {
            AbstractContainerMenu menu = containerScreen.getMenu();
            
            try {
                // プレイヤーインベントリを整理
                if (menu.slots.size() >= 36) {
                    // メインインベントリ（スロット9-35）
                    InventorySorter.sortContainer(
                        menu.slots.get(9).container,
                        9,
                        36
                    );
                }
                
                // チェストの場合、チェスト部分も整理
                if (menu instanceof ChestMenu chestMenu) {
                    int chestSize = menu.slots.size() - 36;
                    if (chestSize > 0) {
                        InventorySorter.sortContainer(
                            menu.slots.get(0).container,
                            0,
                            chestSize
                        );
                    }
                }
                
                InventorySorterMod.LOGGER.info("Inventory sorted!");
            } catch (Exception e) {
                InventorySorterMod.LOGGER.error("Failed to sort inventory", e);
            }
        }
    }
}
