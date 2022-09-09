package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.Singer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationSingerEvent extends ApplicationEvent {
    private String appUrl;
    private Singer singer;
    private Locale locale;

    public OnRegistrationSingerEvent(Singer singer, Locale locale, String appUrl) {
        super(singer);

        this.singer = singer;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
