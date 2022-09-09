package com.simiys.choirmanager.events;

import com.simiys.choirmanager.model.Singer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OnSingerPasswordRecoveryEvent extends ApplicationEvent {
    private Singer singer;

    public OnSingerPasswordRecoveryEvent(Singer singer) {
        super(singer);
        this.singer = singer;

    }


}
