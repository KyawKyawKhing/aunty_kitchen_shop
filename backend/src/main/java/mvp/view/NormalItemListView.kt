package mvp.view

import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface NormalItemListView : BaseView {
    fun displayNormalItemList(itemList: List<AvailableItemVO>)
}