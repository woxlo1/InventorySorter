package com.woxloi.inventorysorter;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORY = "key.categories.inventorysorter";
    public static final String KEY_SORT = "key.inventorysorter.sort";

    public static KeyMapping sortKey;

    public static void register() {
        sortKey = new KeyMapping(
            KEY_SORT,
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            KEY_CATEGORY
        );
    }
}
