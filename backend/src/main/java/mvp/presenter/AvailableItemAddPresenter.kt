package mvp.presenter

import com.aceplus.backend.Util.Utils
import com.aceplus.shared.VO.AvailableItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.AvailableItemAddView

/**
 * Created by kkk on 9/22/2018.
 */
class AvailableItemAddPresenter(view: AvailableItemAddView) : BasePresenter<AvailableItemAddView>(view) {
    fun displayItemNameSpinner() {
        BackendModel.getInstance().displayAllItem(object : ModelCallback.GetAllItemCallback {
            override fun getDataSucceed(itemList: List<AvailableItemVO>) {
                mView.displayItemNameList(itemList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage("Cannot Load Item List")
            }

        })
    }

    fun displayItemCountSpinner() {
        val countList = mutableListOf<String>()
        countList.add("1")
        countList.add("2")
        countList.add("3")
        countList.add("4")
        countList.add("5")
        countList.add("6")
        countList.add("7")
        countList.add("8")
        countList.add("9")
        countList.add("10")
        mView.displayItemCountList(countList)
    }

    fun addTodayAvailableItem(availableItemVO: AvailableItemVO) {
        BackendModel.getInstance().addTodayAvailableItem(Utils.getTodayDateNode(), availableItemVO, object : ModelCallback.AddTodoayAvailableItem {
            override fun addDataSucceed(message: String) {
                mView.displayMessage(message)
            }

            override fun addDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }
}