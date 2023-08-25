package per.muyi.bosai.queue;

import per.muyi.bosai.model.SessionModel;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chen_muyi
 * @date 2023/8/25 14:44
 */
public class SessionDelay implements Delayed {

    private SessionModel sessionModel;
    private Long delayTime;

    public SessionDelay(SessionModel sessionModel, Long delayTime) {
        this.sessionModel = sessionModel;
        this.delayTime = delayTime+System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = delayTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.delayTime.compareTo( ((SessionDelay) o).delayTime);
    }
}
