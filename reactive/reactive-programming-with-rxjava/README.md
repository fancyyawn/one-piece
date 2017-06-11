# Rxjava

## Single
* SingleSubscriber
    * onSuccess
    * onError
    * add
    * unsubscribe
    * isUnsubscribed
* merge 

## Observable
### create
* Subscriber
    * onNext
    * onCompleted
    * onError
* while (!subscriber.isUnsubscribed()) { }
* new Thread(r).start();
* subscriber.add(Subscriptions.create(thread::interrupt));

* fromCallable: try-catch
* range(0, 100)
* just(1,2,3)
* empty()

* startWith(default)
* repeat()
* cache()

* timer(1, TimeUnit.SECONDS)
* interval(1_000_000 / 60, MICROSECONDS)
* delay(word -> timer(word.length(), SECONDS))
* timestamp() -> Timestamped<T>.getTimestampMillis()


* publish().refCount() + subscribe()
* publish() -> ConnectableObservable + connect()
* PublishSubject.create() -> Observable<Status>

* map(Status::getCreatedAt)
* flatMap(customer -> Observable.from(customer.getOrders()))
* map(Customer::getOrders).flatMap(Observable::from)
* flatMapIterable(Customer::getOrders) //不按源的顺序
* flatMap(bytes -> Observable.empty(),e -> Observable.error(e),() -> rate(id))
* concatMap(this::loadRecordsFor) //源数量不定，按源的顺序

* merge(a, b) //多线程
* concat(a,b) //单线程
* switchOnNext
* a.mergeWith(b)
* zip(a,b, (x,y)->{})  <- CompletableFuture.thenCombine //可以当作多条件语句，只有同时满足时才会返回
* zipWith(windMeasurements, (temperature, wind) -> new Weather(temperature, wind))
* combineLatest(...) //缓存最后一个
* withLatestFrom(fast.startWith(default) (s, f) -> s + ":" + f) //缓存指定序列的最后一个
* amb(a1,a2...,a9)  //让多个竞争，取第一个吐数据的序列
* ambWith

* reduce //得到归约值
* scan //得到归约的过程
* collect //得到列表等单值

* all
* exists
* contains
* sequenceEqual(a,b)

* concat(a,b).first()
* last()

* toBlocking()

* groupBy


### subscribe
* onErrorResumeNext(lazyFallback)
* onErrorReturn
* subscribe(onNext, onError, onCompleted) -> Subscription
* Observer/Subscriber { onNext, onError, onCompleted }
* subscribeOn(Schedulers.io())
* Sleeper.sleep(Duration.ofSeconds(2));
* forEach

### log
* doOnNext
* doOnError
* doOnSubscribe
* doOnUnsubscribe


### filter
* filter(s -> s.startsWith("#"))
* distinct
* distinctUntilChanged
* skip
* skipLast
* take
* takeLast
* takeUntil
* takeWhile









































## category

* create
    * create
    * defer
    * empty
    * never
    * throw
    * from
    * interval
    * timer
    * just
    * range
    * repeat
    * start
* combine
    * and?
    * then?
    * when?
    * combineLatest
    * join
    * merge
    * startWith
    * switch
    * zip 
    * concat
* transform
    * buffer?
    * flatMap
    * concatMap
    * groupBy
    * map
    * scan
    * window?
* filter(条件、位置、时间)
    * debounce
    * distinct
    * elementAt
    * filter
    * first
    * last
    * ignoreElements
    * sample
    * skip
    * skipLast
    * take
    * takeLast
* error
    * catch
    * retry
* util
    * delay
    * do
    * materialize
    * observeOn
    * serialize
    * subscribe
    * subscribeOn
    * timeout
    * timeInterval
    * timestamp
    * using
* conditional
    * all
    * amb?
    * contains
    * defaultIfEmpty
    * sequenceEqual
    * skipUntil
    * skipWhile
    * takeUntil
    * takeWhile
* mathematical
    * average
    * concat
    * count
    * max
    * min
    * reduce
    * sum
* connectable
    * connect
    * publish
    * refCount
    * replay
