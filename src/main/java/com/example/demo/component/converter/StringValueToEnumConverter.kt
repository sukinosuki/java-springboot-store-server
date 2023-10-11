package com.miiro.fruit.converter

import com.miiro.fruit.model.AppException
import com.miiro.fruit.model.enums.ILabelValue
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

// 如果需要对get方法的params参数string转换成enum类型，需要定义converter并注册
// WebConfig: registry.addConverterFactory(StringValueToEnumConverterFactory())

class StringValueToEnumConverter<T : ILabelValue>(private val enumType: Class<T>) : Converter<String, T> {
    //    private val idEnumMap: MutableMap<String, T> = hashMapOf()
    private val codeEnumMap: MutableMap<String, T> = hashMapOf()
    private val logger = LoggerFactory.getLogger(StringValueToEnumConverter::class.java)

    init {
        logger.info("IdCodeToEnumConverter enumType $enumType")
        enumType.enumConstants.forEach { x ->
            codeEnumMap[x.value] = x
        }
    }

    override fun convert(source: String): T? {
        println("IdCodeToEnumConverter [convert] source $source")
        println("IdCodeToEnumConverter [convert] codeEnumMap ${this.codeEnumMap}")
//        println("IdCodeToEnumConverter [convert] idEnumMap ${this.idEnumMap}")
        println("IdCodeToEnumConverter [convert] enumType ${this.enumType}")

        val t = codeEnumMap.get(source)
        println("IdCodeToEnumConverter [convert] enumType $t")
        if (t == null) {
            // TODO: 抛出异常还是返回null继续执行query
            throw AppException.paramError("参数错误, enum转换失败")
        }
        return t
    }
}

class StringValueToEnumConverterFactory : ConverterFactory<String, ILabelValue> {
    private val logger = LoggerFactory.getLogger(StringValueToEnumConverterFactory::class.java)

    override fun <T : ILabelValue> getConverter(targetType: Class<T>): Converter<String, T> {
//        var converter: Converter<String, T> = CONVERTERS[targetType]
        var converter = CONVERTERS[targetType]
        logger.info("IdCodeToEnumConverterFactory [getConverter] converter $converter")
        if (converter == null) {
//            val cc = StringValueToEnumConverter(targetType)
            converter = StringValueToEnumConverter(targetType)
            CONVERTERS[targetType] = converter
        }
        return converter as Converter<String, T>
    }

    companion object {
        //        private val CONVERTERS: MutableMap<Class<*>, Converter<*, *>> = hashMapOf()
        private val CONVERTERS: MutableMap<Class<*>, Converter<*, *>> = hashMapOf()
    }
}