package utp.edu.pe.restaurant.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DataAccessRedis {
    private static JedisPool jedisPool;

    // Inicializa la conexión a Redis
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); // Número máximo de conexiones en el pool
        poolConfig.setMaxIdle(5);    // Número máximo de conexiones inactivas
        poolConfig.setMinIdle(2);    // Número mínimo de conexiones inactivas
        poolConfig.setTestOnBorrow(true); // Validar las conexiones al ser tomadas del pool

        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    // Método para obtener una instancia de Jedis
    public static Jedis getJedisConnection() {
        return jedisPool.getResource();
    }

    // Cerrar el pool de conexiones
    public static void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public static void main(String[] args) {
        try (Jedis jedis = getJedisConnection()) {
            System.out.println("Conexión a Redis establecida: " + jedis.ping());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
