package com.codedawn.word.util;

import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.json.booklist.BookJsonRootBean;
import com.google.gson.Gson;

import java.util.List;

public class GsonUtil {
    private static Gson gson = new Gson();

    public static BookJsonRootBean parseJsonToBook(String jsonData) {
        BookJsonRootBean bookJsonRootBean = gson.fromJson(jsonData, BookJsonRootBean.class);
        return bookJsonRootBean;
    }
}
