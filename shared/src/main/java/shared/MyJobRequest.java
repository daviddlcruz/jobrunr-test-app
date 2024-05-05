package shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class MyJobRequest implements JobRequest {
    private final String name;

    public MyJobRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Override
    public Class<MyJobRequestHandler> getJobRequestHandler() {
        return MyJobRequestHandler.class;
    }

    @Override
    public String toString() {
        return "Trabajo " + name + " para mostrar";
    }
}
