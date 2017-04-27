package top.zhacker.ms.reactive.rxjava.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.netflix.rx.RxResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Observable;
import rx.Single;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

/**
 * DATE: 2017/4/25 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@RestController
@RequestMapping("/v1/rx/")
public class RxJavaApi {

    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public Single<String> single() {
        return Single.just("single value");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/singleWithResponse")
    public ResponseEntity<Single<String>> singleWithResponse() {
        return new ResponseEntity<>(Single.just("single value"),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/singleCreatedWithResponse")
    public Single<ResponseEntity<String>> singleOuterWithResponse() {
        return Single.just(new ResponseEntity<>("single value", HttpStatus.CREATED));
    }

    /**
     * /v1/rx/throw
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/throw")
    public Single<Object> error() {
        return Single.error(new RuntimeException("Unexpected"));
    }

    /*****************************/

    @RequestMapping(method = RequestMethod.GET, value = "/singleFromObservable")
    public Single<String> singleFromObservable() {
        return Observable.just("single value").toSingle();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/multiple")
    public Single<List<String>> multiple() {
        return Observable.just("multiple", "values").toList().toSingle();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/responseWithObservable")
    public ResponseEntity<Single<String>> responseWithObservable() {

        Observable<String> observable = Observable.just("single value");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(observable.toSingle(), headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/timeout")
    public Single<String> timeout() {
        return Observable.timer(10, TimeUnit.SECONDS).map(aLong -> "single value").toSingle();
    }

    /*****************************/

    @RequestMapping(method = RequestMethod.GET, value = "/sse")
    public SseEmitter singleSse() {
        return RxResponse.sse(Observable.just("single value"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messages")
    public SseEmitter messages() {
        return RxResponse.sse(Observable.just("message 1", "message 2", "message 3"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messagesTimeout")
    public SseEmitter messagesWithTimeout() {
        return RxResponse.sse(10, APPLICATION_JSON_UTF8, Observable.just("message 1", "message 2", "message 3"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/messagesInterval")
    public SseEmitter messagesWithInterval() {
        Observable.just("1").repeat(10);
        return RxResponse.sse(Observable.interval(2, TimeUnit.SECONDS).map(x-> "hello at "+ LocalDateTime.now()));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public SseEmitter event() {
        return RxResponse.sse(APPLICATION_JSON_UTF8,
                Observable.just(new EventDto("Spring io", new Date()),
                        new EventDto("SpringOnePlatform", new Date())));
    }

    @Data
    @AllArgsConstructor
    public static class EventDto{
        private String msg;
        private Date launchAt;
    }
}
