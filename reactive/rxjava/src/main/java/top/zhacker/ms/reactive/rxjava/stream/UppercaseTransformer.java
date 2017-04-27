package top.zhacker.ms.reactive.rxjava.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.cloud.stream.reactive.ObservableSender;
import reactor.core.publisher.Flux;
import rx.Observable;

@EnableBinding(Processor.class)
@EnableAutoConfiguration
@Slf4j
public class UppercaseTransformer {

  @StreamListener
  @Output(Processor.INPUT)
  public Observable<String> receive(@Input(Processor.OUTPUT) Observable<String> input) {
    return input.map(s -> {
      log.info("before transform={}", s);
      return s.toUpperCase();
    });
  }

  //@StreamListener
  public void receive(@Input(Processor.OUTPUT) Observable<String> input,
                      @Output(Processor.INPUT) ObservableSender output) {
    output.send(input.map(s -> s.toUpperCase()));
  }

  //@StreamListener
  //@Output(Processor.OUTPUT)
  public Flux<String> receive(@Input(Processor.INPUT) Flux<String> input) {
    return input.map(s -> s.toUpperCase());
  }

  //@StreamListener
  public void receive(@Input(Processor.INPUT) Flux<String> input,
                      @Output(Processor.OUTPUT) FluxSender output) {
    output.send(input.map(s -> s.toUpperCase()));
  }

}