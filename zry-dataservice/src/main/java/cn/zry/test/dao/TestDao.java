package cn.zry.test.dao;

import cn.zry.modules.data.mongo.AbstractDAO;
import cn.zry.test.entity.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDao extends AbstractDAO<Test, String> {

    @Autowired
    private Datastore datastore;

    public TestDao(Datastore ds) {
        super(ds);
    }
}
