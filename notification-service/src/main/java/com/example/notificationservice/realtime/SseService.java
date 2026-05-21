package com.example.notificationservice.realtime;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final List<SseEmitter> emitters =
            new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {

        SseEmitter emitter =
                new SseEmitter(Long.MAX_VALUE);

        emitters.add(emitter);

        emitter.onCompletion(() -> {

            System.out.println("SSE COMPLETED");

            emitters.remove(emitter);
        });

        emitter.onTimeout(() -> {

            System.out.println("SSE TIMEOUT");

            emitters.remove(emitter);
        });

        emitter.onError(error -> {

            System.out.println("SSE ERROR");
            error.printStackTrace();

            emitters.remove(emitter);
        });

        try {

            // IMPORTANT:
            // INITIAL EVENT

            emitter.send(
                    SseEmitter.event()
                            .name("connected")
                            .data("connected")
            );

            System.out.println("INITIAL SSE EVENT SENT");

        } catch (Exception e) {

            System.out.println("INITIAL SSE FAILED");

            e.printStackTrace();

            emitters.remove(emitter);
        }

        return emitter;
    }

    public void sendEvent(
            String eventName,
            Object data
    ) {

        List<SseEmitter> deadEmitters =
                new CopyOnWriteArrayList<>();

        emitters.forEach(emitter -> {

            try {

                emitter.send(
                        SseEmitter.event()
                                .name(eventName)
                                .data(data)
                );

                System.out.println(
                        "EVENT SENT: " + eventName
                );

            } catch (IOException e) {

                e.printStackTrace();

                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }
}