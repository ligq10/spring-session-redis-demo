package com.changhongit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import redis.clients.jedis.JedisPoolConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisConfig {


/*	@Autowired
	private Environment env;
	
	@Bean
	@Primary
	public RedisProperties redisProperties() {
		RedisProperties redisProperties = new RedisProperties();
		redisProperties.setDatabase(0);
		redisProperties.setHost("10.9.42.203");
		redisProperties.setPort(6379);
		redisProperties.setPassword("changhongit");
		redisProperties.setTimeout(60);
		Pool pool = new Pool();
		pool.setMaxActive(8);
		pool.setMaxIdle(8);
		pool.setMaxWait(-1);
		pool.setMinIdle(0);
		redisProperties.setPool(pool);
		return redisProperties;
	}
	
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		CacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		return redisCacheManager;
	}

	private JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		Pool props = redisProperties().getPool();
		config.setMaxTotal(props.getMaxActive());
		config.setMaxIdle(props.getMaxIdle());
		config.setMinIdle(props.getMinIdle());
		config.setMaxWaitMillis(props.getMaxWait());
		return config;
	}

	@Bean
	public RedisConnectionFactory secondaryRedisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
		redisConnectionFactory.setDatabase(0);
		redisConnectionFactory.setPassword(redisProperties().getPassword());
		redisConnectionFactory.setHostName(redisProperties().getHost());
		redisConnectionFactory.setTimeout(redisProperties().getTimeout());
		redisConnectionFactory.setPort(redisProperties().getPort());
		redisConnectionFactory.afterPropertiesSet();
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		om.setSerializationInclusion(Include.NON_EMPTY);// 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper
		GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// template.afterPropertiesSet();
		return template;
	}

	@Bean
	public RedisOperationsSessionRepository sessionRepository() {
		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(
				secondaryRedisConnectionFactory());
		sessionRepository.setDefaultMaxInactiveInterval(redisProperties().getTimeout());// 设置session的有效时长
		return sessionRepository;
	}


	@Bean
	public RedisTemplate<String, Object> sessionRedisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(secondaryRedisConnectionFactory());
		RedisSerializer stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(sessionRedisSerializer());
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(sessionRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}


	@Bean
	@SuppressWarnings("rawtypes")
	public RedisSerializer sessionRedisSerializer() {
		return new Jackson2JsonRedisSerializer<Object>(Object.class);
	}
*/
}
