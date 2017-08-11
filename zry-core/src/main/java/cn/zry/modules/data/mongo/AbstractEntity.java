package cn.zry.modules.data.mongo;

import org.mongodb.morphia.annotations.Id;

/**
 * The base entity for mongo .
 * Created by lyf on 2017/8/9.
 */
public abstract class AbstractEntity {

    @Id
    private String id;

    public AbstractEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
