package io.junrock.GAZA.domain.message.controller;

import io.junrock.GAZA.domain.message.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.MapKeyColumn;
/*
@CrossOrigin
@RequiredArgsConstructor
@Component
public class TestTime {
    private final MessageController messageController;
    @Scheduled(fixedDelay = 10000)
    public void runGetListMethod(){
        messageController.messageCount();
    }
}*/
