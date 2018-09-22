package mvp.view

import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface AvailableItemAddView : BaseView {
    fun displayItemNameList(nameList: List<AvailableItemVO>)
    fun displayItemCountList(countList: List<String>)
}