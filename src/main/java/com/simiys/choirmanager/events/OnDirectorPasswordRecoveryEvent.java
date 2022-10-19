package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.tables.ChoirDirector;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnDirectorPasswordRecoveryEvent extends ApplicationEvent {
    ChoirDirector director;

    public OnDirectorPasswordRecoveryEvent(ChoirDirector director) {
        super(director);

        this.director = director;
    }

}
