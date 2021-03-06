package com.xionghl.gongchang.factory;

import com.xionghl.gongchang.service.Animals;
import com.xionghl.gongchang.service.impl.Bunny;
import com.xionghl.gongchang.service.impl.Monkeys;
import com.xionghl.gongchang.service.impl.Tiger;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:xionghl
 * @Date:2021/8/22 4:28 下午
 * 动物工厂 生产动物实体对象
 */
@Slf4j
public class AnimalsFactory {

    private  static final String BUNNY = "Bunny";
    private  static final String MONKEYS = "Monkeys";
    private  static final String TIGER = "Tiger";

    /** 第一种方式 简单工厂设计模式 通过一个标识生产对象的对象
     * @param animalType  动物类型
     * @return  动物实体对象
     */
    public static Animals getBean(String animalType) {

        if (animalType == null) {
            return null;
        }
        switch (animalType) {
            case BUNNY:
                return new Bunny();
            case MONKEYS:
                return new Monkeys();
            case TIGER:
                return new Tiger();
            default:
                return null;
        }

    }

    /**  第二种方式  通过反射获取（Spring Aop的原理就是如此（工厂+反射））
      *  @param clazz  动物实体类型
      *  @return  动物实体对象
     */
    public static Animals getBean2(Class clazz) {
        try {
            return (Animals) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //第一种方式获取动物实体 工厂设计模式
        Animals bean = AnimalsFactory.getBean("Bunny");
        log.warn("所获的Animals的子类为：{}",bean);
        //第二种方式  通过反射获取（Spring Aop的原理就是如此（工厂+反射））
        Animals bean2 = AnimalsFactory.getBean2(Bunny.class);
        log.warn("所获的Animals的子类为：{}",bean2);
    }
}
