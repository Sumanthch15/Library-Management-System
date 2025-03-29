package com.example.library.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system")
public class SystemController {


    private final ApplicationContext context;

    public SystemController(ApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/exit")
    public String exitSystem() {
        if (context instanceof ConfigurableApplicationContext) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // short delay to allow response to return
                    ((ConfigurableApplicationContext) context).close(); // triggers shutdown
                } catch (InterruptedException ignored) {}
            }).start();
            return "Shutting down the system...";
        }
        return "Unable to shut down.";
    }
}
