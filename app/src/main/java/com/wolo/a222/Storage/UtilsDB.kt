package com.wolo.a222.Storage

import com.wolo.a222.Storage.Packs.PacksDB

class UtilsDB {

    fun addPacksToDB(it: MutableMap<String, Any>?){
       /* var packDB = getPackDB(it)
        try{
        //WoloApp.getComponent().packsDatabase().packsDao().insert(packDB)}
        catch (e: Exception){
            Log.d("ERROR", e.toString())
        }
*/

    }

    fun getPackDB(it: MutableMap<String, Any>?): PacksDB {
        var packDB = PacksDB()
        if (it != null) {
            for (p in it)
            {
                when (p.key) {
                    "name" -> packDB.name = p.value as String
                    "id" -> packDB.id = p.value as String
                    "cards" -> packDB.card = p.value as ArrayList<String>
                }
            }
        }
        return packDB
    }
}