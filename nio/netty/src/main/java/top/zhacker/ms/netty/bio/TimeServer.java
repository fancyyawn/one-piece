/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.zhacker.ms.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lilinfeng
 * @version 1.0
 * @date 2014年2月14日
 */
public class TimeServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }

        }
        /**
         * http://blog.csdn.net/jackiehff/article/details/17765909
         *  一个 try-with-resources 语句可以像普通的 try 语句那样有 catch 和 finally 块。在try-with-resources 语句中,
         *  任意的 catch 或者 finally 块都是在声明的资源被关闭以后才运行。
         *  可以在一个 try-with-resources 语句中声明一个或多个资源。
         *
         */
        try(ServerSocket server = new ServerSocket(port)){
            System.out.println("The time server is start in port : " + port);
            while (true) {
                Socket socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }finally {
            System.out.println("The time server close");
        }

//        ServerSocket server = null;
//        try {
//            server = new ServerSocket(port);
//            System.out.println("The time server is start in port : " + port);
//            Socket socket = null;
//            while (true) {
//                socket = server.accept();
//                new Thread(new TimeServerHandler(socket)).start();
//            }
//        } finally {
//            if (server != null) {
//                System.out.println("The time server close");
//                server.close();
//                server = null;
//            }
//        }
    }
}
