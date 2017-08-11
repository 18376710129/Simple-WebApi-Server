package cn.zry.modules.data.jdbc;

public interface IDialect {

	String getLimitString(String sql, long offset, long maxResults);

}
