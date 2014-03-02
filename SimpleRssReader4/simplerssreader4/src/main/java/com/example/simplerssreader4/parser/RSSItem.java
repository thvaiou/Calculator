package com.example.simplerssreader4.parser;

import java.io.Serializable;

/**
 * Created by thomas on 2/24/14.
 */
public class RSSItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String _title = null;
    private String _description = null;
    private String _date = null;
    private String _image = null;

    void setTitle(String title) {
        _title = title;
    }

    void setDescription(String description) {
        _description = description;
    }

    void setDate(String pubDate) {
        _date = pubDate;
    }

    void setImage(String image) {
        _image = image;
    }

    public String getTitle() {
        return _title;
    }

    public String getDescription() {
        return _description;
    }

    public String getDate() {
        return _date;
    }

    public String getImage() {
        return _image;
    }
}
