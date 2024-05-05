package shared;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

public class MyJobRequestHandler implements JobRequestHandler<MyJobRequest> {

    @Override
    @Job(name = "%0", retries = 2)
    public void run(MyJobRequest myJobRequest) throws Exception {
        System.out.println("hello, " + myJobRequest.name() + " - " + jobContext().getJobState() + "!");
    }
}
