package com.ertugrulakkaya.spaceexplorer.data.mapper

import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LaunchDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.LinksDto
import com.ertugrulakkaya.spaceexplorer.data.remote.dto.PatchDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LaunchMapperTest {
    @Test
    fun `LauchDto should be mapped to Launch`() {
        val dto = LaunchDto(
            id = "id-123",
            name = "FalconSat",
            flightNumber = 42,
            details = "Details of FalconSat",
            success = true,
            dateUtc = "2023-01-01T00:00:00Z",
            rocket = "dsfdsfdsfds",
            links = LinksDto(
                patch = PatchDto(
                    small = "small_url", large =
                        "large_url"
                ),
                article = "article_url",
                wikipedia = "wiki_url",
                youtubeId = "yt-id",
                webcast = "web_url"
            )
        )

        val domain = dto.toDomain(
            rocketName = "Falcon Heavy",
            rocketDescription = "Description of Falcon Heavy"
        )

        assertEquals("id-123", domain.id)
        assertEquals("FalconSat", domain.name)
        assertEquals("Details of FalconSat", domain.details)
        assertEquals(42, domain.flightNumber)
        assertEquals(true, domain.success)


    }


    @Test
    fun `if dto patch is null, domain image urls should be null`() {
        val dto = LaunchDto(
            id = "id",
            name = "name",
            rocket = "r",
            flightNumber = 1,
            details = null,
            success = null,
            dateUtc = "2023",
            links = LinksDto(
                patch = null,
                article = null,
                wikipedia = null,
                youtubeId = null,
                webcast = null
            )
        )

        val domain = dto.toDomain("R", "D")


        assertNull(domain.patchImageSmall, "if dto patch is null, domain image urls should be null ")
        assertNull (domain.patchImageBig, "if dto patch is null, domain image urls should be null ")
    }

}