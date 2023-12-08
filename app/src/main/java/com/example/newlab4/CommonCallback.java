package com.example.newlab4;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CommonCallback<T> extends DiffUtil.Callback{
    private final List<T> oldItems;
    private final List<T> newItems;

    public CommonCallback(List<T> oldItems, List<T> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
       T oldItem = oldItems.get(oldItemPosition);
        T newItem = newItems.get(newItemPosition);
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldItems.get(oldItemPosition);
        T newItem = newItems.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
