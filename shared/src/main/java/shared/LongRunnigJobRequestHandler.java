package shared;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

public class LongRunnigJobRequestHandler implements JobRequestHandler<LongRunnigJobRequest>{
    @Override
    @Job(name = "%0", retries = 2)
    public void run(LongRunnigJobRequest longRunnigJobRequest) throws Exception {
        longRunnigJobRequest.doLongRunningJobWithJobContext(this.jobContext());
        System.out.println("long running, " + longRunnigJobRequest.name() + " - " + jobContext().getJobState() + "!");
    }
}
