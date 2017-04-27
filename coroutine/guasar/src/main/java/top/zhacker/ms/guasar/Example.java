package top.zhacker.ms.guasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;

import java.util.concurrent.ExecutionException;

public class Example {

  private static void printer(Channel<Integer> in) throws SuspendExecution,  InterruptedException {
    Integer v;
    while ((v = in.receive()) != null) {
      System.out.println(v);
    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException, SuspendExecution {
    //定义两个Channel
    Channel<Integer> naturals = Channels.newChannel(-1);
    Channel<Integer> squares = Channels.newChannel(-1);

    //运行两个Fiber实现.
    new Fiber(() -> {
      for (int i = 0; i < 10; i++)
        naturals.send(i);
      naturals.close();
    }).start();

    new Fiber(() -> {
      Integer v;
      while ((v = naturals.receive()) != null)
        squares.send(v * v);
      squares.close();
    }).start();

    printer(squares);
  }
}