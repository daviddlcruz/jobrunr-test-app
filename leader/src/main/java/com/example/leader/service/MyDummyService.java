package com.example.leader.service;

import org.springframework.stereotype.Service;

@Service
public class MyDummyService implements MyDummyInterface{
    @Override
    public String logTipeAction() {


        return "llego... al servicio";
    }
}
