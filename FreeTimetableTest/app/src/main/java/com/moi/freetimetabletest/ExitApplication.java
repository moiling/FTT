package com.moi.freetimetabletest;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moi on 2015/3/28.
 *
 */
public class ExitApplication extends Application {
    private static List <Activity> activityList = new ArrayList<Activity>();
    private static ExitApplication instance;

    private ExitApplication()  {

    }

    //单例模式中获取唯一的ExitApplication实例
    public static ExitApplication getInstance()  {
        if(null == instance)  {
            instance = new ExitApplication();
        }
        return instance;
    }

    //添加Activity到容器中
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }

    //遍历所有Activity并finish
    public void exit()  {
        for(Activity activity : activityList)  {
            activity.finish();
        }
        System.exit(0);
    }
}
