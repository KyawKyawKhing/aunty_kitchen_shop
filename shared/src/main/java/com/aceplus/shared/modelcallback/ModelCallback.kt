package com.aceplus.shared.modelcallback

import com.aceplus.shared.VO.AvailableItemVO
import com.aceplus.shared.VO.OrderItemVO
import com.aceplus.shared.VO.UserVO

/**
 * Created by kkk on 9/22/2018.
 */
interface ModelCallback {

    interface LoginAdminCallback {
        fun loginSucceed(userId: String)
        fun loginFailed(message: String)
    }

    interface LoginUserCallback {
        fun loginSucceed(userVO: UserVO)
        fun loginFailed(message: String)
    }

    interface GetTodayAvailableItemCallback {
        fun getDataSucceed(itemList: List<AvailableItemVO>)
        fun getDataFailed(message: String)
    }

    interface GetOrderCallback {
        fun getDataSucceed(orderList: List<OrderItemVO>)
        fun getDataFailed(message: String)
    }

    interface GetAllItemCallback {
        fun getDataSucceed(itemList: List<AvailableItemVO>)
        fun getDataFailed(message: String)
    }

    interface AddTodoayAvailableItem {
        fun addDataSucceed(message: String)
        fun addDataFailed(message: String)
    }

    interface AddOrderCallback {
        fun addOrderSucceed(message: String)
        fun addOrderFailed(message: String)
    }
}