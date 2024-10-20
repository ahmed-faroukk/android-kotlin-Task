import com.example.alamiya_task.core.helper_functions.findNextPrayer
import com.example.alamiya_task.domin.entity.prayer_time.Timings
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class PrayerTimeTest {

    @Test
    fun `test findNextPrayer returns Fajr when current time is before Fajr`() {
        val timings = Timings(
            Asr = "15:15",
            Dhuhr = "12:00",
            Fajr = "05:00",
            Firstthird = "04:15",
            Imsak = "04:30",
            Isha = "19:30",
            Lastthird = "22:30",
            Maghrib = "18:00",
            Midnight = "00:00",
            Sunrise = "05:45",
            Sunset = "18:00"
        )

        val currentTime = LocalTime.of(4, 0) // 04:00
        val (prayerName, prayerTime) = findNextPrayer(currentTime, timings)

        assertEquals("Fajr", prayerName)
        assertEquals(LocalTime.of(5, 0), prayerTime)
    }

    @Test
    fun `test findNextPrayer returns Dhuhr when current time is between Fajr and Dhuhr`() {
        val timings = Timings(
            Asr = "15:15",
            Dhuhr = "12:00",
            Fajr = "05:00",
            Firstthird = "04:15",
            Imsak = "04:30",
            Isha = "19:30",
            Lastthird = "22:30",
            Maghrib = "18:00",
            Midnight = "00:00",
            Sunrise = "05:45",
            Sunset = "18:00"
        )

        val currentTime = LocalTime.of(6, 0) // 06:00
        val (prayerName, prayerTime) = findNextPrayer(currentTime, timings)

        assertEquals("Dhuhr", prayerName)
        assertEquals(LocalTime.of(12, 0), prayerTime)
    }

    @Test
    fun `test findNextPrayer returns Maghrib when current time is between Asr and Maghrib`() {
        val timings = Timings(
            Asr = "15:15",
            Dhuhr = "12:00",
            Fajr = "05:00",
            Firstthird = "04:15",
            Imsak = "04:30",
            Isha = "19:30",
            Lastthird = "22:30",
            Maghrib = "18:00",
            Midnight = "00:00",
            Sunrise = "05:45",
            Sunset = "18:00"
        )

        val currentTime = LocalTime.of(16, 0) // 16:00
        val (prayerName, prayerTime) = findNextPrayer(currentTime, timings)

        assertEquals("Maghrib", prayerName)
        assertEquals(LocalTime.of(18, 0), prayerTime)
    }

    @Test
    fun `test findNextPrayer returns next day's Fajr when current time is after Isha`() {
        val timings = Timings(
            Asr = "15:15",
            Dhuhr = "12:00",
            Fajr = "05:00",
            Firstthird = "04:15",
            Imsak = "04:30",
            Isha = "19:30",
            Lastthird = "22:30",
            Maghrib = "18:00",
            Midnight = "00:00",
            Sunrise = "05:45",
            Sunset = "18:00"
        )

        val currentTime = LocalTime.of(20, 0) // 20:00
        val (prayerName, prayerTime) = findNextPrayer(currentTime, timings)

        assertEquals("Fajr", prayerName)
        assertEquals(LocalTime.of(5, 0).plusHours(24), prayerTime) // Next day's Fajr
    }

    @Test
    fun `test findNextPrayer returns Isha when current time is after Maghrib but before Isha`() {
        val timings = Timings(
            Asr = "15:15",
            Dhuhr = "12:00",
            Fajr = "05:00",
            Firstthird = "04:15",
            Imsak = "04:30",
            Isha = "19:30",
            Lastthird = "22:30",
            Maghrib = "18:00",
            Midnight = "00:00",
            Sunrise = "05:45",
            Sunset = "18:00"
        )

        val currentTime = LocalTime.of(19, 0) // 19:00
        val (prayerName, prayerTime) = findNextPrayer(currentTime, timings)

        assertEquals("Isha", prayerName)
        assertEquals(LocalTime.of(19, 30), prayerTime)
    }
}
