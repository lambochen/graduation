package com.chenlinghong.graduation.common.executor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecutorConfigurationTest {

    @Test
    public void asyncServiceExecutor() {
        for (int i = 0; i < 100; i++) {
            asyncFunction(i);
            System.out.println("asyncServiceExecutor item is " + i);
        }
    }

    /**
     * 需启动项目进行执行.此方法为使用示例，测试尚未成功。
     *
     * @param data
     */
    @Async("asyncServiceExecutor")
    public void asyncFunction(int data) {
        log.info("asyncFunction is running. data={}", data);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("asyncFunction throws exception. e={}", e);
        }
        log.info("asyncFunction is ended. data={}", data);
    }

    /**
     * TODO 异步测试在项目已成功执行
     *
     * 详细日志如下：
     *
     * 2019-04-07 10:13:48,426 [main] [org.springframework.core.env.PropertySourcesPropertyResolver] [DEBUG] - Found key 'spring.liveBeansView.mbeanDomain' in PropertySource 'systemProperties' with value of type String
     *   2019-04-07 10:13:48,456 [main] [org.springframework.boot.web.embedded.tomcat.TomcatWebServer] [INFO] - Tomcat started on port(s): 8080 (http) with context path '/graduation'
     *   2019-04-07 10:13:48,466 [main] [com.chenlinghong.graduation.GraduationApplication] [INFO] - Started GraduationApplication in 7.32 seconds (JVM running for 10.34)
     *   2019-04-07 10:13:49,336 [RMI TCP Connection(2)-192.168.1.104] [org.springframework.core.env.PropertySourcesPropertyResolver] [DEBUG] - Found key 'local.server.port' in PropertySource 'server.ports' with value of type Integer
     *   2019-04-07 10:13:49,336 [RMI TCP Connection(2)-192.168.1.104] [org.springframework.core.env.PropertySourcesPropertyResolver] [DEBUG] - Found key 'server.servlet.context-path' in PropertySource 'configurationProperties' with value of type String
     *   四月 07, 2019 10:14:47 上午 org.apache.catalina.core.ApplicationContext log
     * 信息: Initializing Spring DispatcherServlet 'dispatcherServlet'
     * 2019-04-07 10:14:47,185 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [INFO] - Initializing Servlet 'dispatcherServlet'
     *   2019-04-07 10:14:47,185 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [DEBUG] - Detected CommonsMultipartResolver
     *   2019-04-07 10:14:47,195 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [DEBUG] - enableLoggingRequestDetails='false': request parameters and headers will be masked to prevent unsafe logging of potentially sensitive data
     *   2019-04-07 10:14:47,195 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [INFO] - Completed initialization in 10 ms
     *   2019-04-07 10:14:47,231 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [DEBUG] - POST "/graduation/user/login/pwd", parameters={}
     *   2019-04-07 10:14:47,337 [http-nio-8080-exec-1] [org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping] [DEBUG] - Mapped to public com.chenlinghong.graduation.common.ResultVo com.chenlinghong.graduation.api.controller.UserController.loginByPwd(java.lang.String,java.lang.String,javax.servlet.http.HttpServletRequest)
     *   2019-04-07 10:14:47,347 [http-nio-8080-exec-1] [com.chenlinghong.graduation.api.controller.UserController] [INFO] - UserController#loginByPwd: parameter info. telephone=13008142306, password=123456
     *   2019-04-07 10:14:47,357 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Creating a new SqlSession
     *   2019-04-07 10:14:47,377 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3cfff36f] was not registered for synchronization because synchronization is not active
     *   2019-04-07 10:14:47,397 [http-nio-8080-exec-1] [org.springframework.jdbc.datasource.DataSourceUtils] [DEBUG] - Fetching JDBC Connection from DataSource
     *   2019-04-07 10:14:47,447 [http-nio-8080-exec-1] [com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource] [INFO] - Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, contextClassLoaderSource -> caller, dataSourceName -> 1hge160a11w4yjuaspky5s|1a28b346, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.cj.jdbc.Driver, extensions -> {}, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, forceSynchronousCheckins -> false, forceUseNamedDriverClass -> false, identityToken -> 1hge160a11w4yjuaspky5s|1a28b346, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://127.0.0.1:3306/graduation?useUnicode=true&charactorEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 15, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 3, numHelperThreads -> 3, preferredTestQuery -> null, privilegeSpawnedThreads -> false, properties -> {user=******, password=******}, propertyCycle -> 0, statementCacheNumDeferredCloseThreads -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, userOverrides -> {}, usesTraditionalReflectiveProxies -> false ]
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.cfg.MConfig] [DEBUG] - The configuration file for resource identifier '/mchange-commons.properties' could not be found. Skipping.
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.cfg.MConfig] [DEBUG] - The configuration file for resource identifier '/mchange-log.properties' could not be found. Skipping.
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.cfg.MConfig] [DEBUG] - The configuration file for resource identifier '/c3p0.properties' could not be found. Skipping.
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.cfg.MConfig] [DEBUG] - The configuration file for resource identifier 'hocon:/reference,/application,/c3p0,/' could not be found. Skipping.
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - com.mchange.v2.resourcepool.BasicResourcePool@2b54a733 config: [start -> 3; min -> 3; max -> 15; inc -> 3; num_acq_attempts -> 30; acq_attempt_delay -> 1000; check_idle_resources_delay -> 0; max_resource_age -> 0; max_idle_time -> 0; excess_max_idle_time -> 0; destroy_unreturned_resc_time -> 0; expiration_enforcement_delay -> 0; break_on_acquisition_failure -> false; debug_store_checkout_exceptions -> false; force_synchronous_checkins -> false]
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPoolManager] [DEBUG] - Created new pool for auth, username (masked): 'ro******'.
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - acquire test -- pool size: 0; target_pool_size: 3; desired target? 1
     *   2019-04-07 10:14:47,467 [http-nio-8080-exec-1] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - awaitAvailable(): [unknown]
     *   2019-04-07 10:14:47,647 [http-nio-8080-exec-1] [org.mybatis.spring.transaction.SpringManagedTransaction] [DEBUG] - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@4f9cf543 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@2eb81caf]] will not be managed by Spring
     *   2019-04-07 10:14:47,647 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephone] [DEBUG] - ==>  Preparing: SELECT count(distinct id) FROM user WHERE telephone = ?
     *   2019-04-07 10:14:47,667 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephone] [DEBUG] - ==> Parameters: 13008142306(String)
     *   2019-04-07 10:14:47,709 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephone] [DEBUG] - <==      Total: 1
     *   2019-04-07 10:14:47,711 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3cfff36f]
     *   2019-04-07 10:14:47,728 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Creating a new SqlSession
     *   2019-04-07 10:14:47,728 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@77ede023] was not registered for synchronization because synchronization is not active
     *   2019-04-07 10:14:47,728 [http-nio-8080-exec-1] [org.springframework.jdbc.datasource.DataSourceUtils] [DEBUG] - Fetching JDBC Connection from DataSource
     *   2019-04-07 10:14:47,728 [http-nio-8080-exec-1] [org.mybatis.spring.transaction.SpringManagedTransaction] [DEBUG] - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@69a71579 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@2eb81caf]] will not be managed by Spring
     *   2019-04-07 10:14:47,729 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephoneAndPassword] [DEBUG] - ==>  Preparing: SELECT count(distinct id) FROM user WHERE telephone = ? and password = ?
     *   2019-04-07 10:14:47,729 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephoneAndPassword] [DEBUG] - ==> Parameters: 13008142306(String), 653275de9734c4b58d1613545ea6aa01(String)
     *   2019-04-07 10:14:47,730 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.countByTelephoneAndPassword] [DEBUG] - <==      Total: 1
     *   2019-04-07 10:14:47,730 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@77ede023]
     *   2019-04-07 10:14:47,730 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Creating a new SqlSession
     *   2019-04-07 10:14:47,730 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@265c5cec] was not registered for synchronization because synchronization is not active
     *   2019-04-07 10:14:47,730 [http-nio-8080-exec-1] [org.springframework.jdbc.datasource.DataSourceUtils] [DEBUG] - Fetching JDBC Connection from DataSource
     *   2019-04-07 10:14:47,731 [http-nio-8080-exec-1] [org.mybatis.spring.transaction.SpringManagedTransaction] [DEBUG] - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@6085414 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@2eb81caf]] will not be managed by Spring
     *   2019-04-07 10:14:47,731 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.getByTelephone] [DEBUG] - ==>  Preparing: SELECT `id`, gmt_create, gmt_modified, deleted, `nick_name`, `real_name`, `telephone`, `password`, `gender` , `birthday`, `country`, `province`, `city`, `position`, `latitude`, `longitude`, `avatar_url` , `type`, `description` FROM user WHERE telephone=?
     *   2019-04-07 10:14:47,731 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.getByTelephone] [DEBUG] - ==> Parameters: 13008142306(String)
     *   2019-04-07 10:14:47,732 [http-nio-8080-exec-1] [com.chenlinghong.graduation.repository.dao.UserDao.getByTelephone] [DEBUG] - <==      Total: 1
     *   2019-04-07 10:14:47,732 [http-nio-8080-exec-1] [org.mybatis.spring.SqlSessionUtils] [DEBUG] - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@265c5cec]
     *   2019-04-07 10:14:47,732 [http-nio-8080-exec-1] [com.chenlinghong.graduation.common.executor.VisibleThreadPoolTaskExecutor] [INFO] - async-service-, VisibleThreadPoolTaskExecutor#submit(callable),taskCount [0], completedTaskCount [0], activeCount [0], queueSize [0]
     *   2019-04-07 10:14:47,742 [async-service-1] [com.chenlinghong.graduation.util.MyRedisUtil] [INFO] - MyRedisUtil#put(UserVo): userVo=UserVo(userInfo=User(nickName=chenlinghong, realName=chenlinghong, telephone=13008142306, password=653275de9734c4b58d1613545ea6aa01, gender=1, birthday=Wed Apr 03 00:00:00 CST 2019, country=China, province=Sichuan, city=Chengdu, position=xhu university, latitude=30.00, longitude=45.00, avatarUrl=test, type=0, description=test))
     *   2019-04-07 10:14:47,816 [http-nio-8080-exec-1] [org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor] [DEBUG] - Using 'application/json', given [*
     *      and supported[
     *      application/json,application/*+json]
     *   2019-04-07 10:14:47,826 [http-nio-8080-exec-1] [org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor] [DEBUG] - Writing [ResultVo(code=0, msg=请求成功, data=UserVo(userInfo=User(nickName=chenlinghong, realName=chenlinghong, t (truncated)...]
     *   2019-04-07 10:14:47,846 [http-nio-8080-exec-1] [org.springframework.web.servlet.DispatcherServlet] [DEBUG] - Completed 200 OK
     *   2019-04-07 10:14:52,909 [async-service-1] [org.springframework.data.redis.core.RedisConnectionUtils] [DEBUG] - Opening RedisConnection
     *   2019-04-07 10:14:52,919 [async-service-1] [io.lettuce.core.RedisClient] [DEBUG] - Trying to get a Redis connection for: RedisURI [host='39.108.250.164', port=6379]
     *   2019-04-07 10:14:53,049 [async-service-1] [io.lettuce.core.EpollProvider] [INFO] - Starting without optional epoll library
     *   2019-04-07 10:14:53,049 [async-service-1] [io.lettuce.core.KqueueProvider] [INFO] - Starting without optional kqueue library
     *   2019-04-07 10:14:53,059 [async-service-1] [io.lettuce.core.resource.DefaultEventLoopGroupProvider] [DEBUG] - Allocating executor io.netty.channel.nio.NioEventLoopGroup
     *   2019-04-07 10:14:53,059 [async-service-1] [io.lettuce.core.resource.DefaultEventLoopGroupProvider] [DEBUG] - Creating executor io.netty.channel.nio.NioEventLoopGroup
     *   2019-04-07 10:14:53,059 [async-service-1] [io.netty.channel.MultithreadEventLoopGroup] [DEBUG] - -Dio.netty.eventLoopThreads: 8
     *   2019-04-07 10:14:53,059 [async-service-1] [io.netty.channel.nio.NioEventLoop] [DEBUG] - -Dio.netty.noKeySetOptimization: false
     *   2019-04-07 10:14:53,059 [async-service-1] [io.netty.channel.nio.NioEventLoop] [DEBUG] - -Dio.netty.selectorAutoRebuildThreshold: 512
     *   2019-04-07 10:14:53,069 [async-service-1] [io.lettuce.core.resource.DefaultEventLoopGroupProvider] [DEBUG] - Adding reference to io.netty.channel.nio.NioEventLoopGroup@2ac5a768, existing ref count 0
     *   2019-04-07 10:14:53,099 [async-service-1] [io.lettuce.core.RedisClient] [DEBUG] - Resolved SocketAddress 39.108.250.164:6379 using RedisURI [host='39.108.250.164', port=6379]
     *   2019-04-07 10:14:53,109 [async-service-1] [io.lettuce.core.RedisClient] [DEBUG] - Connecting to Redis at 39.108.250.164:6379
     *   2019-04-07 10:14:53,129 [async-service-1] [io.netty.channel.DefaultChannelId] [DEBUG] - -Dio.netty.processId: 11784 (auto-detected)
     *   2019-04-07 10:14:53,139 [async-service-1] [io.netty.util.NetUtil] [DEBUG] - -Djava.net.preferIPv4Stack: false
     *   2019-04-07 10:14:53,139 [async-service-1] [io.netty.util.NetUtil] [DEBUG] - -Djava.net.preferIPv6Addresses: false
     *   2019-04-07 10:14:53,602 [async-service-1] [io.netty.util.NetUtil] [DEBUG] - Loopback interface: [lo, Software Loopback Interface 1, 127.0.0.1] ({}, {})
     *   2019-04-07 10:14:53,602 [async-service-1] [io.netty.util.NetUtil] [DEBUG] - Failed to get SOMAXCONN from sysctl and file \proc\sys\net\core\somaxconn. Default: 200
     *   2019-04-07 10:14:54,081 [async-service-1] [io.netty.channel.DefaultChannelId] [DEBUG] - -Dio.netty.machineId: 08:62:66:ff:fe:b6:a1:c4 (auto-detected)
     *   2019-04-07 10:14:54,115 [async-service-1] [io.netty.buffer.ByteBufUtil] [DEBUG] - -Dio.netty.allocator.type: pooled
     *   2019-04-07 10:14:54,115 [async-service-1] [io.netty.buffer.ByteBufUtil] [DEBUG] - -Dio.netty.threadLocalDirectBufferSize: 0
     *   2019-04-07 10:14:54,115 [async-service-1] [io.netty.buffer.ByteBufUtil] [DEBUG] - -Dio.netty.maxThreadLocalCharBufferSize: 16384
     *   2019-04-07 10:14:54,192 [lettuce-nioEventLoop-4-1] [io.netty.util.Recycler] [DEBUG] - -Dio.netty.recycler.maxCapacityPerThread: 4096
     *   2019-04-07 10:14:54,192 [lettuce-nioEventLoop-4-1] [io.netty.util.Recycler] [DEBUG] - -Dio.netty.recycler.maxSharedCapacityFactor: 2
     *   2019-04-07 10:14:54,192 [lettuce-nioEventLoop-4-1] [io.netty.util.Recycler] [DEBUG] - -Dio.netty.recycler.linkCapacity: 16
     *   2019-04-07 10:14:54,192 [lettuce-nioEventLoop-4-1] [io.netty.util.Recycler] [DEBUG] - -Dio.netty.recycler.ratio: 8
     *   2019-04-07 10:14:54,202 [lettuce-nioEventLoop-4-1] [io.netty.buffer.AbstractByteBuf] [DEBUG] - -Dio.netty.buffer.checkAccessible: true
     *   2019-04-07 10:14:54,202 [lettuce-nioEventLoop-4-1] [io.netty.buffer.AbstractByteBuf] [DEBUG] - -Dio.netty.buffer.checkBounds: true
     *   2019-04-07 10:14:54,202 [lettuce-nioEventLoop-4-1] [io.netty.util.ResourceLeakDetectorFactory] [DEBUG] - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@5f791aef
     *   2019-04-07 10:14:54,232 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, [id: 0xa1b7a46d] (inactive), chid=0x1] channelRegistered()
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] channelActive()
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] activateEndpointAndExecuteBufferedCommands 0 command(s) buffered
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] activating endpoint
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] flushCommands()
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] flushCommands() Flushing 0 commands
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] channelActive()
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] channelActive()
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] channelActive() done
     *   2019-04-07 10:14:54,272 [lettuce-nioEventLoop-4-1] [io.lettuce.core.RedisClient] [DEBUG] - Connecting to Redis at 39.108.250.164:6379: Success
     *   2019-04-07 10:14:54,282 [lettuce-nioEventLoop-4-1] [io.lettuce.core.RedisChannelHandler] [DEBUG] - dispatching command AsyncCommand [type=AUTH, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand]
     *   2019-04-07 10:14:54,282 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] write() writeAndFlush command AsyncCommand [type=AUTH, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand]
     *   2019-04-07 10:14:54,282 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] write(ctx, AsyncCommand [type=AUTH, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand], promise)
     *   2019-04-07 10:14:54,282 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandEncoder] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379] writing command AsyncCommand [type=AUTH, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand]
     *   2019-04-07 10:14:54,292 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] write() done
     *   2019-04-07 10:14:54,292 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] userEventTriggered(ctx, io.lettuce.core.ConnectionEvents$Activated@2da70f6f)
     *   2019-04-07 10:14:54,292 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] userEventTriggered(ctx, io.lettuce.core.ConnectionEvents$Activated@2da70f6f)
     *   2019-04-07 10:14:54,332 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [[channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1], 5, 1] Received: {} bytes, {} commands in the stack
     *   2019-04-07 10:14:54,332 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] Stack contains: 1 commands
     *   2019-04-07 10:14:54,332 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.RedisStateMachine] [DEBUG] - Decode AsyncCommand [type=AUTH, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand]
     *   2019-04-07 10:14:54,332 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.RedisStateMachine] [DEBUG] - Decoded AsyncCommand [type=AUTH, output=StatusOutput [output=OK, error='null'], commandType=io.lettuce.core.protocol.AsyncCommand], empty stack: true
     *   2019-04-07 10:14:54,352 [async-service-1] [io.lettuce.core.RedisChannelHandler] [DEBUG] - dispatching command AsyncCommand [type=HMSET, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.Command]
     *   2019-04-07 10:14:54,352 [async-service-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] write() writeAndFlush command AsyncCommand [type=HMSET, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.Command]
     *   2019-04-07 10:14:54,352 [async-service-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] write() done
     *   2019-04-07 10:14:54,352 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] write(ctx, AsyncCommand [type=HMSET, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.Command], promise)
     *   2019-04-07 10:14:54,362 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandEncoder] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379] writing command AsyncCommand [type=HMSET, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.Command]
     *   2019-04-07 10:14:54,402 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [[channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1], 5, 1] Received: {} bytes, {} commands in the stack
     *   2019-04-07 10:14:54,402 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] Stack contains: 1 commands
     *   2019-04-07 10:14:54,402 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.RedisStateMachine] [DEBUG] - Decode AsyncCommand [type=HMSET, output=StatusOutput [output=null, error='null'], commandType=io.lettuce.core.protocol.Command]
     *   2019-04-07 10:14:54,402 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.RedisStateMachine] [DEBUG] - Decoded AsyncCommand [type=HMSET, output=StatusOutput [output=OK, error='null'], commandType=io.lettuce.core.protocol.Command], empty stack: true
     *   2019-04-07 10:14:54,402 [async-service-1] [org.springframework.data.redis.core.RedisConnectionUtils] [DEBUG] - Closing Redis Connection
     *   2019-04-07 10:14:54,402 [async-service-1] [com.chenlinghong.graduation.util.MyRedisUtil] [INFO] - MyRedisUtil#put(UserVo): test async method is ended.
     *   Disconnected from the target VM, address: '127.0.0.1:4662', transport: 'socket'
     * 2019-04-07 10:15:19,834 [Thread-13] [org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext] [DEBUG] - Closing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@266374ef, started on Sun Apr 07 10:13:42 CST 2019
     *   2019-04-07 10:15:19,834 [Thread-13] [org.springframework.core.env.PropertySourcesPropertyResolver] [DEBUG] - Found key 'spring.liveBeansView.mbeanDomain' in PropertySource 'systemProperties' with value of type String
     *   2019-04-07 10:15:19,834 [Thread-13] [org.springframework.data.redis.listener.RedisMessageListenerContainer] [DEBUG] - Stopped RedisMessageListenerContainer
     *   2019-04-07 10:15:19,834 [Thread-13] [org.springframework.jmx.export.annotation.AnnotationMBeanExporter] [DEBUG] - Unregistering JMX-exposed beans on shutdown
     *   2019-04-07 10:15:19,834 [Thread-13] [com.chenlinghong.graduation.common.executor.VisibleThreadPoolTaskExecutor] [INFO] - Shutting down ExecutorService 'asyncServiceExecutor'
     *   2019-04-07 10:15:19,834 [Thread-13] [io.lettuce.core.RedisChannelHandler] [DEBUG] - close()
     *   2019-04-07 10:15:19,844 [Thread-13] [io.lettuce.core.RedisChannelHandler] [DEBUG] - closeAsync()
     *   2019-04-07 10:15:19,844 [Thread-13] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] closeAsync()
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] channelInactive()
     *   2019-04-07 10:15:19,844 [Thread-13] [io.lettuce.core.RedisClient] [DEBUG] - Initiate shutdown ([100, 100, MILLISECONDS], {}, {})
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.DefaultEndpoint] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, epid=0x1] deactivating endpoint handler
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] channelInactive() done
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] channelInactive()
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] Reconnect scheduling disabled
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] channelInactive()
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.ConnectionWatchdog] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, last known addr=/39.108.250.164:6379] Reconnect scheduling disabled
     *   2019-04-07 10:15:19,844 [lettuce-nioEventLoop-4-1] [io.lettuce.core.protocol.CommandHandler] [DEBUG] - [channel=0xf9c41792, /192.168.1.104:4705 -> /39.108.250.164:6379, chid=0x1] channelUnregistered()
     *   2019-04-07 10:15:19,844 [Thread-13] [io.lettuce.core.resource.DefaultEventLoopGroupProvider] [DEBUG] - Release executor io.netty.channel.nio.NioEventLoopGroup@2ac5a768
     *   2019-04-07 10:15:20,963 [lettuce-nioEventLoop-4-1] [io.netty.buffer.PoolThreadCache] [DEBUG] - Freed 7 thread-local buffer(s) from thread: lettuce-nioEventLoop-4-1
     *   2019-04-07 10:15:20,963 [Thread-13] [io.lettuce.core.resource.DefaultClientResources] [DEBUG] - Initiate shutdown ([0, 2, SECONDS], {}, {})
     *   2019-04-07 10:15:20,963 [Thread-13] [io.lettuce.core.resource.DefaultEventLoopGroupProvider] [DEBUG] - Initiate shutdown ([0, 2, SECONDS], {}, {})
     *   2019-04-07 10:15:21,351 [Thread-13] [com.mchange.v2.c3p0.management.ActiveManagementCoordinator] [DEBUG] - MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=1hge160a11w4yjuaspky5s|1a28b346,name=1hge160a11w4yjuaspky5s|1a28b346 unregistered.
     *   2019-04-07 10:15:21,351 [Thread-13] [com.mchange.v2.c3p0.management.ActiveManagementCoordinator] [DEBUG] - C3P0Registry mbean unregistered.
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Preparing to destroy resource: com.mchange.v2.c3p0.impl.NewPooledConnection@28e3b2c
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Preparing to destroy PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@28e3b2c
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Successfully destroyed PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@28e3b2c
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Successfully destroyed resource: com.mchange.v2.c3p0.impl.NewPooledConnection@28e3b2c
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Preparing to destroy resource: com.mchange.v2.c3p0.impl.NewPooledConnection@6d478394
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Preparing to destroy PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@6d478394
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Successfully destroyed PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@6d478394
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Successfully destroyed resource: com.mchange.v2.c3p0.impl.NewPooledConnection@6d478394
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Preparing to destroy resource: com.mchange.v2.c3p0.impl.NewPooledConnection@5712196f
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Preparing to destroy PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@5712196f
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool] [DEBUG] - Successfully destroyed PooledConnection: com.mchange.v2.c3p0.impl.NewPooledConnection@5712196f
     *   2019-04-07 10:15:21,351 [Resource Destroyer in BasicResourcePool.close()] [com.mchange.v2.resourcepool.BasicResourcePool] [DEBUG] - Successfully destroyed resource: com.mchange.v2.c3p0.impl.NewPooledConnection@5712196f
     */



}