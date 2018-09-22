package com.aceplus.shared.model

import android.content.Context
import com.aceplus.shared.VO.AdminUserVO
import com.aceplus.shared.VO.AvailableItemVO
import com.aceplus.shared.VO.OrderItemVO
import com.aceplus.shared.modelcallback.ModelCallback
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*

/**
 * Created by kkk on 9/21/2018.
 */
class BackendModel constructor(val context: Context) {
    companion object {
        private lateinit var mDatabaseReference: DatabaseReference
        private var POT_USER_LIST_FEED = "users"
        private var mFirebaseUser: FirebaseUser? = null
        private var mFirebaseAuth: FirebaseAuth? = null
        private var INSTANCE: BackendModel? = null
        private lateinit var mPotUserListFeed: DatabaseReference

        fun getInstance(): BackendModel {
            if (INSTANCE == null) {
                throw RuntimeException("BackendModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initBackendModel(context: Context) {
            INSTANCE = BackendModel(context)
        }

    }

    init {
        mDatabaseReference = FirebaseDatabase.getInstance().reference
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth!!.currentUser
        mPotUserListFeed = mDatabaseReference.child(POT_USER_LIST_FEED)

    }

    fun authenticateUserWithGoogleAccount(signInAccount: GoogleSignInAccount, delegate: SignInWithGoogleAccountDelegate) {
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        mFirebaseAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        delegate.onFailureSignIn(task.exception!!.message!!)
                    } else {
                        delegate.onSuccessSignIn(signInAccount)
                    }
                }

                .addOnFailureListener { exception ->
                    delegate.onFailureSignIn(exception.message!!)
                }
    }

    interface SignInWithGoogleAccountDelegate {
        fun onSuccessSignIn(signInAccount: GoogleSignInAccount)
        fun onFailureSignIn(msg: String)
    }


