package com.ertugrulakkaya.spaceexplorer.data.repository

import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LinksDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.PatchDto
import com.ertugrulakkaya.spaceexplorer.fakes.FakeLocalDataSource
import com.ertugrulakkaya.spaceexplorer.fakes.FakeRemoteDataSource
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LaunchRepositoryImplTest {

    private lateinit var fakeLocal : FakeLocalDataSource
    private lateinit var fakeRemote : FakeRemoteDataSource
    private lateinit var repositry : LaunchRepositoryImpl

    @BeforeTest
    fun setup(){
        fakeLocal = FakeLocalDataSource()
        fakeRemote = FakeRemoteDataSource()
        repositry = LaunchRepositoryImpl(fakeLocal,fakeRemote)
    }

    @Test
    fun `when refreshLaunches is called, launches are fetched from remote and saved to local`() = runTest {

        val fakeLaunchDto = LaunchDto(
            id = "launch_1",
            name = "FalconSat",
            rocket = "r1",
            flightNumber = 1,
            details = "Engine failure at 33 seconds and loss of vehicle",
            success = false,
            dateUtc = "2006-03-24T22:30:00.000Z",
            links = LinksDto(
                patch = PatchDto(
                    small = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
                    large = "https://images2.imgbox.com/40/e3/GypSkayF_o.png"
                ),
                article = "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html",
                wikipedia = "https://en.wikipedia.org/wiki/DemoSat",
                youtubeId = "0a_00nJ_Y88",
                webcast = "https://www.youtube.com/watch?v=0a_00nJ_Y88"
            )
        )

        fakeRemote.launchesToReturn = Result.success(listOf(fakeLaunchDto))

        val result = repositry.refreshLaunches()

        assertTrue(result.isSuccess, "Refresh should be successful")
        assertTrue(fakeLocal.isSaveCalled, "Save should be called")

        val savedDate = fakeLocal.savedEntities
        assertEquals(1,savedDate.size)
        assertEquals("Falcon Test", savedDate[0].rocketName)
        assertEquals("Test rocket description", savedDate[0].rocketDescription)

    }



}