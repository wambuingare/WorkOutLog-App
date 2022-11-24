package dev.nancygym.workoutlog.database

class Converters {
    @TypeConverter
    fun listToString(listX:List<String>):String{
        return listX.joinToString(",")
    }
    @TypeConverter
    fun stringToList(stringX:String): List<String>{
        return stringX.split(",")
    }
}