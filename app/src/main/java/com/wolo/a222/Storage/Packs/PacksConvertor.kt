package com.wolo.a222.Storage.Packs

import androidx.room.TypeConverter


class PacksConvertor {

    @TypeConverter
    fun fromPacks(packs: ArrayList<String>): String{
        //val gson = Gson()
        //val json = gson.toJson(packs)
        //return  json
        return packs.toString()
    }

    @TypeConverter
    fun toPacks(data: String):ArrayList<String>{
       return arrayListOf(data)

    }
}