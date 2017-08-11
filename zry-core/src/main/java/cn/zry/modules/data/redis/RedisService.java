package cn.zry.modules.data.redis;

import cn.zry.modules.mapper.JsonMapper;
import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis 读写操作
 * <p>
 * Created by QL on 2017/1/6.
 */
public class RedisService {

    private JsonMapper jsonMapper = new JsonMapper();
    private final int survivalTime = 60;
    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    /**
     * 连接池
     */
    private ShardedJedisPool shardedJedisPool;

    /**
     * 使用此工具类时，必须注入一个连接池
     */
    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    /**
     * 根据指定Key 获取redis中存储的json并转换成相应对象
     * key对应值不存在时，返回null
     *
     * @param key
     * @param clazz
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        T obj = null;
        try {
            ShardedJedis shardedJedis = shardedJedisPool.getResource();
            String cacheStr = shardedJedis.get(key);
            if (cacheStr != null) {
                obj = jsonMapper.fromJson(cacheStr, clazz);
            }
        } catch (Exception e) {
            logger.error("Load cache by key {} fail, {}", key, e.getMessage(), e);
        }
        return obj;
    }

    /**
     * 根据指定Key 获取redis中存储的json并转换成响应对象数组
     * key对应值不存在时，返回null
     *
     * @param key
     * @param elementClasses
     * @return
     */
    public <T> List<T> getList(String key, Class<T> elementClasses) {
        List<T> objList = null;
        try {
            ShardedJedis shardedJedis = shardedJedisPool.getResource();
            String cacheStr = shardedJedis.get(key);
            if (cacheStr != null) {
                JavaType javaType = jsonMapper.createCollectionType(ArrayList.class, elementClasses);
                objList = jsonMapper.fromJson(cacheStr, javaType);
            }
        } catch (Exception e) {
            logger.error("Load cache by key {} fail, {}", key, e.getMessage(), e);
        }
        return objList;
    }

    /**
     * 保存并更新 key - obj 到redis , 存活minute分钟
     *
     * @param key
     * @param obj
     * @return
     */
    public void save(String key, Object obj, Integer minute) {
        if (minute == null) {
            minute = 1;
        }
        String cacheStr = null;
        try {
            ShardedJedis shardedJedis = shardedJedisPool.getResource();
            cacheStr = jsonMapper.toJson(obj);
            shardedJedis.set(key, cacheStr);
            shardedJedis.expire(key, minute * survivalTime);
        } catch (Exception e) {
            logger.error("Save fail, key:{},value:{},fail:{};{}", key, cacheStr, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteByKey(String key) {
        try {
            ShardedJedis shardedJedis = shardedJedisPool.getResource();
            shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("Delete fail, key:{},fail:{};{}", key, e.getMessage(), e);
            throw e;
        }
    }

}
