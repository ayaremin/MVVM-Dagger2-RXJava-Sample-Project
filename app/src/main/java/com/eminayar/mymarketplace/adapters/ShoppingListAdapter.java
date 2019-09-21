package com.eminayar.mymarketplace.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import com.eminayar.mymarketplace.R;
import com.eminayar.mymarketplace.data.models.ShoppingDetail;
import com.eminayar.mymarketplace.databinding.ListItemShoppingChildBinding;
import com.eminayar.mymarketplace.databinding.ListItemShoppingGroupBinding;
import com.eminayar.mymarketplace.viewmodels.ShoppingListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends BaseExpandableListAdapter {

    private ListItemShoppingGroupBinding groupBinding;
    private ListItemShoppingChildBinding childBinding;
    private ShoppingListViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    private final List<ShoppingDetail> data = new ArrayList<>();

    public ShoppingListAdapter(ShoppingListViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.viewModel = viewModel;
        this.lifecycleOwner = lifecycleOwner;
        viewModel.getShoppingDetails().observe(lifecycleOwner, items -> {
            data.clear();
            if (items != null) {
                data.addAll(items);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        //Because every item has only 1 detail object
        return 1;
    }

    @Override
    public Object getGroup(int position) {
        return data.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getProductDetail();
    }

    // Here in getting group id and child id, I am returning hashCode of orderName and productDetail
    // object to make expandable list view works without glitches
    @Override
    public long getGroupId(int i) {
        return data.get(i).getOrderName().hashCode();
    }

    @Override
    public long getChildId(int i, int i1) {
        return data.get(i).getProductDetail().hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            groupBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_item_shopping_group, viewGroup, false);
            view = groupBinding.getRoot();
        } else {
            groupBinding = (ListItemShoppingGroupBinding) view.getTag();
        }
        groupBinding.setDetailItem(data.get(i));
        view.setTag(groupBinding);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            childBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.list_item_shopping_child, viewGroup, false);
            view = childBinding.getRoot();
        } else {
            childBinding = (ListItemShoppingChildBinding) view.getTag();
        }
        childBinding.setOrderDetail(data.get(i).getProductDetail());
        view.setTag(childBinding);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}