package com.example.leader.rest;

import com.example.leader.service.MyDummyInterface;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class DummyRest {
    private final MyDummyInterface myDummyInterface;

    private final JobScheduler jobScheduler;

    public DummyRest(MyDummyInterface myDummyInterface, JobScheduler jobScheduler) {
        this.myDummyInterface = myDummyInterface;
        this.jobScheduler = jobScheduler;
    }

    @GetMapping( "{tipoAction}/something")
    public String dummy(@PathVariable("tipoAction") String tipoAction) {
        myDummyInterface.logTipeAction();
        return "llego...: " + tipoAction;
    }
}
