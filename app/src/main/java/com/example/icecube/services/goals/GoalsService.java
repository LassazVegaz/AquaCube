package com.example.icecube.services.goals;

import com.example.icecube.services.AuthService;

public class GoalsService {
    final AuthService authService;

    public GoalsService(AuthService authService) {
        this.authService = authService;
    }
}
