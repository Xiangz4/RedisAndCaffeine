package com.xyf.reids_and_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Caffeine_基本使用 {
    public static void main(String[] args) {
        Caffeine_基本使用 caffeine = new Caffeine_基本使用();
        caffeine.timeoutbySize();
    }

    /*
        基于过期时间的过期策略
     */
    public void basic(){
        Cache<String, Object> cache = Caffeine.newBuilder()
                .initialCapacity(100) //初始大小
                .maximumSize(200)   //缓存的最大条目
                .expireAfterWrite(3, TimeUnit.SECONDS)  //过期时间
                .build();
        cache.put("本地缓存","Caffeine");
        System.out.println(cache.getIfPresent("本地缓存"));
    }
    /*
        基于大小的过期策略
     */
    public void timeoutbySize(){
        Cache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .build();
        cache.put("test1","1");
        cache.put("test2","2");
        cache.put("test3","3");
        clean(cache);
        System.out.println(cache.getIfPresent("test1"));
        System.out.println(cache.getIfPresent("test2"));
        System.out.println(cache.getIfPresent("test3"));
    }
    /*
        执行过期缓存的清理操作
     */
    public static void clean(Cache<?,?>  cache){
        cache.cleanUp();
    }
}
