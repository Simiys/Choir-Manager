package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.tables.Singer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationSingerEvent extends ApplicationEvent {
    private Singer singer;

    public OnRegistrationSingerEvent(Singer singer) {
        super(singer);
        this.singer = singer;


    }
}
