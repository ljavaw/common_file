package com.ljavaw.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @ClassName: ProtoBufTest
 * @Description:
 * @author liujiawei
 * @create 15/05/2020 13:50
 * @version 1.0
 **/
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Person person = DataInfo.Person.newBuilder().
                setName("张三").setAge(30).setAddress("朝阳").build();

        byte[] bytes = person.toByteArray();
        DataInfo.Person person1 = DataInfo.Person.parseFrom(bytes);

        System.out.println(person1.getName());
        System.out.println(person1.getAge());
        System.out.println(person1.getAddress());
    }
}
