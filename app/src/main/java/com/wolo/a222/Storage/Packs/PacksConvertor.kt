package com.wolo.a222.Storage.Packs

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import java.util.stream.Collectors


class PacksConvertor {

    @TypeConverter
    fun fromPacks(packs: Set<String>): String{
        //val gson = Gson()
        //val json = gson.toJson(packs)
        //return  json
        return packs.toString()
    }

    @TypeConverter
    fun toPacks(data: String):Set<String>{
       return setOf(data)

    }
}