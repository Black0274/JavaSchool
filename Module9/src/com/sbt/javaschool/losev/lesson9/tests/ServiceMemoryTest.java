package com.sbt.javaschool.losev.lesson9.tests;

import com.sbt.javaschool.losev.lesson9.exceptions.IncorrectMethodDefinitionException;
import com.sbt.javaschool.losev.lesson9.java.CacheProxy;
import com.sbt.javaschool.losev.lesson9.java.Service;
import com.sbt.javaschool.losev.lesson9.java.ServiceMemory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServiceMemoryTest {

    @Test
    public void serviceSubstrings() throws NoSuchFieldException, IllegalAccessException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceMemory());
        Field cacheField = CacheProxy.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(cacheProxy);

        assertEquals(cache.size(), 0);
        List<String> substrings = service.substrings("ghci");
        assertEquals(substrings, Arrays.asList("g", "gh", "ghc", "ghci"));
        assertEquals(cache.size(), 1);
        service.substrings("test");
        assertEquals(cache.size(), 2);
        service.substrings("proxy");
        assertEquals(cache.size(), 3);
        service.substrings("test");
        assertEquals(cache.size(), 3);
        service.substrings("ghci");
        assertEquals(cache.size(), 3);
    }

    @Test
    public void serviceDivisors() throws NoSuchFieldException, IllegalAccessException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceMemory());
        Field cacheField = CacheProxy.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(cacheProxy);

        assertEquals(cache.size(), 0);
        List<Integer> divisors = service.divisors(100);
        assertEquals(divisors, Arrays.asList(1, 2, 4, 5, 10, 20, 25, 50, 100));
        assertEquals(cache.size(), 1);
        service.divisors(10);
        assertEquals(cache.size(), 2);
        service.divisors(13);
        assertEquals(cache.size(), 3);
        service.divisors(10);
        assertEquals(cache.size(), 3);
        service.divisors(100);
        assertEquals(cache.size(), 3);
    }

    @Test
    public void serviceLength() throws NoSuchFieldException, IllegalAccessException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceMemory());
        Field cacheField = CacheProxy.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(cacheProxy);

        assertEquals(cache.size(), 0);
        try {
            int length = service.length("ghci");
            Assert.fail("Expected IncorrectMethodDefinitionException");
        } catch (IncorrectMethodDefinitionException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
        assertEquals(cache.size(), 0);
    }

    @Test
    public void serviceLengthNoCache() throws NoSuchFieldException, IllegalAccessException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceMemory());
        Field cacheField = CacheProxy.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(cacheProxy);

        assertEquals(cache.size(), 0);
        int length = service.lengthNoCache("ghci");
        assertEquals(length, 4);
        assertEquals(cache.size(), 0);
        service.lengthNoCache("test");
        assertEquals(cache.size(), 0);
        service.lengthNoCache("proxy");
        assertEquals(cache.size(), 0);
    }

    @Test
    public void serviceDifferentTypes() throws NoSuchFieldException, IllegalAccessException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = cacheProxy.cache(new ServiceMemory());
        Field cacheField = CacheProxy.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(cacheProxy);

        assertEquals(cache.size(), 0);

        List<Integer> divisors = service.divisors(100);
        assertEquals(divisors, Arrays.asList(1, 2, 4, 5, 10, 20, 25, 50, 100));
        assertEquals(cache.size(), 1);

        List<String> substrings = service.substrings("ghci");
        assertEquals(substrings, Arrays.asList("g", "gh", "ghc", "ghci"));
        assertEquals(cache.size(), 2);

        service.divisors(10);
        assertEquals(cache.size(), 3);

        service.divisors(13);
        assertEquals(cache.size(), 4);

        service.substrings("test");
        assertEquals(cache.size(), 5);

        divisors = service.divisors(10);
        assertEquals(divisors, Arrays.asList(1, 2, 5, 10));
        assertEquals(cache.size(), 5);

        service.divisors(100);
        assertEquals(cache.size(), 5);

        substrings = service.substrings("ghci");
        assertEquals(substrings, Arrays.asList("g", "gh", "ghc", "ghci"));
        assertEquals(cache.size(), 5);
    }
}