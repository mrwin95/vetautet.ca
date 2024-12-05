package org.vetau.infrastructure.distributed.redisson;

import java.util.concurrent.TimeUnit;

public interface RedisDistributedLock {
    boolean tryLock(long waitTime, long timeout, TimeUnit unit) throws InterruptedException;
    void unlock();

    void lock(long timeout, TimeUnit unit) throws InterruptedException;
    boolean isLocked();
    boolean isHeldByThread(long threadId);
    boolean isHeldByCurrentThread();
}
