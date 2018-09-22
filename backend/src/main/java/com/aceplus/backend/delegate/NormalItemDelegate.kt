package com.aceplus.backend.delegate

import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface NormalItemDelegate {
    fun onTapItemDelete(itemId: String)
    fun onTapItemEdit(itemVO: AvailableItemVO)
}