package cn.zry.test.entity;

import cn.zry.modules.data.mongo.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "test", noClassnameStored = true)
public class Test extends AbstractEntity {


    private String name;

    public Test() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
