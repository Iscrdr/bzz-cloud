package com.star.cloud.framework.DBSource.config;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @PACKAGE_NAME: com.star.cloud.core.datasource.config
 * @CLASS_NAME: DbConfigA
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-25 15:32
 * @Modified by:
 * @Date:
 */
@Getter
@Setter

@Component
@PropertySource("classpath:database.properties")
@ConfigurationProperties(prefix = "jdbc.pool.mysql.")
public class DataSourceConfig {



    /*
     * 配置这个属性的意义在于，如果存在多个数据源，监控的时候
     * 可以通过名字来区分开来。如果没有配置，将会生成一个名字，
     * 格式是："DataSource-" + System.identityHashCode(this)
     */
    private String name;
    /*
     * 数据库 type:oracle/mysql
     */
    private String type;
    /*
     * database driver name
     */
    private String driver;
    /*
     * 连接数据库的url，不同数据库不一样。例如：
     * mysql : jdbc:mysql://10.20.153.104:3306/druid2
     * oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto
     */
    private String url;
    /*
     *连接数据库的用户名
     */
    private String username;
    /*
     *连接数据库的密码。如果你不希望密码直接写在配置文件中，
     *可以使用ConfigFilter
     */
    private String password;

    /* 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 */
    private int initialSize;
    /* 最小连接池数量 */
    private int minIdle;
    /* 最大连接池数量 */
    private int maxActive;
    /* max-lifetime 连接最大存活时间 **/
    private int maxLifetime;

    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，
     * 缺省启用公平锁，并发效率会有所下降，
     * 如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    private int maxWait;


    /* 配置一个连接在池中最小生存的时间，单位是毫秒 */
    private int minEvictableIdleTimeMillis;

    /**
     *
     * 用来检测连接是否有效的sql，要求是一个查询语句。
     * 如果validationQuery为null，testOnBorrow、testOnReturn、
     * testWhileIdle都不会其作用。
     */
    private String validationQuery;


    /**
     * 建议配置为true，不影响性能，并且保证安全性。
     * 申请连接的时候检测，如果空闲时间大于
     * timeBetweenEvictionRunsMillis，
     * 执行validationQuery检测连接是否有效
     */
    private boolean testWhileIdle;
    /* 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能*/
    private boolean testOnBorrow;
    /* 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 */
    private boolean testOnReturn;
    /**
     *
     * 是否缓存preparedStatement，也就是PSCache。
     * PSCache对支持游标的数据库性能提升巨大，比如说oracle。
     * 在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。
     * 5.5及以上版本有PSCache，建议开启。
     */
    private boolean poolPreparedStatements;

    /* 要启用PSCache，必须配置大于0，当大于0时，
     * poolPreparedStatements自动触发修改为true。
     * 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，
     * 可以把这个数值配置大一些，比如说100
     */
    private int maxOpenPreparedStatements;

    /**
     *
     *   有两个含义：
     *   1) Destroy线程会检测连接的间隔时间
     *   2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private int timeBetweenEvictionRunsMillis;


    /**
     *
     * 属性类型是字符串，通过别名的方式配置扩展插件，
     * 常用的插件有：
     * 监控统计用的filter:stat
     * 日志用的filter:log4j
     * 防御sql注入的filter:wall
     */
    private String filters;
    /* 通过connectProperties属性来打开mergeSql功能；慢SQL记录 */
    private String connectionProperties;





}
