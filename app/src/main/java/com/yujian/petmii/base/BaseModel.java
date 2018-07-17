package com.yujian.petmii.base;


import com.yujian.petmii.rxbus.EventType;
import com.yujian.petmii.rxbus.RxBus;
import com.yujian.petmii.rxbus.RxEvent;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseModel<T> {
    private CompositeDisposable mCompositeDisposable;

    //设置默认接受类型为全部接收
    private @EventType.ReceiveType
    int receiveType = EventType.REC_ALL;
    protected T mPresenter;

    public BaseModel(T mPresenter) {
        this.mPresenter = mPresenter;
        mCompositeDisposable = new CompositeDisposable();
        initRxbus();
    }

    public abstract void onAttached();

    public boolean addDisposable(Disposable disposable)
    {
        return mCompositeDisposable.add(disposable);
    }

    /**
     * 初始化接收类型
     * @param receiveType
     */
    public void initReceiver(@EventType.ReceiveType int receiveType) {
        this.receiveType = receiveType;
    }

    /**
     * 注册rxbus事件接收
     */
    private void initRxbus() {
        RxBus.getDefault().toObserverable(RxEvent.class)
            .filter(new Predicate<RxEvent>() {
                @Override
                public boolean test(@NonNull RxEvent rxEvent) throws Exception {
                    if ((rxEvent.receiveType == receiveType ||
                            rxEvent.receiveType == EventType.REC_ALL) && (rxEvent.threadType ==
                            EventType.THREAD_ALL || rxEvent.threadType == EventType.THREAD_UI)) {
                        return true;
                    }
                    return false;
                }
            }).observeOn(AndroidSchedulers.mainThread())//设置为主线程接收数据
            .subscribe(new Observer<RxEvent>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull RxEvent rxEvent) {
                    onMainEvent(rxEvent.what, rxEvent.event);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

         RxBus.getDefault().toObserverable(RxEvent.class)
            .filter(new Predicate<RxEvent>() {
                @Override
                public boolean test(@NonNull RxEvent rxEvent) throws Exception {
                    //此处可以通过Rxjava的filter过滤函数对数据进行过滤，从而得到自己想要的数据
                    if ((rxEvent.receiveType == receiveType ||
                            rxEvent.receiveType == EventType.REC_ALL) && (rxEvent.threadType ==
                            EventType.THREAD_ALL || rxEvent.threadType == EventType.THREAD_CHILD)) {
                        return true;
                    }
                    return false;
                }
            }).observeOn(Schedulers.io())//设置为子线程接收数据
            .subscribe(new Observer<RxEvent>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(@NonNull RxEvent rxEvent) {
                    onThreadEvent(rxEvent.what, rxEvent.event);
                }

                @Override
                public void onError(@NonNull Throwable e) {}

                @Override
                public void onComplete() {}
            });
    }

    public abstract void onMainEvent(int what, Object event);
    public abstract void onThreadEvent(int what, Object event);

    /**
     * 解除事件的注销，以保证不出现内存泄漏
     */
    public void onDetached() {
        if(mCompositeDisposable != null && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.clear();
        }
    }
}
