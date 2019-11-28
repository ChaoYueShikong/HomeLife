package com.homelife.net.utils;

import com.homelife.net.Feed;
import com.homelife.net.ServerException;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public final class RxJavaUtil {
    private RxJavaUtil() {
        throw new IllegalStateException("No instance!");
    }

    public static CompositeDisposable getCompositeDisposable(CompositeDisposable compositeDisposable) {
        if (compositeDisposable == null) {
            return new CompositeDisposable();
        }
        return compositeDisposable;
    }

    /**
     * observeOn MainThread
     */
    private static final ObservableTransformer OBSERVABLE_MAIN_THREAD = new ObservableTransformer() {
        @Override
        public ObservableSource apply(@NonNull Observable upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static final SingleTransformer SINGLE_MAIN_THREAD = new SingleTransformer() {

        @Override
        public SingleSource apply(@NonNull Single upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static final MaybeTransformer MAYBE_MAIN_THREAD = new MaybeTransformer() {

        @Override
        public MaybeSource apply(@NonNull Maybe upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static final CompletableTransformer COMPLETABLE_MAIN_THREAD = new CompletableTransformer() {
        @Override
        public CompletableSource apply(@NonNull Completable upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private static final FlowableTransformer FLOWABLE_MAIN_THREAD = new FlowableTransformer() {
        @Override
        public Publisher apply(@NonNull Flowable upstream) {
            return upstream
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static ObservableTransformer getObservableMainThread() {
        return OBSERVABLE_MAIN_THREAD;
    }

    public static SingleTransformer getSingleMainThread() {
        return SINGLE_MAIN_THREAD;
    }

    public static MaybeTransformer getMaybeMainThread() {
        return MAYBE_MAIN_THREAD;
    }

    public static CompletableTransformer getCompletableMainThread() {
        return COMPLETABLE_MAIN_THREAD;
    }

    public static FlowableTransformer getFlowableMainThread() {
        return FLOWABLE_MAIN_THREAD;
    }

    private static final FlowableFeedTransformer FLOWABLE_FEED_TRANSFORMER = new FlowableFeedTransformer();
    private static final ObservableFeedTransformer OBSERVABLE_FEED_TRANSFORMER = new ObservableFeedTransformer();
    private static final SingleFeedTransformer SINGLE_FEED_TRANSFORMER = new SingleFeedTransformer();
    private static final SingleResponseTransformer SINGLE_RESPONSE_TRANSFORMER = new SingleResponseTransformer();
    private static final MaybeFeedTransformer MAYBE_FEED_TRANSFORMER = new MaybeFeedTransformer();
    //private static final SingleFileTransformer SINGLE_FILE_TRANSFORMER = new SingleFileTransformer();//文件下载，待扩展

    private static final class FlowableFeedTransformer<T> implements FlowableTransformer<Feed<T>, T> {
        @Override
        public Publisher<T> apply(@NonNull Flowable<Feed<T>> upstream) {
            return upstream.map(new Function<Feed<T>, T>() {
                @Override
                public T apply(@NonNull Feed<T> result) throws Exception {
                    int code = result.getCode();
                    if (code == 200) {
                        return result.getData();
                    }
                    throw new ServerException(code, result.getMsg());
//                    throw new ServerException(code, result.getMsg());
                }
            });
        }
    }

    private static final class ObservableFeedTransformer<T> implements ObservableTransformer<Feed<T>, T> {
        @Override
        public ObservableSource<T> apply(@NonNull Observable<Feed<T>> upstream) {
            return upstream.map(new Function<Feed<T>, T>() {
                @Override
                public T apply(@NonNull Feed<T> result) throws Exception {
                    int code = result.getCode();
                    if (code == 200) {
                        return result.getData();
                    }
                    throw new ServerException(code, result.getMsg());
                }
            });
        }
    }

    private static final class SingleFeedTransformer<T> implements SingleTransformer<Feed<T>, T> {
        @Override
        public SingleSource<T> apply(@NonNull Single<Feed<T>> upstream) {
            return upstream.map(new Function<Feed<T>, T>() {
                @Override
                public T apply(@NonNull Feed<T> result) throws Exception {
                    int code = result.getCode();
                    if (code == 200) {
                        return result.getData();
                    }
                    throw new ServerException(code, result.getMsg());
                }
            });
        }
    }

    private static final class SingleResponseTransformer<T> implements SingleTransformer<T, T> {
        @Override
        public SingleSource<T> apply(@NonNull Single<T> upstream) {
            return upstream.map(new Function<T, T>() {
                @Override
                public T apply(@NonNull T result) throws Exception {
                    return result;
                }
            });
        }
    }

    private static final class MaybeFeedTransformer<T> implements MaybeTransformer<Feed<T>, T> {
        @Override
        public MaybeSource<T> apply(@NonNull Maybe<Feed<T>> upstream) {
            return upstream.map(new Function<Feed<T>, T>() {
                @Override
                public T apply(@NonNull Feed<T> result) throws Exception {
                    int code = result.getCode();
                    if (code == 200) {
                        return result.getData();
                    }
                    throw new ServerException(code, result.getMsg());
                }
            });
        }
    }

    public static FlowableFeedTransformer getFlowableFeedTransformer() {
        return FLOWABLE_FEED_TRANSFORMER;
    }

    public static ObservableFeedTransformer getObservableFeedTransformer() {
        return OBSERVABLE_FEED_TRANSFORMER;
    }

    public static SingleFeedTransformer getSingleFeedTransformer() {
        return SINGLE_FEED_TRANSFORMER;
    }

    public static SingleResponseTransformer getSingleResponseTransformer() {
        return SINGLE_RESPONSE_TRANSFORMER;
    }

    public static MaybeFeedTransformer getMaybeFeedTransformer() {
        return MAYBE_FEED_TRANSFORMER;
    }
}