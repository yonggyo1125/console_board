package org.koreait.global.router;

import org.koreait.global.configs.ControllerConfig;
import org.koreait.main.controllers.MainController;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 컨트롤러 라우팅
 *
 */
public class Router {
    private static Router instance;
    private final Map<Class<?>, Controller> controllers;

    private Router() {
        controllers = new HashMap<>(); // 컨트롤러 컨테이너 공간 생성

        ControllerConfig controllerConfig = new ControllerConfig();
        Method[] methods = controllerConfig.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                Object obj = method.invoke(controllerConfig);
                if (obj instanceof Controller con) {
                    controllers.put(obj.getClass(), con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }

        return instance;
    }

    public static void start() {
        change(MainController.class);
    }

    /**
     * 컨트롤러 변경 처리
     *
     * @param clazz
     */
    public void _change(Class<?> clazz) {
        Controller controller = controllers.get(clazz);
        controller.run();

    }

    public static void change(Class<?> clazz) {
        getInstance()._change(clazz);
    }
}
