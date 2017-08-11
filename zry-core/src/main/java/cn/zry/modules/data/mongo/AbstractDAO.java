package cn.zry.modules.data.mongo;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * @since 2014-8-5
 * @author liukehua
 * @param <T>   Entity Type
 * @param <K>   Id Type
 */
public abstract class AbstractDAO<T, K> extends BasicDAO<T, K> {
    public AbstractDAO(Datastore ds) {
        super(ds);
    }

    public AbstractDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }

    public void setValue(UpdateOperations<T> opts, String field, Object value) {
        if (value != null) {
            opts.set(field, value);
        } else {
            opts.unset(field);
        }
    }

    public void setList(UpdateOperations<T> opts, String field, List<?> value) {
        if (value != null && !value.isEmpty()) {
            opts.set(field, value);
        } else {
            opts.unset(field);
        }
    }

    public void insert(T entity) {
        this.save(entity);
    }

    public void setArray(UpdateOperations<T> opts, String field, Object[] value) {
        if (value != null && value.length > 0) {
            opts.set(field, value);
        } else {
            opts.unset(field);
        }
    }

}