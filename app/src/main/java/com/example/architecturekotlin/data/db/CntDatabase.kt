package com.example.architecturekotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.architecturekotlin.data.entity.CntEntity

@Database(entities = [CntEntity::class], version = 1, exportSchema = true)
abstract class CntDatabase: RoomDatabase() {

    abstract fun cntDao(): CntDao

    companion object {

        @Volatile
        private var INSTANCE: CntDatabase? = null

        fun getDatabase(context: Context): CntDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CntDatabase::class.java,
                    "cnt_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

//val MIGRATION_1_2 = object :  Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todo_table RENAME COLUMN title to todoTitle")
//    }
//}