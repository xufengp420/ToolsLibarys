package com.example.xufeng.toolslibary;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;


/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class BaseAppManager {
//    private static Stack<WeakReference<Activity>> activityStack;
//    private static BaseAppManager instance;
//
//    private BaseAppManager() {
//    }
//
//    /**
//     * 懒汉模式
//     * 单实例 , UI无需考虑多线程同步问题
//     */
//    public static synchronized BaseAppManager getAppManager() {
//        if (instance == null) {
//            instance = new BaseAppManager();
//        }
//        return instance;
//    }
//
//    /**
//     * 添加Activity到栈
//     */
//    public void addActivity(WeakReference<Activity> activity) {
//        if (activityStack == null) {
//            activityStack = new Stack<>();
//        }
//        activityStack.add(activity);
//    }
//
//    /**
//     * 获取当前Activity（栈顶Activity）
//     */
//    public Activity currentActivity() {
//        Activity activity = activityStack.lastElement().get();
//        if (null == activity) {
//            return null;
//        } else {
//            return activityStack.lastElement().get();
//        }
//    }
//
//    /**
//     * 获取当前Activity（栈顶Activity） 没有找到则返回null
//     */
//    public Activity findActivity(Class<?> cls) {
//        Activity activity = null;
//        for (WeakReference<Activity> aty : activityStack) {
//            if (aty.get().getClass().equals(cls)) {
//                activity = aty.get();
//                break;
//            }
//        }
//        return activity;
//    }
//
//    /**
//     * 结束当前Activity（栈顶Activity）
//     */
//    public void finishActivity() {
//        try {
//            WeakReference<Activity> activity = activityStack.lastElement();
//            finishActivity(activity);
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     * 结束指定的Activity(重载)
//     */
//    public void finishActivity(WeakReference<Activity> activity) {
//        try {
//            Iterator<WeakReference<Activity>> iterator = activityStack.iterator();
//            while (iterator.hasNext()) {
//                WeakReference<Activity> stackActivity = iterator.next();
//                if (stackActivity.get() == null) {
//                    iterator.remove();
//                    continue;
//                }
//                if (stackActivity.get().getClass().getName().equals(activity.get().getClass().getName())) {
//                    iterator.remove();
//                    stackActivity.get().finish();
//                    break;
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     * 结束指定的Activity(重载)
//     */
//    public void finishActivity(Class<?> cls) {
//        try {
//            ListIterator<WeakReference<Activity>> listIterator = activityStack.listIterator();
//            while (listIterator.hasNext()) {
//                Activity activity = listIterator.next().get();
//                if (activity == null) {
//                    listIterator.remove();
//                    continue;
//                }
//                if (activity.getClass() == cls) {
//                    listIterator.remove();
//                    if (activity != null) {
//                        activity.finish();
//                    }
//                    break;
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
//     *
//     * @param cls
//     */
//    public void finishOthersActivity(Class<?> cls) {
//        try {
//            for (int i = 0; i < activityStack.size(); i++) {
//                WeakReference<Activity> activity = activityStack.get(i);
//                if (activity.getClass().equals(cls)) {
//                    break;
//                }
//                if (activityStack.get(i) != null) {
//                    finishActivity(activity);
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     * 结束所有Activity
//     */
//    public void finishAllActivity() {
//        try {
//            ListIterator<WeakReference<Activity>> listIterator = activityStack.listIterator();
//            while (listIterator.hasNext()) {
//                Activity activity = listIterator.next().get();
//                if (activity != null) {
//                    activity.finish();
//                }
//                listIterator.remove();
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    /**
//     * 应用程序退出
//     */
//    public void AppExit(Context context) {
//        try {
//            finishAllActivity();
//            ActivityManager activityMgr = (ActivityManager) context
//                    .getSystemService(Context.ACTIVITY_SERVICE);
//            activityMgr.killBackgroundProcesses(context.getPackageName());
//            System.exit(0);
//        } catch (Exception e) {
//            System.exit(0);
//        }
//    }
private static Stack<Activity> activityStack;
    private static BaseAppManager instance;

    private BaseAppManager() {
    }

    /**
     * 懒汉模式
     * 单实例 , UI无需考虑多线程同步问题
     */
    public static synchronized BaseAppManager getAppManager() {
        if (instance == null) {
            instance = new BaseAppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（栈顶Activity）
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    public Activity findActivity(Class<?> cls) {
        Activity activity = null;
        for (Activity aty : activityStack) {
            if (aty.getClass().equals(cls)) {
                activity = aty;
                break;
            }
        }
        return activity;
    }

    /**
     * 结束当前Activity（栈顶Activity）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {

        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();

    }

    /**
     * 应用程序退出
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }
}