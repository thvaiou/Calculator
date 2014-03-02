package com.example.simplerssreader4.parser;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by thomas on 2/24/14.
 */
public class RSSFeed implements Serializable {

    private static final long serialVersionUID = 1L;
    private int _itemcount = 0;
    private List<RSSItem> _itemList;

    RSSFeed() {
        _itemList = new Vector<RSSItem>(0);
    }

    void addItem(RSSItem item) {
        _itemList.add(item);
        _itemcount++;
    }

    public RSSItem getItem(int location) {
        return _itemList.get(location);
    }

    public int getItemCount() {
        return _itemcount;
    }
}
