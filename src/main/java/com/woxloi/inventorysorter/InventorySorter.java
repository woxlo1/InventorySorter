package com.woxloi.inventorysorter;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventorySorter {
    
    /**
     * コンテナ内のアイテムを整理する
     * 1. 同じアイテムをスタックにまとめる
     * 2. アイテムID順にソート
     * 3. 空きスロットを後ろに移動
     */
    public static void sortContainer(Container container, int startSlot, int endSlot) {
        // アイテムを取得
        List<ItemStack> items = new ArrayList<>();
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                items.add(stack.copy());
            }
        }

        // 同じアイテムをまとめる
        List<ItemStack> merged = mergeStacks(items);

        // ソート（アイテムID → 個数順）
        merged.sort(Comparator
            .comparing((ItemStack stack) -> stack.getItem().toString())
            .thenComparing(ItemStack::getCount, Comparator.reverseOrder())
        );

        // コンテナをクリア
        for (int i = startSlot; i < endSlot; i++) {
            container.setItem(i, ItemStack.EMPTY);
        }

        // ソート済みアイテムを配置
        for (int i = 0; i < merged.size() && (startSlot + i) < endSlot; i++) {
            container.setItem(startSlot + i, merged.get(i));
        }
    }

    /**
     * 同じアイテムをスタックにまとめる
     */
    private static List<ItemStack> mergeStacks(List<ItemStack> items) {
        List<ItemStack> result = new ArrayList<>();

        for (ItemStack newStack : items) {
            boolean merged = false;

            // 既存のスタックに追加できるか確認
            for (ItemStack existingStack : result) {
                if (canMerge(existingStack, newStack)) {
                    int spaceLeft = existingStack.getMaxStackSize() - existingStack.getCount();
                    int toAdd = Math.min(spaceLeft, newStack.getCount());
                    
                    existingStack.grow(toAdd);
                    newStack.shrink(toAdd);

                    if (newStack.isEmpty()) {
                        merged = true;
                        break;
                    }
                }
            }

            // マージできなかった分は新しいスタックとして追加
            if (!newStack.isEmpty()) {
                result.add(newStack.copy());
            }
        }

        return result;
    }

    /**
     * 2つのアイテムスタックがマージ可能か判定
     */
    private static boolean canMerge(ItemStack stack1, ItemStack stack2) {
        if (stack1.isEmpty() || stack2.isEmpty()) {
            return false;
        }
        if (!ItemStack.isSameItem(stack1, stack2)) {
            return false;
        }
        if (!ItemStack.isSameItemSameTags(stack1, stack2)) {
            return false;
        }
        return stack1.getCount() < stack1.getMaxStackSize();
    }
}
