package mvp.presenter

import com.aceplus.backend.delegate.NormalItemDelegate
import com.aceplus.shared.VO.AvailableItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.NormalItemListView

/**
 * Created by kkk on 9/22/2018.
 */
class NormalItemListPresenter(view: NormalItemListView) : BasePresenter<NormalItemListView>(view), NormalItemDelegate {

    fun displayNormalItemList() {
        BackendModel.getInstance().displayNormalAllItem(object : ModelCallback.GetAllItemCallback {
            override fun getDataSucceed(itemList: List<AvailableItemVO>) {
                mView.displayNormalItemList(itemList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage("Cannot Load Data")
            }

        })
    }

    override fun onTapItemDelete(itemId: String) {
        BackendModel.getInstance().deleteNormalItem(itemId)
//        val vo = AvailableItemVO()
//        vo.itemId = (System.currentTimeMillis() / 100000).toString()
//        vo.itemName = "Noodle"
//        vo.itemPrice = 1000
//        BackendModel.getInstance().addPredefineNormalItem(vo)
    }

    override fun onTapItemEdit(itemVO: AvailableItemVO) {
        BackendModel.getInstance().addNormalItem(itemVO)
    }

    fun addNormalItem(itemVO: AvailableItemVO) {
        BackendModel.getInstance().addNormalItem(itemVO)
    }

}