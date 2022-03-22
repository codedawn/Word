package com.codedawn.word.litepal;

import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.util.GsonUtil;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * 当前登录用户的信息
 */
public class UserData extends LitePalSupport {

    private static UserData instance;


    static {
        instance = LitePal.findFirst(UserData.class);
        if (instance == null) {
            instance = new UserData();
        }
        instance.currentBook = LitePal.findFirst(CurrentBook.class);
        if (instance.currentBook != null) {
            instance.currentBook.setBook(GsonUtil.parseJsonToBook(instance.currentBook.getBookJson()));
        }
    }

    private CurrentBook currentBook;


    private String openId;
    private String accessToken;
    private String expire;

    private String nickname;
    private String avatar;


    public static UserData getInstance() {
        return instance;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static void setInstance(UserData instance) {
        UserData.instance = instance;
    }

    public CurrentBook getCurrentBook() {
        return currentBook;
    }

    public Book getBook() {
        if (currentBook == null) {
            return null;
        }
        return currentBook.getBook();
    }

    public void setCurrentBook(CurrentBook currentBook) {
        this.currentBook = currentBook;
        //保存到数据库
        LitePal.deleteAll("currentbook");
        this.currentBook.save();
    }

    public void setBook(Book book) {
        if (getCurrentBook() == null) {
            currentBook = new CurrentBook();
        }
        currentBook.setBook(book);
    }
}
