# Purpose of the Repo is to understand spring reactor.

##### What is Reactive Programming.

##### Difference between Imperative and Reactive programming.


##### What is Reactive Streams.
Reactive Stream is a specification/standard for asynchronous stream processing with non-blocking back pressure defined by Netflix, LightBend and Pivotal in 2013.

##### Common interfaces in Reactive Stream specification.
**Publisher** : Who publish messages to its subscribers.
```java
public static interface Publisher<T> {
   public void subscribe(Subscriber<? super T> subscriber)
}
```

**Subscriber** : Who receive messages published by Publisher.
```java
public static interface Subscriber<T> {
   public void onSubscribe(Subscription subscription);
   public void onNext(T item);
   public void onError(Throwable throwable);
   public void onComplete();
}
```
**Subscription** : Used by **Subscriber** to make request for message from **Publisher** or cancel the subscription.
```java
public static interface Subscription {
   public void request(long n);
   public void cancel();
}
```
**Processor :** A Processor represents a processing stage—which is both a Subscriber and a Publisher and obeys the contracts of both.
```java
public static interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
```




##### What is Reactor.
Reactor is a implementation of Reactive Stream specification.

##### Reactor Core Types.
**Mono :** Mono is a publisher which emits 0 or 1 event.  
**Flux :** Flux is a publisher which emits more than 1 events (may be infinite).

##### Dependencies.
``````xml
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
        </dependency>

        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
``````
##### Common Reactive Operations.
Common reactive operations are:  
   1. Creating Reactive Types.
   2. Combining Reactive Types.
   3. Combining Reactive Types.
   4. Performing Logic Operations on Reactive Types.




