package shared;

import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongRunnigJobRequest implements JobRequest {
    private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(LongRunnigJobRequest.class));

    private final String name;
    private final String anArgument;

    public LongRunnigJobRequest(String name, String anArgument) {
        this.name = name;
        this.anArgument = anArgument;
    }

    public String name() {
        return name;
    }

    @Override
    public Class<LongRunnigJobRequestHandler> getJobRequestHandler() {
        return LongRunnigJobRequestHandler.class;
    }

    @Override
    public String toString() {
        return "Long runnnig " + name + " job ";
    }

    public void doLongRunningJobWithJobContext(JobContext jobContext) {
        try {
            LOGGER.warn("Starting long running job...");
            final JobDashboardProgressBar progressBar = jobContext.progressBar(10);
            for (int i = 0; i < 10; i++) {
                LOGGER.info(String.format("Processing item %d: %s", i, anArgument));
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(15000);
                progressBar.increaseByOne();
            }
            LOGGER.warn("Finished long running job...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
