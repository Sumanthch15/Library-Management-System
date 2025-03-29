package com.example.library.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/system")
public class SystemController {


    // used to shut down the beans. so, application will shut down
    private final ApplicationContext context;


    //constructor
    public SystemController(ApplicationContext context) {
        this.context = context;
    }

    // to exit the application
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
