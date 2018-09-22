package mvp.view

import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface AvailableItemListView:BaseView{
    fun displayAvailableItemList(itemList: List<AvailableItemVO>)
}