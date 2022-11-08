package com.example.icecube.services;

import com.example.icecube.services.goals.GoalsService;

public class ServiceLocator {
    static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                if (instance == null) {
                    instance = new ServiceLocator();
                }
            }
        }

        return instance;
    }

    final AuthService authService;
    final GoalsService goalsService;

    private ServiceLocator() {
        authService = new AuthService();
        goalsService = new GoalsService(authService);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public GoalsService getGoalsService() {
        return goalsService;
    }
}
