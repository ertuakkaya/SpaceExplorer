package com.ertugrulakkaya.spaceexplorer.domain.usecase

import com.ertugrulakkaya.spaceexplorer.domain.model.Launch
import com.ertugrulakkaya.spaceexplorer.fakes.FakeLaunchRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ObserveLaunchesUseCaseTest {

    private val fakeRepository = FakeLaunchRepository()
    private val useCase = ObserveLaunchesUseCase(fakeRepository)

    @Test
    fun `usecase returns flow of launches`() = runTest {

        val fakeLaunches = listOf(
            Launch(
                id = "1",
                name = "FalconSat",
                flightNumber = 1,
                details = "Details of FalconSat",
                success = true,
                dateUtc = "2023-01-01T00:00:00Z",
                rocketName = "Falcon",
                rocketDescription = "Description of Falcon",
                patchImageSmall = "https://example.com/image.jpg",
                patchImageBig = "https://example.com/image.jpg",
                article = "https://example.com/article",
                webcast = "https://example.com/webcast",
                wikipedia = "https://example.com/wikipedia",
                youtubeId = "abc123"
            )
        )

        fakeRepository.lauchesToReturn = flowOf(fakeLaunches)

        val result = useCase.invoke()

        result.collect { list ->
            assertEquals(fakeLaunches , list, "useCase returned wrong launches")
        }

    }
}