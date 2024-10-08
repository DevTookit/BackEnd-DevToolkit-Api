package com.project.api.web.api

import com.project.api.internal.SortType
import com.project.api.service.FolderContentService
import com.project.api.web.dto.request.FolderCreateRequest
import com.project.api.web.dto.request.FolderUpdateRequest
import com.project.api.web.dto.response.FolderResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/v1/folder-contents/{groupId}/{sectionId}")
@Tag(name = "Folder Contents", description = "Folder Content API")
class FolderContentController(
    private val folderContentService: FolderContentService,
) {
    @GetMapping
    @Operation(summary = "폴더 가져오기 || 폴더 안 컨텐츠 가져오기")
    fun readAll(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestParam sortType: SortType,
        @PathVariable groupId: Long,
        @PathVariable sectionId: Long,
        @RequestParam parentFolderId: Long?,
    ): FolderResponse =
        folderContentService.readAll(
            email = jwt.subject,
            sortType = sortType,
            groupId = groupId,
            sectionId = sectionId,
            parentFolderId = parentFolderId,
        )

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "폴더 생성")
    fun create(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestPart(name = "FolderCreateRequest") request: FolderCreateRequest,
        @PathVariable groupId: Long,
        @PathVariable sectionId: Long,
        @RequestPart(required = false) files: List<MultipartFile>?,
    ) = folderContentService.create(
        email = jwt.subject,
        request = request,
        groupId = groupId,
        sectionId = sectionId,
        files = files,
    )

    @PatchMapping
    @Operation(summary = "폴더 수정")
    fun update(
        @AuthenticationPrincipal jwt: Jwt,
        @PathVariable groupId: Long,
        @PathVariable sectionId: Long,
        @RequestBody request: FolderUpdateRequest,
    ) = folderContentService.update(
        sectionId = sectionId,
        groupId = groupId,
        email = jwt.subject,
        request = request,
    )
}
