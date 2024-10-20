package com.example.alamiya_task.data.source.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse

@Dao
interface PrayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePrayersTimes(prayerTimeResponse: PrayerTimeResponse): Long

    @Query("SELECT * FROM prayers")
    fun getAllPrayersTimes(): PrayerTimeResponse


    @Query("delete FROM prayers")
    suspend fun deleteAll()

}