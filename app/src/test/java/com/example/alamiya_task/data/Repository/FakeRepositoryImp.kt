import com.example.alamiya_task.core.connectivity_observer.NetworkState
import com.example.alamiya_task.data.Repository.PrayerRepositoryImp
import com.example.alamiya_task.data.source.Database.PrayerDatabase
import com.example.alamiya_task.data.source.Database.PrayerDao
import com.example.alamiya_task.data.source.RemoteData.ApiInterface
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import com.example.alamiya_task.domin.entity.qibla.qiblaResponse
import com.example.alamiya_task.domin.repository.PrayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PrayerRepositoryImpTest {

    private lateinit var prayerRepository: PrayerRepository
    private lateinit var apiInterface: ApiInterface
    private lateinit var db: PrayerDatabase
    private lateinit var prayerDao: PrayerDao
    private lateinit var networkState: NetworkState

    @Before
    fun setup() {
        apiInterface = mock(ApiInterface::class.java)
        db = mock(PrayerDatabase::class.java)
        prayerDao = mock(PrayerDao::class.java)
        networkState = mock(NetworkState::class.java)
        `when`(db.getPrayerDao()).thenReturn(prayerDao)

        prayerRepository = PrayerRepositoryImp(apiInterface, db, networkState, Dispatchers.Unconfined)
    }

    @Test
    fun `getPrayerTimes should return PrayerTimeResponse from API`() {
        runBlocking {
            val year = 2024
            val month = 10
            val latitude = 30.0
            val longitude = 31.0
            val method = 1
            val expectedResponse = mock(PrayerTimeResponse::class.java)

            `when`(apiInterface.getPrayerTimes(year, month, latitude, longitude, method)).thenReturn(expectedResponse)

            val result = prayerRepository.getPrayerTimes(year, month, latitude, longitude, method)

            assertEquals(expectedResponse, result)
            verify(apiInterface).getPrayerTimes(year, month, latitude, longitude, method)
        }
    }

    @Test
    fun `getQiblaDirection should return qiblaResponse from API`() {
        runBlocking {
            val latitude = 30.0
            val longitude = 31.0
            val expectedResponse = mock(qiblaResponse::class.java)

            `when`(apiInterface.getQiblaDirection(latitude, longitude)).thenReturn(expectedResponse)

            val result = prayerRepository.getQibla(latitude, longitude)

            assertEquals(expectedResponse, result)
            verify(apiInterface).getQiblaDirection(latitude, longitude)
        }
    }

    @Test
    fun `savePrayersTimes should save PrayerTimeResponse in the database`() {
        runBlocking {
            val response = mock(PrayerTimeResponse::class.java)
            val expectedId = 1L

            `when`(prayerDao.savePrayersTimes(response)).thenReturn(expectedId)

            val result = prayerRepository.savePrayersTimes(response)

            assertEquals(expectedId, result)
            verify(prayerDao).savePrayersTimes(response)
        }
    }

    @Test
    fun `getAllPrayersTimes should return PrayerTimeResponse from database`() {
        runBlocking {
            val expectedResponse = mock(PrayerTimeResponse::class.java)

            `when`(prayerDao.getAllPrayersTimes()).thenReturn(expectedResponse)

            val result = prayerRepository.getAllPrayersTimes()

            assertEquals(expectedResponse, result)
            verify(prayerDao).getAllPrayersTimes()
        }
    }

    @Test
    fun `deleteAll should call deleteAll on PrayerDao`() {
        runBlocking {
            prayerRepository.deleteAll()

            verify(prayerDao).deleteAll()
        }
    }

    @Test
    fun `getCachedPrayerTimes should return cached data if online`() {
        runBlocking {
            val year = 2024
            val month = 10
            val latitude = 30.0
            val longitude = 31.0
            val method = 1
            val response = mock(PrayerTimeResponse::class.java)

            `when`(networkState.isOnline()).thenReturn(true)
            `when`(apiInterface.getPrayerTimes(year, month, latitude, longitude, method)).thenReturn(response)
            `when`(prayerDao.getAllPrayersTimes()).thenReturn(response)

            val result = prayerRepository.getCachedPrayerTimes(year, month, latitude, longitude, method)

            assertEquals(response, result)
            verify(apiInterface).getPrayerTimes(year, month, latitude, longitude, method)
            verify(prayerDao).savePrayersTimes(response)
        }
    }

    @Test
    fun `getCachedPrayerTimes should return cached data if offline`() {
        runBlocking {
            val year = 2024
            val month = 10
            val latitude = 30.0
            val longitude = 31.0
            val method = 1
            val response = mock(PrayerTimeResponse::class.java)

            `when`(networkState.isOnline()).thenReturn(false)
            `when`(prayerDao.getAllPrayersTimes()).thenReturn(response)

            val result = prayerRepository.getCachedPrayerTimes(year, month, latitude, longitude, method)

            assertEquals(response, result)
            verify(apiInterface, never()).getPrayerTimes(anyInt(), anyInt(), anyDouble(), anyDouble(), anyInt())
            verify(prayerDao).getAllPrayersTimes()
        }
    }
}
