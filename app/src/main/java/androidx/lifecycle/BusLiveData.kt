package androidx.lifecycle

import androidx.annotation.MainThread
import com.plbear.lxc.module.bus.core.BaseBusObserverWrapper
import com.plbear.lxc.module.bus.core.BusLifecycleObserver
import com.plbear.lxc.module.bus.core.LiveDataBusCore
import com.plbear.lxc.utils.logcat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 整个利用LiveData实现Bus的流程是:
 * 1. 要新建自定义的Observer类, 来覆盖onChange方法, 这里就需要新建一个BusLiveData类, 一方面要重新写version方法
 * 另外一方面, 是保证所有observe的都是自定义的observe
 * 2. 涉及到remove, 所以需要新建一个map, 将所有的observe都存起来
 * created by yanyongjun on 2020/6/27
 */
class BusLiveData<T>(private val key: String) : MutableLiveData<T>() {
    // 新建一个ObserverMap的作用, 主要是将Observer类转换为BaseBusObserverWrapper, 以用来规避因为version接收到历史消息的"bug"
    // 那如果只是只是这个作用, 只转换就好了, 为什么要新建一个Map存起来呢? 这里是为了在removeObserver的时候, 能够找到之前转换的Observer
    private val observerMap: MutableMap<Observer<in T>, BaseBusObserverWrapper<T>> = mutableMapOf()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val exist = observerMap.getOrPut(observer, {
            BusLifecycleObserver(observer, owner, this).apply {
                owner.lifecycle.addObserver(this)
            }
        })
        super.observe(owner, exist)
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        val exist = observerMap.getOrPut(observer, {
            BaseBusObserverWrapper( observer,this)//为什么上一步是ok的, 这一步不行了呢?
        })
        super.observeForever(exist)
    }

    @MainThread
    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    @MainThread
    fun observeStickyForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        logcat("remove Observers")
        logcat(Exception("remove observers exception"))
        observerMap.iterator().forEach {
            if (it.value.isAttachedTo(owner)) {
                observerMap.remove(it.key)
            }
        }
        super.removeObservers(owner)
        tryRemoveCoreKey()
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        logcat("removeObserver")
        var exist = observer

        if (observerMap.containsKey(observer)) {
            exist = observerMap.remove(observer)!!
        }
        super.removeObserver(exist)
        tryRemoveCoreKey()
    }

    override fun postValue(value: T) {
        GlobalScope.launch(Dispatchers.Main) {
            setValue(value)
        }
    }

    @MainThread
    override fun onInactive() {
        super.onInactive()
        logcat("OnInactive")
        tryRemoveCoreKey()
    }

    private fun tryRemoveCoreKey() {
        if (!hasObservers()) {
            logcat("remove key")
            LiveDataBusCore.instance.busMap.remove(key)
        }
    }

    public override fun getVersion(): Int {
        return super.getVersion()
    }
}
