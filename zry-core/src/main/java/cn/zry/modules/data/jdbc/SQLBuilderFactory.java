package cn.zry.modules.data.jdbc;

import java.util.Map;

/**
 * SQLBuilder静态工厂类
 * 功效为简化SQLBuilder创建，简化客户端语句，透明化Dialect
 */
public class SQLBuilderFactory {

	public static SQLBuilder newMySQLBuilderInstance(Map<String, String> sortMaps) {
		SQLBuilder builder = new SQLBuilder(sortMaps);
		builder.setDialect(new Mysql5Dialect());
		return builder;
	}

	public static SQLBuilder newOracleBuilderInstance(Map<String, String> sortMaps) {
		throw new RuntimeException("not valid yet");
	}

	public static SQLBuilder newSQLServerBuilderInstance(Map<String, String> sortMaps) {
		throw new RuntimeException("not valid yet");
	}

}