    //login admin
    fun loginAdmin(username: String, password: String, callback: ModelCallback.LoginAdminCallback) {
        mDatabaseReference.child("admin").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (ds in p0.children) {
                    val adminUser = ds.getValue(AdminUserVO::class.java)
                    if (adminUser!!.userName == username && adminUser.password == password) {
                        callback.loginSucceed(adminUser.userId!!)
                        return
                    }
                }
            }

        })
    }

    fun addPredefineNormalItem(vo: AvailableItemVO) {
        mDatabaseReference.child("normal_item").child(vo.itemId!!).setValue(vo)

    }

    fun addPredefineSpecialItem(vo: AvailableItemVO) {
        mDatabaseReference.child("normal_item").child(vo.itemId!!).setValue(vo)
    }

    //add today available item by admin
    fun addTodayAvailableItem(itemNode: String, item: AvailableItemVO, callback: ModelCallback.AddTodoayAvailableItem) {
        mDatabaseReference.child("daily_item").child(itemNode).child("available_item").child(item.itemId!!).setValue(item)
        mDatabaseReference.child("daily_item").child(itemNode).child("available_item")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.hasChild(item.itemId!!)) {
                            callback.addDataSucceed("ထည့္ျပီးပါျပီ")
                        } else {
                            callback.addDataFailed("မေအာင္ျမင္ပါ")
                        }
                    }

                })
    }

    fun displayTodayNormalOrder(itemNode: String, callback: ModelCallback.GetOrderCallback) {
        mDatabaseReference.child("daily_item").child(itemNode).child("normal_orders").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val orderList = mutableListOf<OrderItemVO>()
                for (ds in p0.children) {
                    for (dsChild in ds.children) {
                        orderList.add(dsChild.getValue(OrderItemVO::class.java)!!)
                    }
                }
                callback.getDataSucceed(orderList)
            }

        })
    }

    fun displayTodaySpecialOrder(itemNode: String, callback: ModelCallback.GetOrderCallback) {
        mDatabaseReference.child("daily_item").child(itemNode).child("special_orders").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val orderList = mutableListOf<OrderItemVO>()
                for (ds in p0.children) {
                    for (dsChild in ds.children) {
                        orderList.add(dsChild.getValue(OrderItemVO::class.java)!!)
                    }
                }
                callback.getDataSucceed(orderList)
            }

        })
    }

    //admin & user to display available item list
    fun displayTodayAvailableItem(itemNode: String, callback: ModelCallback.GetTodayAvailableItemCallback) {
        mDatabaseReference.child("daily_item").child(itemNode).child("available_item").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemList = p0.children.map { it.getValue(AvailableItemVO::class.java)!! }
                callback.getDataSucceed(itemList)
            }

        })
    }

    //user
    fun addTodayNormalOrder(itemNode: String, order: OrderItemVO, callback: ModelCallback.AddOrderCallback) {
        mDatabaseReference.child("daily_item").child(itemNode).child("available_item").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (ds in p0.children) {
                    if (ds.hasChild(order.itemId!!)) {
                        val itemVO = ds.child(order.itemId!!).getValue(AvailableItemVO::class.java)
                        var count = itemVO!!.itemCount!! - order.itemCount!!.toLong()
                        if (count >= 0) {
                            itemVO.itemCount = count
                            mDatabaseReference.child("daily_item").child(itemNode).child("available_item").child(order.itemId!!).setValue(itemVO)
                            mDatabaseReference.child("daily_item").child(itemNode).child("normal_orders").child(order.customerId!!).setValue(order)
                        } else {
                            callback.addOrderFailed("Cannot get this item amount")
                        }
                    }
                }
            }

        })
    }

    fun addTodaySpecialOrder(itemNode: String, order: OrderItemVO) {
        mDatabaseReference.child("daily_item").child(itemNode).child("special_orders").child(order.customerId!!).setValue(order)
    }

    fun displayNormalAllItem(callback: ModelCallback.GetAllItemCallback) {
        mDatabaseReference.child("normal_item").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemList = p0.children.map { it.getValue(AvailableItemVO::class.java)!! }
                callback.getDataSucceed(itemList)
            }

        })
    }

    fun displaySpecialAllItem(callback: ModelCallback.GetAllItemCallback) {
        mDatabaseReference.child("special_item").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemList = p0.children.map { it.getValue(AvailableItemVO::class.java)!! }
                callback.getDataSucceed(itemList)
            }

        })
    }

    fun deleteNormalItem(itemId: String) {
        mDatabaseReference.child("normal_item").child(itemId).removeValue()
    }

    fun addNormalItem(itemVO: AvailableItemVO) {
        mDatabaseReference.child("normal_item").child(itemVO.itemId!!).setValue(itemVO)
    }

    fun changeSentOrder(itemNode: String, orderItemVO: OrderItemVO) {
        mDatabaseReference.child("daily_item").child(itemNode).child("normal_orders").child(orderItemVO.customerId!!).child(orderItemVO.orderId!!).setValue(orderItemVO)
    }

    fun displayAllOrder(callback: ModelCallback.GetOrderCallback) {
        mDatabaseReference.child("daily_item").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback.getDataFailed("Cannot Load Data")
            }

            override fun onDataChange(p0: DataSnapshot) {
                val orderList = mutableListOf<OrderItemVO>()
                for (ds in p0.children) {
                    for (dsChild in ds.child("normal_orders").children) {
                        for (order in dsChild.children) {
                            orderList.add(dsChild.getValue(OrderItemVO::class.java)!!)
                        }
                    }

                    for (dsChild in ds.child("special_orders").children) {
                        for (order in dsChild.children) {
                            orderList.add(dsChild.getValue(OrderItemVO::class.java)!!)
                        }
                    }
                }
                callback.getDataSucceed(orderList)
            }

        })
    }

    fun displayOrderByDateByUser(orderNode: String, userId: String, callback: ModelCallback.GetOrderCallback) {
        mDatabaseReference.child("daily_item").child(orderNode).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val orderList = mutableListOf<OrderItemVO>()
                for (normalOrder in p0.child("normal_orders").child(userId).children) {
                    orderList.add(normalOrder.getValue(OrderItemVO::class.java)!!)
                }
                for (normalOrder in p0.child("special_orders").child(userId).children) {
                    orderList.add(normalOrder.getValue(OrderItemVO::class.java)!!)
                }
                callback.getDataSucceed(orderList)
            }

        })
    }

    fun displayOrderByDateByAdmin(orderNode: String, callback: ModelCallback.GetOrderCallback) {
        mDatabaseReference.child("daily_item").child(orderNode).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val orderList = mutableListOf<OrderItemVO>()
                for (normalOrder in p0.child("normal_orders").children) {
                    for (ds in normalOrder.children) {
                        orderList.add(ds.getValue(OrderItemVO::class.java)!!)
                    }
                }
                for (normalOrder in p0.child("special_orders").children) {
                    for (ds in normalOrder.children) {
                        orderList.add(ds.getValue(OrderItemVO::class.java)!!)
                    }
                }
                callback.getDataSucceed(orderList)
            }

        })
    }
}