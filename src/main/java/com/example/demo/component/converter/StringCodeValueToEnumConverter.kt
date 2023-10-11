package com.miiro.fruit.converter

import com.miiro.fruit.model.AppException
import com.miiro.fruit.model.enums.ICodeValue
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

class StringCodeValueToEnumConverter<T : ICodeValue>(private val enumType: Class<T>) : Converter<String, T> {
    //    private val idEnumMap: MutableMap<String, T> = hashMapOf()
    private val codeEnumMap: MutableMap<String, T> = hashMapOf()
    private val logger = LoggerFactory.getLogger(StringCodeValueToEnumConverter::class.java)

    init {
        logger.info("IdCodeToEnumConverter enumType $enumType")
        enumType.enumConstants.forEach { x ->
            logger.info("IdCodeToEnumConverter x $x")
//            idEnumMap[x!!.code.toString()] = x
            codeEnumMap[x.code.toString()] = x
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
            throw AppException.paramError("参数错误, enum转换失败")
        }

        return t
//        val res = Optional.of(source)
//            .map { key: Any? -> codeEnumMap[key] }
//            .orElseGet {
//                val a = Optional.of(source).map { key: Any? -> idEnumMap[key] }
//
//
//                if (a.isPresent) {
//                    return@orElseGet a.get()
//                }
//                null
//            }
//
//        println("IdCodeToEnumConverter [convert] res $res")
//
//        if (res == null) {
//            throw AppException.paramError("参数错误, enum转换失败")
//        }
//
//        return res
    }
}

class StringCodeValueToEnumConverterFactory : ConverterFactory<String, ICodeValue> {
    private val logger = LoggerFactory.getLogger(StringCodeValueToEnumConverterFactory::class.java)

    override fun <T : ICodeValue> getConverter(targetType: Class<T>): Converter<String, T> {
//        var converter: Converter<String, T> = CONVERTERS[targetType]
        var converter = CONVERTERS[targetType]
        logger.info("IdCodeToEnumConverterFactory [getConverter] converter $converter")

        if (converter == null) {
            val cc = StringCodeValueToEnumConverter(targetType)
            converter = StringCodeValueToEnumConverter(targetType)
            CONVERTERS[targetType] = converter
        }

        return converter as Converter<String, T>
    }

    companion object {
        //        private val CONVERTERS: MutableMap<Class<*>, Converter<*, *>> = hashMapOf()
//        private val CONVERTERS: MutableMap<Class<*>, Converter<*, *>> = hashMapOf()
        private val CONVERTERS: MutableMap<Class<*>, Converter<String, *>> = hashMapOf()
    }
}