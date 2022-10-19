package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationDirectorEvent extends ApplicationEvent {
    private ChoirDirector director;
    public OnRegistrationDirectorEvent(ChoirDirector director) {
        super(director);
        this.director = director;
    }
}
