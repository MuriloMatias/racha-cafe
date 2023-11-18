package com.assinaturamicroservice.assinaturaMicroservice.exceptions;

public class SubscriptionCreationException extends RuntimeException{

    public SubscriptionCreationException(String message){
        super(message);
    }

    public static class subscriptionAlreadyRegisterd extends SubscriptionCreationException{
        public subscriptionAlreadyRegisterd(){super("Esse usuário já está relacionado com um plano");}
    }
}
