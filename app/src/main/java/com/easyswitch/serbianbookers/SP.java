package com.easyswitch.serbianbookers;

import android.content.Context;
import android.content.SharedPreferences;

import com.easyswitch.serbianbookers.models.Data;
import com.easyswitch.serbianbookers.models.User;

public class SP {

    private static SP myInstance;
    private static SharedPreferences pref = null;

    private static final String USER = "user_new";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASS = "pass";
    private static final String REMEMBER = "remember";
    private static final String API_KEY = "api_key";
    private static final String USER_EXIST = "user_exist";
    private static final String DATA = "data";

    private SP(Context context) {
        init(context);
    }

    private void init(Context context) {
        pref = context.getSharedPreferences("SAFetlz", Context.MODE_PRIVATE);
    }

    public Data getDATA() {
        String dataString = getStringValueFromPreferences(DATA, null);

        return  App.getInstance().getGson().fromJson(dataString, Data.class);
//        if (dataString == null) {
//            return null;
//        }
//
//        try {
//            Data data = App.getInstance().getGson().fromJson(dataString, Data.class);
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//            logout();
//            return  null;
//        }
    }

    public void setDATA(Data data) {
        saveStringValueToPreferences(DATA, App.getInstance().getGson().toJson(data));
    }

    public boolean getUserExist() {
        return getBooleanValueFromPreferences(USER_EXIST, false);
    }

    public void setUserExist(boolean userExist) {
        saveBooleanValueToPreferences(USER_EXIST, userExist);
    }

    public User getUser() {
        String userString = getStringValueFromPreferences(USER, null);

        if (userString == null)
            return null;

        try {
            User user = App.getInstance().getGson().fromJson(userString, User.class);
            return user;
        } catch (Exception e){
            e.printStackTrace();
            logout();
            return null;
        }
    }

    public void logout() {
        saveStringValueToPreferences(USER, null);
        saveStringValueToPreferences(EMAIL, null);
        saveStringValueToPreferences(USERNAME, null);
        saveStringValueToPreferences(PASS, null);
        saveStringValueToPreferences(API_KEY, null);
//        saveStringValueToPreferences(EMAIL, null);
        saveBooleanValueToPreferences(USER, false);
//        saveStringValueToPreferences(DATA, null);
//        App.getInstance().setCurrentUser(null);

//        App.getInstance().setCurrentUser(null);
    }

//    public void setUser(User user) {
//        saveStringValueToPreferences(USER, App.getInstance().getGson().toJson(user));
//    }

    public static SP getMyInstance() {
        return myInstance;
    }

    public static void setMyInstance(SP myInstance) {
        SP.myInstance = myInstance;
    }

    public void setEmail(String email) { saveStringValueToPreferences(EMAIL , email);}

    public String getEmail(){
        return getStringValueFromPreferences(EMAIL, null);
    }

    public void setUsername(String username) { saveStringValueToPreferences(USERNAME, username);}

    public String getUsername() {
        return getStringValueFromPreferences(USERNAME, null);
    }

    public void setPassword(String password) { saveStringValueToPreferences(PASS, password); }

    public String getPassword() {
        return getStringValueFromPreferences(PASS, null);
    }

    private boolean getBooleanValueFromPreferences(String key, boolean defaultVal) {
        return pref.getBoolean(key, defaultVal);
    }

    public void setRemember(String remember) { saveStringValueToPreferences(REMEMBER, remember);}

    public String getRemember() { return getStringValueFromPreferences(REMEMBER, null); }

    private void saveBooleanValueToPreferences(String key, boolean value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void saveStringValueToPreferences(String key, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String getStringValueFromPreferences(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    public static synchronized SP getInstance() {
        if (myInstance == null) {
            myInstance = new SP(App.getInstance().getApplicationContext());
        }
        return myInstance;
    }
}
