package com.z.quick.orm.connection;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.setting.Setting;
import com.z.quick.orm.sql.JdbcUtils;

public class JDBCConfig implements DefaultConfig {
	private static final Log log = LogFactory.get();
	/**数据库地址*/
	private String url;
	/**数据库用户名*/
	private String username;
	/**数据库密码*/
	private String password;
	/**数据库驱动*/
	private String driverClassName;
	/**数据库类型*/
	private String dbType;
	/**最大连接数*/
	private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;
	/**最小连接数*/
	private int minPoolSize = DEFAULT_MIN_POOL_SIZE;
	/**初始化连接数*/
	private int initialPoolSize = DEFAULT_INITIAL_POOL_SIZE;
	/**是否启用sql监控*/
	private boolean executeTimeMonitor = DEFAULT_EXECUTE_TIME_MONITOR;
	/**单位毫秒 当连接池连接耗尽时，客户端调用getConnection()后等待获取新连接的时间，超时后将抛出RuntimeException。单位毫秒。默认: 10000*/
	private int maxWait = DEFAULT_MAX_IDLE_TIME;
	/**最大空闲时间*/
	private int maxIdleTime = DEFAULT_MAX_IDLE_TIME;
	/**每X秒检查所有连接池中的空闲连接。默认值: 0，不检查*/
	private int idleConnectionTestPeriod = DEFAULT_IDLE_CONNECTION_TEST_PERIOD;
	
	
	public static JDBCConfig newInstance(String jdbcConfigPath){
		return new JDBCConfig(new Setting(jdbcConfigPath, true));
	}
	
	private JDBCConfig(Setting jdbcSetting) {
		super();
		if (jdbcSetting.getStr("jdbc.url") != null) {
			url = jdbcSetting.getStr("jdbc.url");
			dbType = JdbcUtils.getDbType(url);
		}
		if (jdbcSetting.getStr("jdbc.username") != null) {
			username = jdbcSetting.getStr("jdbc.username");
		}
		if (jdbcSetting.getStr("jdbc.password") != null) {
			password = jdbcSetting.getStr("jdbc.password");
		}
		if (jdbcSetting.getStr("jdbc.driverClassName") != null) {
			driverClassName = jdbcSetting.getStr("jdbc.driverClassName");
			try {
				Class.forName(driverClassName);
			} catch (ClassNotFoundException e) {
				log.error("加载数据库驱动出错",e);
				throw new RuntimeException("加载数据库驱动出错",e);
			}
		}
		if (jdbcSetting.get("jdbc.minPoolSize") != null) {
			minPoolSize = jdbcSetting.getInt("jdbc.minPoolSize");
		}
		if (jdbcSetting.get("jdbc.maxPoolSize") != null) {
			maxPoolSize = jdbcSetting.getInt("jdbc.maxPoolSize");
		}
		if (jdbcSetting.get("jdbc.initialPoolSize") != null) {
			initialPoolSize = jdbcSetting.getInt("jdbc.initialPoolSize");
		}
		if (jdbcSetting.get("jdbc.executeTimeMonitor") != null) {
			executeTimeMonitor = jdbcSetting.getBool("jdbc.executeTimeMonitor");
		}
		if (jdbcSetting.get("jdbc.maxWait") != null) {
			maxWait = jdbcSetting.getInt("jdbc.maxWait");
		}
		if (jdbcSetting.get("jdbc.maxIdleTime") != null) {
			maxIdleTime = jdbcSetting.getInt("jdbc.maxIdleTime");
		}
		if (jdbcSetting.get("jdbc.idleConnectionTestPeriod") != null) {
			idleConnectionTestPeriod = jdbcSetting.getInt("jdbc.idleConnectionTestPeriod");
		}

	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public boolean isExecuteTimeMonitor() {
		return executeTimeMonitor;
	}

	public String getDbType() {
		return dbType;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public int getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

}