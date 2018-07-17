package com.gmail.gak.artem.app;

import org.springframework.data.domain.Sort;

public class StaticValue {

    public static final String APP_NAME = "Vesta Home Finances";
    public static final String APP_NAME_SHORT = "Vesta HF";

    public static final String PATH_DEFAULT = "";
    public static final String PATH_ADMIN_PANEL = "apanel";
    public static final String PATH_LOGIN = "login";
    public static final String PATH_USER = PATH_ADMIN_PANEL + "/user";
    public static final String PATH_USER_PROFILE = "";

    public static final String ICON_USER = "user";
    public static final String ICON_ADMIN_PANEL = "assessment";
    public static final String ICON_USER_PROFILE = "account-box";
    public static final String ICON_LEDGER = "account-box";

    public static final String TITLE_ADMIN_APANEL = "Панель управления";
    public static final String TITLE_LOGIN = "Вход";
    public static final String TITLE_USER = "Пользователи";
    public static final String TITLE_USER_PROFILE = "Мой аккаунт";
    public static final String TITLE_LEDGER = "Книги";

    public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
    public static final String[] USER_SORT_FIELDS = {"id", "email", "username"};
    public static final String[] ROLE_SORT_FIELDS = {"id", "name"};

    public static final String VIEWPORT = "width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes";
}
