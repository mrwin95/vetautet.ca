package org.vetau.infrastructure.distributed.redisson;

public interface RedisDistributedService {
    public RedisDistributedLock getDistributedLock(String lockName);
}
