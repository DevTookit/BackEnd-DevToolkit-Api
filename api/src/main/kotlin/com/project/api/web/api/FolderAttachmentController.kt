package com.project.api.web.api

import com.project.api.service.FolderAttachmentService
import com.project.api.web.dto.request.FolderAttachmentCreateRequest
import com.project.api.web.dto.request.FolderAttachmentUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.DeleteMapping
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
@RequestMapping("/v1/folder-contents/attachments")
class FolderAttachmentController(
    private val folderAttachmentService: FolderAttachmentService,
) {
    @GetMapping("/{groupId}/{sectionId}")
    @Operation(summary = "파일 읽기")
    fun readOne(
        @AuthenticationPrincipal jwt: Jwt,
        @PathVariable groupId: Long,
        @PathVariable sectionId: Long,
        @RequestParam folderAttachmentId: Long,
    ) = folderAttachmentService.readOne(
        email = jwt.subject,
        groupId = groupId,
        sectionId = sectionId,
        folderAttachmentId = folderAttachmentId,
    )

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "파일 생성")
    fun create(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestPart(name = "FolderAttachmentCreateRequest") request: FolderAttachmentCreateRequest,
        @RequestPart files: List<MultipartFile>,
    ) = folderAttachmentService.create(jwt.subject, request, files)

    @PatchMapping
    @Operation(summary = "파일 이름 수정")
    fun update(
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody request: FolderAttachmentUpdateRequest,
    ) = folderAttachmentService.update(jwt.subject, request)

    @DeleteMapping("/{groupId}/{sectionId}")
    @Operation(summary = "파일 삭제")
    fun delete(
        @AuthenticationPrincipal jwt: Jwt,
        @PathVariable groupId: Long,
        @PathVariable sectionId: Long,
        @RequestParam folderAttachmentId: Long,
    ): ResponseEntity<Any> {
        folderAttachmentService.delete(jwt.subject, groupId, sectionId, folderAttachmentId)
        return ResponseEntity
            .noContent()
            .build()
    }
}
