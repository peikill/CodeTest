package com.example.architecture.ext.util

import java.lang.reflect.ParameterizedType

object ClassUtils {

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenericManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration,
     * or <code>Object.class</code> if cannot be determine
     */
    fun <T> getSuperClassGenericType(clazz: Class<*>): Class<T> {
        return getSuperClassGenericType<T>(clazz, 1)
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenericManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.</Book>
     */
     fun <T> getSuperClassGenericType(clazz: Class<*>, index: Int): Class<T> {
        var cls = clazz
        var genType = cls.genericSuperclass
        while (genType !is ParameterizedType) {
            cls = cls.superclass
            if (cls == null) {
                throw IllegalArgumentException()
            }
            genType = cls.genericSuperclass
        }
        val params = genType.actualTypeArguments
        if (index >= params.size || index < 0) {
            throw IllegalArgumentException()
        }
        return if (params[index] !is Class<*>) {
            throw IllegalArgumentException()
        } else params[index] as Class<T>
    }
}