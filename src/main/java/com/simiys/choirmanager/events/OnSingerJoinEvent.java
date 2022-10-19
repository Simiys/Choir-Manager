package com.simiys.choirmanager.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnSingerJoinEvent extends ApplicationEvent {
    String name;
    long id;
    String directorEmail;
    String singerEmail;

    public OnSingerJoinEvent(String name, long id, String directorEmail, String singerEmail) {
        super(id);
        this.id = id;
        this.name = name;
        this.directorEmail = directorEmail;
        this.singerEmail = singerEmail;
    }

}
