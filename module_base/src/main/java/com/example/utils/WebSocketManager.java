package com.example.utils;

import android.os.Handler;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：BarUser
 * 创建人： MR. Li
 * 创建时间：2018/12/17  10:59
 * 包名：com.yjcul.common
 */

public class WebSocketManager {
    private static WebSocketManager mInstance;

    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
    private String url;

    private WebSocketStatus mStatus;
    private WebSocket ws;
    private WebSocketListener mListener;

    private WebSocketManager() {
    }

    public static WebSocketManager getInstance() {
        if (mInstance == null) {
            synchronized (WebSocketManager.class) {
                if (mInstance == null) {
                    mInstance = new WebSocketManager();
                }
            }
        }
        return mInstance;
    }

    public void init(String url) {
        this.url = url;
        try {
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener = new WebSocketListener())//添加回调监听
                    .connectAsynchronously();//异步连接
            heartJump();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void heartJump() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ws.sendText("ping");
                    try {
                        Thread.sleep(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void sendText(String txt) {
        ws.sendText(txt);
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WebSocketListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.e("tag", "socket收到消息：" + text);

        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Log.e("tag", "连接成功");
            setStatus(WebSocketStatus.CONNECT_SUCCESS);
            cancelReconnect();
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Log.e("tag", "连接错误");
            setStatus(WebSocketStatus.CONNECT_FAIL);
            reconnect();
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.e("tag", "断开连接");
            setStatus(WebSocketStatus.CONNECT_FAIL);
            reconnect();
        }
    }

    private void setStatus(WebSocketStatus status) {
        this.mStatus = status;
    }

    private WebSocketStatus getStatus() {
        return mStatus;
    }

    public void disconnect() {
        if (ws != null)
            ws.disconnect();
    }

    public enum WebSocketStatus {
        CONNECT_SUCCESS,//连接成功
        CONNECT_FAIL,//连接失败
        CONNECTING;//正在连接
    }

    private Handler handler = new Handler();
    private int reconnectCount = 0;//重连次数
    private long interval = 3000;//重连最小时间间隔

    public void reconnect() {
        if (ws != null && !ws.isOpen() && getStatus() != WebSocketStatus.CONNECTING) {
            reconnectCount++;
            setStatus(WebSocketStatus.CONNECTING);

            Log.e("tag", "准备开始第" + reconnectCount + "次重连");
            handler.postDelayed(mReconnectTask, interval);
        }
    }

    private Runnable mReconnectTask = new Runnable() {
        @Override
        public void run() {
            try {
                ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                        .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                        .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                        .addListener(mListener == null ? new WebSocketListener() : mListener)//添加回调监听
                        .connectAsynchronously();//异步连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void cancelReconnect() {
        reconnectCount = 0;
        handler.removeCallbacks(mReconnectTask);
    }
}
