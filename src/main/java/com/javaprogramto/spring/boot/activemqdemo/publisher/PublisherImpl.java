package com.javaprogramto.spring.boot.activemqdemo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RequestMapping("/api/publish")
@RestController
public class PublisherImpl {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JmsTemplate jmsTemplate;


    @Autowired
    private Queue queue;


    @GetMapping("/{msg}")
    public String publishMessage(@PathVariable("msg") String content ){

        jmsTemplate.convertAndSend(queue, content);
        logger.info("Message published : "+content);
        return "Success";
    }

}
