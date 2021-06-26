package com.ad.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.*;

public class LockManagerTest {
    @Mock
    Map<Long, ReentrantLock> lockMap;
    @InjectMocks
    LockManager lockManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLockByTradeId() {
        ReentrantLock result1 = lockManager.getLockByTradeId(Long.valueOf(1));
        Assert.assertNotNull(result1);

        ReentrantLock result2 = lockManager.getLockByTradeId(Long.valueOf(1));
        Assert.assertSame(result1, result2);

    }
}
