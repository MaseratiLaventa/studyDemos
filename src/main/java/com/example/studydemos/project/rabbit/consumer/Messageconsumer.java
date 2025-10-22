//package com.example.studydemos.project.rabbit.consumer;
//
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Messageconsumer {
//
//    @RabbitListener(queues = "ks_sku")
//    public void receiveSkuMessage(String message) {
//        System.out.println("received ks_sku message: " + message);
//    }
//
//    @RabbitListener(queues = "ks_in")
//    public void receiveInMessage(String message) {
//        System.out.println("received ks_in message: " + message);
//    }
//
//    @RabbitListener(queues = "ks_out")
//    public void receiveOutMessage(String message) {
//        System.out.println("received ks_out message: " + message);
//    }
//
//    @RabbitListener(queues = "ks_api")
//    public void receiveApiMessage(String message) {
//        System.out.println("received ks_api message: " + message);
//    }
//}
