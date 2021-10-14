package com.devblock.db.api.mappers

import com.devblock.db.api.models.Contact
import com.devblock.network.api.response.ContactItemResp
import java.util.*


fun ContactItemResp.toDbEntity() =
    Contact(
        id = id,
        full_name = fullName(),
        email = email,
        avatar = avatar,
        updatedAt = Date().time

    )

