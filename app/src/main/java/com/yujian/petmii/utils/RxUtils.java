package com.yujian.petmii.utils;


import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lisc
 * RxJava2.x tools
 */

public class RxUtils {
	public static final Object None ="";

    private RxUtils(){}

    /**
	 * pattern: Observable/Observer
     * make Observable to emit some data without dealing with backpressure now.
     * @param func
     * @return
	 *
     */
    public synchronized static <T> Observable<T> makeObservable(final Callable<T> func)
    {
        return Observable.create(new ObservableOnSubscribe<T>() {
			@Override
			public void subscribe(ObservableEmitter<T> emitter) throws Exception {
				// TODO Auto-generated method stub
				try{
					emitter.onNext(func.call());
					emitter.onComplete();
                }catch (Exception e){
                	emitter.onError(e);
                }
			}
		});
    }

	/**
	 * common obserable using io-main schedules model.
	 * @param func
	 * @param <T>
	 * @return
	 */
	public synchronized static<T> Observable<T> observableOnIoMain(final Callable<T> func)
	{
		return makeObservable(func).compose(oIoMain());
	}

    /**
	 * pattern : Flowable/Subscriber
     * make flowable to emit data support for backpressure.
     * @param func
     * @return
     */
    public synchronized static <T> Flowable<T> makeFlowable(final Callable<T> func)
    {
    	return Flowable.create(new FlowableOnSubscribe<T>(){
			@Override
			public void subscribe(FlowableEmitter<T> emitter) throws Exception {
				// TODO Auto-generated method stub
				try{
					emitter.onNext(func.call());
					emitter.onComplete();
                }catch (Exception e){
                	emitter.onError(e);
                }
			}
    	}, BackpressureStrategy.BUFFER);
    }

	/**
	 * common flowable using io-main schedules model
	 * @param func
	 * @param <T>
	 * @return
	 */
	public synchronized static<T> Flowable<T> flowableOnIoMain(final Callable<T> func)
	{
		return makeFlowable(func).compose(fIoMain());
	}

	/**
	 * pattern: Single/SingleObserver
	 * @param func
	 * @param <T>
	 * @return
	 */
	public synchronized static <T> Single<T> singleOnIoMain(final Callable<T> func)
	{
		return Single.create(new SingleOnSubscribe<T>() {
			@Override
			public void subscribe(@NonNull SingleEmitter<T> e) throws Exception {
				try{
					e.onSuccess(func.call());
				}catch (Exception error){
					e.onError(error);
				}
			}
		}).compose(sIoMain());
	}

	public synchronized static Disposable singleTask(final Runnable func, Scheduler scheduler)
	{
		return Observable.create(new ObservableOnSubscribe() {
			@Override
			public void subscribe(ObservableEmitter emitter) throws Exception {
				// TODO Auto-generated method stub
				try{
					func.run();
					emitter.onComplete();
				}catch (Exception e){
					emitter.onError(e);
				}
			}
		}).subscribeOn(scheduler).subscribe();
	}

	public synchronized static Disposable singleTaskOnIo(final Runnable func)
	{
		return singleTask(func,Schedulers.io());
	}

	public synchronized static Disposable singleTaskOnThread(final Runnable func)
	{
		return singleTask(func,Schedulers.newThread());
	}

    /**
     * for rxjava2.x Obserable
     * @return
     */
    public synchronized static <T> ObservableTransformer<T, T> oIoMain() {
    	
    	return new ObservableTransformer<T, T>() {    
    		@Override  
    		public ObservableSource<T> apply(Observable<T> upstream) {    
    			return upstream.subscribeOn(Schedulers.io())
    					.observeOn(AndroidSchedulers.mainThread(),false,100);   
    		}  
    	}; 
    } 
 
    /**
     * for rxjava2.x Flowable
     * @return
     */
    public synchronized static <T> FlowableTransformer<T, T> fIoMain() {
    	return new FlowableTransformer<T, T>() {
			@Override
			public Publisher<T> apply(Flowable<T> flowable) {
				// TODO Auto-generated method stub
				return flowable.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread());
			}    
    		
    	}; 
    }

	/**
	 * for rxjava2.x Single
	 * @param <T>
	 * @return
	 */
	public synchronized static <T>SingleTransformer<T,T> sIoMain()
	{
		return new SingleTransformer<T, T>() {
			@Override
			public SingleSource<T> apply(@NonNull Single<T> upstream) {
				return upstream.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}



}
