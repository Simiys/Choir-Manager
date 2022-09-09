package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.ChoirDirector;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationDirectorEvent extends ApplicationEvent {
    private String appUrl;
    private ChoirDirector director;
    private Locale locale;


    public OnRegistrationDirectorEvent(ChoirDirector director, Locale locale, String appUrl) {
        super(director);

        this.director =director;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
