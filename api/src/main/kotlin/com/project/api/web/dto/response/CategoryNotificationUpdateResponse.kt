package com.project.api.web.dto.response

import com.project.core.domain.section.SectionNotification
import com.project.core.internal.CategoryNotificationType

data class CategoryNotificationUpdateResponse(
    val categoryId: Long?,
    val categoryNotificationId: Long?,
    val type: CategoryNotificationType,
) {
    companion object {
        fun SectionNotification.toCategoryNotificationUpdateResponse(categoryId: Long?): CategoryNotificationUpdateResponse =
            CategoryNotificationUpdateResponse(
                categoryId = categoryId,
                categoryNotificationId = this.id,
                type = this.type,
            )
    }
}
