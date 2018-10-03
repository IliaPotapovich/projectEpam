package com.potapovich.project.localization;

import com.potapovich.project.constant.Constant;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    /**
     * A class that allows you send localized messages using the Resource Bundle
     */

    private String sessionLocale;
    private ResourceBundle bundle;

    public MessageManager(String sessionLocale) {
        this.sessionLocale = sessionLocale;
    }

    /**
     * Sets a certain Resource Bundle depending on the required locale
     * void
     */
    private void setResourceBundle(String sessionLocale) {
        if (sessionLocale != null) {
            if (sessionLocale.equals("be_BY")) {
                bundle = ResourceBundle.getBundle(Constant.MESSAGES, new Locale("be", "BY"));
            }
            if (sessionLocale.equals("en_US")) {
                bundle = ResourceBundle.getBundle(Constant.MESSAGES, new Locale("en", "US"));
            }
            if (sessionLocale.equals("ru_RU")) {
                bundle = ResourceBundle.getBundle(Constant.MESSAGES, new Locale("ru", "RU"));
            }
        } else {
            bundle = ResourceBundle.getBundle(Constant.MESSAGES, Locale.getDefault());
        }
    }
    /**
     * Gets a certain message from the Resource Bundle by the key
     * @return String (key of message)
     */
    public String getMessage(String key) {
        setResourceBundle(sessionLocale);
        return bundle.getString(key);
    }
}
