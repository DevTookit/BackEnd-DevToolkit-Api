package com.project.core.domain.group

import com.project.core.domain.BaseEntity
import com.project.core.domain.user.User
import com.project.core.internal.ContentType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class GroupLog(
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    val group: Group,
    @Enumerated(EnumType.STRING)
    val type: ContentType,
    val contentId: Long,
    val contentName: String,
    val sectionId: Long,
) : BaseEntity()
