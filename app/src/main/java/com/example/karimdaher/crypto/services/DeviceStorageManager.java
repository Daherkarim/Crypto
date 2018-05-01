package com.example.karimdaher.crypto.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.models.User;
import com.google.gson.Gson;


public class DeviceStorageManager {


        private Gson gson;
        private final String USER_KEY = "PROFILE";
        private final String testing = "Currency";
        private static DeviceStorageManager deviceStorageManager;
        private SharedPreferences sharedPreferences;

        public DeviceStorageManager(Context context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            gson = new Gson();
        }

        public static DeviceStorageManager getInstance(Context context) {
            if (deviceStorageManager == null) {
                deviceStorageManager = new DeviceStorageManager(context);
            }
            return deviceStorageManager;
        }

        public User getUser() {
            String userJson = sharedPreferences.getString(USER_KEY, null);
            if (userJson == null) {
                return null;
            }
            try {
                return gson.fromJson(userJson, User.class);
            } catch (Exception e) {
                return null;
            }
        } public Currency doTest() {
            String userJson = sharedPreferences.getString(testing, null);
            if (userJson == null) {
                return null;
            }
            try {
                return gson.fromJson(userJson, Currency.class);
            } catch (Exception e) {
                return null;
            }
        }

        public void saveUser(User user) {
            String userJson = gson.toJson(user);
            sharedPreferences
                    .edit()
                    .putString(USER_KEY, userJson)
                    .commit();
        }

        public void deleteUser() {
            sharedPreferences
                    .edit()
                    .putString(USER_KEY, null)
                    .commit();
        }


    public void saveCurrency(Currency currency) {
        String userJson = gson.toJson(currency);
        sharedPreferences
                .edit()
                .putString(testing, userJson)
                .commit();
    }
}

