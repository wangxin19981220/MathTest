package com.example.mathtest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.util.Random;

public class ViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    Boolean win_flag = false;
    static String key_high_score = "key_high_score";
    static String left_number = "left_number";
    static String right_number = "ringht_number";
    static String fuhao = "key_fuhao";
    static String answer = "key_answer";
    static String key_current_score = "key_current_scor";

    public ViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        if (!handle.contains(key_high_score)) {
            SharedPreferences shp = getApplication().getSharedPreferences("save_shp", Context.MODE_PRIVATE);
            handle.set(key_high_score, shp.getInt(key_high_score, 0));
            handle.set(left_number, 0);
            handle.set(right_number, 0);
            handle.set(fuhao, "+");
            handle.set(answer, 0);
            handle.set(key_current_score, 0);
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getleftnumber() {
        return handle.getLiveData(left_number);
    }

    public MutableLiveData<Integer> getrightnumber() {
        return handle.getLiveData(right_number);
    }

    public MutableLiveData<String> getfuhao() {
        return handle.getLiveData(fuhao);
    }

    public MutableLiveData<Integer> getanswer() {
        return handle.getLiveData(answer);
    }

    public MutableLiveData<Integer> getcurrentscore() {
        return handle.getLiveData(key_current_score);
    }

    public MutableLiveData<Integer> gethighscore() {
        return handle.getLiveData(key_high_score);
    }

    void title() {
        int level = 10;
        int fuhao1 = 4;
        Random random = new Random();
        int x, y;
        x = random.nextInt(level) + 1;
        y = random.nextInt(level) + 1;
        if (random.nextInt(fuhao1) + 1 == 1) {
            getfuhao().setValue("+");
            getanswer().setValue(x + y);
            getleftnumber().setValue(x);
            getrightnumber().setValue(y);
        } else if (random.nextInt(fuhao1) + 1 == 2) {
            getfuhao().setValue("-");
            if (x > y) {
                getanswer().setValue(x - y);
                getleftnumber().setValue(x);
                getrightnumber().setValue(y);
            } else {
                getanswer().setValue(y - x);
                getleftnumber().setValue(y);
                getrightnumber().setValue(x);
            }

        } else {
            getfuhao().setValue("*");
            getanswer().setValue(x * y);
            getleftnumber().setValue(x);
            getrightnumber().setValue(y);
        }
    }

    public void save() {
        SharedPreferences shp = getApplication().getSharedPreferences("save_shp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key_high_score, gethighscore().getValue());
        editor.apply();
    }

    public void End() {
        getcurrentscore().setValue(getcurrentscore().getValue() + 1);
        if (getcurrentscore().getValue() > gethighscore().getValue()) {
            gethighscore().setValue(getcurrentscore().getValue());
            win_flag = true;
        }
        title();
    }
}
