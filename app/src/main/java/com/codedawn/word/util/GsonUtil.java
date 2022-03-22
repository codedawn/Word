package com.codedawn.word.util;

import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.json.booklist.BookJsonRootBean;
import com.codedawn.word.json.wordbook.VocabularyJsonRootBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtil {
    private static Gson gson = new Gson();

    public static BookJsonRootBean parseJsonToBookJsonRootBean(String jsonData) {
        return gson.fromJson(jsonData, BookJsonRootBean.class);
    }

    public static String bookToJson(Book book) {
        return gson.toJson(book);
    }

    public static Book parseJsonToBook(String jsonData) {
        return gson.fromJson(jsonData, Book.class);
    }

    public static List<VocabularyJsonRootBean> parseJsonToVocabularyList(String jsonData) {
        return gson.fromJson(jsonData, new TypeToken<List<VocabularyJsonRootBean>>() {
        }.getType());
    }

    public static String vocabularyJsonRootBeanToJson(VocabularyJsonRootBean vocabularyJsonRootBean) {
        return gson.toJson(vocabularyJsonRootBean);
    }

    public static VocabularyJsonRootBean parseJsonToVocabularyJsonRootBean(String jsonData) {
        return gson.fromJson(jsonData,VocabularyJsonRootBean.class);
    }
}
