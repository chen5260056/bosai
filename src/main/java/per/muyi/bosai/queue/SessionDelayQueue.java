package per.muyi.bosai.queue;

import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * @author chen_muyi
 * @date 2023/8/25 15:17
 */
public class SessionDelayQueue<SessionDelay> extends AbstractQueue {

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }
}
