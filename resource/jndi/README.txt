定义Pentaho报表时，如果使用JDBC连接方式，报表定义文件（.prpt）中包含了数据库服务器IP、端口和用户名、密码，在生产环境中不合适。生产环境中需要使用JNDI连接方式。
要在报表中使用JNDI连接方式，需要配置（用户目录）~/.pentaho/simple-jndi/default.properties文件（见示例）。
确保报表设计器程序目录的lib/jdbc下包含所需数据库的JDBC驱动。
配置完后需重启报表设计器，增加JDBC数据源，连接名类型不需要选，JNDI名称输入配置的名称（例如java:jboss/datasources/scctbi），连接方式选择JNDI。