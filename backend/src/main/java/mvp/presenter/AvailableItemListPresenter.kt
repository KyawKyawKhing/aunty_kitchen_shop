package mvp.presenter

import com.aceplus.backend.delegate.AvailableItemDelegate
import com.aceplus.shared.VO.AvailableItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.AvailableItemListView

/**
 * Created by kkk on 9/22/2018.
 */
class AvailableItemListPresenter(view: AvailableItemListView) : BasePresenter<AvailableItemListView>(view), AvailableItemDelegate {
    fun displayTodayAvailableItemList(itemNode: String) {
        BackendModel.getInstance().displayTodayAvailableItem(itemNode, object : ModelCallback.GetTodayAvailableItemCallback {
            override fun getDataSucceed(itemList: List<AvailableItemVO>) {
                mView.displayAvailableItemList(itemList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage("Cannot Load Data")
            }

        })
    }
}