package org.vetau.infrastructure.distributed.redisson.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.vetau.infrastructure.distributed.redisson.RedisDistributedLock;
import org.vetau.infrastructure.distributed.redisson.RedisDistributedService;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisDistributedLockImpl implements RedisDistributedService {
    private final RedissonClient redissonClient;

    public RedisDistributedLockImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    @Override
    public RedisDistributedLock getDistributedLock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);
        return new RedisDistributedLock() {

            @Override
            public boolean tryLock(long waitTime, long timeout, TimeUnit unit) throws InterruptedException {
                boolean isLockSuccess = lock.tryLock(waitTime, timeout, unit);
                log.info("{} get lock result: {}", lockName, isLockSuccess);
                return isLockSuccess;
            }

            @Override
            public void unlock() {
                if(isLocked() && isHeldByCurrentThread()){
                    lock.unlock();
                }
            }

            @Override
            public void lock(long timeout, TimeUnit unit) throws InterruptedException {

            }

            @Override
            public boolean isLocked() {
                return lock.isLocked();
            }

            @Override
            public boolean isHeldByThread(long threadId) {
                return lock.isHeldByThread(threadId);
            }

            @Override
            public boolean isHeldByCurrentThread() {
                return lock.isHeldByCurrentThread();
            }
        };
    }
}
